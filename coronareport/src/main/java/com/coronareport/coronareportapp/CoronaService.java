package com.coronareport.coronareportapp;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class CoronaService {
    CoronaRepository coronaRepository;

    public CoronaService(CoronaRepository coronaRepository) {
        this.coronaRepository = coronaRepository;
    }
    public void save(CoronaPojo coronaPojo){
        coronaRepository.save(coronaPojo);
    }
    public void populateDB() throws IOException, CsvValidationException {
        String csvFilePath="C:\\Projects\\SpringProject\\SpringCorona\\coronareport\\csvFiles\\conora02.csv";
        URL url= new URL("https://github.com/CSSEGISandData/COVID-19/blob/82f805052bc7420ad3368db17fcfd09eac826c97/csse_covid_19_data/csse_covid_19_daily_reports/05-06-2020.csv");
        HttpURLConnection huc= (HttpURLConnection) url.openConnection();
        int responseCode= huc.getResponseCode();
        log.info("--Successfully Connected to github--");

        CSVReader reader= new CSVReader(new FileReader(csvFilePath));
        String[] csvLine;
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        int i=0;
        while ((csvLine=reader.readNext())!=null) {
            if (i == 0) {
                i++;
                continue;
            }
            CoronaPojo corona = new CoronaPojo();
            corona.setLastUpdate(LocalDateTime.parse(csvLine[4],formatter));
            corona.setConfirmed((csvLine[7]));
            corona.setRecovered((csvLine[9]));
            corona.setActive((csvLine[10]));
            corona.setCombinedKey(csvLine[11]);
            log.info(corona.toString());
            coronaRepository.save(corona);
            reader.close();
        }
    }
}
