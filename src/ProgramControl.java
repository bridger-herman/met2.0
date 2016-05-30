import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ProgramControl extends JPanel implements ActionListener {
  private static final String editLabel = "Edit Measure";
  private static final String addLabel = "Add Measure";
  private JButton editButton;
  private JButton addButton;
  private JScrollPane pane;
  private static JList<Measure> displayList;

  public ProgramControl() {
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    displayList = new JList<Measure>(Player.getMeasureList());
    displayList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
    displayList.setCellRenderer(new MeasureRenderer());
    displayList.setVisibleRowCount(1);

    pane = new JScrollPane(displayList);
    pane.setPreferredSize(new Dimension(400, 65));
    pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

    JPanel actionPanel = new JPanel();
    editButton = new JButton(editLabel);
    editButton.setActionCommand(editLabel);
    editButton.addActionListener(this);
    actionPanel.add(editButton);

    // addButton = new JButton(addLabel);
    // addButton.setActionCommand(addLabel);
    // addButton.addActionListener(this);
    // actionPanel.add(addButton);

    this.add(pane);
    this.add(actionPanel);
  }

  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case editLabel:
        System.out.println("Edit!");
        break;
      case addLabel:
        System.out.println("Add!");

        break;
      default:
        break;
    }
  }

  public static JList<Measure> getDisplayList() {
    return displayList;
  }
}
