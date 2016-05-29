public class Measure {
  private TimeSignature timeSignature;
  private Tempo tempo;
  private Subdivision subdivision;

  public Measure() {
    this.timeSignature = new TimeSignature();
    this.tempo = new Tempo();
    this.subdivision = new Subdivision();
  }

  public Measure(TimeSignature timeSignature, Tempo tempo,
    Subdivision subdivision) {
      this.timeSignature = timeSignature;
      this.tempo = tempo;
      this.subdivision = subdivision;
    }

  public TimeSignature getTimeSignature() {
    return this.timeSignature;
  }

  public Tempo getTempo() {
    return this.tempo;
  }

  public Subdivision getSubdivision() {
    return this.subdivision;
  }

  public String toString() {
    String add = this.timeSignature.toString() + ",";
    add += this.tempo.toString() + ",";
    add += this.subdivision.toString();
    return add;
  }
}
