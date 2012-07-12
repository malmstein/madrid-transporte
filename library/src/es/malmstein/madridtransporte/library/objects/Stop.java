package es.malmstein.madridtransporte.library.objects;

import java.util.ArrayList;
import java.util.List;

import android.location.Location;

public class Stop
{
  String coordinateX = "";
  String coordinateY = "";
  String customName = "";
  public String idStop = "";
  List<Line> lineList = new ArrayList<Line>();
  String name = "";
  String pmv = "";
  String postalAddress = "";

  public String getCoordinateX()
  {
    return this.coordinateX;
  }

  public String getCoordinateY()
  {
    return this.coordinateY;
  }

  public String getCustomName()
  {
    return this.customName;
  }

  public String getIdStop()
  {
    return this.idStop;
  }

  public List<Line> getLineList()
  {
    return this.lineList;
  }

  public Location getLocation()
  {
    Location localLocation1;
    Location localLocation2;
    try
    {
      localLocation1 = new Location("");
      localLocation1.setLongitude(Double.valueOf(getCoordinateX()).doubleValue());
      localLocation1.setLatitude(Double.valueOf(getCoordinateY()).doubleValue());
      localLocation2 = localLocation1;
      return localLocation2;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      localNumberFormatException.printStackTrace();
      localLocation2 = null;
      return localLocation2;
    }
  }

  public String getName()
  {
    return this.name;
  }

  public String getPmv()
  {
    return this.pmv;
  }

  public String getPostalAddress()
  {
    return this.postalAddress;
  }

//  public void hydrate(Cursor paramCursor)
//  {
//    this.idStop = String.valueOf(paramCursor.getInt(0));
//    this.name = paramCursor.getString(1);
//    this.coordinateX = String.valueOf(paramCursor.getDouble(3));
//    this.coordinateY = String.valueOf(paramCursor.getDouble(2));
//    String str1 = paramCursor.getString(4);
//    String str2 = paramCursor.getString(5);
//    String[] arrayOfString1 = str1.split("  ");
//    String[] arrayOfString2 = str2.split(" ");
//    this.customName = "";
//    int i = 0;
//    int j = arrayOfString2.length;
//    int k = 0;
//    if (k >= j)
//      return;
//    String str3 = arrayOfString2[k];
//    Line localLine = new Line();
//    String[] arrayOfString3 = str3.split("/");
//    localLine.setIdLine(arrayOfString3[0]);
//    if (arrayOfString3.length > 1)
//    {
//      if (!(arrayOfString3[1].equals("1")))
//        break label209;
//      localLine.setDirection("A");
//    }
//    while (true)
//    {
//      while (true)
//      {
//        if (arrayOfString1.length > i)
//          localLine.setLabel(arrayOfString1[i]);
//        ++i;
//        this.lineList.add(localLine);
//        ++k;
//      }
//      label209: localLine.setDirection("B");
//    }
//  }

  public void setCoordinateX(String paramString)
  {
    this.coordinateX = paramString;
  }

  public void setCoordinateY(String paramString)
  {
    this.coordinateY = paramString;
  }

  public void setCustomName(String paramString)
  {
    this.customName = paramString;
  }

  public void setIdStop(String paramString)
  {
    this.idStop = paramString;
  }

  public void setLineList(List<Line> paramList)
  {
    this.lineList = paramList;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setPmv(String paramString)
  {
    this.pmv = paramString;
  }

  public void setPostalAddress(String paramString)
  {
    this.postalAddress = paramString;
  }
}