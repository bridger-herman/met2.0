import java.util.ArrayList;
import java.util.List;

public class Program {
  private List<Measure> measureList;
  private int currentIndex;

  public Program() {
    this.measureList = new ArrayList<Measure>();
    this.currentIndex = 0;
  }

  public Measure getNextMeasure() {
    try {
      Measure tmp = (Measure) this.measureList.get(this.currentIndex);
      this.currentIndex++;
      return tmp;
    }
    catch (IndexOutOfBoundsException e) {
      this.restart();
      return null;
    }
  }

  public void addMeasure(Measure measure) {
    this.measureList.add(measure);
  }

  public void restart() {
    this.currentIndex = 0;
  }

  public void loadFromFile(String fileName) {
    // Use regex (or JSON) to load from file?
  }

  public void saveToFile(String fileName) {
    // Use regex (or JSON) to store to file?
  }
}
