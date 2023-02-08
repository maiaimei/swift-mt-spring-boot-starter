package cn.maiaimei.framework.swift.validation.validator.formatvalidator;

import cn.maiaimei.framework.swift.model.FieldComponentInfo;
import cn.maiaimei.framework.swift.validation.ValidationError;
import cn.maiaimei.framework.swift.validation.validator.AbstractFormatValidator;
import com.prowidesoftware.swift.model.field.Field;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class IndexTotalFieldValidator extends AbstractFormatValidator {

    private static final String FORMAT = "1!n/1!n";
    private static final String REGEX = "^[1-9]{1}/[1-9]{1}$";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    @Override
    public boolean supportsFormat(Field field, String format) {
        return FORMAT.equals(format);
    }

    @Override
    public String validate(FieldComponentInfo validationInfo, Field field, String label, String value) {
        if (!PATTERN.matcher(value).matches()) {
            return ValidationError.mustMatchFormat(label, FORMAT, value);
        }
        return null;
    }
}
