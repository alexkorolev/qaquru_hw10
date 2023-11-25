import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class MainTest extends BaseTest{

    @ParameterizedTest
    @DisplayName("Проверка поиска всех акций на zack.com")
    @Tag("BLOCKER")
    @CsvFileSource(resources = "allListUnCert.cvs")
    void checkStocksOnZackCsv(String stock) {

        open("");
        $("#search-q").click();
        $("#search-q").setValue(stock).pressEnter();
        $("#quote_ribbon_v2").shouldHave(text(stock));

    }

    @ParameterizedTest
    @DisplayName("Проверка поиска крупных акций из индекса QQQ на zack.com")
    @Tag("CRITICAL")
    @ValueSource(strings = {"MSFT","AAPL","TSLA","NVDA",
            "AMD","META","AMZN","NFLX"})
    void checkStocksOnZackSource(String stock) {

        open("");
        $("#search-q").click();
        $("#search-q").setValue(stock).pressEnter();
        $("#quote_ribbon_v2").shouldHave(text(stock));

    }

    @ParameterizedTest
    @DisplayName("Проверка отображения имени компаний на zack.com")
    @Tag("MAJOR")
    @CsvSource(value = {
            "MSFT , Microsoft",
            "AAPL , Apple",
            "TSLA , Tesla",
            "NVDA , NVIDIA",
            "AMD , Advanced Micro Devices",
            "META , Meta Platforms",
            "AMZN , Amazon.com",
            "NFLX , Netflix",
    })
    void checkStocksOnZackCsvSource(String stock, String fullNameCompany) {

        open("");
        $("#search-q").click();
        $("#search-q").setValue(stock).pressEnter();
        $("#quote_ribbon_v2").shouldHave(text(fullNameCompany));

    }
}
