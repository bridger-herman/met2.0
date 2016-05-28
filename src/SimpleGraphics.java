import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SimpleGraphics extends JFrame {
  private static final String title = "Met2.0 Simple Mode";
  private MediaControl mediaControls;
  private MeasureControl measureControls;


  private void createGUI() {
    //Create and set up the window.
    this.setTitle(title);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel container = new JPanel();
    container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));

    this.mediaControls = new MediaControl();
    this.mediaControls.setOpaque(true);
    // this.add(this.mediaControls, BorderLayout.CENTER);

    this.measureControls = new MeasureControl();
    this.measureControls.setOpaque(true);
    // this.add(this.measureControls, BorderLayout.CENTER);

    container.add(this.mediaControls);
    container.add(this.measureControls);

    this.add(container);
    //Display the window.
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
}
