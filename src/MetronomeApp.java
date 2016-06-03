import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MetronomeApp extends SwingGFXApp {
  private static final String title = "Metronome 2.0";

  // Container for all the main components
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

  private Player player;
  private boolean playing; // TODO make this a part of the "Player" class

  public MetronomeApp() {
    player = new Player();
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
          player.setAudibleDownbeats(e.getStateChange() != ItemEvent.DESELECTED);
        else if (source == mediaPlaySubdivisionsBox)
          player.setAudibleSubdivisions(e.getStateChange() != ItemEvent.DESELECTED);
        else if (source == mediaLoopPlaybackBox)
          player.setLoopPlayback(e.getStateChange() != ItemEvent.DESELECTED);
      }
    };

    mediaPlayDownbeatsBox = SwingGFXApp.setupJCheckBox(
      new JCheckBox(mediaPlayDownbeatsLabel, player.hasAudibleDownbeats()), mediaBoxListener);
    mediaControlPanel.add(mediaPlayDownbeatsBox);

    mediaPlaySubdivisionsBox = SwingGFXApp.setupJCheckBox(
      new JCheckBox(mediaPlaySubdivisionsLabel, player.hasAudibleSubdivisions()), mediaBoxListener);
    mediaControlPanel.add(mediaPlaySubdivisionsBox);

    mediaLoopPlaybackBox = SwingGFXApp.setupJCheckBox(
    new JCheckBox(mediaLoopPlaybackLabel, player.hasLoopPlayback()), mediaBoxListener);
    mediaControlPanel.add(mediaLoopPlaybackBox);

    ActionListener mediaButtonListener = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
          case mediaPlayButtonLabel:
            togglePlaybackState();
            player.play();
            togglePlaybackState();
            break;
          case mediaShowProgramLabel:
            player.show();
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
    if (player.isPlaying()) {
      mediaPlayButton.setActionCommand(mediaPlayButtonLabel);
      mediaPlayButton.setText(mediaPlayButtonLabel);
    }
    else {
      mediaPlayButton.setActionCommand(mediaStopButtonLabel);
      mediaPlayButton.setText(mediaStopButtonLabel);
    }
    player.togglePlaying();
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
    // frameContainerConstraints.gridx = 1;
    // MeasureControl measureControls = new MeasureControl();
    // measureControls.setOpaque(true);
    // frameContainer.add(measureControls, frameContainerConstraints);
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
