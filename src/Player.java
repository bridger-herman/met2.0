
public class Player {
  private static String mode = "simple";
  private static Program currentProgram = new Program();
  private static AudioManager audioManager = new AudioManager();
  private static Measure testMeasure = new Measure(new TimeSignature(4, 4), new Tempo(120), new Subdivision(4), true, false);

  public static void playCurrentProgram() {
    audioManager.playMeasure(testMeasure);
  }
}
