package cn.maiaimei.example.validation.model;

import lombok.Data;

import java.util.List;

@Data
public class MT798ValidationConfig {
    private String subMessageType;
    private List<FieldConfig> fields;
}
