package es.malmstein.madridtransporte.library.objects;

import java.io.Serializable;
import java.util.ArrayList;

public class MetroStation implements Serializable {
	
  private static final long serialVersionUID = 1052053508762402757L;
  private String corr;
  private ArrayList<MetroCorrespondence> correspondencias = new ArrayList<MetroCorrespondence>();
  private int distancia;
  private int handicap = 0;
  private int has_connections = 0;
  private int id;
  private float latitud;
  private float longitud;
  boolean marcado = false;
  private String nombre;
  public boolean titulo = false;
  private int x;
  private int y;

  public boolean equals(Object paramObject) {
    return getNombre().equals(paramObject.toString());
  }

  public String getCorr()  {
    int i = 0;
    int j = 0;
    
    if (this.correspondencias != null)
    {
      i = this.correspondencias.size();
      j = 0;
    }
    while (true)
    {
      if (j >= i)
        return this.corr;
      this.corr += ((MetroCorrespondence)this.correspondencias.get(j)).toString();
      ++j;
    }
  }

  public ArrayList<MetroCorrespondence> getCorrespondencias()
  {
    return this.correspondencias;
  }

  public int getDistancia()
  {
    return this.distancia;
  }

  public int getHandicap()
  {
    return this.handicap;
  }

  public int getHas_connections()
  {
    return this.has_connections;
  }

  public int getId()
  {
    return this.id;
  }

  public float getLatitud()
  {
    return this.latitud;
  }

  public float getLongitud()
  {
    return this.longitud;
  }

  public String getNombre()
  {
    return this.nombre;
  }

  public int getX()
  {
    return this.x;
  }

  public int getY()
  {
    return this.y;
  }

  public boolean isMarcado()
  {
    return this.marcado;
  }

  public void setCorr(String paramString)
  {
    this.corr = paramString;
  }

  public void setCorrespondencias(ArrayList<MetroCorrespondence> paramList)
  {
    this.correspondencias = paramList;
  }

  public void setDistancia(int paramInt)
  {
    this.distancia = paramInt;
  }

  public void setHandicap(int paramInt)
  {
    this.handicap = paramInt;
  }

  public void setHas_connections(int paramInt)
  {
    this.has_connections = paramInt;
  }

  public void setId(int paramInt)
  {
    this.id = paramInt;
  }

  public void setLatitud(String paramString)
  {
    this.latitud = Float.parseFloat(paramString);
  }

  public void setLongitud(String paramString)
  {
    this.longitud = Float.parseFloat(paramString);
  }

  public void setMarcado(boolean paramBoolean)
  {
    this.marcado = paramBoolean;
  }

  public void setNombre(String paramString)
  {
    this.nombre = paramString;
  }

  public void setX(String paramString)
  {
    this.x = Integer.parseInt(paramString);
  }

  public void setY(String paramString)
  {
    this.y = Integer.parseInt(paramString);
  }

  public String toString()
  {
    return getNombre();
  }
}