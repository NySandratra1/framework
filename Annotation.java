package etu2018.framework.annotation;
import java.lang.annotation.*;
import java.lang.reflect.*;
@Retention (RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Annotation{
    String url();
}
