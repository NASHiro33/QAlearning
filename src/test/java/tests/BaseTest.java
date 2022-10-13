package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest
{
    @BeforeAll
    public static void setUp()
    {
        Configuration.browser = "firefox";
    }
}
