package tp.pr5.Util;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * A class that allows us to play songs
 *
 * @author	Alvaro Bermejo
 * @author	Francisco Lozano
 * @version	15/04/2015
 * @since	Assignment 5
 */
public class SongPlayer extends Thread {

    // size of the byte buffer used to read/write the audio stream
    private static final int BUFFER_SIZE = 4096;
    private AudioInputStream audioStream;
    private SourceDataLine audioLine;
    private byte[] bytesBuffer;
    private String audioPath;
    boolean forcedInterruption; //This is the dangerous way to do it

    /**
     * Initializes the SongPlayer to the music selected and starts playing it
     *
     * @param path The path to the song
     */
    public SongPlayer(String path) {
        super ("SongThread");

        forcedInterruption = false;

        audioPath = path;

        initialization();

    }

    private void initialization() {
        String audioFilePath;
        File audioFile;
        AudioFormat format;
        DataLine.Info info;

        audioFilePath = audioPath;
        audioFile = new File(audioFilePath);
        try {
            audioStream = AudioSystem.getAudioInputStream(audioFile);

            format = audioStream.getFormat();

            info = new DataLine.Info(SourceDataLine.class, format);

            audioLine = (SourceDataLine) AudioSystem.getLine(info);

            audioLine.open(format);
            audioLine.start();
            bytesBuffer = new byte[BUFFER_SIZE];

        } catch (UnsupportedAudioFileException ex) {
            System.err.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.err.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.err.println("Error opening the audio file.");
            ex.printStackTrace();
        }
    }

    public final void run() {
        int bytesRead;

        try {
            while (!isInterrupted()) {
                while ((bytesRead = audioStream.read(bytesBuffer)) != -1 && !forcedInterruption && !isInterrupted()) {
                    audioLine.write(bytesBuffer, 0, bytesRead);
                }
                initialization();
            }
        } catch (IOException ex) {
            System.err.println("Error playing the audio file.");
        } finally {
            this.close();
        }
    }

    private void close() {
        try {
            audioLine.drain();
            audioLine.close();
            audioStream.close();
        } catch (IOException ex) {
            System.err.println("Error closing the audio file.");
            ex.printStackTrace();
        }
    }

    /**
     * Procedure that allow us to stop the current song, unfortunately it uses some unorthodox means to do it
     * I have to do this because I'm not able to interrupt it any other way
     *
     */
    public void forcedStop() {
        forcedInterruption = true;
    }
}