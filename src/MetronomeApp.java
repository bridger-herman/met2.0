import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MetronomeApp extends JFrame {
  private static final String title = "Met2.0 Simple Mode";
  private MediaControl mediaControls;
  private MeasureControl measureControls;
  // TODO make fancy program Display
  // TODO improve GUI

  public MetronomeApp() {
    // TODO eventually put parameters for size and such here
  }

  private void createGUI() {
    this.setTitle(title);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel container = new JPanel();
    container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));

    this.mediaControls = new MediaControl();
    this.mediaControls.setOpaque(true);

    this.measureControls = new MeasureControl();
    this.measureControls.setOpaque(true);

    container.add(this.mediaControls, BorderLayout.CENTER);
    container.add(this.measureControls, BorderLayout.CENTER);

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
