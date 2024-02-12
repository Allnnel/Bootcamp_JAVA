package printers;

import interfaces.Printer;
import interfaces.Renderer;
import java.time.LocalDateTime;

public class PrinterWithDateTimeImpl implements Printer {
  private Renderer renderer;
  private LocalDateTime dateTime;

  public PrinterWithDateTimeImpl(Renderer renderer) {
    this.renderer = renderer;
  }

  public void setDateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
  }

  @Override
  public void print(String str) {
    if (dateTime == null) {
      renderer.render(str);
    } else {
      renderer.render(dateTime + " " + str);
    }
  }
}
