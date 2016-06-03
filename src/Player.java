import javax.swing.JList;
import javax.swing.DefaultListModel;

public class Player {
  private boolean audibleDownbeats;
  private boolean audibleSubdivisions;
  private boolean loopPlayback;
  private boolean isPlaying;
  private Program currentProgram;
  private Measure currentMeasure;

  // Definitions for the audio files
  private static final String location = "../sounds/";
  private static final String downbeatPath = location + "a6.wav";
  private static final String beatPath = location + "d6.wav";
  private static final String subdivPath = location + "a5.wav";
  private static AudioClip downbeat = new AudioClip(downbeatPath);
  private static AudioClip beat = new AudioClip(beatPath);
  private static AudioClip subdivision = new AudioClip(subdivPath);
  // Conversion between microseconds and milliseconds
  private static final int conversion = 1000;

  public Player() {
    audibleDownbeats = true;
    audibleSubdivisions = false;
    isPlaying = false;
    currentProgram = new Program();
    currentMeasure = null;
  }

  public void play() {
    currentMeasure = currentProgram.getNextMeasure();
    while (currentMeasure != null) {
      // TODO this is REALLY bad design, fix ASAP
      // updateSelection(ProgramControl.getDisplayList());
      playMeasure(currentMeasure);
      currentMeasure = currentProgram.getNextMeasure();
    }
  }
  private void playMeasure(Measure measure) {
    int beats = measure.getTimeSignature().getBeats();
    int subdivisions = measure.getSubdivision().getNum();
    int microsecondsPerBeat = measure.getTempo().getMSPB();
    int millisecondsPerBeat = microsecondsPerBeat / conversion;
    int millisecondsPerSubdivision =
      microsecondsPerBeat / conversion / (subdivisions + 1);
    long adjust = 0;
    for (int b = 0; b < beats; b++) {
      if (b == 0 && audibleDownbeats)
        adjust = downbeat.play();
      else
        adjust = beat.play();
      long subdivAdjust = 0;
      try {
        if (audibleSubdivisions) {
          Thread.sleep(millisecondsPerSubdivision - adjust);
          for (int s = 0; s < subdivisions; s++) {
            Thread.sleep(millisecondsPerSubdivision - subdivAdjust);
            subdivAdjust = subdivision.play();
          }
          adjust += subdivAdjust;
        }
        else {
          Thread.sleep(millisecondsPerBeat - adjust);
        }
      }
      catch (Exception e) {
        System.out.println("An error occured in audio playback");
      }
    }
  }


  public void addMeasureToProgram(Measure measure) {
    currentProgram.addMeasure(measure);
  }

  public void removeMeasureFromProgram(Measure measure) {
    currentProgram.removeMeasure(measure);
  }

  public void setLoopPlayback(boolean loop) {
    loopPlayback = true;
  }

  public boolean hasLoopPlayback() {
    return loopPlayback;
  }

  public DefaultListModel<Measure> getMeasureList() {
    return currentProgram.getList();
  }

  public void updateSelection(JList<Measure> displayList) {
    displayList.setSelectedValue(currentMeasure, true);
  }

  public void setAudibleDownbeats(boolean value) {
    audibleDownbeats = value;
  }

  public void setAudibleSubdivisions(boolean value) {
    audibleSubdivisions = value;
  }

  public void togglePlaying() {
    isPlaying = !isPlaying;
  }

  public boolean hasAudibleDownbeats() {
    return audibleDownbeats;
  }

  public boolean hasAudibleSubdivisions() {
    return audibleSubdivisions;
  }

  public boolean isPlaying() {
    return isPlaying;
  }
  public void show() {
    System.out.println(currentProgram);
  }
}
