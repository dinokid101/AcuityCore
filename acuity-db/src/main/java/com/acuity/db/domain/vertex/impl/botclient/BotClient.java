package com.acuity.db.domain.vertex.impl.botclient;

import com.acuity.db.domain.vertex.Vertex;

import java.time.LocalDateTime;

/**
 * Created by Zachary Herridge on 8/3/2017.
 */
public class BotClient extends Vertex {

    private String ownerID; // TODO: 8/6/2017 Rename to key
    private LocalDateTime connectionTime = LocalDateTime.now();

    public BotClient(String ownerID) {
        this.ownerID = ownerID;
    }

    public BotClient() {
    }

    public String getOwnerID() {
        return ownerID;
    }

    public LocalDateTime getConnectionTime() {
        return connectionTime;
    }


    @Override
    public String toString() {
        return "BotClient{" +
                "ownerID='" + ownerID + '\'' +
                ", connectionTime=" + connectionTime +
                '}';
    }
}
