package controller;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;

import model.IMEModel;
import model.IMEModelImpl;
import model.Image;
import view.IMEView;
import view.IMEViewText;
import view.InvalidMockAppendable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test class for the controller implementation.
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
          "- To get a heat map of intensity in the img type 'intensity " +
          "<img_former> <img_new>' into the command line.\n" +
          "- To flip the img horizontally type 'horizontal " +
          "<img_former> <img_new>' into the command line.\n" +
          "- To get the sepia color transformation type 'sepia " +
          "<img_former> <img_new>'into the command line.\n" +
          "- To sharpen an image type 'sharpen " +
          "<img_former> <img_new>'into the command line.\n" +
          "- To get a heat map of green in the img type 'green " +
          "<img_former> <img_new>' into the command line.\n" +
          "- To get a heat map of blue in the img type 'blue " +
          "<img_former> <img_new>'into the command line.\n" +
          "- To blur an image type 'blur " +
          "<img_former> <img_new>'into the command line.\n" +
          "- To flip the img vertically type 'vertical " +
          "<img_former> <img_new>' into the command line.\n" +
          "- To get the greyscale color transform type 'greyscale " +
          "<img-former> <img-new>into the commandline.\n" +
          "- To get a heat map of the max value in the img type 'value " +
          "<img_former> <img_new>' into the command line.\n" +
          "- To get a heat map of luminosity in the img type 'luma " +
          "<img_former> <img_new>' into the command line.\n";

  @Before
  public void setUp() {
    this.goodModel = new IMEModelImpl();
    this.goodView = new IMEViewText();
    this.goodRead = new StringReader("quit");
    this.emptyRead = new StringReader("");
    this.badView = new IMEViewText(new InvalidMockAppendable());
    this.emptyReadModel = new IMEControllerText(goodModel, goodView, emptyRead);
    this.badAppendableModel = new IMEControllerText(goodModel, badView, goodRead);

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
    this.test = new IMEControllerText(null, this.goodView, this.goodRead);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadConstructionView() {
    this.test = new IMEControllerText(this.goodModel, null, this.goodRead);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadConstructionReadable() {
    this.test = new IMEControllerText(this.goodModel, this.goodView, null);
  }

  @Test(expected = IllegalStateException.class)
  public void testIncompleteInputZero() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("");
    IMEView view = new IMEViewText(appendable);
    IMEController test = new IMEControllerText(this.goodModel, view, readable);
    test.runIME();
  }

  @Test(expected = IllegalStateException.class)
  public void testIncompleteInputOne() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load ");
    IMEView view = new IMEViewText(appendable);
    IMEController test = new IMEControllerText(this.goodModel, view, readable);
    test.runIME();
  }

  @Test(expected = IllegalStateException.class)
  public void testIncompleteInputTwo() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load res/onePixelImage.ppm ");
    IMEView view = new IMEViewText(appendable);
    IMEController test = new IMEControllerText(this.goodModel, view, readable);
    test.runIME();
  }

  @Test(expected = IllegalStateException.class)
  public void testIncompleteInputThree() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load barney res/onePixelImage.ppm " +
            "brightness barney 10 ");
    IMEView view = new IMEViewText(appendable);
    IMEController test = new IMEControllerText(this.goodModel, view, readable);
    test.runIME();

    assertTrue(this.goodModel.containsImage("barney"));
    assertEquals(this.goodModel.getImageFromModel("barney").getType(), Image.Type.PPM);
  }

  @Test
  public void testSimpleRun() {
    Appendable appendable = new StringBuilder();
    IMEView view = new IMEViewText(appendable);
    IMEController test = new IMEControllerText(this.goodModel, view, this.goodRead);
    test.runIME();

    String output = appendable.toString();
    assertEquals(menu +
            "\n" +
            "Please enter a command:\n" +
            "\n" +
            "Thank you for using IME!", output);
  }

  @Test
  public void testIncorrectCommandInput() {
    Appendable appendable = new StringBuilder();
    IMEView view = new IMEViewText(appendable);
    StringWriter stringWriter = new StringWriter();
    PrintWriter printWriter = new PrintWriter(stringWriter);
    printWriter.println("command pixel res/onePixelImage.ppm");
    printWriter.println("quit");
    Readable readable = new StringReader(stringWriter.toString());
    IMEController test = new IMEControllerText(this.goodModel, view, readable);
    test.runIME();

    String output = appendable.toString();
    assertEquals(menu +
            "\n" +
            "Please enter a command:\n" +
            "Please wait...\n" +
            "Command not recognized. Please try again.\n" +
            "\n" +
            "Please enter a command:\n" +
            "\n" +
            "Thank you for using IME!", output);
  }

  @Test
  public void testIncorrectFileInput() {
    Appendable appendable = new StringBuilder();
    IMEView view = new IMEViewText(appendable);
    StringWriter stringWriter = new StringWriter();
    PrintWriter printWriter = new PrintWriter(stringWriter);
    printWriter.println("load pixel aFileThatDoesNotExist");
    printWriter.println("quit");
    Readable readable = new StringReader(stringWriter.toString());
    IMEController test = new IMEControllerText(this.goodModel, view, readable);
    test.runIME();

    String output = appendable.toString();
    assertEquals(menu +
            "\n" +
            "Please enter a command:\n" +
            "Please wait...\n" +
            "File name was not correct.\n" +
            "\n" +
            "Please enter a command:\n" +
            "\n" +
            "Thank you for using IME!", output);
  }

  @Test
  public void testIncorrectImageInput() {
    Appendable appendable = new StringBuilder();
    IMEView view = new IMEViewText(appendable);
    StringWriter stringWriter = new StringWriter();
    PrintWriter printWriter = new PrintWriter(stringWriter);
    printWriter.println("load pixel res/onePixelImage.ppm");
    printWriter.println("blue imageNotInModel blue");
    printWriter.println("quit");
    Readable readable = new StringReader(stringWriter.toString());
    IMEController test = new IMEControllerText(this.goodModel, view, readable);
    test.runIME();

    String output = appendable.toString();
    assertEquals(menu +
            "\n" +
            "Please enter a command:\n" +
            "Please wait...\n" +
            "Please enter a command:\n" +
            "Please wait...\n" +
            "Image not found in model. Please try again.\n" +
            "\n" +
            "Please enter a command:\n" +
            "\n" +
            "Thank you for using IME!", output);
  }


  @Test
  public void testLoad() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load pixel res/onePixelImage.ppm quit");
    IMEView view = new IMEViewText(appendable);
    IMEController test = new IMEControllerText(this.goodModel, view, readable);
    test.runIME();

    assertTrue(this.goodModel.containsImage("pixel"));
    assertEquals(this.goodModel.getImageFromModel("pixel").getType(), Image.Type.PPM);
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
    Readable readable = new StringReader("load pixel res/onePixelImage.ppm " +
            "save pixel res/onePixelImage.ppm quit ");
    IMEView view = new IMEViewText(appendable);
    IMEController test = new IMEControllerText(this.goodModel, view, readable);
    test.runIME();

    assertTrue(this.goodModel.containsImage("pixel"));
    assertEquals(this.goodModel.getImageFromModel("pixel").getType(), Image.Type.PPM);
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
    Readable readable = new StringReader("load pixel res/onePixelImage.ppm " +
            "brightness pixel  50 bright quit ");
    IMEView view = new IMEViewText(appendable);
    IMEController test = new IMEControllerText(this.goodModel, view, readable);
    test.runIME();

    assertTrue(this.goodModel.containsImage("bright"));
    assertEquals(this.goodModel.getImageFromModel("bright").getType(), Image.Type.PPM);
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
  public void scriptEntryNotFound() {
    String scriptPath = "iDontExist.txt";
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("-file " + scriptPath);
    IMEView view = new IMEViewText(appendable);
    IMEController test = new IMEControllerText(this.goodModel, view, readable);
    test.runIME();

    String output = appendable.toString();
    assertEquals(menu +
            "\n" +
            "Please enter a command:\n" +
            "The file given was not valid.\n" +
            "Thank you for using IME!", output);
  }

  @Test
  public void scriptEntryInvalidFilename() {
    String scriptPath = "iDontExistAndAmNotInTheRightFormat";
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("-file " + scriptPath);
    IMEView view = new IMEViewText(appendable);
    IMEController test = new IMEControllerText(this.goodModel, view, readable);
    test.runIME();

    String output = appendable.toString();
    assertEquals(menu +
            "\n" +
            "Please enter a command:\n" +
            "The file given was not valid.\n" +
            "Thank you for using IME!", output);
  }

  /**
   * Ensures files, which demonstrate all IME functionality, are created with example script. Then,
   * delete them.
   */
  @Test
  public void scriptEntryDefault() {
    String scriptPath = "script.txt";
    ArrayList<String> filesToCreate = new ArrayList<>(Arrays.asList("res/test/redGoat.ppm",
            "res/test/blueGoat.ppm",
            "res/test/greenGoat.ppm", "res/test/intenseGoat.ppm", "res/test/valuedGoat.jpg",
            "res/test/luminousGoat.jpg", "res/test/brightGoat.jpg", "res/test/darkGoat.jpg",
            "res/test/horizontalGoat.png", "res/test/verticalGoat.png", "res/test/blurryGoat.png",
            "res/test/greyGoat.png", "res/test/sharpGoat.bmp", "res/test/sepiaGoat.bmp",
            "res/test/greenGoat.bmp", "res/test/intenseGoat.bmp"));
    for (String filepath:filesToCreate) {
      assertFalse(new File(filepath).exists());
    }

    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("-file " + scriptPath);
    IMEView view = new IMEViewText(appendable);
    IMEController test = new IMEControllerText(this.goodModel, view, readable);
    test.runIME();

    for (String filepath:filesToCreate) {
      File file = new File(filepath);
      assertTrue(file.exists());
      assertTrue(file.delete());
    }
  }
}
