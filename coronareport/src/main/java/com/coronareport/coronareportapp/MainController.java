package com.coronareport.coronareportapp;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//its service layer and also used for constructor injection

@Slf4j
@Controller
public class MainController {
    CoronaService service;

    public MainController(CoronaService service) {
        this.service = service;
    }

    @Autowired
    CoronaRepository coronaRepository;

    @GetMapping("/")
    public String root(Model model) throws CsvValidationException, IOException {
        model.addAttribute("test1","Hey let's begin");
        service.populateDB();
        service.populateDB1();
        return "mainTemplate";
    }
    @GetMapping("/2")
    public String root2(Model model) throws CsvValidationException, IOException {
      //  model.addAttribute("test1","Hey let's begin");
       // model.addAttribute("coronaData",service.findbyLastUpdate(LocalDate.now()));
       model.addAttribute("coronaData",service.findAllData());
        return "mainTemplate";
    }

}
