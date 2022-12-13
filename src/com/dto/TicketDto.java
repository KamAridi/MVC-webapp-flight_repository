package com.dto;

public class TicketDto {
    private Long id;
    private String seatNo;

    public TicketDto(Long id, String seatNo) {
        this.id = id;
        this.seatNo = seatNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }
}
