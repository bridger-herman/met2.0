import java.util.ArrayList;
import java.util.List;

public class Program {
  private List<Measure> measureList;
  private int currentIndex;
  private boolean loop;

  public Program() {
    this.measureList = new ArrayList<Measure>();
    this.currentIndex = 0;
    this.loop = false;
  }

  public Program(boolean loop) {
    this.measureList = new ArrayList<Measure>();
    this.currentIndex = 0;
    this.loop = loop;
  }

  public Measure getNextMeasure() {
    try {
      Measure tmp = (Measure) this.measureList.get(this.currentIndex);
      this.currentIndex++;
      return tmp;
    }
    catch (IndexOutOfBoundsException e) {
      if (this.loop) {
        this.restart();
        return (Measure) this.measureList.get(this.currentIndex);
      }
      else
        return null;
    }
  }

  public void addMeasure(Measure measure) {
    this.measureList.add(measure);
  }

  public void removeMeasure(Measure measure) {
    this.measureList.remove(measure);
  }

  public void restart() {
    this.currentIndex = 0;
  }

  // FORMAT: just save measures with their toString
  // 4/4,120,1
  // 5/8,240,1
  // ...
  // TODO
  public void loadFromFile(String fileName) {
    // Use regex to load from file?
  }

  // TODO
  public void saveToFile(String fileName) {
    // store to file
  }

  public String toString() {
    String add = "";
    for (Measure measure : this.measureList) {
      add += measure.toString() + "\n";
    }
    return add;
  }

}
