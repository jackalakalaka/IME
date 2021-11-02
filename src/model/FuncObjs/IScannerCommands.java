package model.FuncObjs;

import java.util.Scanner;
import java.util.function.Function;

import model.Image;

public interface IScannerCommands extends Function<Scanner, Image> {

  /**
   * Applies this function to the given argument.
   *
   * @param scanner The scanner that provides the info for the function object.
   * @return A new iImage that has been altered form the original.
   */
  Image apply(Scanner scanner);


}
