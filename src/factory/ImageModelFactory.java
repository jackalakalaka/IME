package factory;

import java.io.FileNotFoundException;
import java.util.Objects;

import model.iImage;
import model.ImagePpm;

public class ImageModelFactory {
  public iImage createImageModel(String imgFormat, String filePath)
          throws FileNotFoundException {
    Objects.requireNonNull(imgFormat, filePath);

    switch (imgFormat) {
      case "ppm": return new ImagePpm(filePath);
      default: return null;
    }
  }
}
