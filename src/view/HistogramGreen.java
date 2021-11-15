package view;

import java.util.HashMap;

import model.IPixel;
import model.Image;

public class HistogramGreen extends AHistogram {

  public HistogramGreen(Image img) {
    super(img);
  }

  @Override
  protected int fillHistogram(Image image, int i, int j) {
    return image.getPixelAt(i, j).getColors().get(IPixel.Color.Green);
  }

  @Override
  public HashMap<Integer, Integer> getHistogram() {
    return this.counts;
  }
}
