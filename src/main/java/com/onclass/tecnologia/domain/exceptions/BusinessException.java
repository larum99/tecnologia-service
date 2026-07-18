package com.onclass.tecnologia.domain.exceptions;

import com.onclass.tecnologia.domain.enums.TechnicalMessage;
import lombok.Getter;

@Getter
public class BusinessException extends ProcessorException {

    public BusinessException(TechnicalMessage technicalMessage) {
        super(technicalMessage.getDescription(), technicalMessage);
    }
}
