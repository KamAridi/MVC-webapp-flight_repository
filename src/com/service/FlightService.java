package com.service;

import com.dao.FlightDao;
import com.dto.FlightDto;

import java.util.List;
import java.util.stream.Collectors;

public class FlightService {

    private static final FlightService INSTANCE = new FlightService();
    private final FlightDao flightDao = FlightDao.getInstance();

    public List<FlightDto> findAll() {
        return flightDao.findAll().stream()
                .map(flight -> new FlightDto(
                        flight.getId(),
                        """
                                    %s - %s - %s
                                """.formatted(
                                flight.getDepartureAirportCode(),
                                flight.getArrivalAirportCode(),
                                flight.getDepartureDate())
                ))
                .collect(Collectors.toList());
    }
    public static FlightService getInstance(){
        return INSTANCE;
    }
    private FlightService() {
    }
}
