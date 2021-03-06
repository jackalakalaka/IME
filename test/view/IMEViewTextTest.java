package view;

import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.funcobjs.CommandsBlue;
import model.funcobjs.CommandsGreen;
import model.funcobjs.CommandsIntensity;
import model.funcobjs.CommandsLuma;
import model.funcobjs.CommandsRed;
import model.funcobjs.CommandsValue;
import model.funcobjs.ConvertByHorizontal;
import model.funcobjs.ConvertByVertical;
import model.funcobjs.ICommands;

import static org.junit.Assert.assertEquals;

/**
 * Tests the view, which takes in or has a default list of commands that give the menu. The view
 * also allows system messaging whose string appends are tested.
 */
public class IMEViewTextTest {
  private static final List<ICommands> mtCommands = new ArrayList<>();
  private final List<ICommands> allCommands;
  private static final Appendable invalidMockAppendable = new InvalidMockAppendable();
  private final Appendable appendable1 = new StringWriter();
  private final Appendable appendable2 = new StringWriter();
  private final IMEViewText invalidAppendableView = new IMEViewText(invalidMockAppendable);
  private final IMEViewText view1 = new IMEViewText(appendable1);
  private final IMEViewText view2 = new IMEViewText(appendable2);

  /**
   * Initialize test variables.
   */
  public IMEViewTextTest() {
    HashMap<String, ICommands> commandsHashmap = new HashMap<>();
    commandsHashmap.put("horizontal", new ConvertByHorizontal());
    commandsHashmap.put("vertical", new ConvertByVertical());
    commandsHashmap.put("blue", new CommandsBlue());
    commandsHashmap.put("green", new CommandsGreen());
    commandsHashmap.put("red", new CommandsRed());
    commandsHashmap.put("intensity", new CommandsIntensity());
    commandsHashmap.put("value", new CommandsValue());
    commandsHashmap.put("luma", new CommandsLuma());
    this.allCommands = new ArrayList<>(commandsHashmap.values());
  }

  /**
   * Test invalid null appendable input to 1-arg constructor.
   */
  @Test(expected = NullPointerException.class)
  public void constructor1ArgAppendableNull() throws IOException {
    IMEViewText invalidView = new IMEViewText(null);
  }

  /**
   * Test invalid null appendable to method printMenu of IMEViewText class.
   */
  @Test(expected = NullPointerException.class)
  public void printMenuNullAppendable() throws IOException {
    this.view1.printMenu(null);
  }

  /**
   * Test method printMenu of IMEViewText class when appendable cannot be appended to.
   *
   * @throws IOException when the appendable cannot be used
   */
  @Test(expected = IllegalStateException.class)
  public void printMenuInvalidAppendable() throws IOException {
    this.invalidAppendableView.printMenu(this.allCommands);
  }

  /**
   * Test invalid null string to method renderMsg of IMEViewText class.
   */
  @Test(expected = NullPointerException.class)
  public void renderMsgNullStr() throws IllegalStateException {
    this.view1.renderMsg(null);
  }

  /**
   * Test method renderMsg of IMEViewText class when appendable cannot be appended to.
   *
   * @throws IOException when the appendable cannot be used
   */
  @Test(expected = IllegalStateException.class)
  public void renderMsgInvalidAppendable() throws IOException {
    this.invalidAppendableView.renderMsg("I'm just a message :3");
  }

  /**
   * Test default behavior of method printMenu of IMEViewText class.
   *
   * @throws IOException when the appendable cannot be used
   */
  @Test
  public void printMenu() throws IOException {
    String menuPromptStr = "Here are the commands for using IME (Image Manipulation & " +
            "Enhancement).\n" +
            "- To quit type: quit.\n" +
            "- To load an image type: load <image-name> <file-path>.\n" +
            "- To save an image type: save <image-name> <file-name>.\n" +
            "- To change the brightness type: brightness <image-name> " +
            "<integer-change> <new-name>.\n";
    this.view1.printMenu(mtCommands);
    assertEquals(menuPromptStr, this.appendable1.toString());

    String fullMenuStr = menuPromptStr +
            "- To get a heat map of red in the img type 'red <img_former> <img_new>' into the" +
            " command line.\n" +
            "- To get a heat map of intensity in the img type 'intensity <img_former> <img_new>'" +
            " into the command line.\n" +
            "- To flip the img horizontally type 'horizontal <img_former> <img_new>' into the" +
            " command line.\n" +
            "- To get a heat map of green in the img type 'green <img_former> <img_new>'" +
            " into the command line.\n" +
            "- To get a heat map of blue in the img type 'blue <img_former> <img_new>'into the " +
            "command line.\n" +
            "- To flip the img vertically type 'vertical <img_former> <img_new>' into the " +
            "command line.\n" +
            "- To get a heat map of the max value in the img type 'value <img_former> <img_new>'" +
            " into the command line.\n" +
            "- To get a heat map of luminosity in the img type 'luma <img_former> <img_new>'" +
            " into the command line.\n";
    this.view2.printMenu(this.allCommands);
    assertEquals(fullMenuStr, this.appendable2.toString());
  }

  /**
   * Test default behavior of method renderMsg of IMEViewText class.
   *
   * @throws IOException when the appendable cannot be used
   */
  @Test
  public void renderMsg() throws IOException {
    this.view1.renderMsg("");
    assertEquals("", this.appendable1.toString());
    this.view1.renderMsg("I'm just a message :3");
    assertEquals("I'm just a message :3", this.appendable1.toString());
  }
}