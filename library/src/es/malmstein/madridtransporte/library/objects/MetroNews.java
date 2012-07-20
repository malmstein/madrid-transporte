package es.malmstein.madridtransporte.library.objects;

import java.io.Serializable;

public class MetroNews  implements Serializable
{
	  private static final long serialVersionUID = 8029528936503633317L;
	  private String fecha;
	  private String link;
	  private String rootUrl;
	  private String text;
	  private String title;

	  public MetroNews(String paramString)
	  {
	    this.rootUrl = paramString;
	  }

	  public String getFecha()
	  {
	    return this.fecha;
	  }

	  public String getLink()
	  {
	    return this.rootUrl + this.link;
	  }

	  public String getText()
	  {
	    return this.text;
	  }

	  public String getTitle()
	  {
	    return this.title;
	  }

	  public void setFecha(String paramString)
	  {
	    this.fecha = paramString;
	  }

	  public void setLink(String paramString)
	  {
	    this.link = paramString;
	  }

	  public void setText(String paramString)
	  {
	    this.text = paramString;
	  }

	  public void setTitle(String paramString)
	  {
	    this.title = paramString;
	  }
	}