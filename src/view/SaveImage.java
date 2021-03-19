package view;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SaveImage extends JFrame implements DisplayType {


  JFileChooser file;
  JButton save;

  public SaveImage(int w, int h) {
    file = new JFileChooser();
    file.removeChoosableFileFilter(file.getAcceptAllFileFilter());
    file.addChoosableFileFilter(new FileNameExtensionFilter("Images (*.jpg)", "jpg"));
    file.addChoosableFileFilter(new FileNameExtensionFilter("Images (*.png)", "png"));
    file.addChoosableFileFilter(new FileNameExtensionFilter("Images (*.bmp)", "bmp"));
    save = new JButton();
    save.setActionCommand("save");
  }


  @Override
  public boolean canDisplayImage() {
    return false;
  }

  @Override
  public void replaceImage(BufferedImage image) {
    throw new IllegalArgumentException("no");
  }

  @Override
  public String[] read() {
    String temp = file.getFileFilter().getDescription();
    String ext = temp.substring(temp.lastIndexOf('.')+1, temp.lastIndexOf(')'));
    return new String[]{file.getSelectedFile().getAbsolutePath()+"."+ext, ext};
  }

  @Override
  public void displayErrorMessage(String message) {
    // left empty as this is a default dialog box provided by JFileChooser.
  }

  @Override
  public void setListener(ActionListener listener) {
    save.addActionListener(listener);
  }

  @Override
  public void shows() {
    int result = file.showSaveDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
      save.doClick();
    }
  }

  @Override
  public void close() {
    this.setVisible(false);
  }
}
