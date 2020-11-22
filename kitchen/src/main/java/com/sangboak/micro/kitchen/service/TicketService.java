package com.sangboak.micro.kitchen.service;

import com.sangboak.micro.kitchen.dto.TicketCreatedEvent;
import com.sangboak.micro.kitchen.entity.Ticket;
import com.sangboak.micro.kitchen.messaging.TicketProducer;
import com.sangboak.micro.kitchen.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class TicketService {
    final private TicketRepository ticketRepository;
    final private TicketProducer ticketProducer;

    @Transactional
    public Long createTicket(Long id, String menuName) {
        Ticket ticket = new Ticket();
        ticket.setId(id);
        ticket.setMenuName(menuName);
        ticketRepository.save(ticket);

        ticketProducer.sendMessage("TICKET_CREATED", new TicketCreatedEvent(ticket.getId()));

        return ticket.getId();
    }
}
