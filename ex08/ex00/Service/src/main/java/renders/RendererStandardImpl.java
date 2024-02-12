package renders;

import static java.lang.System.out;

import interfaces.PreProcessor;
import interfaces.Renderer;

public class RendererStandardImpl implements Renderer {
  private PreProcessor preProcessor;

  public RendererStandardImpl(PreProcessor preProcessor) {
    this.preProcessor = preProcessor;
  }

  @Override
  public void render(String str) {
    out.print(preProcessor.process(str));
  }
}
