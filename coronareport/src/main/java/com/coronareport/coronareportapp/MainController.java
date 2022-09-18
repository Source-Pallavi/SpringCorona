package com.coronareport.coronareportapp;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Slf4j
@Controller
public class MainController {
    @GetMapping("/")
    public String root(Model model) throws CsvValidationException, IOException {
        model.addAttribute("test1","Hey let's begin");
        String csvFilePath="C:\\Projects\\SpringProject\\SpringCorona\\coronareport\\csvFiles\\coronaReport.csv";
        CSVReader reader= new CSVReader(new FileReader(csvFilePath));
        String[] csvLine;
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("MM-dd-yy HH:mm");
        int i=0;
        while ((csvLine=reader.readNext())!=null) {
            if (i ==0) {
                i++;
                continue;
            }

            CoronaPojo corona = new CoronaPojo();
            corona.setLastUpdate((csvLine[2]));
            corona.setConfirmed(Long.valueOf(csvLine[3]));
            corona.setRecovered(Long.valueOf(csvLine[4]));
            //corona.setActive(Long.valueOf(csvLine[5]));
            corona.setCombinedKey(csvLine[1]);
            log.info(corona.toString());
            reader.close();
        }
        return "mainTemplate";
    }
}
