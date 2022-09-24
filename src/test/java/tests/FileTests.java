package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.xlstest.XLS;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import utils.ZipUtils;

import static com.codeborne.selenide.Selenide.sleep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static utils.FileUtils.*;


public class FileTests
{
    @Test
    void checkTextInPdf()
    {
        SelenideLogger.addListener("allure", new AllureSelenide());//подключаем листенера
        //data
        String filePath     = "./src/test/resources/data/Google.pdf";
        String expectedText = "google";

        //read file
        PDF actualPdf = readPdfFromFile(filePath);

        //assert
        assertThat(actualPdf, PDF.containsText(expectedText));
    }

    @Test
    void checkTextInXls()
    {
        SelenideLogger.addListener("allure", new AllureSelenide());//подключаем листенера
        //data
        String filePath     = "./src/test/resources/data/testFile.xls";
        String expectedText = "Апатиты";

        //read file
        XLS actualXls     = readXlsFromFile(filePath);
        String actualCell = actualXls.excel.getSheetAt(0).getRow(1).getCell(5).toString();

        //assert
        assertThat(actualXls, XLS.containsText(expectedText));
        assertThat(actualCell, containsString(expectedText));
    }

    @Test
    void checkFilesInZip()
    {
        SelenideLogger.addListener("allure", new AllureSelenide());//подключаем листенера
        //data
        String pathToZip     = "./src/test/resources/data/health.zip";
        String pathToUnzip   = "./src/test/resources/data/unzip";
        String unzippedFile  = "./src/test/resources/data/unzip/health";
        String expectedText  = "no bad days";

        //read file
        new ZipUtils().unzip(pathToZip, pathToUnzip);
        sleep(1000);

        String actualFile = readStringFromFile(unzippedFile);

        //assert
        assertThat(actualFile, containsString(expectedText));
    }
}
