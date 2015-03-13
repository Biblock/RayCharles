package jeu;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.*;
import java.net.URL;
import java.util.Scanner;

import javax.sound.sampled.*;
 
public class AudioRecorder{
    
    AudioFormat audioFormat;
    TargetDataLine targetDataLine;
    private String fileName;
 
    public void readAudio(String word, Scanner scanner){
        try {
            URL url1 = AudioRecorder.class.getResource("../son/" + word + ".wav");
            AudioClip son = Applet.newAudioClip(url1);
            son.play();
            try {
                Thread.sleep(130);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (NullPointerException e){
            System.out.println("Not Found\nRecord " + word + " ? ");
            String choice = scanner.next();
            if ("o".equals(choice)){
                this.captureAudio(word);
            }
            
        }
    }
    
    //This method captures audio input from a
    // microphone and saves it in an audio file.
    public void captureAudio(String mot){
        fileName = mot;
        try{
            //Get things set up for capture
            audioFormat = getAudioFormat();
            DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
            targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
 
            //Create a thread to capture the microphone
            // data into an audio file and start the
            // thread running.  It will run until the
            // Stop button is clicked.  This method
            // will return after starting the thread.
            new CaptureThread().start();
        }catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }//end catch
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        targetDataLine.stop();
        targetDataLine.close();
    }//end captureAudio method
 
    //This method creates and returns an
    // AudioFormat object for a given set of format
    // parameters.  If these parameters don't work
    // well for you, try some of the other
    // allowable parameter values, which are shown
    // in comments following the declarations.
     private AudioFormat getAudioFormat(){
         float sampleRate = 44100;
         //8000,11025,16000,22050,44100
         int sampleSizeInBits = 16;
         //8,16
         int channels = 1;
           //1,2
         boolean signed = true;
           //true,false
         boolean bigEndian = false;
         //true,false
         return new AudioFormat(sampleRate,
                 sampleSizeInBits,
                 channels,
                 signed,
                 bigEndian);
     }//end getAudioFormat
     //=============================================//
 
     //Inner class to capture data from microphone
     // and write it to an output audio file.
     class CaptureThread extends Thread{
    
    
         public void run(){
                AudioFileFormat.Type fileType = null;
                File audioFile = null;
 
                    //Set the file type and the file extension
                   // based on the selected radio button.
                
                fileType = AudioFileFormat.Type.WAVE;
                audioFile = new File("bin/son/" + fileName + ".wav");
 
                try{
                    targetDataLine.open(audioFormat);
                    targetDataLine.start();
                    AudioSystem.write(
                              new AudioInputStream(targetDataLine),
                              fileType,
                              audioFile);
                }catch (Exception e){
                    e.printStackTrace();
                }//end catch
         }//end run
     }//end inner class CaptureThread
     //=============================================//
 }//end outer class AudioRecorder.java