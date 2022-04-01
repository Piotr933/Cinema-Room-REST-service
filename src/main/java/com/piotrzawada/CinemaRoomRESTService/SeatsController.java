package com.piotrzawada.CinemaRoomRESTService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class SeatsController {
    int rows;
    int columns;
    ConcurrentHashMap<String, Seat> bookings;
    CinemaRoom cinemaRoom;

    public SeatsController(@Qualifier("rows") int rows, @Qualifier("columns") int columns) {
        this.rows = rows;
        this.columns = columns;
        bookings = new ConcurrentHashMap<>();
        cinemaRoom = new CinemaRoom(rows,columns);
    }

    @GetMapping("/seats")
    public CinemaRoom cinemaRoom() {
        return cinemaRoom;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> ticketPurchase(@RequestBody TicketReq ticketReq) throws ResponseStatusException {
        UUID token = UUID.randomUUID();
        Seat seat = new Seat(ticketReq.getRow(), ticketReq.getColumn());

        if (ticketReq.getRow() > rows || ticketReq.getColumn() > columns
                || ticketReq.getRow() < 1 || ticketReq.getColumn() < 1) {
            return new ResponseEntity<>(Map.of("error", "The number of a row or a column is out of bounds!"),
                    HttpStatus.BAD_REQUEST);
        }
        if (!cinemaRoom.checkAvailability(ticketReq.getRow(), ticketReq.getColumn())) {
            return new ResponseEntity<>(Map.of("error", "The ticket has been already purchased!"),
                    HttpStatus.BAD_REQUEST);
        }

        cinemaRoom.bookSeat(ticketReq.getRow(),ticketReq.getColumn());
        bookings.put(String.valueOf(token),seat);
        ProofOfPurchase proofOfPurchase = new ProofOfPurchase(token,seat);

        return new ResponseEntity<>(proofOfPurchase, HttpStatus.OK);

    }

    @PostMapping("/return")
    public ResponseEntity<Map> returnTicket(@RequestBody Token token) {

        if (bookings.containsKey(token.token)) {
            HashMap<String,Seat> returnStatement = new HashMap<>();
            Seat seat = bookings.get(token.token);
            returnStatement.put("returned_ticket", seat);
            bookings.remove(token.token);
            cinemaRoom.cancelBooking(seat.getRow(),seat.getColumn());

            return new ResponseEntity<>(returnStatement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Map.of("error", "Wrong token!"), HttpStatus.BAD_REQUEST);
        }

    }
}



















