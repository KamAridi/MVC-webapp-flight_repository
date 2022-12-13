package com.service;

import com.dao.TicketDao;
import com.dto.TicketDto;

import java.util.List;
import java.util.stream.Collectors;

public class TicketService {

    private static final TicketService INSTANCE = new TicketService();

    public static TicketService getInstance(){
        return INSTANCE;
    }

    private final TicketDao ticketDao = TicketDao.getInstance();

    public List<TicketDto> findByFlightId(Long id){

        return ticketDao.findByFlightId(id).stream()
                .map(ticket -> new TicketDto(
        ticket.getId(),
        ticket.getSeatNo()
                ))
                .collect(Collectors.toList());
    }
    private TicketService(){}
}
