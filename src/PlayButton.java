import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PlayButton extends JPanel implements ActionListener {
  private static final String stopText = "Stop";
  private static final String playText = "Play";
  private JButton button;
  private boolean playing;

  public PlayButton() {
    playing = false;
    button = new JButton(playText);
    button.setActionCommand(playText);
    button.addActionListener(this);
    add(button);
  }
  private void toggleState() {
    if (playing) {
      button.setActionCommand(playText);
      button.setText(playText);
    }
    else {
      button.setActionCommand(stopText);
      button.setText(stopText);
    }
    playing = !playing;
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals(stopText)) {
      toggleState();
    }
    else {
      toggleState();
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
        public void run() {
          Player.playCurrentProgram();
          toggleState();
        }
      });
    }
  }

  public boolean isPlaying() {
    return playing;
  }

}
