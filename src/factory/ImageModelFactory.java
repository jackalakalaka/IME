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
<<<<<<< HEAD
      case "ppm": return new ImagePpm(filePath);
      default: return null;
=======
      case "ppm": return new ImageModelPpm(filePath);
      default: throw new IllegalArgumentException(String.format("Img format %1$s is not supported",
              imgFormat));
>>>>>>> 266e5074b2ed59cca12d59214ba9238efbe623e9
    }
  }
}
