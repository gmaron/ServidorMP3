package org.unlp.info.tpii.utils;

public class Cancion {
	private String full_path;
	private String nombre_archivo;
	private String titulo;
	private String artista;
	public static String ruta = "D:/Musica/";
	
	
	public Cancion(String titulo, String artista, String playlist, String nombre_archivo) {
		this.titulo = titulo;
		this.artista = artista;
		this.nombre_archivo = nombre_archivo;
		this.full_path = Cancion.ruta+playlist+"/"+this.nombre_archivo ;
		
	}
	
	
	
	public String getNombre_archivo() {
		return nombre_archivo;
	}



	public void setNombre_archivo(String nombre_archivo) {
		this.nombre_archivo = nombre_archivo;
	}



	public String getRuta() {
		return full_path;
	}


	public void setFull_path(String ruta) {
		this.full_path = ruta;
	}


	public String getNombre() {
		return titulo;
	}
	public void setNombre(String nombre) {
		this.titulo = nombre;
	}
	public String getArtista() {
		return artista;
	}
	public void setArtista(String artista) {
		this.artista = artista;
	}
	
	
	
}
