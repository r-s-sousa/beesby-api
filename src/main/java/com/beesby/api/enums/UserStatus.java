package com.beesby.api.enums;

public enum UserStatus {

    ACTIVE("active"),
    REMOVED("removed");

    private final String status;

    UserStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
