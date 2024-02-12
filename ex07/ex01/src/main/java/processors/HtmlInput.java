package processors;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface HtmlInput {
  String type();

  String name();

  String placeholder();
}
