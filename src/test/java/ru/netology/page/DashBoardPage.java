package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import lombok.val;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class DashBoardPage {
    private ElementsCollection topUpCards = $$("[data-test-id='action-deposit'");
    private SelenideElement topUpCard1 = topUpCards.get(0);
    private SelenideElement topUpCard2 = topUpCards.get(1);
    private ElementsCollection cards = $$(".list__item div");
    private SelenideElement card1 = cards.get(0);
    private SelenideElement card2 = cards.get(1);
    private SelenideElement dashboard = $("[data-test-id='dashboard']");
    private String balanceStart = "баланс: ";
    private String balanceFinish = " р.";


    public DashBoardPage() {
        dashboard.shouldBe(Condition.visible);
    }


    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }


    public int getCard1Balance() {
        val text = card1.text();
        return extractBalance(text);
    }

    public int getCard2Balance() {
        val text = card2.getText();
        return extractBalance(text);
    }


    public ActionTransferPage actionTransferToCard1() {
        topUpCard1.click();
        return new ActionTransferPage();
    }

    public ActionTransferPage actionTransferToCard2() {
        topUpCard2.click();
        return new ActionTransferPage();
    }
}
