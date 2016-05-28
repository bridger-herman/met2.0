class MetronomeApp {
  private String mode;
  private Program currentProgram;
  private AudioManager audioManager;

  public MetronomeApp() {
    this.mode = "simple";
    this.currentProgram = new Program();
    this.audioManager = new AudioManager();
    Measure m = new Measure();
    this.audioManager.playMeasure(m);
  }
}
