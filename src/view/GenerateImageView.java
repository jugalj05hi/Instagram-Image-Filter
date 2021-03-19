package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

/**
 * This class is used to construct a view for generating images.
 */
public class GenerateImageView extends JFrame implements DisplayType {

  private JPanel panel;
  private JComboBox menu;
  private String[] menuOptions;
  private JLabel side;
  private JLabel width;
  private JLabel error;
  private JTextField sideText;
  private JTextField widthText;
  private JButton save;
  private JButton back;


  public GenerateImageView(int w, int h) {
    this.setSize(w, h);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    panel = new JPanel(new GridLayout(5, 1));
    JPanel main = new JPanel();
    menuOptions = new String[]{"Flag of Greece",
            "Flag of France", "Flag of Switzerland", "CheckerBoard", "Vertical Rainbow"
            , "Horizontal Rainbow"};
    menu = new JComboBox(menuOptions);

    main.setMaximumSize(new Dimension(300, 10));
    JPanel sub = new JPanel();
    sub.add(new JLabel("Select Image"));
    sub.add(menu);
    JPanel dimension = new JPanel(new FlowLayout());
    side = new JLabel("Height");
    sideText = new JTextField(20);
    width = new JLabel("Width");
    widthText = new JTextField(20);
    panel.add(main);
    panel.add(sub);
    dimension.add(side);
    dimension.add(sideText);
    dimension.add(width);
    dimension.add(widthText);
    save = new JButton("Save");
    back = new JButton("Back");

    JPanel move = new JPanel();
    move.add(back);
    move.add(save);
    error = new JLabel();
    panel.add(dimension);
    panel.add(move);
    panel.add(error);

    save.setActionCommand("validate");
    back.setActionCommand("initial view");
    menu.addActionListener(actionEvent -> {
      switch (menu.getSelectedItem().toString()) {
        case "CheckerBoard":
        case "Flag of Switzerland":
          width.setVisible(false);
          widthText.setVisible(false);
          side.setText("Side");
          dimension.repaint();
          dimension.validate();
          panel.repaint();
          panel.validate();
          break;
        default:
          width.setVisible(true);
          widthText.setVisible(true);
          side.setText("Height");
          dimension.repaint();
          dimension.validate();
          panel.repaint();
          panel.validate();
      }
    });
    this.add(panel);
  }

  @Override
  public boolean canDisplayImage() {
    return false;
  }

  @Override
  public void replaceImage(BufferedImage image) {
    throw new IllegalArgumentException("plis staph");
  }

  @Override
  public String[] read() {
    return new String[]{menu.getSelectedItem().toString(), sideText.getText(), widthText.getText()};
  }

  @Override
  public void displayErrorMessage(String message) {
    error.setText(message);
    panel.repaint();
    panel.validate();
  }


  @Override
  public void setListener(ActionListener listener) {
    save.addActionListener(listener);
    back.addActionListener(listener);
  }

  @Override
  public void shows() {
    this.setVisible(true);
  }

  @Override
  public void close() {
    this.setVisible(false);
  }

}
