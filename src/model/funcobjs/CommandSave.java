package model.funcobjs;

import factory.ImageFactory;
import model.Image;

public class CommandSave {

  public CommandSave(Image image, String filePath){
    String[] pathSplit = filePath.split("\\.");
    String fileType = pathSplit[(pathSplit.length-1)];
    switch (fileType) {
      case "ppm":
        new ImageFactory().convertImage(image, Image.Type.PPM).saveImageToFile(filePath);
        break;
      case "jpg":
        new ImageFactory().convertImage(image, Image.Type.JPG).saveImageToFile(filePath);
        break;
      case "png":
        new ImageFactory().convertImage(image, Image.Type.PNG).saveImageToFile(filePath);
        break;
      default:
        throw new IllegalArgumentException(String.format("Img format %1$s is not supported",
                filePath));
    }
  }



}
