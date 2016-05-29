import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ProgramControl extends JPanel implements ActionListener {
  private static final String editLabel = "Edit Measure";
  private static final String addLabel = "Add Measure";
  JButton editButton;
  JButton addButton;
  JScrollPane pane;
  JList<Measure> displayList;

  public ProgramControl() {
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    displayList = new JList<Measure>(Player.getDisplayList());
    displayList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
    displayList.setVisibleRowCount(1);
    // displayList.setBorder(new EmptyBorder(10,10, 10, 10));

    pane = new JScrollPane(displayList);
    pane.setPreferredSize(new Dimension(400, 50));
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
}
