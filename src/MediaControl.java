import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MediaControl extends JPanel implements ActionListener {
  private static final String stopText = "Stop";
  private static final String playText = "Play";
  private JButton playButton;
  private boolean playing;

  public MediaControl() {
    playing = false;
    playButton = new JButton(playText);
    playButton.setActionCommand(playText);
    playButton.addActionListener(this);
    add(playButton);
  }

  private void togglePlayState() {
    if (playing) {
      playButton.setActionCommand(playText);
      playButton.setText(playText);
    }
    else {
      playButton.setActionCommand(stopText);
      playButton.setText(stopText);
    }
    playing = !playing;
  }

  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case stopText:
        togglePlayState();
        break;
      case playText:
      togglePlayState();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
          public void run() {
            Player.playCurrentProgram();
            togglePlayState();
          }
        });
        break;
      default:
        break;
    }
  }

  public boolean isPlaying() {
    return playing;
  }

}
