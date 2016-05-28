import javax.swing.*;
import javax.sound.sampled.*;
import java.io.File;

public class AudioManager {
  public static final String location = "../sounds/";
  public static final String downbeat = location + "a6.wav";
  public static final String beat = location + "d6.wav";
  public static final String subdiv = location + "a5.wav";

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

  public void playMeasure(Measure measure) {
    try {
      this.downbeatClip.start();
      Thread.sleep(1000);
      this.beatClip.start();
      Thread.sleep(1000);
      this.subdivClip.start();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
