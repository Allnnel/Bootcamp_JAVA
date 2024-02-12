package edu.school21.printer.logic;

import static java.lang.System.out;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class Logic {
  private final String pathBmp;
  private final char black;
  private final char white;

  public Logic(String pathBmp, char black, char white) {
    this.pathBmp = pathBmp;
    this.black = black;
    this.white = white;
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
          char symbolPixel = (pixel == -1) ? white : black;
          out.print(symbolPixel);
        }
        out.println();
      }
    }
  }

}
