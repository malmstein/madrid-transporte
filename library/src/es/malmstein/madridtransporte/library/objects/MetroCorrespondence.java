package es.malmstein.madridtransporte.library.objects;

import java.io.Serializable;

public class MetroCorrespondence implements Serializable {
	
  private static final long serialVersionUID = 5888963255679778473L;
  private String estacion;
  private int id;
  private String linea;
  private String nlinea;
  private int numero;
  private int tiempo;

  public String getEstacion()  {
    return this.estacion;
  }

  public int getId()  {
    return this.id;
  }

  public String getLinea() {
    return this.linea;
  }

  public String getNlinea() {
    return this.nlinea;
  }

  public int getNumero() {
    return this.numero;
  }

  public int getTiempo() {
    return this.tiempo;
  }

  public void sacarNumLinea(){
	  if (getLinea().equals("L1")){
		  setNumero(0);
	  }else{
		  if ((getLinea().equals("L2"))){
			  setNumero(1);	
		  }else{
			  if (getLinea().equals("L3")){
				  setNumero(2);		
			  }else{
				  if (getLinea().equals("L4")){
					  setNumero(3);
				  }else{
					  if (getLinea().equals("L5")){
						  setNumero(4);
					  }else{
						  if (getLinea().equals("L6")){                            
							  setNumero(5);
						  }else{
							  if (getLinea().equals("L7")){
								  setNumero(6);
							  }else{
								  if (getLinea().equals("L8")){
									  setNumero(7);
								  }else{
									  if (getLinea().equals("L9")){                                        
										  setNumero(8);
									  }else{
										  if (getLinea().equals("L10")){
											  setNumero(9);
										  }else{
											  if (getLinea().equals("L11")){
												  setNumero(10);
											  }else{	
												  if (getLinea().equals("L12")){
													  setNumero(11);	
												  }else{
													  if (getLinea().equals("LR")){
														  setNumero(12);
													  }else{
														  if (getLinea().equals("ML1")){
															  setNumero(13);
														  }else{
															  if (getLinea().equals("ML2")){
																  setNumero(14);
															  }else{
																  if (getLinea().equals("ML3")){
																	  setNumero(15);
																  }else{
                                            						
																  }
															  }                                            				
														  }
													  }
												  }
											  }                                             
										  }                                             	
									  }                                                                 
								  }                            	                                                          
							  }
						  }                                   
					  }
				  }
			  }
		  }
	  }
  }

  public void setEstacion(String paramString) {
    this.estacion = paramString;
  }

  public void setId(int paramInt) {
    this.id = paramInt;
  }

  public void setLinea(String paramString)  {
    this.linea = paramString;
  }

  public void setNlinea(String paramString)  {
    this.nlinea = paramString;
  }

  public void setNumero(int paramInt) {
    this.numero = paramInt;
  }

  public void setTiempo(int paramInt) {
    this.tiempo = paramInt;
  }
  
}