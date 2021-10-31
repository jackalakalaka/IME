package factory;

import java.io.FileNotFoundException;
import java.util.Objects;

import model.IMEModel;
import model.IMEModelImpl;

public class IMEModelFactory {
  public IMEModel createIMEModel(String variant)
          throws FileNotFoundException {
    Objects.requireNonNull(variant);

    switch (variant) {
      case "standard": return new IMEModelImpl();
      default: throw new IllegalArgumentException(String.format("Img format %1$s is not supported",
              variant));
    }
  }
}
