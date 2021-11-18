package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.*;

import model.IPixel;
import model.Image;


/**
 * Implementation of an image viewer.
 */
public class IMEViewGUI extends JFrame implements IGUIView, ActionListener {
  private final JButton Red;
  private final JButton Green;
  private final JButton Blue;
  private final JButton Horizontal;
  private final JButton Vertical;
  private final JButton Sepia;
  private final JButton Greyscale;
  private final JButton Intensity;
  private final JButton Blur;
  private final JButton Sharpen;
  private final JButton Luma;
  private final JButton Value;
  private final List<JButton> functionButtons = new ArrayList<>();

  private final JPanel buttonPanel;
  private final JPanel imagePanel;
  private final JPanel histogramPanel;

  private final JPanel filePanel;
  private final JButton load;
  private final JButton save;
  private final JFileChooser fc;
  private final Vector<String> images = new Vector<>();
  private final JComboBox<String> imageSelection;

  private JTextArea systemMessages;
  private final List<ViewListener> listenerList;

  public IMEViewGUI() {
    super();
    this.listenerList = new ArrayList<>();
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    this.Red = new JButton("Red");
    this.Red.setActionCommand("red");

    this.Green = new JButton("Green");
    this.Green.setActionCommand("green");

    this.Blue = new JButton("Blue");
    this.Blue.setActionCommand("blue");

    this.save = new JButton("Save");
    this.save.addActionListener(this);

    this.load = new JButton("Load");
    this.load.addActionListener(this);

    this.Blur = new JButton("Blur");
    this.Blur.setActionCommand("blur");

    this.Sharpen = new JButton("Sharpen");
    this.Sharpen.setActionCommand("sharpen");

    this.Luma = new JButton("Luma");
    this.Luma.setActionCommand("luma");

    this.Value = new JButton("Value");
    this.Value.setActionCommand("value");

    this.Greyscale = new JButton("Greyscale");
    this.Greyscale.setActionCommand("greyscale");

    this.Sepia = new JButton("Sepia");
    this.Sepia.setActionCommand("sepia");

    this.Intensity = new JButton("Intensity");
    this.Intensity.setActionCommand("intensity");

    this.Horizontal = new JButton("Horizontal");
    this.Horizontal.setActionCommand("horizontal");

    this.Vertical = new JButton("Vertical");
    this.Vertical.setActionCommand("vertical");

    fc = new JFileChooser();

    this.systemMessages = new JTextArea(1, 30);
    this.systemMessages.setEditable(false);

    this.buttonPanel = new JPanel();
    this.buttonPanel.setBackground(Color.GRAY);
    this.buttonPanel.setLayout(new GridLayout(12, 1));
    this.functionButtons.add(this.Red);
    this.functionButtons.add(this.Green);
    this.functionButtons.add(this.Blue);
    this.functionButtons.add(this.Blur);
    this.functionButtons.add(this.Sharpen);
    this.functionButtons.add(this.Luma);
    this.functionButtons.add(this.Value);
    this.functionButtons.add(this.Greyscale);
    this.functionButtons.add(this.Sepia);
    this.functionButtons.add(this.Intensity);
    this.functionButtons.add(this.Horizontal);
    this.functionButtons.add(this.Vertical);
    for (JButton button : functionButtons) {
      button.addActionListener(this);
      buttonPanel.add(button);
    }

    this.imagePanel = new JPanel();
    this.imagePanel.setBackground(Color.BLACK);
    this.imagePanel.setPreferredSize(new Dimension(1000, 1000));

    this.histogramPanel = new JPanel();
    this.histogramPanel.setBackground(Color.red);

    this.filePanel = new JPanel();
    this.filePanel.setBackground(Color.CYAN);
    this.filePanel.setLayout(new FlowLayout());
    this.filePanel.add(this.systemMessages);
    this.filePanel.add(this.load);
    this.filePanel.add(this.save);
    this.imageSelection = new JComboBox<>(this.images);
    this.filePanel.add(this.imageSelection);
    this.imageSelection.addActionListener(this);
    this.imageSelection.setPrototypeDisplayValue("long file name goes here");


    this.add(this.histogramPanel, BorderLayout.NORTH);
    this.add(this.imagePanel, BorderLayout.CENTER);
    this.add(this.buttonPanel, BorderLayout.WEST);
    this.add(this.filePanel, BorderLayout.SOUTH);

    this.setFocusable(true);
    this.requestFocus();

    this.pack();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e == null) {
      throw new IllegalArgumentException("Action event is null;");
    }
    if (e.getSource() == imageSelection) {
      //drop down menu swap to selected image
      String imageName = (String) imageSelection.getSelectedItem();
      for (ViewListener listener : listenerList) {
        listener.selectImageEvent(imageName);
      }
    }
    if (e.getSource() == load) {
      int returnVal = fc.showOpenDialog(this);

      if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = fc.getSelectedFile();
        //This is where the controller would be called to load the file.
        for (ViewListener listener : listenerList) {
          listener.loadFileEvent(file.getName());
        }
      } else {
        this.systemMessages.setText("");
        this.systemMessages.append("Image not loaded; User canceled selection.");
      }
    }
    if (e.getSource() == save) {
      int returnValue = fc.showSaveDialog(this);
      if (returnValue == JFileChooser.APPROVE_OPTION) {
        File file = fc.getSelectedFile();
        //This is where the controller would be called to save the file.
        for (ViewListener listener : listenerList) {
          listener.saveFileEvent(file.getName());
        }
      } else {
        this.systemMessages.setText("");
        this.systemMessages.append("Image not saved; User canceled selection.");
      }
    }
    if (this.functionButtons.contains((JButton) e.getSource())) {

      for (ViewListener listener : listenerList) {
        listener.commandEvent(e.getActionCommand());
      }
      }
    this.systemMessages.setText("");
    this.systemMessages.append(e.getActionCommand());

    this.systemMessages.append(" successful!");

  }


  @Override
  public void refresh(Image selectedImage) {
    BufferedImage image = getBuff(selectedImage);
    JLabel picture = new JLabel(new ImageIcon(image));
    this.imagePanel.removeAll();
    this.imagePanel.add(picture);
    this.imagePanel.updateUI();
    for (ViewListener listener : listenerList) {
      for (int i = 0; i < listener.getImageNames().size(); i++) {
        boolean contains = images.contains(listener.getImageNames().get(i));
        if (!contains) {
          images.add(listener.getImageNames().get(i));
        }
      }
    }
  }

  @Override
  public void addListener(ViewListener viewListener) {
    this.listenerList.add(viewListener);
  }

  @Override
  public void requestViewFocus() {

  }

  @Override
  public void showView(boolean show) {
    this.setVisible(show);
  }

  private BufferedImage getBuff(Image image) {
    BufferedImage buff = new BufferedImage(image.getWidth(), image.getHeight(),
            BufferedImage.TYPE_3BYTE_BGR);
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int alpha = 255;
        int red = image.getPixelAt(i, j).getColors().get(IPixel.Color.Red);
        int green = image.getPixelAt(i, j).getColors().get(IPixel.Color.Green);
        int blue = image.getPixelAt(i, j).getColors().get(IPixel.Color.Blue);
        int rgb = (alpha << 24);
        rgb = rgb | (red << 16);
        rgb = rgb | (green << 8);
        rgb = rgb | (blue);
        buff.setRGB(j, i, rgb);//note that the position call is inverted for JPG/PNG.
      }
    }
    return buff;
  }

}

