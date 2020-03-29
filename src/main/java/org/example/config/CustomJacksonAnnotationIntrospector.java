package org.example.config;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import org.example.annotation.JsonEnum;
import org.example.serializer.JsonEnumSerializer;


public class CustomJacksonAnnotationIntrospector extends JacksonAnnotationIntrospector {


    @Override
    public Object findSerializer(Annotated a) {
        JsonEnum annotation = a.getAnnotation(JsonEnum.class);
        if (annotation != null) {
            return JsonEnumSerializer.class;
        }
        return super.findSerializer(a);
    }
}
