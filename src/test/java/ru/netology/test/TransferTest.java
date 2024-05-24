package ru.netology.test;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.netology.data.UserInfo;
import ru.netology.page.ActionTransferPage;
import ru.netology.page.DashBoardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;


import static com.codeborne.selenide.Selenide.open;


public class TransferTest {

    @BeforeEach
    void shouldAuthorization() {

        open("http://localhost:9999/");
        var validUser = UserInfo.getUser();
        var loginPage = new LoginPage();
        loginPage.validLogin(validUser);

        var verificationPage = new VerificationPage();
        var validCode = UserInfo.getCode();

        verificationPage.validCode(validCode);
    }


    @Test
    void shouldTransferToCard1() {
        var dashBoardPage = new DashBoardPage();

        var balance = dashBoardPage.getCard2Balance();
        var balanceTo = dashBoardPage.getCard1Balance();
        var cardsInfo = UserInfo.getCards();


        int amount = balance - balance / 4;

        String sum = String.valueOf(amount);
        dashBoardPage.actionTransferToCard1();
        var actionTransferPage = new ActionTransferPage();
        actionTransferPage.transferToCard(sum, cardsInfo.getValueTopUpCard1(), cardsInfo.getCard2Number());

        int expected1 = balanceTo + amount;
        int actual1 = dashBoardPage.getCard1Balance();
        Assertions.assertEquals(expected1, actual1);

        int expected2 = balance - amount;
        int actual2 = dashBoardPage.getCard2Balance();
        Assertions.assertEquals(expected2, actual2);


    }

    @Test
    void shouldTransferToCard2() {
        var dashBoardPage = new DashBoardPage();

        var balance = dashBoardPage.getCard1Balance();
        var balanceTo = dashBoardPage.getCard2Balance();
        var cardsInfo = UserInfo.getCards();
        int amount = balance - balance / 4;


        String sum = String.valueOf(amount);
        dashBoardPage.actionTransferToCard2();

        var actionTransferPage = new ActionTransferPage();
        actionTransferPage.transferToCard(sum, cardsInfo.getValueTopUpCard2(), cardsInfo.getCard1Number());

        int expected1 = balanceTo + amount;
        int actual1 = dashBoardPage.getCard2Balance();
        Assertions.assertEquals(expected1, actual1);

        int expected2 = balance - amount;
        int actual2 = dashBoardPage.getCard1Balance();
        Assertions.assertEquals(expected2, actual2);


    }

    @Test
    void shouldNotTransferToCard1() {
        var dashBoardPage = new DashBoardPage();

        var balance = dashBoardPage.getCard2Balance();
        var cardsInfo = UserInfo.getCards();
        int amount = balance + 10;
        String sum = String.valueOf(amount);
        dashBoardPage.actionTransferToCard1();
        var actionTransferPage = new ActionTransferPage();
        actionTransferPage.transferToCard(sum, cardsInfo.getValueTopUpCard1(), cardsInfo.getCard2Number());

        actionTransferPage.errorOfTransfer();

    }

    @Test
    void shouldNotTransferToCard2() {

        var dashBoardPage = new DashBoardPage();

        var balance = dashBoardPage.getCard1Balance();
        var cardsInfo = UserInfo.getCards();
        int amount = balance + 10;
        String sum = String.valueOf(amount);
        dashBoardPage.actionTransferToCard2();
        var actionTransferPage = new ActionTransferPage();
        actionTransferPage.transferToCard(sum, cardsInfo.getValueTopUpCard2(), cardsInfo.getCard1Number());

        actionTransferPage.errorOfTransfer();

    }


}
