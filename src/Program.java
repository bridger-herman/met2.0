import java.util.ArrayList;

public class Program {
  private ArrayList measureList;
  private int currentIndex;

  public Program() {
    this.measureList = new ArrayList();
    this.currentIndex = 0;
  }

  public Measure getNextMeasure() {
    Measure tmp = (Measure) this.measureList.get(this.currentIndex);
    this.currentIndex++;
    return tmp;
  }

  public void loadFromFile(String fileName) {
    // Use regex (or JSON) to load from file?
  }

  public void saveToFile(String fileName) {
    // Use regex (or JSON) to store to file?
  }
}
