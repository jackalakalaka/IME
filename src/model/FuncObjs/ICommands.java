package model.FuncObjs;

import java.util.function.Function;

import model.Image;

public interface ICommands extends Function<Image, Image> {

  /**
   * Applies this function to the given argument.
   *
   * @param Image The image that is worked on via apply.
   * @return A new iImage that has been altered form the original.
   */
  Image apply(Image Image);


  /**
   * Returns the signature of the command describing how the command works in the controller.
   *
   * @return String representing the instructions of how to use the command in the controller.
   */
  String giveSignature();
}
