package uploadEmployee.service.implementation;


import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadExcelService  {

    private String file;

    public void readFromExcel (String file, String tableName) throws IOException {
        HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet myExcelSheet = myExcelBook.getSheet(tableName);
        HSSFRow row = myExcelSheet.getRow(0);
        //myExcelSheet.getS

        myExcelBook.close();
    }
}
