package org.unlp.info.tpii.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.unlp.info.tpii.utils.Cancion;
import org.unlp.info.tpii.utils.Playlist;
import org.unlp.info.tpii.utils.Reproductor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


@RestController
public class ServidorController {
	
	Reproductor mi_reproductor = new Reproductor();
	
	@RequestMapping(value = "/musicOn")
	public void musicOn (@RequestParam(value="num", defaultValue="0") String numPlaylist){
		Playlist playlist1 = new Playlist(Integer.parseInt(numPlaylist), Reproductor.playlists.get(Integer.parseInt(numPlaylist)));
		
		try {
			  String cancion_reproducir = playlist1.getLista_canciones().get(0).getRuta();
			  mi_reproductor.AbrirFichero(cancion_reproducir);
			  //mi_reproductor.AbrirFichero("D:/Cosas 2015/Downloads/celular/El Amante.mp3");
			  mi_reproductor.Play();
			} catch (Exception ex) {
			  System.out.println("Error: " + ex.getMessage());
			}
	}
	
	@RequestMapping("/damePlaylist")
	public String damePlaylist(@RequestParam(value="num", defaultValue="0") String numPlaylist){
		Playlist playlist1 = new Playlist(Integer.parseInt(numPlaylist), Reproductor.playlists.get(Integer.parseInt(numPlaylist)));
		String infoPlaylist = "";
		for (Cancion cancion : playlist1.getLista_canciones()){
			infoPlaylist += cancion.getNombre() + " - " +cancion.getArtista()+"\n";
		}
		return infoPlaylist;
	}
	
	@RequestMapping("/dameNombrePlaylist")
	public String dameNombrePlaylist(@RequestParam(value="num", defaultValue="0") String numPlaylist){
		Playlist playlist = new Playlist(Integer.parseInt(numPlaylist), Reproductor.playlists.get(Integer.parseInt(numPlaylist)));
		return playlist.getNombre();
	}
	
	@RequestMapping("/musicOff")
	public void musicOff(){
		try {
			mi_reproductor.Stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/dameNombreCancion")
	public String dameNombreCancion (){
		return null;
	}
	
	@RequestMapping("/pauseMusic")
	public void pauseMusic (){
		try {
			mi_reproductor.Pausa();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/resumeMusic")
	public void resumeMusic (){
		try {
			mi_reproductor.Continuar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
