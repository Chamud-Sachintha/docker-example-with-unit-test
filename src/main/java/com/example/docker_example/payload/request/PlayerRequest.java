package com.example.docker_example.payload.request;

public class PlayerRequest {
    private String playerName;
    private String playerEmail;

    public PlayerRequest(String playerName, String playerEmail) {
        this.playerName = playerName;
        this.playerEmail = playerEmail;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerEmail() {
        return playerEmail;
    }

    public void setPlayerEmail(String playerEmail) {
        this.playerEmail = playerEmail;
    }
}
