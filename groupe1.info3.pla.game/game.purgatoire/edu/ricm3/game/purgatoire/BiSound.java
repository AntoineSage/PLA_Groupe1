package edu.ricm3.game.purgatoire;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Class for playing two sounds alternatively. Use "swap" to change from one
 * sound to the other. Caution; this is a singleton class.
 */
public class BiSound {

	/**
	 * Set the sound played. true = sound 1 false = sound 2
	 */
	private static boolean choice = true;

	/**
	 * Singleton class instance.
	 */
	private static BiSound instance = null;

	/**
	 * Shared variable for stopping the thread. Calling thread.stop is unsafe :
	 * https://docs.oracle.com/javase/1.5.0/docs/guide/misc/threadPrimitiveDeprecation.html
	 */
	private static boolean stop = false;

	/**
	 * Player thread.
	 */
	private Thread thread;

	/**
	 * BiSound constructor
	 * 
	 * @param file_name  the first sound (.wav)
	 * @param file_name2 the second sound (.wav)
	 * @param loop       if the sound should loop
	 */
	private BiSound(String file_name, String file_name2, boolean loop) {
		thread = new Thread(new Player(file_name, file_name2, loop));
	}

	/**
	 * Returns a BiSound
	 * 
	 * @param file_name  the first sound (.wav)
	 * @param file_name2 the second sound (.wav)
	 * @param loop       if the sound should loop
	 */
	public static BiSound make(String file_name, String file_name2, boolean loop) {
		if (instance == null) {
			instance = new BiSound(file_name, file_name2, loop);
		}
		return instance;
	}

	/**
	 * Switch sounds.
	 */
	public void swap() {
		BiSound.choice = !BiSound.choice;
	}

	/**
	 * Starts playing the sound.
	 */
	public void start() {
		thread.start();
	}

	/**
	 * Pause the sound.
	 */
	@SuppressWarnings("deprecation")
	public void pause() {
		System.out.println("pause");
		thread.suspend();
	}

	/**
	 * Resume the sound.
	 */
	@SuppressWarnings("deprecation")
	public void resume() {
		System.out.println("resume");
		thread.resume();
	}

	/**
	 * Stops the sound.
	 */
	public void stop() {
		System.out.println("stop");
		BiSound.stop = true;
	}

	public class Player implements Runnable {
		private String file_path;
		private String file_path2;
		private boolean loop;

		Player(String file_path, String file_path2, boolean loop) {
			this.file_path = file_path;
			this.file_path2 = file_path2;
			this.loop = loop;
		}

		public void run() {
			SourceDataLine soundLine = null;
			SourceDataLine soundLine2 = null;
			int BUFFER_SIZE = 4 * 1024; // 64 KB

			// Set up an audio input stream piped from the sound file.

			while (loop) {
				try {
					File soundFile = new File(this.file_path);
					File soundFile2 = new File(this.file_path2);
					AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
					AudioInputStream audioInputStream2 = AudioSystem.getAudioInputStream(soundFile2);
					AudioFormat audioFormat = audioInputStream.getFormat();
					AudioFormat audioFormat2 = audioInputStream2.getFormat();
					DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
					DataLine.Info info2 = new DataLine.Info(SourceDataLine.class, audioFormat2);
					soundLine = (SourceDataLine) AudioSystem.getLine(info);
					soundLine2 = (SourceDataLine) AudioSystem.getLine(info2);
					soundLine.open(audioFormat);
					soundLine2.open(audioFormat2);
					soundLine.start();
					soundLine2.start();
					int nBytesRead = 0;
					byte[] sampledData = new byte[BUFFER_SIZE];
					byte[] sampledData2 = new byte[BUFFER_SIZE];
					FloatControl gainControl = (FloatControl) soundLine.getControl(FloatControl.Type.MASTER_GAIN);
					gainControl.setValue(-10.0f);
					while (nBytesRead != -1) {
						if (BiSound.stop) {
							break;
						}
						nBytesRead = audioInputStream.read(sampledData, 0, sampledData.length);
						nBytesRead = audioInputStream2.read(sampledData2, 0, sampledData.length);
						if (nBytesRead >= 0) {
							if (BiSound.choice) {
								// Writes audio data to the mixer via this source data line.
								soundLine.write(sampledData, 0, nBytesRead);
							} else {
								soundLine2.write(sampledData2, 0, nBytesRead);
							}
						}
					}
				} catch (UnsupportedAudioFileException ex) {
					ex.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
				} catch (LineUnavailableException ex) {
					ex.printStackTrace();
				} finally {
					soundLine.drain();
					soundLine2.drain();
					soundLine.close();
					soundLine2.close();
				}
			}
		}
	}
}
