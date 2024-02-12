package edu.school21.printer.logic;

import static java.lang.System.out;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class Logic {
  private final String pathBmp;
  private Attribute colorOne;
  private Attribute colorTwo;

  public Logic(String pathBmp, String colorOne, String colorTwo) {
    this.pathBmp = pathBmp;
    this.colorOne = Attribute.BLACK_BACK();
    this.colorTwo = Attribute.WHITE_BACK();
    this.colorOne = resolveColor(colorOne, this.colorOne);
    this.colorTwo = resolveColor(colorTwo, this.colorTwo);
  }

  public void renderImageToConsole() throws IOException {
    URL resourcePath = getClass().getResource(pathBmp);
    if (resourcePath == null) {
      out.println("Resource is not found");
    } else {
      BufferedImage bufferedImage = ImageIO.read(resourcePath);
      int width = bufferedImage.getWidth();
      int height = bufferedImage.getHeight();
      for (int h = 0; h != height; h++) {
        for (int w = 0; w != width; w++) {
          int pixel = bufferedImage.getRGB(w, h);
          Attribute colorPixel = (pixel == -1) ? this.colorOne : this.colorTwo;
          System.out.print(Ansi.colorize(" ", colorPixel));
        }
        out.println();
      }
    }
  }

  private Attribute resolveColor(String colorName, Attribute color) {
    if (colorName.equals("RED")) color = Attribute.RED_BACK();
    else if (colorName.equals("GREEN")) color = Attribute.GREEN_BACK();
    else if (colorName.equals("BLUE")) color = Attribute.BLUE_BACK();
    else if (colorName.equals("WHITE")) color = Attribute.WHITE_BACK();
    else if (colorName.equals("BLACK")) color = Attribute.BLACK_BACK();
    else if (colorName.equals("BRIGHT_RED")) color = Attribute.BRIGHT_RED_BACK();
    else if (colorName.equals("BRIGHT_GREEN")) color = Attribute.BRIGHT_GREEN_BACK();
    else if (colorName.equals("BRIGHT_BLACK")) color = Attribute.BRIGHT_BLACK_BACK();
    else if (colorName.equals("BRIGHT_WHITE")) color = Attribute.BRIGHT_WHITE_BACK();
    else if (colorName.equals("BRIGHT_BLUE")) color = Attribute.BRIGHT_BLUE_BACK();
    else out.println("no color");
    return color;
  }
}
