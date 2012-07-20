package es.malmstein.madridtransporte.library.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.xml.sax.InputSource;

import android.content.Context;
import android.text.TextUtils;

import com.bugsense.trace.BugSenseHandler;

import es.malmstein.madridtransport.library.R;
import es.malmstein.madridtransporte.library.objects.EMTNews;
import es.malmstein.madridtransporte.library.objects.MetroLine;
import es.malmstein.madridtransporte.library.objects.MetroNews;
import es.malmstein.madridtransporte.library.parsers.EMTNewsParserHandler;
import es.malmstein.madridtransporte.library.parsers.MetroNewsParser;
import es.malmstein.madridtransporte.library.requests.Request;
import es.malmstein.madridtransporte.library.utils.EasySSLSocketFactory;
import es.malmstein.madridtransporte.library.utils.LibraryUtils;

public class MTEngine {

	private static MTEngine instance;
	private Context context;
	
	private ArrayList<MetroLine> localMetroLines = new ArrayList<MetroLine>();
	private ArrayList<EMTNews> localEMTIncidencias = new ArrayList<EMTNews>();
	private ArrayList<MetroNews> localMetroIncidencias = new ArrayList<MetroNews>();
	
	// Constructors 
	
	public ArrayList<EMTNews> getLocalEMTIncidencias() {
		return localEMTIncidencias;
	}

	public void setLocalEMTIncidencias(ArrayList<EMTNews> localEMTIncidencias) {
		this.localEMTIncidencias = localEMTIncidencias;
	}
	
	public boolean hasLocalEMTIncidencias(){
		return localEMTIncidencias.size() > 0;
	}
	
	public ArrayList<MetroNews> getLocalMetroIncidencias() {
		return localMetroIncidencias;
	}

	public void setLocalMetroIncidencias(ArrayList<MetroNews> localMetroIncidencias) {
		this.localMetroIncidencias = localMetroIncidencias;
	}
	
	public boolean hasLocalMetroIncidencias(){
		return localMetroIncidencias.size() > 0;
	}
	
	public ArrayList<MetroLine> getLocalMetroLines() {
		return localMetroLines;
	}

