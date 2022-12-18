package com.data;

public enum DateData {
    JANUARY("января", 1),
    OCTOBER("октября", 10),
    FEBRUARY("февраля", 2),
    APRIL("апреля", 4),
    MAY("мая", 5),
    JULY("июля", 7),
    AUGUST("апреля", 8),
    NOVEMBER("ноября", 11),
    DECEMBER("декабря", 12);

    private String name;

    private int number;

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    DateData(String name, int number) {
        this.name = name;
        this.number = number;
    }
}
