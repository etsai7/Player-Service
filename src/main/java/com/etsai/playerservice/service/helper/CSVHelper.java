package com.etsai.playerservice.service.helper;


import com.etsai.playerservice.entity.PlayerEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class CSVHelper {
    public static final String FILE_TYPE = "text/csv";

    public static boolean checkIsCSVFormat(MultipartFile file) {
        return FILE_TYPE.equals(file.getContentType());
    }

    public static List<PlayerEntity> csvToDemoObjs(InputStream inputStream) {
        log.info("About to parse the input Stream");

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<PlayerEntity> objsList = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            log.info("About to create Demo objects");

            for (CSVRecord csvRecord : csvRecords) {
                log.info("CSV Record [{}]", csvRecord);
                try {
                    // Check player birth and death date
                    /*
                        Call the Validator class
                        Birth and Death Date Validator method:
                            Pull out the birth date values and create a Date obj
                            Pull out the death date value or default to max Date value
                            Compare if Death date is later
                        Validate Bat value is valid
                     */

                    /*
                        "object.deathDate > object.birthDate"
                        Set.contains("object.deathDate" )
                     */


                    PlayerEntity obj = new PlayerEntity(
                            /*
                                new Date(parseIntegerValue(csvRecord.get("deathYear")),
                            parseIntegerValue(csvRecord.get("deathMonth")),
                            parseIntegerValue(csvRecord.get("deathDay")), "UTC");
                            replace the 3 separate date numbers
                             */
                            csvRecord.get("playerID"),
                            parseIntegerValue(csvRecord.get("birthYear")),
                            parseIntegerValue(csvRecord.get("birthMonth")),
                            parseIntegerValue(csvRecord.get("birthDay")),
                            csvRecord.get("birthCountry"),
                            csvRecord.get("birthState"),
                            csvRecord.get("birthCity"),
                            parseIntegerValue(csvRecord.get("deathYear")),
                            parseIntegerValue(csvRecord.get("deathMonth")),
                            parseIntegerValue(csvRecord.get("deathDay")),
                            csvRecord.get("deathCountry"),
                            csvRecord.get("deathState"),
                            csvRecord.get("deathCity"),
                            csvRecord.get("nameFirst"),
                            csvRecord.get("nameLast"),
                            csvRecord.get("nameGiven"),
                            parseIntegerValue(csvRecord.get("weight")),
                            parseIntegerValue(csvRecord.get("height")),
                            csvRecord.get("bats"),
                            csvRecord.get("throws"),
                            parseDate(csvRecord.get("debut")),
                            parseDate(csvRecord.get("finalGame")),
                            csvRecord.get("retroID"),
                            csvRecord.get("bbrefID")
                    );


                    objsList.add(obj);
                } catch (Exception e){
                    log.error("Failed to create object from [{}] because of [{}]", csvRecord, e.getMessage());
                    continue;
                }
            }

            log.info("Write [{}] entries", objsList.size());
            return objsList;
        } catch (IOException e) {
            log.error("Failed to create Object: [{}]", e.getMessage());
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        } catch (Exception e){
            log.error("Generic Failed Exception when creating object: [{}]", e.getMessage());
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
    
    private static int parseIntegerValue(String value){
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e){
            log.warn("Number missing or wrong format: [{}]", value);
            return -1;
        }
    }

    private static Date parseDate(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        String [] patterns = new String [] {"M/d/yyyy", "yyyy/mm/dd", "yyyy-MM-dd", "MM/dd/yyyy"};
        log.info("Parsing Date: [{}]", date);

        for (String pattern : patterns) {
            dateFormat.applyPattern(pattern);
            try {
                return dateFormat.parse(date);
            } catch (ParseException e) {

            }
        }

        // If none of the patterns match, return null
        return null;
    }

//    public static MultipartFile createMultipartFile(String path) throws IOException{
//        File file = new File(path);
//
//        // Create a FileSystemResource from the File object
//        FileSystemResource resource = new FileSystemResource(file);
//
//        // Return a new MultipartFile object from the resource
//        return new MockMultiP(file.getName(), resource.getInputStream()) {
//        };
//    }

}

