package com.coronareport.coronareportapp;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
   @Scheduled(cron = "0 4 * * *")
    public void populateDB() throws CsvValidationException, IOException {
        String csvFilePath="C:\\Projects\\SpringProject\\SpringCorona\\coronareport\\csvFiles\\conora02.csv";
        URL url= new URL("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/82f805052bc7420ad3368db17fcfd09eac826c97/csse_covid_19_data/csse_covid_19_daily_reports/05-06-2020.csv");
        HttpURLConnection huc= (HttpURLConnection) url.openConnection();
        int responseCode= huc.getResponseCode();
        log.info(String.valueOf(responseCode));
        if(responseCode==200) {
            log.info("--Successfully Connected to github--");
            BufferedReader br = new BufferedReader(new InputStreamReader(huc.getInputStream()), 8192);
            CSVReader reader = new CSVReader(br);
            //reader.getRecordsRead();
            String[] csvLine;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            int i = 0;
            while ((csvLine = reader.readNext()) !=null) {
                if (i == 0) {
                    i++;
                    continue;
                }
                CoronaPojo corona = new CoronaPojo();
                corona.setLastUpdate(LocalDateTime.parse(csvLine[4], formatter));
                corona.setConfirmed(Double.valueOf(csvLine[7]));
                corona.setRecovered(Double.valueOf(csvLine[9]));
                corona.setActive(Double.valueOf(csvLine[10]));
                corona.setCombinedKey((csvLine[11]));
                List<CoronaPojo> list= findByCombinedKey(corona.getCombinedKey());
               if(!list.isEmpty())
               {
                   corona.setConfirmedChanges(corona.getConfirmed()-list.get(list.size()-1).getConfirmed());
                   corona.setRecoveredChanges(corona.getRecovered()-list.get(list.size()-1).getRecovered());
                   corona.setActiveChanges(corona.getActive()-list.get(list.size()-1).getActive());
               }
                coronaRepository.save(corona);
                log.info(corona.toString());
                if(i==10) break;
            }
            reader.close();
        }
    }

    private List<CoronaPojo> findByCombinedKey(String combinedKey) {
        return coronaRepository.findByCombinedKey(combinedKey);
    }

    public List<CoronaPojo> findbyLastUpdate(LocalDate localDate) {
        return coronaRepository.findByLastUpdateBetween(LocalDateTime.of(localDate, LocalTime.MIN),LocalDateTime.of(localDate, LocalTime.MAX));
    }

    public List<CoronaPojo> findAllData() {
        return coronaRepository.findAll();
    }
    public void populateDB1() throws CsvValidationException, IOException {
        String csvFilePath="C:\\Projects\\SpringProject\\SpringCorona\\coronareport\\csvFiles\\conora02.csv";
        URL url= new URL("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/82f805052bc7420ad3368db17fcfd09eac826c97/csse_covid_19_data/csse_covid_19_daily_reports/06-06-2020.csv");
        HttpURLConnection huc= (HttpURLConnection) url.openConnection();
        int responseCode= huc.getResponseCode();
        log.info(String.valueOf(responseCode));
        if(responseCode==200) {
            log.info("--Successfully Connected to github--");
            BufferedReader br = new BufferedReader(new InputStreamReader(huc.getInputStream()), 8192);
            CSVReader reader = new CSVReader(br);
            //reader.getRecordsRead();
            String[] csvLine;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            int i = 0;
            while ((csvLine = reader.readNext()) !=null&&i<10) {
                if (i == 0) {
                    i++;
                    continue;
                }
                CoronaPojo corona = new CoronaPojo();
                corona.setLastUpdate(LocalDateTime.parse(csvLine[4], formatter));
                corona.setConfirmed(Double.valueOf(csvLine[7]));
                corona.setRecovered(Double.valueOf(csvLine[9]));
                corona.setActive(Double.valueOf(csvLine[10]));
                corona.setCombinedKey((csvLine[11]));
                List<CoronaPojo> list= findByCombinedKey(corona.getCombinedKey());
                if(!list.isEmpty())
                {
                    corona.setConfirmedChanges(corona.getConfirmed()-list.get(list.size()-1).getConfirmed());
                    corona.setRecoveredChanges(corona.getRecovered()-list.get(list.size()-1).getRecovered());
                    corona.setActiveChanges(corona.getActive()-list.get(list.size()-1).getActive());
                }
                coronaRepository.save(corona);
                log.info(corona.toString());
if(i==10) break;
            }
            reader.close();
        }
    }

}

