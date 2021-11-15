package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;


/**
 * Implementation of an image viewer.
 */
public class IMEViewGUI extends JFrame implements IGUIView, ActionListener {
  private final JButton Red;
  private final JButton Green;
  private final JButton Blue;

  private final JPanel buttonPanel;
  private final JPanel imagePanel;
  private final JPanel histogramPanel;

  private final JPanel filePanel;
  private final JButton load;
  private final JButton save;

  private final JTextField textArea;
  private final List<ViewListener> listenerList;

  public IMEViewGUI() {
    super();
    this.listenerList = new ArrayList<>();
    setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE);

    this.Red = new JButton("Red");
    this.Red.setActionCommand("Red");

    this.Green = new JButton("Green");
    this.Green.setActionCommand("Green");

    this.Blue = new JButton("Blue");
    this.Blue.setActionCommand("Blue");

    this.save = new JButton("Save");
    this.save.setActionCommand("save");

    this.load = new JButton("Load");
    this.load.setActionCommand("load");

    this.textArea = new JTextField(30);

    this.buttonPanel = new JPanel();
    this.buttonPanel.setBackground(Color.GRAY);
    this.buttonPanel.setLayout(new GridLayout(10,1));
    this.buttonPanel.add( this.Red);
    this.buttonPanel.add( this.Green);
    this.buttonPanel.add( this.Blue);

    this.imagePanel = new JPanel();
    this.imagePanel.setBackground(Color.BLACK);
    this.imagePanel.setPreferredSize( new Dimension(1000, 1000));

    this.histogramPanel = new JPanel();
    this.histogramPanel.setBackground(Color.red);

    this.filePanel = new JPanel();
    this.filePanel.setBackground(Color.CYAN);
    this.filePanel.setLayout( new FlowLayout() );
    this.filePanel.add(this.load);
    this.filePanel.add(this.save);


    this.add(this.histogramPanel, BorderLayout.NORTH);
    this.add(this.imagePanel, BorderLayout.CENTER);
    this.add(this.buttonPanel, BorderLayout.WEST);
    this.add(this.filePanel, BorderLayout.SOUTH);

    this.setFocusable(true);
    this.requestFocus();

    this.pack();
  }

  private String getImage() {
    JFileChooser fc = new JFileChooser();
    int result = fc.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
      File file = fc.getSelectedFile();
      return file.getAbsolutePath();
    } else {
      return "";
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    for (ViewListener listener : listenerList) {
      listener.saveDataEvent(e.getActionCommand());
    }
  }

  @Override
  public void addListener(ViewListener viewListener) {

  }

  @Override
  public void requestViewFocus() {

  }

  @Override
  public void showView(boolean show) {
    this.setVisible(show);
  }
}