	public void setLocalMetroLines(ArrayList<MetroLine> localMetroLines) {
		this.localMetroLines = localMetroLines;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	private MTEngine(Context cxt){
		this.setContext(cxt);
	}

	public static void startInstance(Context ctx) {
		if (instance == null) {
			instance = new MTEngine(ctx);
		}
		
		BugSenseHandler.setup(ctx, MTConstants.BUGSENSE_API);
	}

	public static void stopInstance() {
		instance = null;
	}

	public static MTEngine getInstance() {
		return instance;
	}
	
	/**
	 * Parser de incidencias de la EMT, se basa en leer de su RSS
	 * @return
	 */
	public ArrayList<EMTNews> parseEMTIncidencias(){
		try{
			ArrayList<EMTNews> localNews = new ArrayList<EMTNews>();
			
			EMTNewsParserHandler saxparser = new EMTNewsParserHandler(context.getString(R.string.emt_incidencias_rss));
			localNews = saxparser.parse();
			
			if (localNews != null){
				setLocalEMTIncidencias(localNews);				
			}
			
			return localNews;
			
		} catch (Exception localException) {
			return new ArrayList<EMTNews>();			
		}  
	}
	
	/**
	 * Parser de noticias del Metro, con jsoup se parsea la p‡gina de noticias y se cogen los datos
	 * @return
	 */
	public ArrayList<MetroNews> parseMetroIncidencias(){
		
		try{
			ArrayList<MetroNews> localMetroNews = new ArrayList<MetroNews>();
			MetroNewsParser localMetroNewsParser = new MetroNewsParser(context.getString(R.string.metro_incidencias_html), context.getString(R.string.metro_root));
			
			localMetroNews = localMetroNewsParser.parse();
			
			if (localMetroNews != null){
				setLocalMetroIncidencias(localMetroNews);
			}
			
			return localMetroNews;
		}catch (Exception e){
			return new ArrayList<MetroNews>();
		}
	}
	
	/**
	 * Handles a secure server request through https
	 * @param request request
	 * @return JSONObject response
	 * @throws BHException
	 */
	public InputSource secureRequest(Request request) throws Exception{
		
		if(!LibraryUtils.isInternetConnected(this.getContext())){
			throw new Exception("Internet Error");
		}
		
		URL url = null;

		try {
			url = new URL(request.getRequestPath());				
		}
		catch (MalformedURLException e) {
		    BugSenseHandler.log(MTConstants.BUGSENSE_LIB_TAG, e);
			throw new Exception( e.toString()+" AT REQUEST SERVER METHOD IN "+ MTEngine.class.toString());
		}
		
		DefaultHttpClient httpclient = getSecureHttpClient();
		
		// Prepare a request object
		HttpRequestBase httpReq;
		// Execute the request
		HttpResponse response;
		
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        nameValuePairs = request.getRequestParameters();                                    
		
		try{
			httpReq= new HttpPost(url.toString());
			((HttpPost)httpReq).setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
			
			response = httpclient.execute(httpReq);
			HttpEntity entity = response.getEntity();
			
			if (entity != null)
			{
				// A Simple JSON Response Read
				String str = convertStreamToString(entity.getContent());
				
				if (!TextUtils.isEmpty(str)){
					return new InputSource(new StringReader(str));		
				}else{
					return null;
				}			 				
								
			}
		}
		catch (ClientProtocolException e)
		{
		    BugSenseHandler.log(MTConstants.BUGSENSE_LIB_TAG, e);
			throw new Exception(e.toString()+" AT REQUEST SERVER MEHTOD IN "+MTEngine.class.toString());
		}
		catch (IOException e)
		{
		    BugSenseHandler.log(MTConstants.BUGSENSE_LIB_TAG, e);
			throw new Exception(e.toString()+" AT REQUEST SERVER MEHTOD IN "+MTEngine.class.toString());
		}
		
		return null;
	}
	
	/**
	 * Constructor of the secure HTTP client
	 * @return
	 */
	public static DefaultHttpClient getSecureHttpClient()
	{
		try
		{
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			trustStore.load(null, null);

			SSLSocketFactory sf = new EasySSLSocketFactory(trustStore);
			sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

			HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, "UTF-8");

			// Set the timeout in milliseconds until a connection is established.
			int timeoutConnection = 120000;
			HttpConnectionParams.setConnectionTimeout(params, timeoutConnection);
			// Set the default socket timeout (SO_TIMEOUT) 
			// in milliseconds which is the timeout for waiting for data.
			int timeoutSocket = 120000;
			HttpConnectionParams.setSoTimeout(params, timeoutSocket);
			
			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
			registry.register(new Scheme("https", sf, 443));

			ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);

			return new DefaultHttpClient(ccm, params);
		}
		catch (Exception e)
		{
		    BugSenseHandler.log(MTConstants.BUGSENSE_LIB_TAG, e);
			return new DefaultHttpClient();
		}
	}
	
	/**
	 * Conversion between stream and string
	 * @param is
	 * @return
	 */
	public static String convertStreamToString(InputStream is)
	{		
        try {            
            final char[] buffer = new char[0x10000];
            StringBuilder out = new StringBuilder();
            Reader in = new InputStreamReader(is, "UTF-8");
            int read;
            do {
              read = in.read(buffer, 0, buffer.length);
              if (read>0) {
                out.append(buffer, 0, read);
              }
            } while (read>=0);
            
            return out.toString();        
	    } catch (Exception e1) {
	        BugSenseHandler.log(MTConstants.BUGSENSE_LIB_TAG, e1);
	        e1.printStackTrace();
	    }
        
        return null;
	}
	
}
