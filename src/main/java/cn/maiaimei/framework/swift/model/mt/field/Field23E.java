package cn.maiaimei.framework.swift.model.mt.field;

import cn.maiaimei.framework.swift.annotation.Component;
import lombok.Data;

@Data
public class Field23E {
    private String value;
    @Component(index = 1)
    private String method;
    @Component(index = 2)
    private String additionalInformation;
}