import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MeasureControl extends JPanel implements ActionListener, ItemListener {
  private static final String addLabel = "Add Measure";
  private static final String downbeatLabel = "Play Downbeats";
  private static final String subdivisionLabel = "Play Subdivisions";
  private JButton addMeasureButton;
  private JSpinner beats;
  private JSpinner division;
  private JSpinner subdivision;
  private JSpinner tempo;
  private JCheckBox playDownbeatsBox;
  private JCheckBox playSubdivisionsBox;
  private boolean playDownbeats;
  private boolean playSubdivisions;

  public MeasureControl() {
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    playDownbeatsBox = new JCheckBox(downbeatLabel, true);
    playDownbeatsBox.addItemListener(this);
    playDownbeatsBox.setActionCommand(downbeatLabel);
    add(playDownbeatsBox);

    playSubdivisionsBox = new JCheckBox(subdivisionLabel, false);
    playSubdivisionsBox.addItemListener(this);
    playSubdivisionsBox.setActionCommand(subdivisionLabel);
    add(playSubdivisionsBox);

    JPanel beatsPanel = new JPanel();
    JLabel beatsLabel = new JLabel("Beats:");
    beats = new JSpinner(new SpinnerNumberModel(4, 1, 64, 1));
    beatsPanel.add(beatsLabel);
    beatsPanel.add(beats);
    add(beatsPanel);

    JPanel divisionPanel = new JPanel();
    JLabel divisionLabel = new JLabel("Division:");
    division = new JSpinner(new SpinnerNumberModel(4, 1, 64, 1));
    divisionPanel.add(divisionLabel);
    divisionPanel.add(division);
    add(divisionPanel);

    JPanel subdivPanel = new JPanel();
    JLabel subdivLabel = new JLabel("Subdivision:");
    subdivision = new JSpinner(new SpinnerNumberModel(1, 1, 8, 1));
    subdivPanel.add(subdivLabel);
    subdivPanel.add(subdivision);
    add(subdivPanel);

    JPanel tempoPanel = new JPanel();
    JLabel tempoLabel = new JLabel("Tempo:");
    tempo = new JSpinner(new SpinnerNumberModel(120, 1, 300, 1));
    tempoPanel.add(tempoLabel);
    tempoPanel.add(tempo);
    add(tempoPanel);


    addMeasureButton = new JButton(addLabel);
    addMeasureButton.setActionCommand(addLabel);
    addMeasureButton.addActionListener(this);
    add(addMeasureButton);
  }

  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case addLabel:
        Player.addMeasureToProgram(new Measure(
          new TimeSignature((int) beats.getValue(), (int) division.getValue()),
          new Tempo((int) tempo.getValue()),
          new Subdivision((int) subdivision.getValue()),
          playDownbeats, playSubdivisions));
        break;
      default:
        break;
    }
  }

  public void itemStateChanged(ItemEvent e) {
    Object source = e.getItemSelectable();
    if (source == playDownbeatsBox)
      playDownbeats = e.getStateChange() != ItemEvent.DESELECTED;
    else if (source == playSubdivisionsBox)
      playSubdivisions = e.getStateChange() != ItemEvent.DESELECTED;
  }
}
