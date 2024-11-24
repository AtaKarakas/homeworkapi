package com.homework.api.data.response;

public enum Status {

    APPROVED("APPROVED"),
    DECLINED("DECLINED");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
