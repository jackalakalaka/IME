package view;

import java.util.HashMap;

import model.Image;

public class HistogramIntensity extends AHistogram {

  public HistogramIntensity(Image img) {
    super(img);
  }

  @Override
  protected int fillHistogram(Image image, int i, int j) {
     return image.getPixelAt(i, j).getIntensity();
  }

  @Override
  public HashMap<Integer, Integer> getHistogram() {
    return this.counts;
  }
}
