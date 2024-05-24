package ru.netology.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;

import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import ru.netology.data.UserInfo;
import ru.netology.data.UserInfo.CardsInfo;
import ru.netology.data.UserInfo.User;
import ru.netology.data.UserInfo.Verify;
import ru.netology.page.ActionTransferPage;
import ru.netology.page.DashBoardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;

public class Steps {
    private static LoginPage loginPage;
    private static VerificationPage verificationPage;
    private static DashBoardPage dashBoardPage;
    private static ActionTransferPage actionTransferPage;
    private static User user;

    @Пусть("пользователь залогинен на {string} с именем 'vasya' и паролем 'qwerty123'")
    public void authorise(String url) {
        User user = UserInfo.getUser();
        Verify verify = UserInfo.getCode();
        loginPage = Selenide.open(url, LoginPage.class);
        verificationPage = loginPage.validLogin(user);
        dashBoardPage = verificationPage.validCode(verify);
    }

    @Когда("пользователь переводит {string} рублей с карты с номером {string} на свою {int} карту с главной страницы")
    public void transfer(String sum, String cardNumber, Integer cardIndex) {
        if (cardIndex == 1) {
            actionTransferPage = dashBoardPage.actionTransferToCard1();
        } else {
            actionTransferPage = dashBoardPage.actionTransferToCard2();
        }
        actionTransferPage.transferToCard(sum, UserInfo.getCards().getValueTopUpCard1(), cardNumber);
    }

    @Тогда("баланс его {int} карты из списка на главной странице должен стать {string} рублей")
    public void balance(Integer cardIndex, String sum) {
        int balance = Integer.parseInt(sum);
        if (cardIndex == 1) {
            Assertions.assertEquals(balance, dashBoardPage.getCard1Balance());
        } else {
            Assertions.assertEquals(balance, dashBoardPage.getCard2Balance());
        }
    }
}