package org.unlp.info.tpii.utils;

import javazoom.jlgui.basicplayer.BasicPlayer;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;



public class Reproductor {

	private BasicPlayer player;
	public static Map<Integer, String> playlists;
	static {
        Map<Integer, String> aMap = new HashMap<Integer, String>();
        aMap.put(0, "one");
        aMap.put(1, "Cumbia");
        aMap.put(2, "two");
        playlists = Collections.unmodifiableMap(aMap);
    }
	
	public Reproductor(){
		
		player = new BasicPlayer();
	}

	public void Play() throws Exception {
		player.play();
	}

	public void AbrirFichero(String ruta) throws Exception {
		player.open(new File(ruta));
	}

	public void Pausa() throws Exception {
		player.pause();
	}

	public void Continuar() throws Exception {
		player.resume();
	}

	public void Stop() throws Exception {
		player.stop();
	}

	public BasicPlayer getPlayer() {
		return player;
	}

	public void setPlayer(BasicPlayer player) {
		this.player = player;
	}

		
	
}


