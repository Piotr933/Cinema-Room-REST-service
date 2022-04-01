package com.piotrzawada.CinemaRoomRESTService;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class ProofOfPurchase {

    @JsonProperty("token")
    private UUID uuid;

    @JsonProperty("ticket")
    private Seat details;

    public ProofOfPurchase(UUID uuid, Seat details) {
        this.uuid = uuid;
        this.details = details;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Seat getDetails() {
        return details;
    }

    public void setDetails(Seat details) {
        this.details = details;
    }
}
