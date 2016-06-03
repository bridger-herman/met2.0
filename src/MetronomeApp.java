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

  // Components for the measure control panel
  private JPanel measureControlPanel;
  private JButton measureAddMeasureButton;
  private JSpinner measureBeatsSpinner;
  private JComboBox<Integer> measureDivisionComboBox;
  private JSpinner measureSubdivisionSpinner;
  private JSpinner measureTempoSpinner;
  private JSpinner measureNumMeasuresSpinner;
  private double measurePreviousDivision;
  private static final String measureAddMeasureLabel = "Add Measure";
  private static final String measureBeatsLabel = "Beats:";
  private static final String measureDivisionLabel = "Division:";
  private static final String measureSubdivisionLabel = "Subdivision:";
  private static final String measureTempoLabel = "Tempo:";
  private static final String measureNumMeasuresLabel = "Measures to add:";

  // Components for the program control panel
  private JPanel programControlPanel;
  private JButton programEditButton;
  private JButton programAddButton;
  private JScrollPane programScrollPane;
  private static JList<Measure> programMeasureList;
  private static final String programEditLabel = "Edit Measure";
  private static final String programAddLabel = "Add Measure";

  private Player player;

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
    frameContainerConstraints.gridx = 0;
    frameContainerConstraints.gridy = 0;
    frameContainerConstraints.gridwidth = 1;

    // TODO make more verbose media controls
    mediaControlPanel = new JPanel();
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

    mediaPlayDownbeatsBox = setupJCheckBox(
      new JCheckBox(mediaPlayDownbeatsLabel, player.hasAudibleDownbeats()), mediaBoxListener);
    mediaControlPanel.add(mediaPlayDownbeatsBox);

    mediaPlaySubdivisionsBox = setupJCheckBox(
      new JCheckBox(mediaPlaySubdivisionsLabel, player.hasAudibleSubdivisions()), mediaBoxListener);
    mediaControlPanel.add(mediaPlaySubdivisionsBox);

    mediaLoopPlaybackBox = setupJCheckBox(
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

    mediaPlayButton = setupJButton(
      new JButton(mediaPlayButtonLabel), mediaPlayButtonLabel, mediaButtonListener);
    mediaControlPanel.add(mediaPlayButton);

    mediaShowProgramButton = setupJButton(
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

  private void initMeasureControls() {
    frameContainerConstraints.gridx = 1;
    frameContainerConstraints.gridy = 0;
    frameContainerConstraints.gridwidth = 1;

    measureControlPanel = new JPanel();
    measureControlPanel.setLayout(new BoxLayout(measureControlPanel, BoxLayout.Y_AXIS));

    measureBeatsSpinner = new JSpinner(new SpinnerNumberModel(4, 1, 64, 1));
    JPanel beatsPanel = componentWithLabel(measureBeatsSpinner, measureBeatsLabel);
    beatsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    measureControlPanel.add(beatsPanel);

    ActionListener measureListener = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(measureAddMeasureLabel)) {
            player.addMeasureToProgram(new Measure(
            new TimeSignature((int) measureBeatsSpinner.getValue(), (int) measureDivisionComboBox.getSelectedItem()),
            new Tempo((int) measureTempoSpinner.getValue()),
            new Subdivision((int) measureSubdivisionSpinner.getValue())));
        }
        else {
          try {
            int selected = (int) measureDivisionComboBox.getSelectedItem();
            double multiplier = selected / measurePreviousDivision;
            measurePreviousDivision = (double) selected;
            int newTempo = (int) (((int) measureTempoSpinner.getValue()) * multiplier);
            measureTempoSpinner.setValue(newTempo);
          }
          catch (NullPointerException ex) {
            // ex.printStackTrace();
            return;
          }
        }
      }
    };

    measureDivisionComboBox = setupJComboBox(
      new JComboBox<Integer>(new Integer[] {1, 2, 4, 8, 16, 32, 64}), measureListener, 2);
    JPanel divisionPanel = componentWithLabel(measureDivisionComboBox, measureDivisionLabel);
    measurePreviousDivision = 4.0;
    divisionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    measureControlPanel.add(divisionPanel);

    JPanel subdivPanel = new JPanel();
    measureSubdivisionSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 8, 1));
    JPanel subdivisionPanel = componentWithLabel(measureSubdivisionSpinner, measureSubdivisionLabel);
    subdivisionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    measureControlPanel.add(subdivisionPanel);

    measureTempoSpinner = new JSpinner(new SpinnerNumberModel(120, 1, 300, 1));
    JPanel tempoPanel = componentWithLabel(measureTempoSpinner, measureTempoLabel);
    tempoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    measureControlPanel.add(tempoPanel);

    measureNumMeasuresSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 512, 1));
    JPanel howManyMeasuresPanel = componentWithLabel(measureNumMeasuresSpinner, measureNumMeasuresLabel);
    howManyMeasuresPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    measureControlPanel.add(howManyMeasuresPanel);

    measureAddMeasureButton= new JButton(measureAddMeasureLabel);
    measureAddMeasureButton = setupJButton(measureAddMeasureButton, measureAddMeasureLabel, measureListener);
    measureAddMeasureButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    measureControlPanel.add(measureAddMeasureButton);

    measureControlPanel.setOpaque(true);
    frameContainer.add(measureControlPanel, frameContainerConstraints);
  }

  private void initProgramControls() {
    frameContainerConstraints.gridx = 0;
    frameContainerConstraints.gridy = 1;
    frameContainerConstraints.gridwidth = 2;

    programControlPanel = new JPanel();
    programControlPanel.setLayout(new BoxLayout(programControlPanel, BoxLayout.Y_AXIS));

    programMeasureList = new JList<Measure>(player.getMeasureList());
    programMeasureList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
    programMeasureList.setCellRenderer(new MeasureRenderer());
    programMeasureList.setVisibleRowCount(1);

    programScrollPane = new JScrollPane(programMeasureList);
    programScrollPane.setPreferredSize(new Dimension(400, 65)); // TODO avoid magic numbers
    programScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    programScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

    ActionListener programListener = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
          case programEditLabel:
            System.out.println("Edit!");
            break;
          case programAddLabel:
            System.out.println("Add!");
            break;
          default:
            break;
        }
      }
    };

    JPanel actionPanel = new JPanel();

    programEditButton = setupJButton(new JButton(programEditLabel), programEditLabel, programListener);
    actionPanel.add(programEditButton);

    programAddButton = setupJButton(new JButton(programAddLabel), programAddLabel, programListener);
    actionPanel.add(programAddButton);

    programControlPanel.add(programScrollPane);
    programControlPanel.add(actionPanel);

    programControlPanel.setVisible(true);
    frameContainer.add(programControlPanel, frameContainerConstraints);
  }

  protected void createGUI() {
    this.setTitle(title);
    initContainer();
    initMediaControls();
    initMeasureControls();
    initProgramControls();

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
