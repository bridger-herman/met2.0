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
  private double previousDivision;

  public MeasureControl() {
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    JPanel beatsPanel = new JPanel();
    JLabel beatsLabel = new JLabel("Beats:");
    beats = new JSpinner(new SpinnerNumberModel(4, 1, 64, 1));
    beatsPanel.add(beatsLabel);
    beatsPanel.add(beats);
    add(beatsPanel);

    JPanel divisionPanel = new JPanel();
    JLabel divisionLabel = new JLabel("Division:");
    Integer[] options = {1, 2, 4, 8, 16, 32, 64};
    division = new JComboBox<Integer>(options);
    division.addActionListener(this);
    division.setSelectedIndex(2);
    previousDivision = 4.0;
    divisionPanel.add(divisionLabel);
    divisionPanel.add(division);
    add(divisionPanel);

    JPanel subdivPanel = new JPanel();
    JLabel subdivLabel = new JLabel("Subdivision:");
    subdivision = new JSpinner(new SpinnerNumberModel(0, 0, 8, 1));
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
    if (e.getActionCommand().equals(addLabel)) {
      Player.addMeasureToProgram(new Measure(
        new TimeSignature((int) beats.getValue(), (int) division.getSelectedItem()),
        new Tempo((int) tempo.getValue()),
        new Subdivision((int) subdivision.getValue())));
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
