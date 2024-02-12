package processors;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface HtmlForm {
  String fileName();

  String action();

  String method() default "POST";
}
