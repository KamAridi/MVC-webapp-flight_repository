package com.dao;

import com.entity.Ticket;
import com.util.ConnectionManager;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketDao implements Dao<Long, Ticket> {
    private static final TicketDao INSTANCE = new TicketDao();

    private static final String FIND_BY_FLIGHT_ID = """
            SELECT id, passenger_no, passenger_name, flight_id, seat_no, cost
            FROM ticket
            WHERE flight_id = ?
            """;

    public static TicketDao getInstance() {
        return INSTANCE;
    }

    public List<Ticket> findByFlightId(Long id) {
        try (
                Connection connection = ConnectionManager.getConnect();
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_FLIGHT_ID)) {
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Ticket> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(buildTicket(resultSet));
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Ticket> findAll() {
        return null;
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(Ticket entity) {

    }

    @Override
    public Ticket save(Ticket entity) {
        return null;
    }

    private TicketDao() {

    }

    private static Ticket buildTicket(ResultSet resultSet) throws SQLException {
        return new Ticket(
                resultSet.getObject("id", Long.class),
                resultSet.getObject("passenger_no", String.class),
                resultSet.getObject("passenger_name", String.class),
                resultSet.getObject("flight_id", Long.class),
                resultSet.getObject("seat_no", String.class),
                resultSet.getObject("cost", BigDecimal.class)
        );
    }
}
