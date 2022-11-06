package Relojes;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;


public class RelojMusica extends Thread {
	protected File archivoMusica;
	protected AudioInputStream audio;
	protected Clip clip;
		
	public RelojMusica() {
		archivoMusica = new File("/Recursos/Cancion.wav");
		try {
			audio = AudioSystem.getAudioInputStream(archivoMusica);
			if (AudioSystem.getClip()!=null)
				System.out.println("NO NULO");
			clip = AudioSystem.getClip();
			clip.open(audio);
		}
		catch(IOException e) {
			System.out.print("Archivo no encontrado");
		}
		catch(UnsupportedAudioFileException e){
			System.out.println("Audio no compatible");
		}
		catch(LineUnavailableException e) {
			System.out.println("Linea no encontrada");
		}
	}
	
	public void reproducirMusica() {
		clip.start();
		if (clip.getMicrosecondPosition()==clip.getMicrosecondLength()) {
			clip.setMicrosecondPosition(0);
		}
	}
	
	public void pararMusica() {
		clip.stop();
	}
	
	
}
