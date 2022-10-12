package com.prss.chatbot.communication.domain.dto.message;

import com.prss.registers.domain.dao.Messages;
import com.prss.registers.domain.dto.Domain;
import lombok.*;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PutMessageRequest implements Domain<Messages> {
    private Integer id;
    private String messageType;
    @Column( nullable = false)
    private String message;
    @Override
    public Messages toDomain() {
        return Messages.builder()
                .id(id)
                .messageType(messageType)
                .message(message)
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
