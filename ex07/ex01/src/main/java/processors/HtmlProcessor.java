package processors;

import com.google.auto.service.AutoService;
import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

@AutoService(javax.annotation.processing.Processor.class)
public class HtmlProcessor extends AbstractProcessor {

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    for (Element element : roundEnv.getElementsAnnotatedWith(HtmlForm.class)) {
      HtmlForm htmlFormAnnotation = element.getAnnotation(HtmlForm.class);
      String forFileName = htmlFormAnnotation.fileName();
      String formAction = htmlFormAnnotation.action();
      String formMethod = htmlFormAnnotation.method();

      StringBuilder formFields = new StringBuilder();
      for (Element enclosedElement : element.getEnclosedElements()) {
        if (enclosedElement.getKind() == ElementKind.FIELD
            && enclosedElement.getAnnotation(HtmlInput.class) != null) {
          HtmlInput htmlInputAnnotation = enclosedElement.getAnnotation(HtmlInput.class);
          String inputType = htmlInputAnnotation.type();
          String inputName = enclosedElement.getSimpleName().toString();
          String inputPlaceholder = htmlInputAnnotation.placeholder();

          formFields.append(
              "\t<input type=\""
                  + inputType
                  + "\" name=\""
                  + inputName
                  + "\" placeholder=\""
                  + inputPlaceholder
                  + "\">\n");
        }
      }

      String htmlForm =
          "<form action=\""
              + formAction
              + "\" method=\""
              + formMethod
              + "\">\n"
              + formFields
              + "\t<input type=\"submit\" value=\"Send\">\n</form>";

      try {
        Filer filer = processingEnv.getFiler();
        FileObject resource =
            filer.createResource(StandardLocation.CLASS_OUTPUT, "", "user_form.html", element);
        try (Writer writer = resource.openWriter()) {
          writer.write(htmlForm);
        }
      } catch (IOException e) {
        processingEnv
            .getMessager()
            .printMessage(Diagnostic.Kind.ERROR, "Ошибка записи файла: " + e);
      }
    }
    return false;
  }

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    Set<String> annotationTypes = new HashSet<>();
    annotationTypes.add(HtmlForm.class.getName());
    annotationTypes.add(HtmlInput.class.getName());
    return annotationTypes;
  }
}
