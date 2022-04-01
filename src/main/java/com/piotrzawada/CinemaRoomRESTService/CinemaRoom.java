package com.piotrzawada.CinemaRoomRESTService;

import java.util.ArrayList;

public class CinemaRoom {
    private int total_rows;
    private int total_columns;
    private ArrayList<Seat> available_seats;

    public CinemaRoom(int rows, int columns) {
        this.total_rows = rows;
        this.total_columns = columns;
        this.available_seats = seats();

    }

    public int getTotal_rows() {
        return total_rows;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }

    public ArrayList<Seat> getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(ArrayList<Seat> available_seats) {
        this.available_seats = available_seats;
    }

    public synchronized void bookSeat(int row, int column) {
        for (Seat available_seat : available_seats) {
            if (available_seat.getRow() == row && available_seat.getColumn() == column) {
                available_seat.setBooked(true);
            }
        }
    }

    public synchronized void cancelBooking(int row, int column) {
        for (Seat available_seat : available_seats) {
            if (available_seat.getRow() == row && available_seat.getColumn() == column) {
                available_seat.setBooked(false);
            }
        }
    }

    public boolean checkAvailability(int row, int column) {
        for (Seat available_seat : available_seats) {
            if (available_seat.getRow() == row && available_seat.getColumn() == column && available_seat.isBooked()) {
                return false;
            }
        }
        return true;
    }

    private ArrayList<Seat> seats() {
        ArrayList<Seat> seats = new ArrayList<Seat>();
        int nrSeats = total_rows * total_columns;
        for (int i = 1; i <= total_rows; i++) {
            for (int j = 1; j <= total_columns; j++) {
                seats.add(new Seat(i , j));
            }
        }
        return seats;
    }
}


