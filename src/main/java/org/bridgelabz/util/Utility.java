package org.bridgelabz.util;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Utility {

    public static String readDataToExcelSheet(String EXCEL_PATH, String sheet, int row, int column) {
        String value = "";
        try {
            Workbook workbook = WorkbookFactory.create(new FileInputStream(EXCEL_PATH));
            value = workbook.getSheet(sheet).getRow(row).getCell(column).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static void writeDataToExcelSheet() throws IOException {
        String path = "C:\\Users\\ankit\\Desktop" +
                "\\xcelDatadrivenframework\\src\\test\\resources\\dataSheet1.xlsx";
        FileInputStream fileinputstream = new FileInputStream(path);
        Workbook workbook = new XSSFWorkbook(fileinputstream);
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(1);
        Cell cell = row.createCell(2);
        cell.setCellValue("Pass");
        FileOutputStream fileoutputstream = new FileOutputStream(path);
        workbook.write(fileoutputstream);
        fileoutputstream.close();
    }

        public static String screenShotsCapture(WebDriver driver) throws IOException {
            //1. take screenShots and store it as file format;
            File screenshotfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

            File file = new File("src/../applicationImage/" + System.currentTimeMillis() + ".jpg");
            String fileAbsolutePath = file.getAbsolutePath();
            FileUtils.copyFile(screenshotfile, file);
            return fileAbsolutePath;
        }

}
