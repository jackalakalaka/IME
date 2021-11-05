package factory;

import java.io.File;
import java.util.Objects;

import model.IPixel;
import model.Image;
import model.ImageJPG;
import model.ImagePNG;
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
        return new ImageJPG(filePath);
      case "png":
        return new ImagePNG(filePath);
      default:
        throw new IllegalArgumentException(String.format("Img format %1$s is not supported",
                filePath));
    }
  }

  public Image createImage(int maxValue, IPixel[][] pixels, Image.Type type){
    if (pixels == null || type == null){
      throw new IllegalArgumentException("Inputs for created images must not be null.");
    }
    switch(type) {
      case PPM:
        return new ImagePpm(maxValue, pixels);
      case PNG:
        return new ImagePNG(maxValue, pixels);
      case JPG:
        return new ImageJPG(maxValue, pixels);
      default:
        throw new IllegalArgumentException("Image type not supported.");
    }
  }

  public Image convertImage(Image image, Image.Type type) {
    IPixel[][] pixels = new IPixel[image.getHeight()][image.getWidth()];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        pixels[i][j] = image.getPixelAt(i, j);
      }
    }
    switch(type) {
      case PPM:
        return new ImagePpm(image.getMaxValue(), pixels);
      case PNG:
        return new ImagePNG(image.getMaxValue(), pixels);
      case JPG:
        return new ImageJPG(image.getMaxValue(), pixels);
      default:
        throw new IllegalArgumentException("Image type not supported.");
    }
  }


}

