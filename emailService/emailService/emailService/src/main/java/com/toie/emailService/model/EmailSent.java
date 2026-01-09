package com.toie.emailService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.lang.NonNull;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "email_sent")
public class EmailSent {
    @Id
    private String id;

    @Column
    @NonNull
    private String email;

    @Column
    @NonNull
    private String productName;
}
