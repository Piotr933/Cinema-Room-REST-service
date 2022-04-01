package com.piotrzawada.CinemaRoomRESTService;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Seat {
    private int row;
    private int column;
    private int price;
    @JsonIgnore
    private boolean booked;

    public Seat(int rows, int column) {
        this.row = rows;
        this.column = column;
        this.price = ticketPrice(row);
        this.booked = false;
    }

    public Seat(int row, int column,boolean booked) {
        this.row = row;
        this.column = column;
        this.price = ticketPrice(row);
        this.booked = booked;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isBooked() {
        return booked;
    }
    public void setBooked(boolean booked) {
        this.booked = booked;
    }
    private int ticketPrice(int row) {
        return (row <= 4) ? 10 : 8;
    }
}

