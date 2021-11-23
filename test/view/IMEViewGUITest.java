package view;

import org.junit.Test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import controller.IMEControllerGUI;
import controller.IMEControllerGuiLogging;
import model.IMEModel;
import model.IMEModelImpl;
import model.image.AbstractImage;
import model.image.ImageJPG;
import static org.junit.Assert.assertEquals;

/**
 * Test class for a GUI-based view implementation. Tests view's event listening functionality as
 * well as those events' specific effects.
 */
public class IMEViewGUITest {
  private final ActionListener view1;

  private final JButton red;
  private final JButton sharpen;
  private final JButton horizontal;
  private final JButton load1;
  private final JButton save1;
  private final JComboBox<String> imageSelection1;
  private final IMEViewGUI customView1;
  private final IMEControllerGuiLogging ctrlr1Logged;

  /**
   * Initialize test variables.
   */
  public IMEViewGUITest() {
    this.view1 = new IMEViewGUI();

    java.util.List<JButton> functionButtons1 = new ArrayList<>();
    this.red = new JButton("Red");
    this.red.setActionCommand("red");
    functionButtons1.add(this.red);
    this.sharpen = new JButton("Sharpen");
    this.sharpen.setActionCommand("sharpen");
    functionButtons1.add(this.sharpen);
    this.horizontal = new JButton("Horizontal");
    this.horizontal.setActionCommand("horizontal");
    functionButtons1.add(this.horizontal);
    AbstractImage img1 = new ImageJPG("./res/goat.jpg");
    IMEModel model1 = new IMEModelImpl();
    model1.addImage("goatJpgImg", img1);
    java.util.List<ViewListener> listenerList1 = new ArrayList<>();
    Vector<String> loadedImgs1 = new Vector<>();
    loadedImgs1.add("goatJpgImg");
    this.load1 = new JButton("Load");
    this.save1 = new JButton("Save");
    JFileChooser fc1 = new JFileChooser();
    this.imageSelection1 = new JComboBox<>(loadedImgs1);
    this.customView1 = new IMEViewGUI(listenerList1, loadedImgs1, functionButtons1,
            this.load1, this.save1, fc1, this.imageSelection1);
    IMEControllerGUI ctrlrGui1 = new IMEControllerGUI(model1, this.customView1);
    this.ctrlr1Logged = new IMEControllerGuiLogging(ctrlrGui1, model1, loadedImgs1);
    this.customView1.addListener((ViewListener) this.ctrlr1Logged);
  }

  /**
   * Invalid null input to method actionPerformed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void actionPerformedInvalidNull() {
    this.view1.actionPerformed(null);
  }

  /**
   * imageSelection event input to method actionPerformed.
   */
  @Test
  public void actionPerformedImageSelection() {
    ActionEvent e = new ActionEvent(this.imageSelection1, 0, "Image selection");
    this.customView1.actionPerformed(e);
    assertEquals("Success selecting image.", this.ctrlr1Logged.getLogs());
  }

  /**
   * load and save event input events to method actionPerformed. Image 'goat.jpg' to load can be
   * found in directory res/.
   */
  @Test
  public void actionPerformedLoadSave() {
    ActionEvent eLoad = new ActionEvent(this.load1, 0, "Load");
    this.customView1.actionPerformed(eLoad);
    String loadLogs = this.ctrlr1Logged.getLogs();
    assertEquals("Success loading image 'goat.jpg'.", loadLogs);

    ActionEvent eSave = new ActionEvent(this.save1, 0, "Save");
    this.customView1.actionPerformed(eSave);
    assertEquals(loadLogs + "Success saving image.", this.ctrlr1Logged.getLogs());
  }

  /**
   * Image manipulation event inputs to method actionPerformed. Image 'goat.jpg' to load can be
   * found in directory res/.
   */
  @Test
  public void actionPerformedManip() {
    // Load an image to operate on so that the image is registered in the view listener.
    ActionEvent eLoad = new ActionEvent(this.load1, 0, "Load");
    this.customView1.actionPerformed(eLoad);
    String logs1 = this.ctrlr1Logged.getLogs();

    ActionEvent e1 = new ActionEvent(this.red, 0, "red");
    this.customView1.actionPerformed(e1);
    String logs2 = this.ctrlr1Logged.getLogs();
    assertEquals(logs1 + "Success applying command 'red'.", logs2);

    ActionEvent e2 = new ActionEvent(this.sharpen, 0, "sharpen");
    this.customView1.actionPerformed(e2);
    String logs3 = this.ctrlr1Logged.getLogs();
    assertEquals(logs2 + "Success applying command 'sharpen'.", logs3);

    ActionEvent e3 = new ActionEvent(this.horizontal, 0, "horizontal");
    this.customView1.actionPerformed(e3);
    assertEquals(logs3 + "Success applying command 'horizontal'.",
            this.ctrlr1Logged.getLogs());
  }

}