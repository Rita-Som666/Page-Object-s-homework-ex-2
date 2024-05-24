package ru.netology.data;

import lombok.Value;

public class UserInfo {


    @Value
    public static class User {
        String login;
        String password;


    }


    @Value
    public static class CardsInfo {
        String card1Number;
        String card2Number;
        String valueTopUpCard1;
        String valueTopUpCard2;


    }

    @Value
    public static class Verify {
        String code;
    }

    public static User getUser() {
        return new User("vasya", "qwerty123");
    }

    public static CardsInfo getCards() {
        return new CardsInfo("5559 0000 0000 0001", "5559 0000 0000 0002",
                "**** **** **** 0001", "**** **** **** 0002");
    }

    public static Verify getCode() {
        return new Verify("12345");
    }


}
