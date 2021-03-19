package view;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LoadImage extends JFrame implements DisplayType {
  JFileChooser file;
  JButton load;

  public LoadImage(int w, int h) {
    file = new JFileChooser();
    load = new JButton("Load");
    load.setActionCommand("filter view");
    file.removeChoosableFileFilter(file.getAcceptAllFileFilter());
    file.addChoosableFileFilter(new FileNameExtensionFilter("Images (*.jpg, *.png, *.bmp)", "jpg"
            , "png", "bmp"));

  }


  @Override
  public boolean canDisplayImage() {
    return false;
  }

  @Override
  public void replaceImage(BufferedImage image) {
    throw new IllegalArgumentException("why");
  }

  @Override
  public String[] read() {
    File f = file.getSelectedFile();
    String temp = f.getAbsolutePath();

    return new String[]{temp};
  }

  @Override
  public void displayErrorMessage(String message) {
    // left empty as this is a default dialog box provided by JFileChooser.
  }

  @Override
  public void setListener(ActionListener listener) {
    load.addActionListener(listener);
  }

  @Override
  public void shows() {
    int result = file.showOpenDialog(null);
    if (result == JFileChooser.APPROVE_OPTION) {
      load.doClick();
    }
  }

  @Override
  public void close() {
    this.setVisible(false);
  }
}
