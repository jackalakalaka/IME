package factory;

import java.io.FileNotFoundException;
import java.util.Objects;

import model.IMEModel;
import model.IMEModelImpl;

/**
 * Creates a new IMEModel implementation object.
 */
public class IMEModelFactory {
  /**
   * Factory function object's primary method.
   */
  public IMEModel createIMEModel(String variant)
          throws FileNotFoundException {
    Objects.requireNonNull(variant);

    // Will be ocnverted to a switch statement if extension is necessary.
    if (variant.equals("standard")) {
      return new IMEModelImpl();
    }
    else {
      throw new IllegalArgumentException(String.format("Img format %1$s is not supported",
              variant));
    }
  }
}
