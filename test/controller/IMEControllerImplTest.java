package controller;

import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import model.IMEModel;
import model.IMEModelImpl;
import view.IMEView;
import view.IMEViewImpl;
import view.InvalidMockAppendable;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the controller.
 */
public class IMEControllerImplTest {
  IMEController test; //Is left undeclared by setUp().
  IMEController emptyReadModel;
  IMEController badAppendableModel;
  IMEModel goodModel;
  IMEView goodView;
  Readable goodRead;
  Readable emptyRead;
  IMEView badView;
  String menu = "Here are the commands for using IME (Image Manipulation & Enhancement).\n" +
          "- To quit type: quit.\n" +
          "- To load an image type: load <image-name> <file-path>.\n" +
          "- To save an image type: save <image-name> <file-name>.\n" +
          "- To change the brightness type: brightness " +
          "<image-name> <integer-change> <new-name>.\n" +
          "- To get a heat map of red in the img type 'red " +
          "<img_former> <img_new>' into the command line.\n" +
          "- To get a heat map of intensity in the img type " +
          "'intensity <img_former> <img_new>' into the command line.\n" +
          "- To flip the img horizontally type 'horizontal  " +
          "<img_former> <img_new>' into the command line.\n" +
          "- To get a heat map of green in the img type 'green  " +
          "<img_former> <img_new>' into the command line.\n" +
          "- To get a heat map of blue in the img type 'blue " +
          "<img_former> <img_new>'into the command line.\n" +
          "- To flip the img vertically type 'vertical <img_former> " +
          "<img_new>' into the command line.\n" +
          "- To get a heat map of the max value in the img type 'value " +
          "<img_former> <img_new>' into the command line.\n" +
          "- To get a heat map of luminosity in the img type 'luma  " +
          "<img_former> <img_new>' into the command line.\n";

  @Before
  public void setUp() {
    this.goodModel = new IMEModelImpl();
    this.goodView = new IMEViewImpl();
    this.goodRead = new StringReader("quit");
    this.emptyRead = new StringReader("");
    this.badView = new IMEViewImpl(new InvalidMockAppendable());
    this.emptyReadModel = new IMEControllerCompact(goodModel, goodView, emptyRead);
    this.badAppendableModel = new IMEControllerCompact(goodModel, badView, goodRead);

  }

  @Test(expected = IllegalStateException.class)
  public void testEmptyReadable() {
    this.emptyReadModel.runIME();
  }

  @Test(expected = IllegalStateException.class)
  public void testBadAppendable() {
    this.badAppendableModel.runIME();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadConstructionModel() {
    this.test = new IMEControllerCompact(null, this.goodView, this.goodRead);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadConstructionView() {
    this.test = new IMEControllerCompact(this.goodModel, null, this.goodRead);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadConstructionReadable() {
    this.test = new IMEControllerCompact(this.goodModel, this.goodView, null);
  }

  @Test(expected = IllegalStateException.class)
  public void testIncompleteInputZero() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("");
    IMEView view = new IMEViewImpl(appendable);
    IMEController test = new IMEControllerCompact(this.goodModel, view, readable);
    test.runIME();
  }

  @Test(expected = IllegalStateException.class)
  public void testIncompleteInputOne() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load ");
    IMEView view = new IMEViewImpl(appendable);
    IMEController test = new IMEControllerCompact(this.goodModel, view, readable);
    test.runIME();
  }

  @Test(expected = IllegalStateException.class)
  public void testIncompleteInputTwo() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load barney ");
    IMEView view = new IMEViewImpl(appendable);
    IMEController test = new IMEControllerCompact(this.goodModel, view, readable);
    test.runIME();
  }

  @Test(expected = IllegalStateException.class)
  public void testIncompleteInputThree() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load barney barney.ppm " +
            "brightness barney 10 ");
    IMEView view = new IMEViewImpl(appendable);
    IMEController test = new IMEControllerCompact(this.goodModel, view, readable);
    test.runIME();
  }

  @Test
  public void testSimpleRun() {
    Appendable appendable = new StringBuilder();
    IMEView view = new IMEViewImpl(appendable);
    IMEController test = new IMEControllerCompact(this.goodModel, view, this.goodRead);
    test.runIME();
    String output = appendable.toString();
    assertEquals(menu +
            "\n" +
            "Please enter a command:\n" +
            "\n" +
            "Thank you for using IME!", output);
  }

  @Test
  public void testLoad() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load barney barney.ppm quit");
    IMEView view = new IMEViewImpl(appendable);
    IMEController test = new IMEControllerCompact(this.goodModel, view, readable);
    test.runIME();
    String output = appendable.toString();
    assertEquals(menu +
            "\n" +
            "Please enter a command:\n" +
            "Please wait...\n" +
            "Please enter a command:\n" +
            "\n" +
            "Thank you for using IME!", output);
  }

  @Test
  public void testSave() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load barney barney.ppm " +
            "save barney barney.ppm quit ");
    IMEView view = new IMEViewImpl(appendable);
    IMEController test = new IMEControllerCompact(this.goodModel, view, readable);
    test.runIME();
    String output = appendable.toString();
    assertEquals(menu +
            "\n" +
            "Please enter a command:\n" +
            "Please wait...\n" +
            "Please enter a command:\n" +
            "Please wait...\n" +
            "Please enter a command:\n" +
            "\n" +
            "Thank you for using IME!", output);
  }

  @Test
  public void testBrightness() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load barney barney.ppm " +
            "brightness barney 20 barney.ppm quit ");
    IMEView view = new IMEViewImpl(appendable);
    IMEController test = new IMEControllerCompact(this.goodModel, view, readable);
    test.runIME();
    String output = appendable.toString();
    assertEquals(menu +
            "\n" +
            "Please enter a command:\n" +
            "Please wait...\n" +
            "Please enter a command:\n" +
            "Please wait...\n" +
            "Please enter a command:\n" +
            "\n" +
            "Thank you for using IME!", output);
  }
}
