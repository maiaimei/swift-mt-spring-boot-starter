package cn.maiaimei.framework.swift.validation.validator;

import com.prowidesoftware.swift.model.field.Field;

public interface FieldValidator<T extends Field> {
    boolean supportsFormat(String format);

    boolean supportsName(String name);

    String validate(T field, String tag, String format, String value);
}
