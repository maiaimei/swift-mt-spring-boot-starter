package cn.maiaimei.framework.swift.converter.mt.mt7xx.strategy;

import cn.maiaimei.framework.swift.model.mt.mt7xx.MT798DetailMessage;
import cn.maiaimei.framework.swift.model.mt.mt7xx.MT798ExtensionMessage;
import cn.maiaimei.framework.swift.model.mt.mt7xx.MT798IndexMessage;
import cn.maiaimei.framework.swift.model.mt.mt7xx.MT798Transaction;
import cn.maiaimei.framework.swift.model.mt.mt7xx.transaction.MT784Transaction;
import org.springframework.stereotype.Component;

@Component
public class MT784ToTransactionConverterStrategy extends AbstractMT798ToTransactionConverterStrategy {
    @Override
    public boolean supportsMessageType(String subMessageType) {
        return "784".equals(subMessageType);
    }

    @Override
    public <T extends MT798Transaction> Class<T> getTransactionType() {
        return (Class<T>) MT784Transaction.class;
    }

    @Override
    protected MT798IndexMessage getIndexMessage() {
        return new MT784Transaction.MT784IndexMessage();
    }

    @Override
    protected MT798DetailMessage getDetailMessage() {
        MT784Transaction.MT784DetailMessage message = new MT784Transaction.MT784DetailMessage();
        message.setSequenceA(new MT784Transaction.MT784DetailSequenceA());
        message.setSequenceB(new MT784Transaction.MT784DetailSequenceB());
        message.setSequenceC(new MT784Transaction.MT784DetailSequenceC());
        return message;
    }

    @Override
    protected MT798ExtensionMessage getExtensionMessage() {
        return new MT784Transaction.MT784ExtensionMessage();
    }
}
