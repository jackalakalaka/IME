package factory;

import java.io.FileNotFoundException;
import java.util.Objects;

import model.ImageModel;
import model.ImageModelPpm;

public class ImageModelFactory {
  public ImageModel createImageModel(String imgFormat, String filePath)
          throws FileNotFoundException {
    Objects.requireNonNull(imgFormat, filePath);

    switch (imgFormat) {
      case "ppm": return new ImageModelPpm(filePath);
      default: return null;
    }
  }
}
