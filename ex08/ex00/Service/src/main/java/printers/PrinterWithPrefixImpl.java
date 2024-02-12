package printers;

import interfaces.Printer;
import interfaces.Renderer;

public class PrinterWithPrefixImpl implements Printer {
  private Renderer renderer;
  private String prefix = "";

  public PrinterWithPrefixImpl(Renderer renderer) {
    this.renderer = renderer;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  @Override
  public void print(String str) {
    if (prefix.isEmpty()) {
      renderer.render(str);
    } else {
      renderer.render(prefix + " " + str);
    }
  }
}
