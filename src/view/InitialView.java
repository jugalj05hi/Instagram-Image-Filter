package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class is used to construct a view for giving the initial options.
 */
public class InitialView extends JFrame implements DisplayType {

  JButton generateButton;
  JButton filterButton;

  public InitialView(int width, int height) {
    this.setSize(width, height);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(5, 1));
    generateButton = new JButton("Generate Images");
    filterButton = new JButton("Filter Images");
    JPanel innerPanel1 = new JPanel(new BorderLayout());
    JPanel innerPanel2 = new JPanel(new BorderLayout());
    panel.add(new JPanel());
    innerPanel1.add(new JPanel(), BorderLayout.WEST);
    innerPanel1.add(generateButton, BorderLayout.CENTER);
    innerPanel1.add(new JPanel(), BorderLayout.EAST);

    innerPanel2.add(new JPanel(), BorderLayout.WEST);
    innerPanel2.add(filterButton, BorderLayout.CENTER);
    innerPanel2.add(new JPanel(), BorderLayout.EAST);
    panel.add(innerPanel1);
    panel.add(new JPanel());
    panel.add(innerPanel2);
    panel.add(new JPanel());


    generateButton.setActionCommand("generate view");
    filterButton.setActionCommand("load view");
    this.add(panel);

  }

  @Override
  public boolean canDisplayImage() {
    return false;
  }

  @Override
  public void replaceImage(BufferedImage image) {
    throw new IllegalArgumentException("cannot display image in initial window");
  }

  @Override
  public String[] read() {
    return new String[0];
  }

  @Override
  public void displayErrorMessage(String message) {
    throw new IllegalArgumentException("no error on initial screen");
  }

  @Override
  public void setListener(ActionListener listener) {
    generateButton.addActionListener(listener);
    filterButton.addActionListener(listener);
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
