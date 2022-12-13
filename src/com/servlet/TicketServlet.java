package com.servlet;

import com.service.TicketService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {

    private final TicketService ticketService = TicketService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long flightId = Long.valueOf(req.getParameter("flightId"));

        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (Writer writer = resp.getWriter()) {
            writer.write("<h1>Купленные билеты</h1>");
            writer.write("<ul>");
            ticketService.findByFlightId(flightId).forEach(ticketDto ->
            {
                try {
                    writer.write("""
                            <li>
                                %s
                            </li>
                            """.formatted(ticketDto.getSeatNo()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            writer.write("</ul>");
        }
    }
}
