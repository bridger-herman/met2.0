public class Tempo {
  private static final int conversionMicro = 60000000;
  private static final int conversionMilli = 60000;
  private int bpm;
  private int milliSPB;
  private int microSPB;

  public Tempo(int bpm) {
    this.bpm = bpm;
    this.milliSPB = this.bpmToMilliSPB();
    this.microSPB = this.bpmToMicroSPB();
  }

  public Tempo() {
    this.bpm = 120;
    this.milliSPB = this.bpmToMilliSPB();
    this.microSPB = this.bpmToMicroSPB();
  }

  private int bpmToMicroSPB() {
    // Conversion from beats per minute to microseconds per beat
    return (int) ((1.0 / bpm) * this.conversionMicro);
  }

  private int bpmToMilliSPB() {
    // Conversion from beats per minute to milliseconds per beat
    return (int) ((1.0 / bpm) * this.conversionMilli);
  }

  public int getBPM() {
    return this.bpm;
  }

  public int getMicroSPB() {
    return this.microSPB;
  }

  public int getMilliSPB() {
    return this.milliSPB;
  }

  public String toString() {
    return Integer.toString(this.bpm);
  }
}
