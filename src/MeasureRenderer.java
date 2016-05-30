import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

class MeasureRenderer extends JLabel implements ListCellRenderer<Object> {
  public Component getListCellRendererComponent(JList<?> list, Object value,
    int index, boolean isSelected, boolean cellHasFocus) {

    JPanel container = new JPanel();
    container.setBackground(Color.WHITE);
    container.setBorder(new EmptyBorder(5, 5, 5, 5));

    JPanel label = new JPanel();
    label.setBorder(new EmptyBorder(2, 2, 2, 2));

    this.setText(value.toString());

    if (isSelected) {
      label.setBackground(Color.BLUE);
      this.setForeground(Color.WHITE);
    }
    else {
      label.setBackground(Color.WHITE);
      this.setForeground(Color.BLACK);
    }

    label.add(this);
    container.add(label);
    return container;
  }
}
