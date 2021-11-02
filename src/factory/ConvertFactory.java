//package factory;
//
//import java.util.Objects;
//
//import model.FuncObjs.ConvertByHorizontal;
//import model.FuncObjs.ConvertByHorizontalVertical;
//import model.FuncObjs.ConvertByVertical;
//import model.FuncObjs.CommandsBlue;
//import model.FuncObjs.CommandsGreen;
//import model.FuncObjs.CommandsIntensity;
//import model.FuncObjs.CommandsLuma;
//import model.FuncObjs.CommandsRed;
//import model.FuncObjs.CommandsValue;
//import model.FuncObjs.ICommands;
//
//public class ConvertFactory {
//  String cmdName;
//
//  public ConvertFactory(String cmdName) {
//    Objects.requireNonNull(cmdName);
//    this.cmdName = cmdName;
//  }
//
//  public ICommands createConverter(String newImgName) {
//    Objects.requireNonNull(newImgName);
//
//    switch (this.cmdName) {
//      case "horizontal-flip":
//        return new ConvertByHorizontal(newImgName);
//      case "vertical-flip":
//        return new ConvertByVertical(newImgName);
//      case "flip-both":
//        return new ConvertByHorizontalVertical(newImgName);
//      case "blue-component":
//        return new CommandsBlue(newImgName);
//      case "green-component":
//        return new CommandsGreen(newImgName);
//      case "red-component":
//        return new CommandsRed(newImgName);
//      case "intensity-component":
//        return new CommandsIntensity(newImgName);
//      case "value-component":
//        return new CommandsValue(newImgName);
//      case "luma":
//        return new CommandsLuma(newImgName);
//      default:
//        throw new IllegalArgumentException(String.format("Converter type %1$s is not supported",
//                this.cmdName));
//    }
//  }
//}
