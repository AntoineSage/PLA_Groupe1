package edu.ricm3.game.purgatoire;
import java.io.*;
import javax.sound.sampled.*;

/**
 * Class for playing a sound.
 */
public class Sound {

    /**
     * Player thread.
     */
    private Thread thread;

    /**
     * Sound constructor
     * @param file_name the sound (.wav)
     */
    public Sound(String file_name) {
        thread = new Thread(new Player(file_name));
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
    public void pause() {
        thread.suspend();
    }

    /**
     * Resume the sound.
     */
    public void resume() {
        thread.resume();
    }

    /**
     * Stops the sound.
     */
    public void stop() {
        thread.stop();
    }

    public class Player implements Runnable {
        private String file_path;

        Player (String file_path) {
            this.file_path = file_path;
        }

        public void run() {
            SourceDataLine soundLine = null;
            int BUFFER_SIZE = 64*1024;  // 64 KB

            // Set up an audio input stream piped from the sound file.
            try {
                File soundFile = new File(this.file_path);
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
                AudioFormat audioFormat = audioInputStream.getFormat();
                DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
                soundLine = (SourceDataLine) AudioSystem.getLine(info);
                soundLine.open(audioFormat);
                soundLine.start();
                int nBytesRead = 0;
                byte[] sampledData = new byte[BUFFER_SIZE];
                while (nBytesRead != -1) {
                    nBytesRead = audioInputStream.read(sampledData, 0, sampledData.length);
                    if (nBytesRead >= 0) {
                        soundLine.write(sampledData, 0, nBytesRead);
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
            soundLine.close();
            }
        }
    }
}
