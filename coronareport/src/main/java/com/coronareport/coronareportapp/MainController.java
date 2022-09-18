package com.coronareport.coronareportapp;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.FileReader;
import java.io.IOException;

@Controller
public class MainController {
    @GetMapping("/")
    public String root(Model model) throws CsvValidationException, IOException {
        model.addAttribute("test1","Hey let's begin");
        String csvFilePath="C:\\Projects\\SpringProject\\SpringCorona\\coronareport\\csvFiles";
        CSVReader reader= new CSVReader(new FileReader(csvFilePath));
        String[] csvLine;
        int i=0;
        while ((csvLine=reader.readNext())!=null)
        {
               if(i==0)i++;
        }
       if(reader==null) reader.close();
        return "mainTemplate";
    }
}
