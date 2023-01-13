package com.example.server.servermanager.enumeration;

public enum Status {

    SERVER_UP("SERVERUP"),
    SERVER_DOWN("SERVERDOWN");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
