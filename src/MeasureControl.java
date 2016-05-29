import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MeasureControl extends JPanel implements ActionListener {
  private static final String addLabel = "Add Measure";
  private JButton addMeasureButton;
  private JSpinner beats;
  private JComboBox<Integer> division;
  private JSpinner subdivision;
  private JSpinner tempo;
  private JSpinner howManyMeasures;
  private double previousDivision;

  // TODO possibly rework constructors of UI to have a method (defined in
  // superclass) that can construct JPanels with labels
  public MeasureControl() {
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    JPanel beatsPanel = new JPanel();
    JLabel beatsLabel = new JLabel("Beats:");
    beats = new JSpinner(new SpinnerNumberModel(4, 1, 64, 1));
    beatsPanel.add(beatsLabel);
    beatsPanel.add(beats);
    beatsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.add(beatsPanel);

    JPanel divisionPanel = new JPanel();
    JLabel divisionLabel = new JLabel("Division:");
    Integer[] options = {1, 2, 4, 8, 16, 32, 64};
    division = new JComboBox<Integer>(options);
    division.addActionListener(this);
    division.setSelectedIndex(2);
    previousDivision = 4.0;
    divisionPanel.add(divisionLabel);
    divisionPanel.add(division);
    divisionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.add(divisionPanel);

    JPanel subdivPanel = new JPanel();
    JLabel subdivLabel = new JLabel("Subdivision:");
    subdivision = new JSpinner(new SpinnerNumberModel(0, 0, 8, 1));
    subdivPanel.add(subdivLabel);
    subdivPanel.add(subdivision);
    subdivPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.add(subdivPanel);

    JPanel tempoPanel = new JPanel();
    JLabel tempoLabel = new JLabel("Tempo:");
    tempo = new JSpinner(new SpinnerNumberModel(120, 1, 300, 1));
    tempoPanel.add(tempoLabel);
    tempoPanel.add(tempo);
    tempoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.add(tempoPanel);

    JPanel howManyMeasuresPanel = new JPanel();
    JLabel howManyLabel = new JLabel("Measures to add:");
    howManyMeasures = new JSpinner(new SpinnerNumberModel(1, 1, 512, 1));
    howManyMeasuresPanel.add(howManyLabel);
    howManyMeasuresPanel.add(howManyMeasures);
    howManyMeasuresPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.add(howManyMeasuresPanel);

    addMeasureButton = new JButton(addLabel);
    addMeasureButton.setActionCommand(addLabel);
    addMeasureButton.addActionListener(this);
    addMeasureButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.add(addMeasureButton);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals(addLabel)) {
      for (int i = 0; i < (int) howManyMeasures.getValue(); i++) {
        Player.addMeasureToProgram(new Measure(
        new TimeSignature((int) beats.getValue(), (int) division.getSelectedItem()),
        new Tempo((int) tempo.getValue()),
        new Subdivision((int) subdivision.getValue())));
      }
    }
    else {
      try {
        int selected = (int) division.getSelectedItem();
        double multiplier = selected / previousDivision;
        previousDivision = (double) selected;
        int newTempo = (int) (((int) tempo.getValue()) * multiplier);
        tempo.setValue(newTempo);
      }
      catch (NullPointerException ex) {
        // ex.printStackTrace();
        return;
      }
    }
  }
}
