package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


import model.image.IPixel;
import model.image.Image;


/**
 * Implementation of a GUI-based image viewer. This view adds an object implementing the
 * ViewListener interface which, in this case, is implemented by the GUI-based controller.
 */
public class IMEViewGUI extends JFrame implements IMEGUIView, ActionListener {
  private final List<ViewListener> listenerList;
  private final Vector<String> loadedImgs;
  private final List<JButton> functionButtons;
  private final Histogram[] histograms = new Histogram[4];

  private final JPanel histogramsPanel = new JPanel();
  private final JPanel imagePanel = new JPanel();
  private final JPanel filePanel = new JPanel();

  private final JButton load;
  private final JButton save;
  private final JFileChooser fc;
  private final JComboBox<String> imageSelection;

  private final JTextArea systemMessages;

  /**
   * Auto-sets fields.
   */
  public IMEViewGUI() {
    super();
    this.listenerList = new ArrayList<>();
    this.functionButtons = new ArrayList<>();
    this.loadedImgs = new Vector<>();

    // Button panel containing each image manipulation command.

    JButton red = new JButton("Red");
    red.setActionCommand("red");
    this.functionButtons.add(red);
    JButton green = new JButton("Green");
    green.setActionCommand("green");
    this.functionButtons.add(green);
    JButton blue = new JButton("Blue");
    blue.setActionCommand("blue");
    this.functionButtons.add(blue);
    JButton blur = new JButton("Blur");
    blur.setActionCommand("blur");
    this.functionButtons.add(blur);
    JButton sharpen = new JButton("Sharpen");
    sharpen.setActionCommand("sharpen");
    this.functionButtons.add(sharpen);
    JButton luma = new JButton("Luma");
    luma.setActionCommand("luma");
    this.functionButtons.add(luma);
    JButton value = new JButton("Value");
    value.setActionCommand("value");
    this.functionButtons.add(value);
    JButton greyscale = new JButton("Greyscale");
    greyscale.setActionCommand("greyscale");
    this.functionButtons.add(greyscale);
    JButton sepia = new JButton("Sepia");
    sepia.setActionCommand("sepia");
    this.functionButtons.add(sepia);
    JButton intensity = new JButton("Intensity");
    intensity.setActionCommand("intensity");
    this.functionButtons.add(intensity);
    JButton horizontal = new JButton("Horizontal");
    horizontal.setActionCommand("horizontal");
    this.functionButtons.add(horizontal);
    JButton vertical = new JButton("Vertical");
    vertical.setActionCommand("vertical");
    this.functionButtons.add(vertical);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(Color.GRAY);
    buttonPanel.setLayout(new GridLayout(this.functionButtons.size(), 1));
    for (JButton button : this.functionButtons) {
      button.addActionListener(this);
      buttonPanel.add(button);
    }


    // Image panel.

    this.imagePanel.setBackground(Color.BLACK);
    this.imagePanel.setPreferredSize(new Dimension(1000, 1000));
    JScrollPane imagePanelScrollPane = new JScrollPane(this.imagePanel);

    // Histogram panel.
    this.histogramsPanel.setBackground(Color.PINK);
    this.histogramsPanel.setLayout(new GridLayout(1, this.histograms.length));
    for (int i = 0; i < 4; i += 1) {
      this.histograms[i] = new Histogram(300, 200);
      this.histogramsPanel.add(this.histograms[i]); // Placeholder histogram panels
    }

    // File panel.

    this.systemMessages = new JTextArea(1, 30);
    this.systemMessages.setEditable(false);
    this.load = new JButton("Load");
    this.load.addActionListener(this);
    this.save = new JButton("Save");
    this.save.addActionListener(this);
    fc = new JFileChooser();
    this.imageSelection = new JComboBox<>(this.loadedImgs);
    this.imageSelection.setActionCommand("Image selection");
    this.imageSelection.addActionListener(this);
    this.imageSelection.setPrototypeDisplayValue("long file name goes here");

    this.filePanel.setBackground(Color.CYAN);
    this.filePanel.setLayout(new FlowLayout());
    this.filePanel.add(this.systemMessages);
    this.filePanel.add(this.load);
    this.filePanel.add(this.save);
    this.filePanel.add(this.imageSelection);

    // Add panels

    this.add(this.histogramsPanel, BorderLayout.NORTH);
    this.add(imagePanelScrollPane, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.WEST);
    this.add(this.filePanel, BorderLayout.SOUTH);

    // Config.

    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setFocusable(true);
    this.requestFocus();
    this.pack();
  }

