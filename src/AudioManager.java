import javax.swing.*;
import javax.sound.sampled.*;
import java.io.File;

public class AudioManager {
  private static final String location = "../sounds/";
  private static final String downbeat = location + "a6.wav";
  private static final String beat = location + "d6.wav";
  private static final String subdiv = location + "a5.wav";
  // Conversion between microseconds and milliseconds
  private static final int conversion = 1000;

  private AudioInputStream beatInputStream;
  private AudioInputStream downbeatInputStream;
  private AudioInputStream subdivInputStream;
  private Clip beatClip;
  private Clip downbeatClip;
  private Clip subdivClip;

  public AudioManager() {
    this.setup();
  }

  public void setup() {
    try {
      this.beatInputStream = AudioSystem.getAudioInputStream(new File(beat).getAbsoluteFile());
      this.downbeatInputStream = AudioSystem.getAudioInputStream(new File(downbeat).getAbsoluteFile());
      this.subdivInputStream = AudioSystem.getAudioInputStream(new File(subdiv).getAbsoluteFile());
      AudioFormat format;
      DataLine.Info info;

      format = this.beatInputStream.getFormat();
      info = new DataLine.Info(Clip.class, format);
      this.beatClip = (Clip) AudioSystem.getLine(info);
      this.beatClip.open(beatInputStream);

      format = this.downbeatInputStream.getFormat();
      info = new DataLine.Info(Clip.class, format);
      this.downbeatClip = (Clip) AudioSystem.getLine(info);
      this.downbeatClip.open(downbeatInputStream);

      format = this.subdivInputStream.getFormat();
      info = new DataLine.Info(Clip.class, format);
      this.subdivClip = (Clip) AudioSystem.getLine(info);
      this.subdivClip.open(subdivInputStream);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void playMeasure(Measure measure, boolean loop) {
    if (loop) {
      while (true)
        this.playMeasure(measure);
    }
    else
      this.playMeasure(measure);
  }

  // TODO add support for subivisions and downbeats
  public void playMeasure(Measure measure) {
    int microsecondsPerBeat = measure.getTempo().getMSPB();
    int millisecondsPerBeat = microsecondsPerBeat / this.conversion;
    int beats = measure.getTimeSignature().getBeats();
    try {
      long duration = 0;
      long adjust = 0;
      for (int i = 0; i < beats; i++) {
        long startTime = System.currentTimeMillis();
        if (i == 0 && measure.playDownbeat()) {
          this.downbeatClip.start();
          Thread.sleep(millisecondsPerBeat - adjust);
          this.downbeatClip.stop();
          this.downbeatClip.setFramePosition(0);
        }
        else {
          this.beatClip.start();
          Thread.sleep(millisecondsPerBeat - adjust);
          this.beatClip.stop();
          this.beatClip.setFramePosition(0);
        }
        duration = (System.currentTimeMillis() - startTime);
        adjust = duration - millisecondsPerBeat;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
