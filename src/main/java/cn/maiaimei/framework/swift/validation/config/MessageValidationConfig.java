package cn.maiaimei.framework.swift.validation.config;

import lombok.Data;

import java.util.List;

@Data
public class MessageValidationConfig {
    private String b2c;
    private String messageType;
    private List<FieldInfo> fields;
    private List<SequenceInfo> sequences;
    private List<RuleInfo> rules;
}