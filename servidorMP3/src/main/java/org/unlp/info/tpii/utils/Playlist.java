package org.unlp.info.tpii.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

public class Playlist {
	private String nombre;
	private String ruta;
	private List<Cancion> lista_canciones;	
		
	public Playlist (String nombre){		
		this.nombre = nombre;
		this.ruta = Cancion.ruta+nombre+"/";
		this.lista_canciones = new ArrayList<Cancion>();		
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<Cancion> getLista_canciones() {
		File[] files = new File(this.ruta).listFiles();		
		for (File file : files) {
			Cancion cancion = new Cancion(this.nombre,file.getName());			
			try {
				Mp3File mp3file = new Mp3File(cancion.getRuta());				
				if (mp3file.hasId3v1Tag()) {
					  ID3v1 id3v1Tag = mp3file.getId3v1Tag();					  
					  cancion.setNombre(id3v1Tag.getTitle());
					  cancion.setArtista(id3v1Tag.getArtist());					  
					  this.lista_canciones.add(cancion);
				}else{
					if (mp3file.hasId3v2Tag()) {
						  ID3v2 id3v2Tag = mp3file.getId3v2Tag();						  
						  cancion.setNombre(id3v2Tag.getTitle());
						  cancion.setArtista(id3v2Tag.getArtist());						  
						  this.lista_canciones.add(cancion);
					}
				}				
			} catch (UnsupportedTagException | InvalidDataException
					| IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lista_canciones;
	}
	
	public void setLista_canciones(List<Cancion> lista_canciones) {
		this.lista_canciones = lista_canciones;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
}
