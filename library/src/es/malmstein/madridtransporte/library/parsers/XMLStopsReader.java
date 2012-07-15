package es.malmstein.madridtransporte.library.parsers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import es.malmstein.madridtransporte.library.objects.BusLine;
import es.malmstein.madridtransporte.library.objects.BusStop;

public class XMLStopsReader extends DefaultHandler
{
	  static XMLStopsReader sharedInstance;
	  public final String COORDINATE_X_TAG = "CoordinateX";
	  public final String COORDINATE_Y_TAG = "CoordinateY";
	  public final String DAY_TYPE_TAG = "DayType";
	  public final String DIRECTION_TAG = "Direction";
	  public final String HEADER_A_TAG = "HeaderA";
	  public final String HEADER_B_TAG = "HeaderB";
	  public final String ID_LINE_TAG = "IdLine";
	  public final String ID_STOP_TAG = "IdStop";
	  public final String LABEL_TAG = "Label";
	  public final String LINE_TAG = "Line";
	  public final String MAX_FREQUENCY_TAG = "MaximumFrequency";
	  public final String MINFREQUENCY_TAG = "MinimunFrequency";
	  public final String NAME_TAG = "Name";
	  public final String PMV_TAG = "PMV";
	  public final String POSTAL_ADDRESS_TAG = "PostalAdress";
	  public final String START_TIME_TAG = "StartTime";
	  public final String STOP_TAG = "Stop";
	  public final String STOP_TIME_TAG = "StopTime";
	  private String coordinateX;
	  private String coordinateY;
	  private BusLine currentLine;
	  private BusStop currentStop;
	  private List<BusStop> stopList;
	  private String value;

	  public XMLStopsReader()
	  {
	    initialize();
	  }

	  private void initialize()
	  {
	    this.coordinateX = "";
	    this.coordinateY = "";
	    if (this.stopList != null)
	      this.stopList.clear();
	    
	      this.value = "";
	      this.currentLine = null;
	      this.currentStop = null;
	      this.stopList = new ArrayList<BusStop>();

	  }

	  public void characters(char[] paramArrayOfChar, int paramInt1, int paramInt2)
	  {
	    this.value += new String(paramArrayOfChar).substring(paramInt1, paramInt1 + paramInt2);
	  }

	  public void endElement(String paramString1, String paramString2, String paramString3)
	    throws SAXException
	  {
	    String str = paramString2.trim().toLowerCase();
	    if ((str.equalsIgnoreCase("CoordinateX")) && (this.coordinateX.equalsIgnoreCase("")))
	      this.coordinateX = this.value;
	    if ((str.equalsIgnoreCase("CoordinateY")) && (this.coordinateY.equalsIgnoreCase("")))
	      this.coordinateY = this.value;
	    if (str.equalsIgnoreCase("Stop"))
	      this.stopList.add(this.currentStop);
	    if ((str.equalsIgnoreCase("Line")) && (this.currentStop != null))
	      this.currentStop.getLineList().add(this.currentLine);
	    if ((str.equalsIgnoreCase("IdStop")) && (this.currentStop != null))
	      this.currentStop.setIdStop(this.value);
	    if ((str.equalsIgnoreCase("PMV")) && (this.currentStop != null))
	      this.currentStop.setPmv(this.value);
	    if ((str.equalsIgnoreCase("Name")) && (this.currentStop != null))
	      this.currentStop.setName(this.value);
	    if ((str.equalsIgnoreCase("PostalAdress")) && (this.currentStop != null))
	      this.currentStop.setPostalAddress(this.value);
	    if ((str.equalsIgnoreCase("CoordinateX")) && (this.currentStop != null))
	      this.currentStop.setCoordinateX(this.value);
	    if ((str.equalsIgnoreCase("CoordinateY")) && (this.currentStop != null))
	      this.currentStop.setCoordinateY(this.value);
	    if ((str.equalsIgnoreCase("IdLine")) && (this.currentLine != null))
	      this.currentLine.setIdLine(this.value);
	    if ((str.equalsIgnoreCase("Label")) && (this.currentLine != null))
	      this.currentLine.setLabel(this.value);
	    if ((str.equalsIgnoreCase("HeaderA")) && (this.currentLine != null))
	      this.currentLine.setHeaderA(this.value);
	    if ((str.equalsIgnoreCase("HeaderB")) && (this.currentLine != null))
	      this.currentLine.setHeaderB(this.value);
	    if ((str.equalsIgnoreCase("Direction")) && (this.currentLine != null))
	      this.currentLine.setDirection(this.value);
	    if ((str.equalsIgnoreCase("DayType")) && (this.currentLine != null))
	      this.currentLine.setDayType(this.value);
	    if ((str.equalsIgnoreCase("StartTime")) && (this.currentLine != null))
	      this.currentLine.setStartTime(this.value);
	    if ((str.equalsIgnoreCase("StopTime")) && (this.currentLine != null))
	      this.currentLine.setStopTime(this.value);
	    if ((str.equalsIgnoreCase("MinimunFrequency")) && (this.currentLine != null))
	      this.currentLine.setMinFrequency(this.value);
	    if ((str.equalsIgnoreCase("MaximumFrequency")) && (this.currentLine != null))
	      this.currentLine.setMaxFrequency(this.value);
	  }

	  public List<BusStop> getStopList()
	  {
	    return this.stopList;
	  }

	  public List<BusStop> parse(InputSource paramInputSource)
	    throws IOException, SAXException, ParserConfigurationException
	  {
	    initialize();
	    XMLReader localXMLReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
	    localXMLReader.setContentHandler(this);
	    localXMLReader.parse(paramInputSource);
	    return this.stopList;
	  }

	  public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
	  {
	    this.value = "";
	    String str = paramString2.trim().toLowerCase();
	    if (str.equalsIgnoreCase("Stop"))
	      this.currentStop = new BusStop();
	    if (str.equalsIgnoreCase("Line"))
	      this.currentLine = new BusLine();
	  }
	}