package renders;

import static java.lang.System.err;

import interfaces.PreProcessor;
import interfaces.Renderer;

public class RendererErrImpl implements Renderer {
  private PreProcessor preProcessor;

  public RendererErrImpl(PreProcessor preProcessor) {
    this.preProcessor = preProcessor;
  }

  @Override
  public void render(String str) {
    err.print(preProcessor.process(str));
  }
}
