package com.toie.emailService.mapper;

import com.toie.emailService.model.EmailRequest;
import com.toie.emailService.model.EmailSent;
import org.mapstruct.Mapper;

@Mapper
public interface EmailSentMapper {
    EmailSent map(EmailRequest emailRequest, String id);
}
