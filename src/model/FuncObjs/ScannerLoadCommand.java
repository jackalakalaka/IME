package model.FuncObjs;

import java.util.Scanner;

import model.Image;
import model.ImagePpm;

public class ScannerLoadCommand implements IScannerCommands {

  @Override
  public Image apply(Scanner scanner) {
    return new ImagePpm(scanner.next());
  }
}
