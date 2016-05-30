import javax.swing.JList;
import javax.swing.DefaultListModel;

public class Player {
  public static boolean playDownbeats = true;
  public static boolean playSubdivisions = false;
  private static Program currentProgram = new Program();
  private static Measure currentMeasure = null;

  public static void playCurrentProgram() {
    currentProgram.restart();
    currentMeasure = currentProgram.getNextMeasure();
    while (currentMeasure != null) {
      // TODO this is REALLY bad design, fix ASAP
      updateSelection(ProgramControl.getDisplayList());
      AudioManager.playMeasure(currentMeasure);
      currentMeasure = currentProgram.getNextMeasure();
    }
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

  public static DefaultListModel<Measure> getMeasureList() {
    return currentProgram.getList();
  }

  public static void updateSelection(JList<Measure> displayList) {
    displayList.setSelectedValue(currentMeasure, true);
  }

  public static void showProgram() {
    System.out.println(currentProgram);
  }
}
