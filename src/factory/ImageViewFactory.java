package factory;

import java.io.FileNotFoundException;
import java.util.Objects;

import model.ImageModel;
import view.ImageView;
import view.ImageViewImpl;

public class ImageViewFactory {
  public ImageView createImageView(String style, ImageModel model)
          throws FileNotFoundException {
    Objects.requireNonNull(style);
    Objects.requireNonNull(model);

    switch (style) {
      case "standard": return new ImageViewImpl(model);
      default: throw new IllegalArgumentException(String.format("View style %1$s is not supported",
              style));
    }
  }
}

