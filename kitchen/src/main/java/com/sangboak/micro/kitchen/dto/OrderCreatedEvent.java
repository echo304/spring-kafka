package com.sangboak.micro.kitchen.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class OrderCreatedEvent {
    private Long id;
    private String menuName;

    public OrderCreatedEvent(Long id, String menuName) {
        this.id = id;
        this.menuName = menuName;
    }
}
