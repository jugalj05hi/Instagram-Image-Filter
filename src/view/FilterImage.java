package view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * This class is used to construct a view for filtering images.
 */
public class FilterImage extends JFrame implements DisplayType {
  JPanel panel;
  JButton dither;
  JButton mosaic;
  JTextField mosaicText;
  JButton greyscale;
  JButton sepia;
  JButton sharpen;
  JButton blur;
  JButton back;
  JButton save;
  JLabel error;
  JPanel imagePanel;
  JScrollPane imagePane;
  JLabel imageLabel;

  public FilterImage(int w, int h) {
    this.setSize(w, h);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    BufferedImage image = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
    imageLabel = new JLabel(new ImageIcon(image));
    imagePanel = new JPanel();
    imagePanel.add(imageLabel);
    imagePanel.setSize(w / 2, h / 2);
    imagePane = new JScrollPane(imagePanel);
    imagePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    imagePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    panel = new JPanel(new GridLayout(1,2));

    JPanel temp = new JPanel();
    JPanel rightPanel = new JPanel(new GridLayout(6, 1));
    dither = new JButton("Dither");
    mosaic = new JButton("Mosaic");
    mosaicText = new JTextField(10);
    greyscale = new JButton("Greyscale");
    sepia = new JButton("Sepia");
    sharpen = new JButton("Sharpen");
    blur = new JButton("Blur");
    back = new JButton("Back");
    save = new JButton("Save");
    rightPanel.add(dither);
    JPanel m = new JPanel();
    m.add(mosaic);
    m.add(mosaicText);
    rightPanel.add(m);
    rightPanel.add(greyscale);
    rightPanel.add(sepia);
    rightPanel.add(sharpen);
    rightPanel.add(blur);
    JPanel bottom = new JPanel();
    bottom.add(back);
    bottom.add(save);
    panel.add(imagePane);
    temp.add(rightPanel);
    temp.add(bottom);
    error = new JLabel();
    temp.add(error);
    panel.add(temp);


    back.setActionCommand("initial view");
    blur.setActionCommand("blur");
    dither.setActionCommand("dither");
    sepia.setActionCommand("sepia");
    sharpen.setActionCommand("sharpen");
    mosaic.setActionCommand("mosaic");
    greyscale.setActionCommand("greyscale");
    save.setActionCommand("save view");


    this.add(panel);
  }


  @Override
  public boolean canDisplayImage() {
    return true;
  }

  @Override
  public void replaceImage(BufferedImage image) {
    imagePanel.removeAll();
    imagePanel.add(new JLabel(new ImageIcon(image)));
    imagePanel.repaint();
    imagePanel.validate();
  }

  @Override
  public String[] read() {
    return new String[]{mosaicText.getText()};
  }

  @Override
  public void displayErrorMessage(String message) {

    error.setText(message);
  }

  @Override
  public void setListener(ActionListener listener) {

    back.addActionListener(listener);
    blur.addActionListener(listener);
    dither.addActionListener(listener);
    sepia.addActionListener(listener);
    sharpen.addActionListener(listener);
    mosaic.addActionListener(listener);
    greyscale.addActionListener(listener);
    save.addActionListener(listener);
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
