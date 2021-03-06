package cloud.autotests.tests.ios;

import cloud.autotests.tests.TestBase;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static cloud.autotests.helpers.DriverHelper.byTestId;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


@Story("Login tests")
@Tag("ios")
@Tag("login")
class LoginTests extends TestBase {
    @Test
    @DisplayName("Successful login with iOS react-native app")
    void successfulLogin() {
        step("Go to login page", ()-> {
            open();
            $(byTestId("Header label")).shouldHave(text("Not authorized"));
        });

        step("Fill the authorization form", ()-> {
            $(byTestId("Authorization form")).shouldBe(visible);
            $(byTestId("Login input")).setValue(DEFAULT_LOGIN);
            $(byTestId("Password input")).setValue(DEFAULT_PASSWORD);
            $(byTestId("Remember me checkbox")).click();
            $(byTestId("Login button")).click();
        });

        step("Verify successful authorization", ()-> {
            $(byTestId("Authorization form")).shouldNot(exist);
            $(byTestId("Header label")).shouldHave(text("Hello, Alex!"));
            $$(byTestId("Private content"))
                    .shouldHaveSize(2)
                    .shouldHave(texts("Here is your private content #1", "and private content #2"));
        });
    }
}