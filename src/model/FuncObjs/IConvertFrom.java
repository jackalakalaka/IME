package model.FuncObjs;

import java.util.function.Function;

import model.iImage;

public interface IConvertFrom extends Function<iImage, iImage> {

  /**
   * Applies this function to the given argument.
   *
   * @param iImage The image that is worked on via apply.
   * @return A new iImage that has been altered form the original.
   */
  iImage apply(iImage iImage);


  /**
   * Returns the signature of the command describing how the command works in the controller.
   *
   * @return String representing the instructions of how to use the command in the controller.
   */
  String giveSignature();
}
