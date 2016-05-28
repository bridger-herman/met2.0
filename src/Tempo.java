public class Tempo {
  private static final int conversion = 60000000;
  private int bpm;
  private int mspb;

  public Tempo(int bpm) {
    this.bpm = bpm;
    this.mspb = this.bpmToMSPB();
  }

  public Tempo() {
    this.bpm = 120;
    this.mspb = this.bpmToMSPB();
  }

  private int bpmToMSPB() {
    return (int) ((1.0 / bpm) * this.conversion);
  }

  public int getBPM() {
    return this.bpm;
  }

  public int getMSPB() {
    return this.mspb;
  }
}
