import java.awt.event.*;
import javax.swing.*;

public class Player {
  private Timer programTimer;
  private boolean loopPlayback;
  private boolean isPlaying;
  private Program currentProgram;
  private Measure currentMeasure;

  public Player() {
    isPlaying = false;
    currentProgram = new Program();
    currentMeasure = null;
    programTimer = new Timer(0, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        currentMeasure = currentProgram.getNextMeasure();
        if (isPlaying && currentMeasure != null) {
          currentMeasure.getAudioManager().play();
          programTimer.setDelay(currentMeasure.getAudioManager().getLengthMilliseconds());
          // TODO works with print statement, doesn't work without. WHYY??
          System.out.println(currentMeasure.getAudioManager().getLengthMilliseconds());
        }
        else if (currentMeasure == null) {
          stop();
        }
      }
    });
    programTimer.setInitialDelay(0);
  }

  public void play() {
    isPlaying = true;
    programTimer.start();
  }

  public void stop() {
    isPlaying = false;
    try {
      programTimer.stop();
      currentMeasure.getAudioManager().stop();
    }
    catch (NullPointerException e) {
      // Do nothing
    }
    currentProgram.restart();
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
    AudioManager.setAudibleDownbeats(value);
  }

  public void setAudibleSubdivisions(boolean value) {
    AudioManager.setAudibleSubdivisions(value);
  }

  public void togglePlaying() {
    isPlaying = !isPlaying;
  }

  public boolean hasAudibleDownbeats() {
    return AudioManager.hasAudibleDownbeats();
  }

  public boolean hasAudibleSubdivisions() {
    return AudioManager.hasAudibleSubdivisions();
  }

  public boolean isPlaying() {
    return isPlaying;
  }
  public void show() {
    System.out.println(currentProgram);
  }
}
