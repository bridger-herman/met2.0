
public class Player {
  private static String mode = "simple";
  private static Program currentProgram = new Program();

  public static void playCurrentProgram() {
    AudioManager.playProgram(currentProgram);
  }

  public static void addMeasureToProgram(Measure measure) {
    currentProgram.addMeasure(measure);

  }
}
