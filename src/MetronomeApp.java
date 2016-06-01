import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MetronomeApp extends SwingGFXApp {
  private static final String title = "Metronome 2.0";

  // The container for all the main components
  private JPanel frameContainer;
  private GridBagConstraints frameContainerConstraints;

  // Components for the media control panel
  private JPanel mediaControlPanel;
  private JButton mediaPlayButton;
  private JButton mediaShowProgramButton;
  private JCheckBox mediaPlayDownbeatsBox;
  private JCheckBox mediaPlaySubdivisionsBox;
  private JCheckBox mediaLoopPlaybackBox;
  private static final String mediaPlayDownbeatsLabel = "Audible Downbeats";
  private static final String mediaPlaySubdivisionsLabel = "Audible Subdivisions";
  private static final String mediaLoopPlaybackLabel = "Loop Playback";
  private static final String mediaPlayButtonLabel = "Play Program";
  private static final String mediaStopButtonLabel = "Stop Program";
  private static final String mediaShowProgramLabel = "Show Program";

  private boolean playing;


  public MetronomeApp() {
  }

  private void initContainer() {
    frameContainer = new JPanel();
    frameContainer.setLayout(new GridBagLayout());
    frameContainerConstraints = new GridBagConstraints();
    frameContainerConstraints.fill = GridBagConstraints.HORIZONTAL;
  }

  private void initMediaControls() {
    mediaControlPanel = new JPanel();
    frameContainerConstraints.gridx = 0;
    frameContainerConstraints.gridy = 0;
    mediaControlPanel.setLayout(new BoxLayout(mediaControlPanel, BoxLayout.Y_AXIS));

    ItemListener mediaBoxListener = new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        if (source == mediaPlayDownbeatsBox)
          Player.playDownbeats = e.getStateChange() != ItemEvent.DESELECTED;
        else if (source == mediaPlaySubdivisionsBox)
          Player.playSubdivisions = e.getStateChange() != ItemEvent.DESELECTED;
        else if (source == mediaLoopPlaybackBox)
          Player.setProgramLoop(e.getStateChange() != ItemEvent.DESELECTED);
      }
    };

    mediaPlayDownbeatsBox = SwingGFXApp.setupJCheckBox(
      new JCheckBox(mediaPlayDownbeatsLabel, Player.playDownbeats), mediaBoxListener);
    mediaControlPanel.add(mediaPlayDownbeatsBox);

    mediaPlaySubdivisionsBox = SwingGFXApp.setupJCheckBox(
      new JCheckBox(mediaPlaySubdivisionsLabel, Player.playSubdivisions), mediaBoxListener);
    mediaControlPanel.add(mediaPlaySubdivisionsBox);

    mediaLoopPlaybackBox = SwingGFXApp.setupJCheckBox(
    new JCheckBox(mediaLoopPlaybackLabel, Player.getProgramLoop()), mediaBoxListener);
    mediaControlPanel.add(mediaLoopPlaybackBox);


    ActionListener mediaButtonListener = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
          case mediaPlayButtonLabel:
            togglePlaybackState();
            Player.playCurrentProgram(); // TODO change this to an instance variable, move everything away from static
            togglePlaybackState();
            break;
          case mediaShowProgramLabel:
            Player.showProgram();
            break;
          default:
            break;
        }
      }
    };

    mediaPlayButton = SwingGFXApp.setupJButton(
      new JButton(mediaPlayButtonLabel), mediaPlayButtonLabel, mediaButtonListener);
    mediaControlPanel.add(mediaPlayButton);

    mediaShowProgramButton = SwingGFXApp.setupJButton(
      new JButton(mediaShowProgramLabel), mediaShowProgramLabel, mediaButtonListener);
    mediaControlPanel.add(mediaShowProgramButton);

    mediaControlPanel.setOpaque(true);
    frameContainer.add(mediaControlPanel, frameContainerConstraints);
  }

  private void togglePlaybackState() {
    if (playing) {
      mediaPlayButton.setActionCommand(mediaPlayButtonLabel);
      mediaPlayButton.setText(mediaPlayButtonLabel);
    }
    else {
      mediaPlayButton.setActionCommand(mediaStopButtonLabel);
      mediaPlayButton.setText(mediaStopButtonLabel);
    }
    playing = !playing;
  }


  protected void createGUI() {
    this.setTitle(title);
    playing = false;
    initContainer();
    initMediaControls();

    // frameContainerConstraints.gridx = 0;
    // frameContainerConstraints.gridy = 0;
    // MediaControl mediaControls = new MediaControl();
    // mediaControls.setOpaque(true);
    // frameContainer.add(mediaControls, frameContainerConstraints);
    //
    frameContainerConstraints.gridx = 1;
    MeasureControl measureControls = new MeasureControl();
    measureControls.setOpaque(true);
    frameContainer.add(measureControls, frameContainerConstraints);
    //
    // frameContainerConstraints.gridx = 0;
    // frameContainerConstraints.gridy = 1;
    // frameContainerConstraints.gridwidth = 2;
    // ProgramControl programControls = new ProgramControl();
    // programControls.setOpaque(true);
    // frameContainer.add(programControls, frameContainerConstraints);
    //
    // this.add(frameContainer);
    this.add(frameContainer);
    this.pack();
    this.setVisible(true);
  }


  public static void main(String[] args) {
    MetronomeApp met = new MetronomeApp();
    met.run();
  }
}
