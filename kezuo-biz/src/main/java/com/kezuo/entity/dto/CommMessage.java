package com.kezuo.entity.dto;

import com.kezuo.core.dto.Message;
import com.kezuo.core.dto.ObjectMessage;

import java.util.List;

public class CommMessage extends ObjectMessage {

    @Override
    public List<Message> toMessage() {
        return convertor.toByteMessage(this);
    }
}
