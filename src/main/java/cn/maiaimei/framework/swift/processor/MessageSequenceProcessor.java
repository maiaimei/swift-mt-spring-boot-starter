package cn.maiaimei.framework.swift.processor;

import com.prowidesoftware.swift.model.SwiftTagListBlock;
import com.prowidesoftware.swift.model.mt.AbstractMT;

import java.util.List;
import java.util.Map;

public interface MessageSequenceProcessor {
    boolean supportsMessageType(String messageType);

    Map<String, List<SwiftTagListBlock>> getSequenceMap(AbstractMT mt);
}
