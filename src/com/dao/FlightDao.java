package com.dao;

import com.entity.Flight;
import com.entity.FlightStatus;
import com.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightDao implements Dao<Long, Flight> {

    private static final String FIND_ALL = """
            SELECT id, flight_no, departure_date, departure_airport_code,
            arrival_date, arrival_airport_code, aircraft_id, status
            FROM flight
            """;

    @Override
    public List<Flight> findAll() {
        try (Connection connection = ConnectionManager.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Flight> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(buildFly(resultSet));
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Flight> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(Flight entity) {

    }

    @Override
    public Flight save(Flight entity) {
        return null;
    }

    private Flight buildFly(ResultSet resultSet) throws SQLException {
        return new Flight(
                resultSet.getObject("id", Long.class),
                resultSet.getObject("flight_no", String.class),
                resultSet.getObject("departure_date", Timestamp.class).toLocalDateTime(),
                resultSet.getObject("departure_airport_code", String.class),
                resultSet.getObject("arrival_date", Timestamp.class).toLocalDateTime(),
                resultSet.getObject("arrival_airport_code", String.class),
                resultSet.getObject("aircraft_id", Long.class),
                FlightStatus.valueOf(resultSet.getObject("status", String.class))
        );
    }
}
