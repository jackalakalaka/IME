package model.funcobjs;

import java.util.function.Function;

import model.image.Image;

/**
 * An interface for command function objects. Can take in a subclass-decided amount of arguments.
 */
public interface ICommands extends Function<Image, Image> {

  /**
   * Applies this function to the given argument.
   *
   * @param image The image that is worked on via apply.
   * @return A new iImage that has been altered form the original.
   */
  Image apply(Image image);


  /**
   * Returns the signature of the command describing how the command works in the controller.
   *
   * @return String representing the instructions of how to use the command in the controller.
   */
  String giveSignature();
}
