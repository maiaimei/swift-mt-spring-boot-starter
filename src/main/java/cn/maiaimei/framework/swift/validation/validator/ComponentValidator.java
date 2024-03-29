package cn.maiaimei.framework.swift.validation.validator;

import cn.maiaimei.framework.swift.model.mt.config.ComponentInfo;
import cn.maiaimei.framework.swift.model.mt.config.FieldComponentInfo;
import cn.maiaimei.framework.swift.model.mt.config.FieldInfo;
import cn.maiaimei.framework.swift.validation.ValidationResult;
import com.prowidesoftware.swift.model.field.Field;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class ComponentValidator implements FieldValidatorHandler {

    @Autowired private FieldValidatorChain fieldValidatorChain;

    @Override
    public void handleValidation(
            ValidationResult result,
            FieldComponentInfo fieldComponentInfo,
            Field field,
            String label,
            String value) {
        if (!FieldInfo.class.isAssignableFrom(fieldComponentInfo.getClass())) {
            return;
        }
        FieldInfo fieldInfo = (FieldInfo) fieldComponentInfo;
        List<ComponentInfo> componentInfos = fieldInfo.getComponents();
        if (StringUtils.isBlank(value) || CollectionUtils.isEmpty(componentInfos)) {
            return;
        }
        ValidationResult res = ValidationResult.newInstance();
        List<String> components = field.getComponents();
        List<String> componentLabels = field.getComponentLabels();
        for (ComponentInfo componentInfo : componentInfos) {
            if (!CollectionUtils.isEmpty(res.getErrorMessages())) {
                break;
            }
            if (componentInfo.getIndex() != null) {
                int index = componentInfo.getIndex() - 1;
                String componentLabel =
                        StringUtils.isNotBlank(componentInfo.getLabel())
                                ? componentInfo.getLabel()
                                : componentLabels.get(index);
                String componentValue = components.get(index);
                fieldValidatorChain.handleValidation(
                        res, componentInfo, field, componentLabel, componentValue);
            } else if (componentInfo.getStartIndex() != null
                    && componentInfo.getEndIndex() != null) {
                for (int i = componentInfo.getStartIndex(); i <= componentInfo.getEndIndex(); i++) {
                    int index = i - 1;
                    String componentLabel = componentLabels.get(index);
                    String componentValue = components.get(index);
                    fieldValidatorChain.handleValidation(
                            res, componentInfo, field, componentLabel, componentValue);
                }
            } else {
                result.addErrorMessage(
                        label
                                + " component config error, either index or start-index and end-index must be present");
            }
        }
        if (!CollectionUtils.isEmpty(res.getErrorMessages())) {
            String errorMessage = String.join(", ", res.getErrorMessages());
            result.addErrorMessage(
                    label + StringUtils.SPACE + errorMessage + ", field value is " + value);
        }
    }

    @Override
    public FieldValidatorHandler getNextValidationHandler() {
        return null;
    }
}