  /**
   * Allows composition with custom structures.
   * @param listenerList list of view listeners
   * @param loadedImgs images currently loaded in the program
   * @param functionButtons buttons corresponding to function object commands
   * @param load load filesystem command button
   * @param save save filesystem command button
   * @param fc file chooser
   * @param imageSelection image selection area for loaded images
   */
  public IMEViewGUI(List<ViewListener> listenerList, Vector<String> loadedImgs,
                    List<JButton> functionButtons, JButton load, JButton save, JFileChooser fc,
                    JComboBox<String> imageSelection) {
    super();
    this.listenerList = listenerList;
    this.loadedImgs = loadedImgs;
    this.functionButtons = functionButtons;
    this.load = load;
    this.save = save;
    this.fc = fc;
    this.imageSelection = imageSelection;

    // Button panel containing each image manipulation command.

    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(Color.GRAY);
    buttonPanel.setLayout(new GridLayout(this.functionButtons.size(), 1));
    for (JButton button : this.functionButtons) {
      button.addActionListener(this);
      buttonPanel.add(button);
    }

    // Image panel.

    this.imagePanel.setBackground(Color.BLACK);
    this.imagePanel.setPreferredSize(new Dimension(1000, 1000));
    JScrollPane imagePanelScrollPane = new JScrollPane(this.imagePanel);

    // Histogram panel.
    this.histogramsPanel.setBackground(Color.PINK);
    this.histogramsPanel.setLayout(new GridLayout(1, this.histograms.length));
    for (int i = 0; i < 4; i += 1) {
      this.histograms[i] = new Histogram(300, 200);
      this.histogramsPanel.add(this.histograms[i]); // Placeholder histogram panels
    }

    // File panel.

    this.systemMessages = new JTextArea(1, 30);
    this.systemMessages.setEditable(false);
    this.load.addActionListener(this);
    this.save.addActionListener(this);
    this.imageSelection.setActionCommand("Image selection");
    this.imageSelection.addActionListener(this);
    this.imageSelection.setPrototypeDisplayValue("long file name goes here");

    this.filePanel.setBackground(Color.CYAN);
    this.filePanel.setLayout(new FlowLayout());
    this.filePanel.add(this.systemMessages);
    this.filePanel.add(this.load);
    this.filePanel.add(this.save);
    this.filePanel.add(this.imageSelection);

    // Add panels

    this.add(this.histogramsPanel, BorderLayout.NORTH);
    this.add(imagePanelScrollPane, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.WEST);
    this.add(this.filePanel, BorderLayout.SOUTH);

    // Config.

    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setFocusable(true);
    this.requestFocus();
    this.pack();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e == null) {
      throw new IllegalArgumentException("Action event cannot be null.");
    }
    String actionCmd = e.getActionCommand();
    System.out.println(actionCmd);
    Object eventSrc = e.getSource();

    if (eventSrc == this.imageSelection) {
      String imageName = (String) this.imageSelection.getSelectedItem();

      // Drop down menu swaps to the selected image out of pre-loaded images.
      for (ViewListener listener : this.listenerList) {
        listener.selectImageEvent(imageName);
        this.renderMsg(actionCmd + " successful!");
      }
    }

    if (eventSrc == this.load) {
      int returnVal = this.fc.showOpenDialog(this);

      if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = this.fc.getSelectedFile();
        for (ViewListener listener : this.listenerList) {
          // The controller is called as a view listener to load the file.
          listener.loadFileEvent(file.getPath(), file.getName());
        }
      } else {
        this.renderMsg("Image not loaded; User canceled selection.");
      }
    }

    if (eventSrc == this.save) {
      int returnValue = fc.showSaveDialog(this);

      if (returnValue == JFileChooser.APPROVE_OPTION) {
        File file = this.fc.getSelectedFile();
        // The controller is called as a view listener to save the file.
        for (ViewListener listener : this.listenerList) {
          listener.saveFileEvent(file.getPath());
          this.renderMsg(actionCmd + " successful!");
        }
      } else {
        this.renderMsg("Image not saved; User canceled selection.");
      }
    }

    for (JButton button : this.functionButtons) {
      if (eventSrc == button) {
        for (ViewListener listener : this.listenerList) {
          // If an image is not currently loaded, a command cannot be called.
          if (listener.getImageNames().size() == 0) {
            continue;
          }
          listener.commandEvent(actionCmd);
          this.renderMsg(actionCmd + " successful!");
        }
      }
    }

  }


  @Override
  public void refresh(Image selectedImage) {
    // Update image panel.
    BufferedImage imgModel = getBuff(selectedImage);
    JLabel picture = new JLabel(new ImageIcon(imgModel));
    this.imagePanel.removeAll();
    this.imagePanel.add(picture);
    this.imagePanel.updateUI();

    // Ensure any new image (added in the current session, likely by a controller) is registered in
    // the loaded-image selection dropdown.
    for (ViewListener listener : this.listenerList) {
      for (String imgNm : listener.getImageNames()) {
        if (!this.loadedImgs.contains(imgNm)) {
          this.loadedImgs.add(imgNm);
        }
      }
    }
    this.filePanel.updateUI();

    // Update histograms.
    this.histogramsPanel.removeAll();
    this.histograms[0].setImgAndComponent(selectedImage, IPixel.Color.Red);
    this.histograms[1].setImgAndComponent(selectedImage, IPixel.Color.Green);
    this.histograms[2].setImgAndComponent(selectedImage, IPixel.Color.Blue);
    this.histograms[3].setImgAndComponent(selectedImage, null);
    for (Histogram hist : this.histograms) {
      this.histogramsPanel.add(hist);
    }
    this.histogramsPanel.updateUI();
  }

  @Override
  public void addListener(ViewListener viewListener) {
    this.listenerList.add(viewListener);
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

  /**
   * Renders a message to the view's appendable.
   *
   * @param str The message to be appended.
   */
  @Override
  public void renderMsg(String str) {
    this.systemMessages.setText("");
    this.systemMessages.append(str);
  }
}

