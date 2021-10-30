package factory;

import java.io.FileNotFoundException;
import java.util.Objects;

import view.ImageView;
import view.ImageViewImpl;

public class ImageViewFactory {
  public ImageView createImageView(String style)
          throws FileNotFoundException {
    Objects.requireNonNull(style);

    switch (style) {
      case "standard": return new ImageViewImpl();
      default: throw new IllegalArgumentException(String.format("View style %1$s is not supported",
              style));
    }
  }
}

