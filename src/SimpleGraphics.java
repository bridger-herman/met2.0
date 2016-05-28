import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SimpleGraphics extends JFrame {
  private static final String title = "Met2.0 Simple Mode";
  private PlayButton button;

  private void createGUI() {
    //Create and set up the window.
    this.setTitle(title);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.button = new PlayButton();
    this.button.setOpaque(true);
    this.add(button, BorderLayout.CENTER);

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
