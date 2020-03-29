package org.example.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.example.annotation.JsonEnum;
import org.example.constant.IEnum;

import java.io.IOException;
import java.lang.reflect.Field;

@Slf4j
public class JsonEnumSerializer extends StdSerializer<Integer> {

    public JsonEnumSerializer() {
        this(Integer.class);
    }

    protected JsonEnumSerializer(Class<Integer> t) {
        super(t);
    }

    protected JsonEnumSerializer(JavaType type) {
        super(type);
    }

    protected JsonEnumSerializer(Class<?> t, boolean dummy) {
        super(t, dummy);
    }

    protected JsonEnumSerializer(StdSerializer<?> src) {
        super(src);
    }

    @Override
    public void serialize(Integer value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeNumber(value);
        JsonStreamContext outputContext = gen.getOutputContext();
        String currentName = outputContext.getCurrentName();
        String enumName = currentName + "Desc";
        String enumValue = null;
        try {
            Field field = outputContext.getCurrentValue().getClass().getDeclaredField(currentName);
            JsonEnum jsonEnum = field.getAnnotation(JsonEnum.class);
            if (jsonEnum != null) {
                String filedName = jsonEnum.filedName();
                if (StringUtils.isNotBlank(filedName)) {
                    enumName = filedName;
                }
                if (jsonEnum.value() != null) {
                    Class<?> enumClass = jsonEnum.value();
                    Object[] enumConstants = enumClass.getEnumConstants();
                    for (Object enumConstant : enumConstants) {
                        IEnum iEnum = (IEnum) enumConstant;
                        if (value.equals(iEnum.getCode())) {
                            enumValue = iEnum.getDesc();
                            break;
                        }
                    }
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            log.warn(e.getMessage(), e);
        }
        gen.writeFieldName(enumName);
        gen.writeString(enumValue);
    }
}
