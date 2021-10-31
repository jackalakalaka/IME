package factory;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Objects;

import view.IMEView;
import view.IMEViewImpl;

public class IMEViewFactory {
  public IMEView createIMEView(String style, HashMap<String, String> twoArgCmds,
                               HashMap<String, String> threeArgCmds,
                               HashMap<String, ConvertFactory> conversionCmds)
          throws FileNotFoundException {
    Objects.requireNonNull(style);
    Objects.requireNonNull(conversionCmds);

    switch (style) {
      case "standard":
        return new IMEViewImpl(twoArgCmds, threeArgCmds, conversionCmds);
      default:
        throw new IllegalArgumentException(String.format("View style %1$s is not supported",
                style));
    }
  }
}

