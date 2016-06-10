import javax.swing.DefaultListModel;

public class Program {
  private DefaultListModel<Measure> measureList;
  private int currentIndex;

  public Program() {
    this.measureList = new DefaultListModel<Measure>();
    this.currentIndex = 0;
  }

  public Measure getNextMeasure() {
    try {
      Measure tmp = (Measure) this.measureList.get(this.currentIndex);
      this.currentIndex++;
      return tmp;
    }
    catch (IndexOutOfBoundsException e) {
      return null;
    }
  }

  public void addMeasure(Measure measure) {
    this.measureList.addElement(measure);
  }

  public void removeMeasure(Measure measure) {
    this.measureList.removeElement(measure);
  }

  public void restart() {
    this.currentIndex = 0;
  }

  public DefaultListModel<Measure> getList() {
    return this.measureList;
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
    for (int i = 0; i < this.measureList.getSize(); i++) {
      add += this.measureList.get(i).toString() + "\n";
    }
    return add;
  }
}
