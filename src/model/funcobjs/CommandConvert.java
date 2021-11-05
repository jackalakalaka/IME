package model.funcobjs;

import model.IPixel;
import model.Image;
import model.ImageJPG;
import model.ImagePNG;
import model.ImagePpm;

public class CommandConvert {
  private final Image image;

  public CommandConvert(Image image, String type) {
    IPixel[][] pixels = new IPixel[image.getHeight()][image.getWidth()];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        pixels[i][j] = image.getPixelAt(i, j);
      }
    }
    switch (type) {
      case "ppm":
        this.image = new ImagePpm(255, pixels);
        break;
      case "jpg":
        this.image = new ImageJPG(255, pixels);
        break;
      case "png":
        this.image = new ImagePNG(255, pixels);
        break;
      default:
        throw new IllegalArgumentException(String.format("Img format %1$s is not supported",
                type));
    }

  }

  public Image apply() {
    return this.image;
  }
}
