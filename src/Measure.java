public class Measure {
  AudioManager audioManager;
  private TimeSignature timeSignature;
  private Tempo tempo;
  private Subdivision subdivision;

  public Measure() {
    this.timeSignature = new TimeSignature();
    this.tempo = new Tempo();
    this.subdivision = new Subdivision();
    this.audioManager = new AudioManager(this.timeSignature, this.tempo, this.subdivision);
  }

  public Measure(TimeSignature timeSignature, Tempo tempo,
    Subdivision subdivision) {
      this.timeSignature = timeSignature;
      this.tempo = tempo;
      this.subdivision = subdivision;
      this.audioManager = new AudioManager(this.timeSignature, this.tempo, this.subdivision);
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

  public AudioManager getAudioManager() {
    return this.audioManager;
  }

  public String toString() {
    String add = this.timeSignature.toString() + "@";
    add += this.tempo.toString() + ",";
    add += this.subdivision.toString();
    return add;
  }

}
