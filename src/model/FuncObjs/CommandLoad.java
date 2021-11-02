package model.FuncObjs;

import java.io.FileNotFoundException;

import model.Image;
import model.ImagePpm;

public class CommandLoad implements IFileFunctions {

  @Override
  public Image apply(String filePath) throws FileNotFoundException {
    return new ImagePpm(filePath);
  }

}
