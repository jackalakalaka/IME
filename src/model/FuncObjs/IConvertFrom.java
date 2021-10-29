package model.FuncObjs;

import java.util.function.Function;

import model.ImageModel;

public interface IConvertFrom extends Function<ImageModel, ImageModel> {

  /**
   * Applies this function to the given argument.
   *
   * @param initModel the function argument
   * @return the function result
   */
  ImageModel apply(ImageModel imageModel);


}
