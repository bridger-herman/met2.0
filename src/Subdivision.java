public class Subdivision {
  private int num;

  public Subdivision(int num) {
    this.num = num;
  }

  public Subdivision() {
    this.num = 1;
  }

  public int getNum() {
    return this.num;
  }

  public String toString() {
    return Integer.toString(this.num);
  }
}
