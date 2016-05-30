import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MetronomeApp extends JFrame {
  private static final String title = "Metronome 2.0";
  // TODO make fancy program Display
  // TODO improve GUI

  public MetronomeApp() {
    // TODO eventually put parameters for size and such here
  }

  private void createGUI() {
    this.setTitle(title);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel container = new JPanel();
    container.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;

    c.gridx = 0;
    c.gridy = 0;
    MediaControl mediaControls = new MediaControl();
    mediaControls.setOpaque(true);
    container.add(mediaControls, c);

    c.gridx = 1;
    MeasureControl measureControls = new MeasureControl();
    measureControls.setOpaque(true);
    container.add(measureControls, c);

    c.gridx = 0;
    c.gridy = 1;
    c.gridwidth = 2;
    ProgramControl programControls = new ProgramControl();
    programControls.setOpaque(true);
    container.add(programControls, c);

    this.add(container);

    this.pack();
    this.setVisible(true);
  }

  public void run() {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createGUI();
      }
    });
  }

  public static void main(String[] args) {
    MetronomeApp met = new MetronomeApp();
    met.run();
  }
}
