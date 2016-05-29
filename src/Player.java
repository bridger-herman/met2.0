
public class Player {
  private static String mode = "simple";
  public static boolean playDownbeats = true;
  public static boolean playSubdivisions = false;
  private static Program currentProgram = new Program();

  public static void playCurrentProgram() {
    currentProgram.restart();
    AudioManager.playProgram(currentProgram);
  }

  public static void addMeasureToProgram(Measure measure) {
    currentProgram.addMeasure(measure);
  }

  public static void removeMeasureFromProgram(Measure measure) {
    currentProgram.removeMeasure(measure);
  }

  public static void showProgram() {
    System.out.println(currentProgram);
  }
}
