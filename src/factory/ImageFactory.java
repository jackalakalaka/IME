package factory;

import java.io.File;
import java.util.Objects;

import model.Image;
import model.ImageJPG;
import model.ImagePpm;

public class ImageFactory {

  public Image createImage(String filePath) {
    Objects.requireNonNull(filePath);
    String[] pathSplit = filePath.split("\\.");
    String fileType = pathSplit[(pathSplit.length-1)];
    File file = new File(filePath);
    if (!file.exists()) {
      throw new IllegalArgumentException("File path given does not exist.");
    }

    switch (fileType) {
      case "ppm":
        return new ImagePpm(filePath);
      case "jpg":
      case "png":
        return new ImageJPG(filePath);
      default:
        throw new IllegalArgumentException(String.format("Img format %1$s is not supported",
                filePath));
    }
  }

}

