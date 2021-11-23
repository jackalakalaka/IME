package controller;

import org.junit.Test;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import model.IMEModel;
import model.IMEModelImpl;
import model.image.AbstractImage;
import model.image.ImageJPG;
import view.IMEGUIView;
import view.IMEViewGUI;
import view.ViewListener;

import static org.junit.Assert.assertEquals;

/**
 * Tests a GUI controller implementation by directly calling public ViewListener methods in the way
 * that a view implementation would.
 */
public class IMEControllerGUITest {
  private final IMEGUIView view1;

  private final IMEModel model1;
  private final JComboBox<String> imageSelection1;
  private final IMEControllerGuiLogging ctrlr1Logged;

  /**
   * Initialize test variables.
   */
  public IMEControllerGUITest() {
    this.view1 = new IMEViewGUI();

    List<JButton> functionButtons1 = new ArrayList<>();
    JButton red = new JButton("Red");
    red.setActionCommand("red");
    functionButtons1.add(red);
    JButton sharpen = new JButton("Sharpen");
    sharpen.setActionCommand("sharpen");
    functionButtons1.add(sharpen);
    JButton horizontal = new JButton("Horizontal");
    horizontal.setActionCommand("horizontal");
    functionButtons1.add(horizontal);
    AbstractImage img1 = new ImageJPG("./res/goat.jpg");
    this.model1 = new IMEModelImpl();
    this.model1.addImage("goatJpgImg", img1);
    List<ViewListener> listenerList1 = new ArrayList<>();
    Vector<String> loadedImgs1 = new Vector<>();
    loadedImgs1.add("goatJpgImg");
    JButton load1 = new JButton("Load");
    JButton save1 = new JButton("Save");
    JFileChooser fc1 = new JFileChooser();
    this.imageSelection1 = new JComboBox<>(loadedImgs1);
    IMEViewGUI customView1 = new IMEViewGUI(listenerList1, loadedImgs1, functionButtons1,
            load1, save1, fc1, this.imageSelection1);
    IMEControllerGUI ctrlrGui1 = new IMEControllerGUI(this.model1, customView1);
    this.ctrlr1Logged = new IMEControllerGuiLogging(ctrlrGui1, this.model1, loadedImgs1);
    customView1.addListener((ViewListener) this.ctrlr1Logged);
  }

  /**
   * Invalid null model input to constructor.
   */
  @Test(expected = NullPointerException.class)
  public void testBadConstructionModel() {
    IMEController ctrlr = new IMEControllerGUI(null, this.view1);
  }

  /**
   * Invalid null view input to constructor.
   */
  @Test(expected = NullPointerException.class)
  public void testBadConstructionView() {
    IMEController ctrlr = new IMEControllerGUI(this.model1, null);
  }

  /**
   * Test load and save handlers.
   */
  @Test
  public void loadAndSave() {
    this.ctrlr1Logged.loadFileEvent("./res/goat.jpg", "goat.jpg");
    String loadLogs = this.ctrlr1Logged.getLogs();
    assertEquals("Success loading image 'goat.jpg'.", loadLogs);

    this.ctrlr1Logged.saveFileEvent("./res/goat.png");
    assertEquals(loadLogs + "Success saving image.", this.ctrlr1Logged.getLogs());
  }

  /**
   * Test image selection handler.
   */
  @Test
  public void selectImageEvent() {
    ActionEvent e = new ActionEvent(this.imageSelection1, 0, "Image selection");
    this.ctrlr1Logged.selectImageEvent("goatJpgImg");
    assertEquals("Success selecting image.", this.ctrlr1Logged.getLogs());
  }

  /**
   * Method getImageNames: does not include an image simply added to the model or the view's loaded
   * images but only those registered as loaded by the controller / view listener.
   */
  @Test
  public void getImageNames() {
    this.ctrlr1Logged.loadFileEvent("./res/goat.jpg", "goat.jpg");

    List<String> images = new ArrayList<>();
    images.add("goat.jpg");
    assertEquals(images, this.ctrlr1Logged.getImageNames());
  }

  /**
   * Test multiple inputs to general commands handler.
   */
  @Test
  public void commandEvent() {
    // Load an image to operate on so that the image is registered in the view listener.
    this.ctrlr1Logged.loadFileEvent("./res/goat.jpg", "goat.jpg");
    String logs1 = this.ctrlr1Logged.getLogs();

    this.ctrlr1Logged.commandEvent("red");
    String logs2 = this.ctrlr1Logged.getLogs();
    assertEquals(logs1 + "Success applying command 'red'.", logs2);

    this.ctrlr1Logged.commandEvent("sharpen");
    String logs3 = this.ctrlr1Logged.getLogs();
    assertEquals(logs2 + "Success applying command 'sharpen'.", logs3);

    this.ctrlr1Logged.commandEvent("horizontal");
    assertEquals(logs3 + "Success applying command 'horizontal'.",
            this.ctrlr1Logged.getLogs());
  }
}