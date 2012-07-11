package es.malmstein.madridtransporte.library.objects;
public class Direction
{
  String maximumFrequency = "";
  String minimumFrequency = "";
  String startTime = "";
  String stopTime = "";

  public String getMaximumFrequency()
  {
    return this.maximumFrequency;
  }

  public String getMinimumFrequency()
  {
    return this.minimumFrequency;
  }

  public String getStartTime()
  {
    return this.startTime;
  }

  public String getStopTime()
  {
    return this.stopTime;
  }

  public void setMaximumFrequency(String paramString)
  {
    this.maximumFrequency = paramString;
  }

  public void setMinimumFrequency(String paramString)
  {
    this.minimumFrequency = paramString;
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