import java.awt.event.*;
import javax.swing.*;

public class AudioManager {
  // Definitions for the audio files
  private static final String location = "../sounds/";
  private static final String downbeatPath = location + "a6.wav";
  private static final String beatPath = location + "d6.wav";
  private static final String subdivPath = location + "a5.wav";
  private static AudioClip downbeatClip = new AudioClip(downbeatPath);
  private static AudioClip beatClip = new AudioClip(beatPath);
  private static AudioClip subdivisionClip = new AudioClip(subdivPath);
  private static boolean audibleDownbeats = true;
  private static boolean audibleSubdivisions = false;

  private int lengthMilliseconds;

  private Timer downbeatTimer;
  private Timer beatTimer;

  public AudioManager(TimeSignature measureTimeSignature, Tempo measureTempo, Subdivision measureSubdivision) {
    lengthMilliseconds = measureTempo.getMilliSPB() * measureTimeSignature.getBeats();

    final int beats = measureTimeSignature.getBeats();
    int subdivisions = measureSubdivision.getNum();
    // int microsecondsPerBeat = measure.getTempo().getMSPB();
    int millisecondsPerBeat = measureTempo.getMilliSPB();
    int millisecondsPerSubdivision =
      millisecondsPerBeat / (subdivisions);
    // long adjust = 0;

    beatTimer = new Timer(millisecondsPerBeat, new ActionListener() {
      private int beatsPlayed = 0;
      public void actionPerformed(ActionEvent e) {
        if (beatsPlayed < beats) {
          if (beatsPlayed == 0 && audibleDownbeats)
            downbeatClip.play();
          else {
            beatClip.play();
          }
          beatsPlayed++;
        }
        else {
          stopTimers();
          beatsPlayed = 0;
        }
      }
    });

    initTimers();
  }

  private void initTimers() {
    beatTimer.setInitialDelay(0);
    // downbeatTimer.setInitialDelay(0);
  }

  private void startTimers() {
    beatTimer.start();
  }

  private void stopTimers() {
    beatTimer.stop();
    // downbeatTimer.stop();
  }

  public int getLengthMilliseconds() {
    return lengthMilliseconds;
  }

  public void play() {
    startTimers();
  }

  public void stop() {
    stopTimers();
  }

  public static void setAudibleDownbeats(boolean value) {
    audibleDownbeats = value;
  }

  public static void setAudibleSubdivisions(boolean value) {
    audibleSubdivisions = value;
  }

  public static boolean hasAudibleDownbeats() {
    return audibleDownbeats;
  }

  public static boolean hasAudibleSubdivisions() {
    return audibleSubdivisions;
  }


}
