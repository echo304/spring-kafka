package com.sangboak.micro.kitchen.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TicketCreatedEvent {
    @JsonProperty
    private Long id;

    public TicketCreatedEvent(Long id) {
        this.id = id;
    }
}
