package es.malmstein.madridtransporte.library.objects;

import java.util.ArrayList;

import android.database.Cursor;

public class BusLine
{
  String date = "";
  String dayType = "";
  ArrayList<DayType> dayTypesArray = new ArrayList<DayType>();
  String direction = "";
  String groupNumber = "";
  String headerA = "";
  String headerB = "";
  String idLine = "";
  String incidents = "";
  String label = "";
  String lineName = "";
  String maxFrequency = "";
  String minFrequency = "";
  String startTime = "";
  String stopTime = "";

  public String getDate()
  {
    return this.date;
  }

  public String getDayType()
  {
    return this.dayType;
  }

  public ArrayList<DayType> getDayTypesArray()
  {
    return this.dayTypesArray;
  }

  public String getDirection()
  {
    return this.direction;
  }

  public String getGroupNumber()
  {
    return this.groupNumber;
  }

  public String getHeaderA()
  {
    return this.headerA;
  }

  public String getHeaderB()
  {
    return this.headerB;
  }

  public String getIdLine()
  {
    return this.idLine;
  }

  public String getIncidents()
  {
    return this.incidents;
  }

  public String getLabel()
  {
    return this.label;
  }

  public String getLineName()
  {
    return this.lineName;
  }

  public String getMaxFrequency()
  {
    return this.maxFrequency;
  }

  public String getMinFrequency()
  {
    return this.minFrequency;
  }

  public String getStartTime()
  {
    return this.startTime;
  }

  public String getStopTime()
  {
    return this.stopTime;
  }

  public void hydrate(Cursor paramCursor)
  {
    this.idLine = paramCursor.getString(0);
    this.label = paramCursor.getString(1);
    this.groupNumber = paramCursor.getString(2);
    this.lineName = paramCursor.getString(3);
    this.headerA = paramCursor.getString(4);
    this.headerB = paramCursor.getString(5);
  }

  public void setDate(String paramString)
  {
    this.date = paramString;
  }

  public void setDayType(String paramString)
  {
    this.dayType = paramString;
  }

  public void setDayTypesArray(ArrayList<DayType> paramArrayList)
  {
    this.dayTypesArray = paramArrayList;
  }

  public void setDirection(String paramString)
  {
    this.direction = paramString;
  }

  public void setGroupNumber(String paramString)
  {
    this.groupNumber = paramString;
  }

  public void setHeaderA(String paramString)
  {
    this.headerA = paramString;
  }

  public void setHeaderB(String paramString)
  {
    this.headerB = paramString;
  }

  public void setIdLine(String paramString)
  {
    this.idLine = paramString;
  }

  public void setIncidents(String paramString)
  {
    this.incidents = paramString;
  }

  public void setLabel(String paramString)
  {
    this.label = paramString;
  }

  public void setLineName(String paramString)
  {
    this.lineName = paramString;
  }

  public void setMaxFrequency(String paramString)
  {
    this.maxFrequency = paramString;
  }

  public void setMinFrequency(String paramString)
  {
    this.minFrequency = paramString;
  }

  public void setStartTime(String paramString)
  {
    this.startTime = paramString;
  }

  public void setStopTime(String paramString)
  {
    this.stopTime = paramString;
  }
}