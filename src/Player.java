import javax.swing.DefaultListModel;

public class Player {
  private static String mode = "simple";
  public static boolean playDownbeats = true;
  public static boolean playSubdivisions = false;
  private static Program currentProgram = new Program();
  private static Measure[] displayArray = new Measure[0];

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

  public static void setProgramLoop(boolean loop) {
    currentProgram.setLoop(loop);
  }

  public static boolean getProgramLoop() {
    return currentProgram.getLoop();
  }

  public static DefaultListModel<Measure> getDisplayList() {
    return currentProgram.getList();
  }

  public static void showProgram() {
    System.out.println(currentProgram);
  }
}
