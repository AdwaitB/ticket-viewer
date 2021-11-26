package com.zccadwait.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

/**
 * Tracks the ticket list class for the endpoint with no id provided.
 *
 * @author Adwait Bauskar
 */
public class TicketList {
    private static final GsonBuilder builder = new GsonBuilder();
    private static final Gson gson = builder.create();

    public static TicketList parseTicketList(String ticketList){
        if(ticketList == null)
            return null;

        return TicketList.gson.fromJson(ticketList, TicketList.class);
    }

    private List<Ticket> tickets;
    private String next_page;
    private String previous_page;
    private Integer count;

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public String getNextPage() {
        return next_page;
    }

    public void setNextPage(String next_page) {
        this.next_page = next_page;
    }

    public String getPreviousPage() {
        return previous_page;
    }

    public void setPreviousPage(String previous_page) {
        this.previous_page = previous_page;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
