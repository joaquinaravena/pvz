package Relojes;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;


public class ReproductorMusica{
	protected File archivoMusica;
	protected AudioInputStream audio;
	protected Clip clip;
		
	public ReproductorMusica() {
		archivoMusica = new File("src/Recursos/Cancion.wav");
		try {
			audio = AudioSystem.getAudioInputStream(archivoMusica);
			clip = AudioSystem.getClip();
			clip.open(audio);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
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
	
	public boolean reproduciendoMusica() {
		return clip.isRunning();
	}
	
}
