package es.malmstein.madridtransporte.library.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MetroLine
  implements Serializable
{
  private static final long serialVersionUID = -6096305663021884609L;
  private ArrayList<MetroStation> estaciones = new ArrayList<MetroStation>();
  private int imagen;
  private String linea;
  private String nlinea;
  private String nombre;

  public List<MetroStation> getEstaciones()
  {
    return this.estaciones;
  }

  public int getImagen()
  {
    return this.imagen;
  }

  public String getLinea()
  {
    return this.linea;
  }

  public String getNlinea()
  {
    return this.nlinea;
  }

  public String getNombre()
  {
    return this.nombre;
  }

  public void setEstaciones(ArrayList<MetroStation> paramList)
  {
    this.estaciones = paramList;
  }

  public void setImagen(int paramInt)
  {
    this.imagen = paramInt;
  }

  public void setLinea(String paramString)
  {
    this.linea = paramString;
  }

  public void setNlinea(String paramString)
  {
    this.nlinea = paramString;
  }

  public void setNombre(String paramString)
  {
    this.nombre = paramString;
  }
}