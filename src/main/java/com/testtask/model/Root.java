package com.testtask.model;

import java.util.List;

public class Root {
    private List<Ticket> tickets;

    public List<Ticket> getTicket() {
        return tickets;
    }

    public void setTicket(List<Ticket> ticket) {
        this.tickets = ticket;
    }


    @Override
    public String toString() {
        return "Root{" +
                "ticket=" + tickets +
                '}';
    }
}
