package org.unlp.info.tpii.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.unlp.info.tpii.utils.Cancion;
import org.unlp.info.tpii.utils.Playlist;
import org.unlp.info.tpii.utils.Reproductor;


@RestController
public class ServidorController {
	
	private Reproductor mi_reproductor = new Reproductor();
	private int indiceTrack;
	private Playlist playlistActual = null;
	
	@RequestMapping(value = "/musicOn")
	public void musicOn (@RequestParam(value="num", defaultValue="0") String numPlaylist){
		playlistActual = new Playlist(Integer.parseInt(numPlaylist), Reproductor.playlists.get(Integer.parseInt(numPlaylist)));
		indiceTrack = 0;
		try {
			  String cancion_reproducir = playlistActual.getLista_canciones().get(indiceTrack).getRuta();
			  mi_reproductor.AbrirFichero(cancion_reproducir);
			  mi_reproductor.Play();
			} catch (Exception ex) {
			  System.out.println("Error: " + ex.getMessage());
			}
	}
	
	@RequestMapping("/damePlaylist")
	public String damePlaylist(@RequestParam(value="num", defaultValue="0") String numPlaylist){
		Playlist playlist = new Playlist(Integer.parseInt(numPlaylist), Reproductor.playlists.get(Integer.parseInt(numPlaylist)));
		String infoPlaylist = "";
		for (Cancion cancion : playlist.getLista_canciones()){
			infoPlaylist += cancion.getNombre() + " - " +cancion.getArtista()+"\n";
		}
		return infoPlaylist;
	}
	
	@RequestMapping("/changePlayList")
	public void changePlayList(@RequestParam(value="num", defaultValue="0") String numPlaylist){
		this.playlistActual = null;
		this.playlistActual = new Playlist(Integer.parseInt(numPlaylist), Reproductor.playlists.get(Integer.parseInt(numPlaylist)));
		indiceTrack = 0;
		try {
			  String cancion_reproducir = playlistActual.getLista_canciones().get(indiceTrack).getRuta();
			  mi_reproductor.AbrirFichero(cancion_reproducir);
			  mi_reproductor.Play();
			} catch (Exception ex) {
			  System.out.println("Error: " + ex.getMessage());
			}
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
			playlistActual = null;
			indiceTrack = 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/dameNombreCancion")
	public String dameNombreCancion (){
		if (playlistActual != null)
			return playlistActual.getLista_canciones().get(indiceTrack).getNombre()	;
		else
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
	
	@RequestMapping("/changeMusicBack")
	public void changeMusicBack(){
		if (playlistActual == null)
			return;
		this.StopLocal();
		if (indiceTrack == 0)
			indiceTrack = playlistActual.getLista_canciones().size()-1;
		else
			indiceTrack--;
		String cancion_reproducir = playlistActual.getLista_canciones().get(indiceTrack).getRuta();
		try {
			mi_reproductor.AbrirFichero(cancion_reproducir);
			mi_reproductor.Play();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@RequestMapping("/changeMusicNext")
	public void changeMusicNext(){
		if (playlistActual == null)
			return;
		this.StopLocal();
		if (indiceTrack == playlistActual.getLista_canciones().size()-1)
			indiceTrack = 0;
		else
			indiceTrack++;
		String cancion_reproducir = playlistActual.getLista_canciones().get(indiceTrack).getRuta();
		try {
			mi_reproductor.AbrirFichero(cancion_reproducir);
			mi_reproductor.Play();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	private void StopLocal(){
		try {
			mi_reproductor.Stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
