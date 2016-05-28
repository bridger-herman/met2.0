public class TimeSignature {
  private int beats;
  private int division;

  public TimeSignature(int beats, int division) {
    this.beats = beats;
    this.division = division;
  }

  public TimeSignature() {
    this.beats = 4;
    this.division = 4;
  }

  public int getBeats() {
    return this.beats;
  }

  public int getDivision() {
    return this.division;
  }

}
