package model.FuncObjs;

import java.io.FileNotFoundException;
import java.util.function.Function;

import model.Image;


public interface IFileFunctions {

  Image apply(String filePath) throws FileNotFoundException;

}
