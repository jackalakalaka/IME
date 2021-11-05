package factory;

import java.io.File;
import java.util.Objects;

import model.Image;
import model.ImagePpm;

public class ImageFactory {

  public Image createImage(String filePath) {
    Objects.requireNonNull(filePath);
    String[] pathSplit = filePath.split("\\.");
    String fileType = pathSplit[(pathSplit.length-1)];
    File f = new File(filePath);
    if (!f.exists()) {
      throw new IllegalArgumentException("File path given does not exist.");
    }

    switch (fileType) {
      case "ppm":
        return new ImagePpm(filePath);
      default:
        throw new IllegalArgumentException(String.format("Img format %1$s is not supported",
                filePath));
    }
  }

}

