package uploadEmployee.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uploadEmployee.service.intefaces.FieldMapInterfaceService;
import uploadEmployee.service.intefaces.TabMapXLSXInterfaceService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;


@Getter
@Setter
@NoArgsConstructor
public class FileExcel {


    private XSSFWorkbook myExcelBook;
    private int amtSheets;
    private String nameFile;
    private TabMapXLSX tabMapXLSX;
    private XSSFSheet sheet;
    private Integer sheetId;
    private String pathReport;

    @Autowired
    TabMapXLSXInterfaceService tabMapXLSXInterfaceService;

    @Autowired
    FieldMapInterfaceService fieldMapInterfaceService;

    public FileExcel(MultipartFile file) {
        try {
            this.myExcelBook =  new XSSFWorkbook(file.getInputStream());
            this.amtSheets = myExcelBook.getNumberOfSheets();
            this.nameFile = file.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Map<String, Object>> getSheets(){

        List<Map<String, Object>> listOfMaps = new ArrayList<Map<String, Object>>();


        for (Integer i = 0; i < amtSheets; i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name",getSheetNameById(i));
            map.put("id", i);
            listOfMaps.add(map);
        }
        return listOfMaps;

    }


    public String getSheetNameById(Integer id){
        return myExcelBook.getSheetAt(id).getSheetName().toString();
    }

    public  ArrayList<ArrayList<String>> getMappingExcel(TabMapXLSX tabMapXLSX,  ArrayList<FieldMap> readyMapping){

        sheet = myExcelBook.getSheet(tabMapXLSX.getNameTable());
        int countColumn = sheet.getRow(1).getLastCellNum();
        //ArrayList<String> check = new ArrayList<>();

        ArrayList<ArrayList<String>> mapSkills = new ArrayList<>();
        String skillGroup = new String(); // переменная в которой мы храним группу

        if (readyMapping == null||readyMapping.size() == 0){

            for (int i = 0; i<countColumn; i++){
                String checkSkillGroup = sheet.getRow(0).getCell(i).getStringCellValue();


                String skill = sheet.getRow(1).getCell(i).getStringCellValue();
                if (skill.isEmpty()) {
                    if (checkSkillGroup.isEmpty()){
                        continue;
                    }
                    else {
                        skill = checkSkillGroup;
                        skillGroup = "";
                        checkSkillGroup= "";
                    }
                }
                skillGroup = checkSkillGroup.isEmpty() ? skillGroup : checkSkillGroup;
                //else if (check.contains(skill)) continue;

                ArrayList<String> mSkill = new ArrayList<>();

                mSkill.add(String.valueOf(i));
                mSkill.add(skill);

                if (i == 0)mSkill.add("name");
                else mSkill.add('"' + mSkill.get(1) + '"');
                mSkill.add(String.valueOf(255));
                mSkill.add(skillGroup);
                mapSkills.add(mSkill);
                //check.add(skill);
            }
        }
        else {
            for (int i = 0; i<countColumn; i++){
                String checkSkillGroup = sheet.getRow(0).getCell(i).getStringCellValue();

                String skill = sheet.getRow(1).getCell(i).getStringCellValue();
                if (skill.isEmpty()) {
                    if (checkSkillGroup.isEmpty()){
                        continue;
                    }
                    else {
                        skill = checkSkillGroup;
                        skillGroup = "";
                        checkSkillGroup= "";
                    }
                }
                skillGroup = checkSkillGroup.isEmpty() ? skillGroup : checkSkillGroup;

                ArrayList<String> mSkill = new ArrayList<>();

                mSkill.add(String.valueOf(i));
                mSkill.add(skill);
                int finalI = i;

                for (FieldMap f : readyMapping) {
                    if (f.getXlsxField().equalsIgnoreCase(mSkill.get(1)) &&
                            f.getXlsxGroup().equalsIgnoreCase(skillGroup)) {
                        mSkill.add(f.getTableField());
                        break;
                    }
                }
                if (mSkill.size() == 2)  mSkill.add('"' +  mSkill.get(1) + '"');
                mSkill.add(String.valueOf(255));
                mSkill.add(skillGroup);
                mapSkills.add(mSkill);
               // check.add(skill);

            }

        }

        return mapSkills;
        }

    public  ArrayList<ArrayList<String>> getDataForInsertByMapping(ArrayList<String[]> mapping){

        ArrayList<ArrayList<String>> listOfData = new ArrayList<>();

        for (int i = 2; i < sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);
            if (row.getCell(0).getStringCellValue().isEmpty()){
                break;
            }

            ArrayList<String> listOfValue = new ArrayList<>();
            listOfValue.add(String.valueOf(i));
            for (String[] str: mapping) {
                if (row.getCell(Integer.parseInt(str[0])).getCellType() == XSSFCell.CELL_TYPE_NUMERIC ||
                        row.getCell(Integer.parseInt(str[0])).getCellType() == XSSFCell.CELL_TYPE_BLANK ){
                    listOfValue.add(String.valueOf(row.getCell(Integer.parseInt(str[0])).getNumericCellValue()));
                }
                else if (row.getCell(Integer.parseInt(str[0])).getCellType() == XSSFCell.CELL_TYPE_STRING){
                    listOfValue.add(row.getCell(Integer.parseInt(str[0])).getStringCellValue());
                }
            }
            listOfData.add(listOfValue);
        }
        return listOfData;
    }

    public ArrayList<ArrayList<Object>> getSkills(String sheetName){

        ArrayList<ArrayList<Object>> arrayMapOfSkillsExcel = new ArrayList<>();

        sheet = myExcelBook.getSheet(sheetName);
        int countColumn = sheet.getRow(1).getLastCellNum(); // определяем количество столбцов
        String skillGroup = new String(); // переменная в которой мы храним группу
        for (int i = 1; i <countColumn; i++){
            String skill = sheet.getRow(1).getCell(i).getStringCellValue(); // вытаскиваем скилл
            String checkSkillGroup = sheet.getRow(0).getCell(i).getStringCellValue(); // вытаскиваем группу
            skillGroup = checkSkillGroup.isEmpty() ? skillGroup : checkSkillGroup; // проверяем не пустая ли группа пришла из строки выше
            if (!skill.isEmpty()){
                ArrayList<Object> arrayList = new ArrayList<>();
                arrayList.add(arrayMapOfSkillsExcel.size());
                arrayList.add(skill);
                arrayList.add(skillGroup);
                arrayMapOfSkillsExcel.add(arrayList);
                continue;
            }
            if (!checkSkillGroup.isEmpty()){
                ArrayList<Object> arrayList = new ArrayList<>();
                arrayList.add(arrayMapOfSkillsExcel.size());
                arrayList.add(checkSkillGroup);
                arrayList.add("");
                arrayMapOfSkillsExcel.add(arrayList);

            }

        }

        return arrayMapOfSkillsExcel;
    }

    public void writeIntoExcel( ArrayList report){

        XSSFWorkbook book = new XSSFWorkbook();

        XSSFSheet sheetAdvanced = book.createSheet("Advanced report");
        sheetAdvanced.setDefaultColumnWidth(22);
        XSSFRow rowAdvanced = sheetAdvanced.createRow(0);
        rowAdvanced.createCell(0).setCellValue("Validation type");
        rowAdvanced.createCell(1).setCellValue("Validation name");
        rowAdvanced.createCell(2).setCellValue("Row number");
        rowAdvanced.createCell(3).setCellValue("Table Name");
        rowAdvanced.createCell(4).setCellValue("Validation field");

        XSSFSheet sheetBasic = book.createSheet("Basic report");
        sheetBasic.setDefaultColumnWidth(22);
        XSSFRow rowBasic = sheetBasic.createRow(0);
        rowBasic.createCell(0).setCellValue("Validation type");
        rowBasic.createCell(1).setCellValue("Validation name");
        rowBasic.createCell(2).setCellValue("Table Name");
        rowBasic.createCell(3).setCellValue("Validation field");
        rowBasic.createCell(4).setCellValue("Count");

        Integer rowIndex = 1;
        Integer rowIndexBasic = 1;

        for (int i = 0; i<report.size(); i++ ){

            if (((LinkedHashMap) report.get(i)).get("Lvl").toString().equalsIgnoreCase("Field lvl")){
                rowAdvanced = sheetAdvanced.createRow(rowIndex);
                rowAdvanced.createCell(0).setCellValue("Field lvl");
            }
            else {
                rowAdvanced = sheetAdvanced.createRow(rowIndex);
                rowAdvanced.createCell(0).setCellValue("Table lvl");
            }

            rowAdvanced.createCell(1).setCellValue(((LinkedHashMap) report.get(i)).get("Validation sql request").toString());
            rowAdvanced.createCell(2).setCellValue(((LinkedHashMap) report.get(i)).get("row_number").toString());
            rowAdvanced.createCell(3).setCellValue(((LinkedHashMap) report.get(i)).get("Table name").toString());
            rowAdvanced.createCell(4).setCellValue(getColumnForReport((LinkedHashMap)report.get(i)));

            if (checkBasicSheet(report, i)){
                if (((LinkedHashMap) report.get(i)).get("Lvl").toString().equalsIgnoreCase("Field lvl")){
                    rowBasic = sheetBasic.createRow(rowIndexBasic);
                    rowBasic.createCell(0).setCellValue("Field lvl");
                }
                else {
                    rowBasic = sheetBasic.createRow(rowIndexBasic);
                    rowBasic.createCell(0).setCellValue("Table lvl");
                }
                rowBasic.createCell(1).setCellValue(((LinkedHashMap) report.get(i)).get("Validation sql request").toString());
                rowBasic.createCell(2).setCellValue(((LinkedHashMap) report.get(i)).get("Table name").toString());
                rowBasic.createCell(3).setCellValue(getColumnForReport((LinkedHashMap)report.get(i)));
                rowBasic.createCell(4).setCellValue(((LinkedHashMap) report.get(i)).get("Count").toString());
                rowIndexBasic++;
            }



            rowIndex++;

        }

        try {
            book.write(new FileOutputStream(pathReport + ".xlsx"));
            book.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Boolean checkBasicSheet(ArrayList report, Integer curRow){
        if (curRow == 0){
            return true;
        }
        Boolean isNew = false;
        LinkedHashMap<Object, String> curLinkedHashMap =  (LinkedHashMap) report.get(curRow);
        LinkedHashMap<Object, String> lastLinkedHashMap =  (LinkedHashMap) report.get(curRow - 1);
        for (Map.Entry map: curLinkedHashMap.entrySet()){
            if (lastLinkedHashMap.get(map.getKey().toString()) == null){
                isNew = true;
            }

        }
        return isNew;
    }

    private String getColumnForReport(LinkedHashMap<Object, String> rowReport){
        ArrayList<String> defultFields = new ArrayList<>();
        defultFields.add("Head");
        defultFields.add("Lvl");
        defultFields.add("Table name");
        defultFields.add("Validation sql request");
        defultFields.add("Count");
        defultFields.add("id");
        defultFields.add("row_number");

        for (Map.Entry e :  rowReport.entrySet())
        {
            if (!defultFields.contains(e.getKey().toString())) return e.getKey().toString();
        }

        return "Not found";
    }
}
