package factory;

import java.io.File;
import java.util.Objects;

import model.image.IPixel;
import model.image.Image;
import model.image.ImageBMP;
import model.image.ImageJPG;
import model.image.ImagePNG;
import model.image.ImagePPM;

/**
 * This class allows for the creation of certain types of images given different inputs.
 * The class can create images from file paths or image fields as well as switch between types.
 */
public class ImageFactory {

  /**
   * Simple image creation that checks to see if a file exists before passing to the correct class.
   *
   * @param filePath The file path that supposedly leads to the image.
   * @return A new image from the file.
   */
  public Image createImage(String filePath) {
    System.out.println(filePath);
    Objects.requireNonNull(filePath);
    String[] pathSplit = filePath.split("\\.");
    String fileType = pathSplit[(pathSplit.length - 1)];
    File file = new File(filePath);
    System.out.println(filePath);
    if (!file.exists()) {
      throw new IllegalArgumentException("File path given does not exist.");
    }

    switch (fileType) {
      case "ppm":
        return new ImagePPM(filePath);
      case "jpg":
        return new ImageJPG(filePath);
      case "png":
        return new ImagePNG(filePath);
      case "bmp":
        return new ImageBMP(filePath);
      default:
        throw new IllegalArgumentException(String.format("Img format %1$s is not supported",
                filePath));
    }
  }

  /**
   * Creates a new image with the fields of the image.
   *
   * @param maxValue The maximum value of the image.
   * @param pixels The pixel array that makes up the image.
   * @param type The type of image for formatting.
   * @return A new image from the fields.
   */
  public Image createImage(int maxValue, IPixel[][] pixels, Image.Type type) {
    if (pixels == null || type == null) {
      throw new IllegalArgumentException("Inputs for created images must not be null.");
    }
    switch (type) {
      case PPM:
        return new ImagePPM(maxValue, pixels);
      case PNG:
        return new ImagePNG(maxValue, pixels);
      case JPG:
        return new ImageJPG(maxValue, pixels);
      case BMP:
        return new ImageBMP(maxValue, pixels);
      default:
        throw new IllegalArgumentException("Image type not supported.");
    }
  }

  /**
   * This method creates a new image given one image and a file path.
   * This allows for a change in image type at the point of saving.
   *
   * @param image The old image that is used to create the new one.
   * @param filePath The file path that the image is being saved to.
   * @return A new image in the format specified by the filepath.
   */
  public Image convertImage(Image image, String filePath) {
    String[] pathSplit = filePath.split("\\.");
    String fileType = pathSplit[(pathSplit.length - 1)];
    IPixel[][] pixels = new IPixel[image.getHeight()][image.getWidth()];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        pixels[i][j] = image.getPixelAt(i, j);
      }
    }
    switch (fileType) {
      case "ppm":
        return new ImagePPM(image.getMaxValue(), pixels);
      case "png":
        return new ImagePNG(image.getMaxValue(), pixels);
      case "jpg":
        return new ImageJPG(image.getMaxValue(), pixels);
      case "bmp":
        return new ImageBMP(image.getMaxValue(), pixels);
      default:
        throw new IllegalArgumentException("Image type not supported.");
    }
  }


}

