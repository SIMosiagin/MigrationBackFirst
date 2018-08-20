package uploadEmployee.controller;

import uploadEmployee.entity.*;
import uploadEmployee.service.intefaces.*;

import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.hibernate.validator.constraints.pl.REGON;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin("http://localhost:4200")
@RestController
public class RestConfig {

    @Autowired
    TabMapXLSXInterfaceService tabMapXLSXInterfaceService;

    @Autowired
    FieldMapInterfaceService fieldMapInterfaceService;

    @Autowired
    FileExcel fileExcel;

    @Autowired
    ValidationInterfaceService validationInterfaceService;

    @Autowired
    ValidationManualDataInterfaceService validationManualDataInterfaceService;

    @Autowired
    ValidationsTypeInterfaceService validationsTypeInterfaceService;

    @RequestMapping(value = "/uploadExcel", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    List<Map<java.lang.String, Object>> uploadExcel(@RequestParam(value = "excel", required = true) MultipartFile file) throws IOException {


        fileExcel = new FileExcel(file);
        List<Map<java.lang.String, Object>> listOfMaps = fileExcel.getSheets();
        return listOfMaps;
    }

    @RequestMapping(value = "/uploadFieldMapWithOutValidation", method = RequestMethod.POST)
    void uploadMapping(@RequestBody String arrayMap[][]){

        Integer columnNumber = 0;
        ArrayList<String[]> mappedSheets = new ArrayList<String[]>();
        ArrayList<FieldMap> fieldMapArrayList = fieldMapInterfaceService.findByLink(fileExcel.getTabMapXLSX());

        String[] tmpStrArray = new String[1];
        tmpStrArray[1] = "row_number";
        mappedSheets.add(tmpStrArray);

        for (String[] srt:arrayMap) {
            columnNumber ++;
            if (srt[2] == null){
                continue;
            }
            else {
                FieldMap fieldMap = new FieldMap();
                fieldMap.setXlsxField(srt[1]);
                fieldMap.setTableField(srt[2]);
                fieldMap.setId_tab_map_xlsx(fileExcel.getTabMapXLSX());

                if(!fieldMapArrayList.contains(fieldMap)){
                    fieldMapInterfaceService.save(fieldMap);
                }

                String[] temp = new String[srt.length];
                for (int i = 0; i<srt.length; i++) {
                    temp[i] = srt[i];
                }
                Integer tmpColumnnim = columnNumber-1;
                temp[0] = tmpColumnnim.toString();
                mappedSheets.add(temp);
            }
        }

        try{
            RestTemplate restTemplate = new RestTemplate();

            restTemplate.postForObject("http://localhost:8081/setColumns", mappedSheets, ArrayList.class);
            restTemplate.postForObject("http://localhost:8081/setValue", fileExcel.getDataForInsertByMapping(mappedSheets), ArrayList.class);
            restTemplate.getForObject("http://localhost:8081/uploadInToThird", String.class);
        }
        catch (Exception e){
            System.out.println(e);
        }


    }

    @RequestMapping(value = "/uploadFieldMapWithValidation", method = RequestMethod.POST)
    public void uploadFieldMapWithValidation(@RequestBody String arrayMap[][]){

        Integer columnNumber = 0;
        ArrayList<String[]> mappedSheets = new ArrayList<String[]>();
        ArrayList<FieldMap> fieldMapArrayList = fieldMapInterfaceService.findByLink(fileExcel.getTabMapXLSX());
        for (String[] srt:arrayMap) {
            columnNumber ++;
            if (srt[2] == null){
                continue;
            }
            else {
                FieldMap fieldMap = new FieldMap();
                fieldMap.setXlsxField(srt[1]);
                fieldMap.setTableField(srt[2]);
                fieldMap.setXlsxGroup(srt[4]);
                fieldMap.setId_tab_map_xlsx(fileExcel.getTabMapXLSX());


                if(!fieldMapArrayList.contains(fieldMap)){
                    fieldMapInterfaceService.save(fieldMap);
                }

                String[] temp = new String[srt.length];
                for (int i = 0; i<srt.length; i++) {
                    temp[i] = srt[i];
                }
//                Integer tmpColumnnim = columnNumber-1;
//                temp[0] = tmpColumnnim.toString();
                mappedSheets.add(temp);
            }
        }

        try{
            RestTemplate restTemplate = new RestTemplate();

            restTemplate.postForObject("http://localhost:8081/setColumns", mappedSheets, ArrayList.class);
            restTemplate.postForObject("http://localhost:8081/setValue", fileExcel.getDataForInsertByMapping(mappedSheets), ArrayList.class);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @RequestMapping(value = "/uploadAfterValidation", method = RequestMethod.GET)
    public void uploadAfterValidation(){
        try{
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getForObject("http://localhost:8081/uploadInToThird", String.class);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @RequestMapping(value = "/chousedSheet/{id}",method = RequestMethod.GET)
     public ArrayList<ArrayList<Object>> setSheet(@PathVariable("id") Integer id){

        fileExcel.setSheetId(id);
        return fileExcel.getSkills(fileExcel.getSheetNameById(id));

    }

    @RequestMapping(value = "/getMapField" , method = RequestMethod.GET)
    public  ArrayList<ArrayList<String>> getMapField(){

        TabMapXLSX tabMapXLSX = tabMapXLSXInterfaceService.findByFieldTwoString("nameXLSXPref", fileExcel.getNameFile(),
        "nameTable", fileExcel.getSheetNameById(fileExcel.getSheetId()));

        ArrayList<FieldMap> readyMapping = null;
        try {
            readyMapping = fieldMapInterfaceService.findByLink(tabMapXLSX);
        }
        catch (Exception e){
            System.out.println(e.getStackTrace());
        }

        if (tabMapXLSX != null){
            fileExcel.setTabMapXLSX(tabMapXLSX);
            ArrayList<ArrayList<String>> response = fileExcel.getMappingExcel(tabMapXLSX, readyMapping);

            return response;

        }
        else {
            TabMapXLSX newTabMapXLSX = new TabMapXLSX();
            newTabMapXLSX.setNameXLSXPref(fileExcel.getNameFile());
            newTabMapXLSX.setNameTable(fileExcel.getSheetNameById(fileExcel.getSheetId()));
            tabMapXLSXInterfaceService.save(newTabMapXLSX);
            fileExcel.setTabMapXLSX(newTabMapXLSX);
            ArrayList<ArrayList<String>> response = fileExcel.getMappingExcel(newTabMapXLSX, readyMapping);

            return response;
        }
    }

    @RequestMapping(value = "/getTransitTables", method = RequestMethod.GET)
    public List<Map<String, Object>> getTransitTables(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:8081/getTransitTables", List.class);
    }

    @RequestMapping(value = "/setTransitTable/{tableName}", method = RequestMethod.GET)
    public void setTransitTable(@PathVariable("tableName") String tableName){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject("http://localhost:8081/setTransitTable/" + tableName, String.class);

    }

    @RequestMapping(value = "/pathReport", method = RequestMethod.POST)
    public void setPathReport(@RequestBody String pathReport){
        fileExcel.setPathReport(pathReport);
    }

    @RequestMapping(value = "/getFields", method = RequestMethod.GET)
    public ArrayList<FieldMap> getFields(){
        return  fieldMapInterfaceService.findByLink(fileExcel.getTabMapXLSX());
    }

    @RequestMapping(value = "/getValidation", method = RequestMethod.GET)
    public ArrayList<Validation> getValidation(){

        //ArrayList<Validation> validations =  validationInterfaceService.findByTabMapXLSX(fileExcel.getTabMapXLSX());
        return  validationInterfaceService.findByTabMapXLSX(fileExcel.getTabMapXLSX());
    }

    @RequestMapping(value = "/getManualValidation", method = RequestMethod.GET)
    public ArrayList<ValidationManualData> getManualValidation(){
        return validationManualDataInterfaceService.findAll();
    }

    @RequestMapping(value = "/doValidation", method = RequestMethod.POST)
    public void doValidation (@RequestBody  ArrayList<Map> validation ){
       ArrayList<String> response;

        for (Map map:validation) {
            if(Boolean.parseBoolean(map.get("isNew").toString())){

                Validation validationEntity = new Validation();
                validationEntity.setTabMapXLSX(fileExcel.getTabMapXLSX());
                try {
                    validationEntity.setFieldMap(fieldMapInterfaceService.findByLinkAndFieldString(fileExcel.getTabMapXLSX(),map.get("field").toString()));
                }
                catch (Exception ex){
                    validationEntity.setFieldMap(null);
                }
                validationEntity.setValidationsType(validationsTypeInterfaceService.findByType(map.get("validation").toString()));
                validationInterfaceService.save(validationEntity);

                if (map.get("validationType").toString().equals("Manual")){
                    ValidationManualData validationManualData = new ValidationManualData();
                    validationManualData.setSqlText(map.get("validation").toString());
                    validationManualData.setValidation(validationEntity);
                    validationManualDataInterfaceService.save(validationManualData);
                }
            }
        }
        try{
            RestTemplate restTemplate = new RestTemplate();
            response = restTemplate.postForObject("http://localhost:8081/doValidation", validation, ArrayList.class);
            fileExcel.writeIntoExcel(response);

        }
        catch (Exception e){
            System.out.println(e);
        }


    }

    @RequestMapping(value = "/makeValid", method = RequestMethod.GET)
    public void makeValid(){

      //  fileExcel.writeIntoExcel();

        ValidationsType validationsType = new ValidationsType();
        validationsType.setTypeName("is_Unique");
        validationsTypeInterfaceService.save(validationsType);

        Validation validation = new Validation();
        validation.setValidationsType(validationsType);
        validation.setTabMapXLSX(fileExcel.getTabMapXLSX());
        validationInterfaceService.save(validation);

        ValidationsType validationsType2 = new ValidationsType();
        validationsType2.setTypeName("is_Null");
        validationsTypeInterfaceService.save(validationsType2);

        Validation validation2 = new Validation();
        validation2.setValidationsType(validationsType2);
        validation2.setTabMapXLSX(fileExcel.getTabMapXLSX());
        validationInterfaceService.save(validation2);


        ValidationsType validationsType3 = new ValidationsType();
        validationsType3.setTypeName("is_Number");
        validationsTypeInterfaceService.save(validationsType3);

        Validation validation3 = new Validation();
        validation3.setValidationsType(validationsType3);
        validation3.setTabMapXLSX(fileExcel.getTabMapXLSX());
        validationInterfaceService.save(validation3);

        Validation validation4 = new Validation();
        validation4.setValidationsType(validationsType3);
        validation4.setTabMapXLSX(fileExcel.getTabMapXLSX());
        validationInterfaceService.save(validation4);


        ValidationManualData validationManualData = new ValidationManualData();
        validationManualData.setValidation(validation4);
        validationManualData.setSqlText("Test manual val");

        validationManualDataInterfaceService.save(validationManualData);





    }

    @RequestMapping(value = "/getTypeValidation", method = RequestMethod.GET)
    public List<ValidationsType> getTypeValidation(){
        return validationsTypeInterfaceService.findAll();
    }

}
