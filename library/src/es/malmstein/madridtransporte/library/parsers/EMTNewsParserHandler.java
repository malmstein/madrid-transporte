package es.malmstein.madridtransporte.library.parsers;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;
import es.malmstein.madridtransporte.library.objects.EMTNews;

public class EMTNewsParserHandler
{
	private URL rssUrl;
	
    public EMTNewsParserHandler(String url) 
    {
		try 
		{
            this.rssUrl = new URL(url);
        } 
		catch (MalformedURLException e) 
		{
            throw new RuntimeException(e);
        }
    }
    
    public ArrayList<EMTNews> parse() 
    {
    	ArrayList<EMTNews> noticias = new ArrayList<EMTNews>();
        XmlPullParser parser = Xml.newPullParser();
        
        Calendar localCalendar = Calendar.getInstance();
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.US);
    	        
        try 
        {
            parser.setInput(this.getInputStream(), null);
            
            int evento = parser.getEventType();
            
            EMTNews noticiaActual = null;
            
            while (evento != XmlPullParser.END_DOCUMENT)
            {
                String etiqueta = null;
                
                switch (evento)
                {
                    case XmlPullParser.START_DOCUMENT:
                    	
                    	noticias = new ArrayList<EMTNews>();
                        break;
                        
                    case XmlPullParser.START_TAG:
                    	
                    	etiqueta = parser.getName();
                        
                        if (etiqueta.equals("item"))
                        {
                        	noticiaActual = new EMTNews();
                        } 
                        else if (noticiaActual != null)
                        {
                            if (etiqueta.equals("link"))
                            {
                            	noticiaActual.setLink(parser.nextText());
                            } 
                            else if (etiqueta.equals("description"))
                            {
                            	noticiaActual.setDescription(parser.nextText());
                            } 
                            else if (etiqueta.equals("pubDate"))
                            {
                            	Date localDate = localSimpleDateFormat.parse(parser.nextText());
                            	localCalendar.setTime(localDate);                            	
                            	
                            	if (localCalendar.get(12) >= 10){
                            		StringBuilder localStringBuilder = new StringBuilder(String.valueOf(localCalendar.get(5))).append("-").append(1 + localCalendar.get(2)).append("-").append(localCalendar.get(1)).append(" ").append(localCalendar.get(11)).append(":").append(localCalendar.get(12));
                            		noticiaActual.setPubDate(localStringBuilder.toString());
                            	}else{
                            		StringBuilder localStringBuilder = new StringBuilder(String.valueOf(localCalendar.get(5))).append("-").append(1 + localCalendar.get(2)).append("-").append(localCalendar.get(1)).append(" ").append(localCalendar.get(11)).append(":").append("0" + localCalendar.get(12));
                            		noticiaActual.setPubDate(localStringBuilder.toString());
                            	}
                            	                            	
                            } 
                            else if (etiqueta.equals("title"))
                            {
                            	noticiaActual.setTitle(parser.nextText());
                            } 
                        }
                        break;
                        
                    case XmlPullParser.END_TAG:
                    	
                    	etiqueta = parser.getName();
                    	
                        if (etiqueta.equals("item") && noticiaActual != null)
                        {
                        	noticias.add(noticiaActual);
                        } 
                        break;
                }
                
                evento = parser.next();
            }
        } 
        catch (Exception ex) 
        {
            throw new RuntimeException(ex);
        }
        
        return noticias;
    }
    
	private InputStream getInputStream() 
	{
        try 
        {
            return rssUrl.openConnection().getInputStream();
        } 
        catch (IOException e) 
        {
            throw new RuntimeException(e);
        }
    }
}
