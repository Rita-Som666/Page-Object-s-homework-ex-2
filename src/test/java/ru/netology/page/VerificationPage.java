package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.UserInfo.Verify;

import static com.codeborne.selenide.Selenide.$;


public class VerificationPage {
    private SelenideElement code = $("[data-test-id='code'] .input__control");
    private SelenideElement button = $("[data-test-id='action-verify']");

    public VerificationPage() {
        code.shouldBe(Condition.visible);
    }

    public DashBoardPage validCode(Verify verify) {
        code.sendKeys(verify.getCode());
        button.click();
        return new DashBoardPage();
    }
}
