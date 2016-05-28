class MetronomeApp {
  private String mode;
  private Program currentProgram;
  private AudioManager audioManager;

  public MetronomeApp() {
    this.mode = "simple";
    this.currentProgram = new Program();
    this.audioManager = new AudioManager();
    Measure m = new Measure(new TimeSignature(5, 4), new Tempo(208), new Subdivision(4), true, false);
    this.audioManager.playMeasure(m, true);
  }
}
