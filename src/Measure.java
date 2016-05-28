public class Measure {
  private TimeSignature timeSignature;
  private Tempo tempo;
  private Subdivision subdivision;
  private boolean playDownbeat;
  private boolean playSubdivision;

  public Measure() {
    this.timeSignature = new TimeSignature();
    this.tempo = new Tempo();
    this.subdivision = new Subdivision();
    this.playDownbeat = true;
    this.playSubdivision = false;
  }

  public Measure(TimeSignature timeSignature, Tempo tempo,
    Subdivision subdivision, boolean playDownbeat, boolean playSubdivision) {
      this.timeSignature = timeSignature;
      this.tempo = tempo;
      this.subdivision = subdivision;
      this.playDownbeat = playDownbeat;
      this.playSubdivision = playSubdivision;
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

  public boolean playDownbeat() {
    return this.playDownbeat;
  }

  public boolean playSubdivision() {
    return this.playSubdivision;
  }
}
