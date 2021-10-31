package factory;

import java.util.Objects;

import model.FuncObjs.ConvertByHorizontal;
import model.FuncObjs.ConvertByHorizontalVertical;
import model.FuncObjs.ConvertByVertical;
import model.FuncObjs.ConvertFromBlue;
import model.FuncObjs.ConvertFromGreen;
import model.FuncObjs.ConvertFromIntensity;
import model.FuncObjs.ConvertFromLuma;
import model.FuncObjs.ConvertFromRed;
import model.FuncObjs.ConvertFromValue;
import model.FuncObjs.IConvertFrom;

public class ConvertFactory {
  String cmdName;

  public ConvertFactory(String cmdName) {
    Objects.requireNonNull(cmdName);
    this.cmdName = cmdName;
  }

  public IConvertFrom createConverter(String newImgName) {
    Objects.requireNonNull(newImgName);

    switch (this.cmdName) {
      case "horizontal-flip":
        return new ConvertByHorizontal(newImgName);
      case "vertical-flip":
        return new ConvertByVertical(newImgName);
      case "flip-both":
        return new ConvertByHorizontalVertical(newImgName);
      case "blue-component":
        return new ConvertFromBlue(newImgName);
      case "green-component":
        return new ConvertFromGreen(newImgName);
      case "red-component":
        return new ConvertFromRed(newImgName);
      case "intensity-component":
        return new ConvertFromIntensity(newImgName);
      case "value-component":
        return new ConvertFromValue(newImgName);
      case "luma":
        return new ConvertFromLuma(newImgName);
      default:
        throw new IllegalArgumentException(String.format("Converter type %1$s is not supported",
                this.cmdName));
    }
  }
}
