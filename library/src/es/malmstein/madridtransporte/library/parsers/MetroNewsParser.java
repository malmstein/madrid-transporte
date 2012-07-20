package es.malmstein.madridtransporte.library.parsers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.text.Html;
import es.malmstein.madridtransporte.library.objects.MetroNews;

public class MetroNewsParser {
	
	Document doc = null;
	ArrayList<MetroNews> noticias = new ArrayList<MetroNews>();
	private String root;
	private String rootNot;

	public MetroNewsParser(String paramString1, String paramString2) {
		this.root = paramString1;
	    this.rootNot = paramString2;
	    try {
	      this.doc = Jsoup.parse(new URL(this.root), 30000);
	      return;
	    } catch (IOException localIOException) {
	      localIOException.printStackTrace();
	    }
	  }

	public ArrayList<MetroNews> parse() {
		Elements localElements1 = this.doc.select("a[class=enl]");
	    Elements localElements2 = this.doc.select("p[class=txt]");
	    Elements localElements3 = this.doc.select("p[class=fecha]");
	    
	    for (int i = 0; i < localElements2.size(); i++){
	    	MetroNews localNoticia = new MetroNews(this.rootNot);
		    localNoticia.setFecha(Html.fromHtml(localElements3.get(i).toString()).toString().replaceAll("\n", ""));
		    localNoticia.setLink(Html.fromHtml(localElements1.get(i).attr("href").replaceAll("\n", "")).toString());
		    localNoticia.setTitle(Html.fromHtml(localElements1.get(i).toString()).toString().replaceAll("\n", ""));
		    localNoticia.setText(Html.fromHtml(localElements2.get(i).toString()).toString().replaceAll("\n", ""));
		    this.noticias.add(localNoticia);
	    }
	    
	    return this.noticias;	    
	  }
	  
}
