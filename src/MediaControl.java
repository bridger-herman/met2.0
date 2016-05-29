import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MediaControl extends JPanel implements ActionListener, ItemListener {
  private static final String stopText = "Stop";
  private static final String playText = "Play";
  private static final String downbeatLabel = "Play Downbeats";
  private static final String subdivisionLabel = "Play Subdivisions";
  private static final String loopLabel = "Loop Playback";

  private JButton playButton;
  private JButton showButton;
  private JCheckBox playDownbeatsBox;
  private JCheckBox playSubdivisionsBox;
  private JCheckBox loopPlayback;

  private boolean playing;

  public MediaControl() {
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    playDownbeatsBox = new JCheckBox(downbeatLabel, Player.playDownbeats);
    playDownbeatsBox.addItemListener(this);
    add(playDownbeatsBox);

    playSubdivisionsBox = new JCheckBox(subdivisionLabel, Player.playSubdivisions);
    playSubdivisionsBox.addItemListener(this);
    add(playSubdivisionsBox);

    loopPlayback = new JCheckBox(loopLabel, Player.getProgramLoop());
    loopPlayback.addItemListener(this);
    add(loopPlayback);

    playing = false;
    playButton = new JButton(playText);
    playButton.setActionCommand(playText);
    playButton.addActionListener(this);
    add(playButton);

    showButton = new JButton("Show Program");
    showButton.setActionCommand("Show");
    showButton.addActionListener(this);
    add(showButton);
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
      case "Show":
        Player.showProgram();
        break;
      default:
        break;
    }
  }

  public void itemStateChanged(ItemEvent e) {
    Object source = e.getItemSelectable();
    if (source == playDownbeatsBox)
      Player.playDownbeats = e.getStateChange() != ItemEvent.DESELECTED;
    else if (source == playSubdivisionsBox)
      Player.playSubdivisions = e.getStateChange() != ItemEvent.DESELECTED;
    else if (source == loopPlayback)
      Player.setProgramLoop(e.getStateChange() != ItemEvent.DESELECTED);
  }
}
