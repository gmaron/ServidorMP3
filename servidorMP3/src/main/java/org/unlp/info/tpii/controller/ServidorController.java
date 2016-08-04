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
	
	@RequestMapping(value = "/musicaOn")
	public String musicOn (@RequestParam(value="num", defaultValue="0") String numPlaylist){
		Playlist playlist1 = new Playlist(Integer.parseInt(numPlaylist), Reproductor.playlists.get(Integer.parseInt(numPlaylist)));
		Reproductor mi_reproductor = new Reproductor();
		try {
			  String cancion_reproducir = playlist1.getLista_canciones().get(3).getRuta();
			  mi_reproductor.AbrirFichero(cancion_reproducir);
			  //mi_reproductor.AbrirFichero("D:/Cosas 2015/Downloads/celular/El Amante.mp3");
			  mi_reproductor.Play();
			} catch (Exception ex) {
			  System.out.println("Error: " + ex.getMessage());
			}
		
		ObjectMapper objectMapper = new ObjectMapper();
    	//Set pretty printing of json
    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    	String arrayToJson = "doesn't work";
		try {
			arrayToJson = objectMapper.writeValueAsString(playlist1.getLista_canciones());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayToJson;
		//return playlist1.getLista_canciones();
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
	
}
