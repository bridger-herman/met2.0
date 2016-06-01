import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public abstract class SwingGFXApp extends JFrame {
  public static final int LABEL_ON_RIGHT = 0;
  public static final int LABEL_ON_LEFT = 1;

  public SwingGFXApp() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  private static JPanel componentWithLabel(Component c, String label, int labelAlign) {
    JPanel tmpPanel = new JPanel();
    if (labelAlign == LABEL_ON_LEFT) {
      tmpPanel.add(new JLabel(label));
      tmpPanel.add(c);
    }
    else if (labelAlign == LABEL_ON_RIGHT) {
      tmpPanel.add(new JLabel(label));
      tmpPanel.add(c);
    }
    else
      return null;
    return tmpPanel;
  }

  protected static JCheckBox setupJCheckBox(JCheckBox b, ItemListener listener) {
    b.addItemListener(listener);
    b.setAlignmentX(Component.CENTER_ALIGNMENT);
    return b;
  }

  protected static JButton setupJButton(JButton b, String actionCommand, ActionListener listener) {
    b.setActionCommand(actionCommand);
    b.addActionListener(listener);
    b.setAlignmentX(Component.CENTER_ALIGNMENT);
    return b;
  }

  abstract void createGUI();

  public void run() {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createGUI();
      }
    });
  }

}
