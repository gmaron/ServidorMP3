package org.unlp.info.tpii.utils;

import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerListener;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.unlp.info.tpii.controller.ServidorController;

public class Reproductor implements BasicPlayerListener{

	private BasicPlayer player;
	private ServidorController controller;
	
	public static Map<Integer, String> playlists;
	static {
        Map<Integer, String> aMap = new HashMap<Integer, String>();
        aMap.put(0, "Latino");
        aMap.put(1, "Rock Nacional");
        aMap.put(2, "Rock Internacional");
        playlists = Collections.unmodifiableMap(aMap);
    }
	
	public Reproductor(ServidorController control){
		this.controller = control;
		player = new BasicPlayer();
		player.addBasicPlayerListener(this);
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

	@Override
	public void opened(Object stream, Map properties) {
		// TODO Auto-generated method stub
		//System.out.println("Abierto: "+properties.toString()); 
	}

	@Override
	public void progress(int bytesread, long microseconds, byte[] pcmdata, Map properties) {
		// TODO Auto-generated method stub
		//System.out.println("progress : "+properties.toString());
	}

	@Override
	public void setController(BasicController arg0) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void stateUpdated(BasicPlayerEvent event) { 
		if (event.getCode() == BasicPlayerEvent.EOM){ //Fin de tema actual
			controller.changeMusicNext();
		}		 		
	}

		
	
}


