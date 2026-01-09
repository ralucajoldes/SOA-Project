package com.toie.emailService.repository;

import com.toie.emailService.model.EmailSent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailSentRepository extends JpaRepository<EmailSent, String> {
}
