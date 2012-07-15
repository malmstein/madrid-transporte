package es.malmstein.madridtransporte.library.parsers;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.content.Context;
import es.malmstein.madridtransport.library.R;
import es.malmstein.madridtransporte.library.objects.MetroCorrespondence;
import es.malmstein.madridtransporte.library.objects.MetroLine;
import es.malmstein.madridtransporte.library.objects.MetroStation;

public class MetroParserHandler extends DefaultHandler{
		
	private Context c;
	private MetroCorrespondence correspondenciaActual;	  
	private MetroStation estacionActual;
	private MetroLine lineaActual;
	ArrayList<MetroLine> lineas = new ArrayList<MetroLine>();

	public MetroParserHandler(ArrayList<MetroLine> paramList, Context paramContext) {
		this.lineas = paramList;
		this.c = paramContext;
	}

	public void endElement(String paramString1, String paramString2, String paramString3) throws SAXException  {
		super.endElement(paramString1, paramString2, paramString3);
		if (paramString2.equalsIgnoreCase("linea"))
			this.lineas.add(this.lineaActual);
		if (paramString2.equalsIgnoreCase("estacion"))
			this.lineaActual.getEstaciones().add(this.estacionActual);
		if (paramString2.equalsIgnoreCase("correspondencia"))
			this.estacionActual.getCorrespondencias().add(this.correspondenciaActual);
	}

	public void startDocument() throws SAXException {
		super.startDocument();
	}

	public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes) throws SAXException {
		
		super.startElement(paramString1, paramString2, paramString3, paramAttributes);		
		
		if (paramString2.equalsIgnoreCase("linea")){
			this.lineaActual = new MetroLine();
			this.lineaActual.setNombre(paramAttributes.getValue("nombre"));
			this.lineaActual.setLinea(paramAttributes.getValue("numero"));
			this.lineaActual.setNlinea(paramAttributes.getValue("nlinea"));
			int j = this.c.getResources().getIdentifier(this.lineaActual.getLinea().toLowerCase(), "drawable", this.c.getPackageName());
			
			if (j == 0)
				this.lineaActual.setImagen(R.drawable.ic_launcher);			
			
			this.lineaActual.setImagen(j);
		}
		
		if (paramString2.equalsIgnoreCase("estacion")){
			this.estacionActual = new MetroStation();
			this.estacionActual.setNombre(paramAttributes.getValue("nombre"));
			this.estacionActual.setLatitud(paramAttributes.getValue("latitud"));
			this.estacionActual.setLongitud(paramAttributes.getValue("longitud"));
			this.estacionActual.setX(paramAttributes.getValue("x"));
			this.estacionActual.setY(paramAttributes.getValue("y"));
		}
		
		if (paramString2.equalsIgnoreCase("correspondencia")){
			this.correspondenciaActual = new MetroCorrespondence();
			this.correspondenciaActual.setLinea(paramAttributes.getValue("linea"));
			this.correspondenciaActual.setEstacion(paramAttributes.getValue("estacion"));
			
			if (paramAttributes.getValue("nlinea") == null)
				this.correspondenciaActual.setNlinea(paramAttributes.getValue("linea"));
				
			
			this.correspondenciaActual.setNlinea(paramAttributes.getValue("nlinea"));
			int i = this.c.getResources().getIdentifier(this.correspondenciaActual.getLinea().toLowerCase(), "drawable", this.c.getPackageName());
			if (i == 0)
				this.correspondenciaActual.setId(R.drawable.ic_launcher);				
			
			this.correspondenciaActual.setId(i);
		}
		
    }
}
