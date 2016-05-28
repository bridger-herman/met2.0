import javax.swing.*;
import javax.sound.sampled.*;
import java.io.File;
import java.util.Timer;

public class AudioManager {
  private static final String location = "../sounds/";
  private static final String downbeatPath = location + "a6.wav";
  private static final String beatPath = location + "d6.wav";
  private static final String subdivPath = location + "a5.wav";
  // Conversion between microseconds and milliseconds
  private static final int conversion = 1000;

  private static AudioClip downbeat = new AudioClip(downbeatPath);
  private static AudioClip beat = new AudioClip(beatPath);
  private static Timer timer = new Timer();

  // TODO add support for subivisions
  public static void playProgram(Program program) {
    Measure currentMeasure = program.getNextMeasure();
    while (currentMeasure != null) {
      int microsecondsPerBeat = currentMeasure.getTempo().getMSPB();
      int millisecondsPerBeat = microsecondsPerBeat / conversion;
      int beats = currentMeasure.getTimeSignature().getBeats();
      long adjust;
      for (int i = 0; i < beats; i++) {
        if (i == 0 && currentMeasure.playDownbeat())
          adjust = downbeat.play();
        else
          adjust = beat.play();
        try {
          Thread.sleep(millisecondsPerBeat - adjust);
        }
        catch (Exception e) {
          System.out.println("An error occured in audio playback");
        }
      }
      currentMeasure = program.getNextMeasure();
    }
  }
}
