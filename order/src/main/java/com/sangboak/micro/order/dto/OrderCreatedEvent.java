package com.sangboak.micro.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderCreatedEvent {
    @JsonProperty
    private Long id;
    @JsonProperty
    private String menuName;

    public OrderCreatedEvent(Long id, String menuName) {
        this.id = id;
        this.menuName = menuName;
    }
}
