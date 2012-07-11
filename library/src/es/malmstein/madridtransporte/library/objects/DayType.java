package es.malmstein.madridtransporte.library.objects;
public class DayType
{
  Direction direction1;
  Direction direction2;
  String idDayType = "";

  public Direction getDirection1()
  {
    return this.direction1;
  }

  public Direction getDirection2()
  {
    return this.direction2;
  }

  public String getIdDayType()
  {
    return this.idDayType;
  }

  public void setDirection1(Direction paramDirection)
  {
    this.direction1 = paramDirection;
  }

  public void setDirection2(Direction paramDirection)
  {
    this.direction2 = paramDirection;
  }

  public void setIdDayType(String paramString)
  {
    this.idDayType = paramString;
  }
}