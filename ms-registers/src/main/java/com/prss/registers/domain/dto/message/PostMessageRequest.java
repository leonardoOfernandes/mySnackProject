package com.prss.registers.domain.dto.message;

import com.prss.registers.domain.dao.Messages;
import com.prss.registers.domain.dao.Restaurant;
import com.prss.registers.domain.dto.Domain;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PostMessageRequest implements Domain<Messages> {
    private Restaurant idRestaurant;
    private String messageType;
    private String message;

    @Override
    public Messages toDomain() {
        return Messages.builder()
                .idRestaurant(idRestaurant)
                .messageType(messageType)
                .message(message)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
