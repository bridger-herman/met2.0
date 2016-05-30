import javax.swing.*;
import javax.sound.sampled.*;
import java.io.File;

public class AudioManager {
  private static final String location = "../sounds/";
  private static final String downbeatPath = location + "a6.wav";
  private static final String beatPath = location + "d6.wav";
  private static final String subdivPath = location + "a5.wav";
  // Conversion between microseconds and milliseconds
  private static final int conversion = 1000;

  private static AudioClip downbeat = new AudioClip(downbeatPath);
  private static AudioClip beat = new AudioClip(beatPath);
  private static AudioClip subdivision = new AudioClip(subdivPath);

  // TODO rework this to use threading, and to update the display for each
  // measure it plays.
  public static void playMeasure(Measure currentMeasure) {
    int beats = currentMeasure.getTimeSignature().getBeats();
    int subdivisions = currentMeasure.getSubdivision().getNum();
    int microsecondsPerBeat = currentMeasure.getTempo().getMSPB();
    int millisecondsPerBeat = microsecondsPerBeat / conversion;
    int millisecondsPerSubdivision =
      microsecondsPerBeat / conversion / (subdivisions + 1);
    long adjust = 0;
    for (int b = 0; b < beats; b++) {
      if (b == 0 && Player.playDownbeats)
        adjust = downbeat.play();
      else
        adjust = beat.play();
      long subdivAdjust = 0;
      try {
        if (Player.playSubdivisions) {
          Thread.sleep(millisecondsPerSubdivision - adjust);
          for (int s = 0; s < subdivisions; s++) {
            subdivAdjust = subdivision.play();
            Thread.sleep(millisecondsPerSubdivision - subdivAdjust);
          }
          adjust += subdivAdjust;
        }
        else {
          Thread.sleep(millisecondsPerBeat - adjust);
        }
      }
      catch (Exception e) {
        System.out.println("An error occured in audio playback");
      }
    }
  }
}
