package model;

import java.util.HashMap;
import java.util.Objects;

import model.funcobjs.ICommands;

public abstract class AbstractImage implements Image {
  protected IPixel[][] pixelArray;
  protected int width;
  protected int height;
  protected int maxValue;
  protected Type type;

  AbstractImage(String filePath) {
    this.getImageFromFile(filePath);
  }

  protected abstract void getImageFromFile(String filePath);

  /**
   * 2-arg constructor for making a new ImageModelImpl.
   *
   * @param maxValue   The maximum value carried over from the original model.
   * @param pixelArray An array of pixels for the new model.
   */
  public AbstractImage(int maxValue, IPixel[][] pixelArray) {
    this.pixelArray = Objects.requireNonNull(pixelArray);
    this.height = pixelArray.length;
    this.width = pixelArray[0].length;
    this.maxValue = maxValue;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getMaxValue() {
    return this.maxValue;
  }

  @Override
  public IPixel getPixelAt(int row, int col) {
    HashMap<IPixel.Color, Integer> colors = this.pixelArray[row][col].getColors();
    return new Pixel(this.maxValue, colors.get(IPixel.Color.Red)
            , colors.get(IPixel.Color.Green), colors.get(IPixel.Color.Blue));
  }

  @Override
  public Image convertToViz(ICommands cmd) {
    Objects.requireNonNull(cmd);
    return cmd.apply(this);
  }

  @Override
  public Image changeBrightness(int change) {
    Pixel[][] brighterModel = new Pixel[this.height][this.width];
    for (int row = 0; row < height; row++) {
      for (int column = 0; column < width; column++) {
        IPixel oldPixel = this.getPixelAt(row, column);
        brighterModel[row][column] = changePixelBrightness(oldPixel, change);
      }
    }
    return new ImagePpm(this.maxValue, brighterModel);
  }

  /**
   * Helper for {@link #changeBrightness(int)} to make brighter pixels.
   *
   * @param oldPixel The original pixel.
   * @param change   The brightness change to be implemented.
   * @return A new brighter or darker pixel.
   */
  private Pixel changePixelBrightness(IPixel oldPixel, int change) {
    Objects.requireNonNull(oldPixel);

    HashMap<Pixel.Color, Integer> oldPixelColors = oldPixel.getColors();
    int red = oldPixelColors.get(Pixel.Color.Red);
    int green = oldPixelColors.get(Pixel.Color.Green);
    int blue = oldPixelColors.get(Pixel.Color.Blue);
    return new Pixel(this.maxValue,
            checkBrightness(red + change), checkBrightness(green + change),
            checkBrightness(blue + change));
  }

  /**
   * Helper for {@link #changePixelBrightness(IPixel, int)}.
   *
   * @param colorValue The desired color value after brightening or dimming.
   * @return The acceptable value or max/min.
   */
  private int checkBrightness(int colorValue) {
    if (colorValue < 0) {
      return 0;
    }
    return Math.min(colorValue, this.maxValue);
  }

  @Override
  public abstract void saveImageToFile(String filepath) throws IllegalStateException;

}
