//package factory;
//
//import java.io.FileNotFoundException;
//import java.util.Objects;
//
//import model.Image;
//import model.ImagePpm;
//import model.iPixel;
//
//public class ImageFactory {
//  public Image createImage(String imgFormat, String name, String filePath)
//          throws FileNotFoundException {
//    Objects.requireNonNull(imgFormat);
//    Objects.requireNonNull(name);
//    Objects.requireNonNull(filePath);
//
//    switch (imgFormat) {
//      case "ppm":
//        return new ImagePpm(name, filePath);
//      default:
//        throw new IllegalArgumentException(String.format("Img format %1$s is not supported",
//                imgFormat));
//    }
//  }
//
//  public Image createImage(String imgFormat, String name, iPixel[][] pixelArray, int maxValue)
//          throws FileNotFoundException {
//    Objects.requireNonNull(imgFormat);
//    Objects.requireNonNull(pixelArray);
//    Objects.requireNonNull(name);
//
//    switch (imgFormat) {
//      case "ppm":
//        return new ImagePpm(name, maxValue, pixelArray);
//      default:
//        throw new IllegalArgumentException(String.format(
//                "Img format %1$s is not supported", imgFormat));
//    }
//  }
//}
//
