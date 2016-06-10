import javax.swing.*;
import javax.sound.sampled.*;
import java.io.File;

public class AudioClip {
  private static final int conversion = 1000;
  private AudioInputStream inputStream;
  private Clip clip;
  private String filePath;

  public AudioClip(String filePath) {
    this.filePath = filePath;
    this.setup();
  }

  private void setup() {
    try {
      this.inputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
      AudioFormat format = this.inputStream.getFormat();
      DataLine.Info info = new DataLine.Info(Clip.class, format);
      this.clip = (Clip) AudioSystem.getLine(info);
      this.clip.open(inputStream);
    }
    catch (Exception e) {
      System.out.println("An error occured in audio setup");
    }
  }

  public void play() {
    // long startTime = System.currentTimeMillis();
    // try {

      // Thread.sleep(this.clip.getMicrosecondLength() / this.conversion);
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        clip.start();
      }
    });
    clip.stop();
    clip.setFramePosition(0);
    // }
    // catch (Exception e) {
    //   System.out.println("An error occured in audio playback");
    // }
    // return System.currentTimeMillis() - startTime;
  }

  public int getMillisecondLength() {
    return (int) this.clip.getMicrosecondLength() / this.conversion;
  }
}
