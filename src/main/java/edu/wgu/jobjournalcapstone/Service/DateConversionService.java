package edu.wgu.jobjournalcapstone.Service;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Service
public class DateConversionService extends ConversionService{

    private ZoneId zone = ZoneId.systemDefault();

    // Used if a timestamp needs to be converted to LocalDate
    public LocalDate zonedStringToLocalDate(String dateString) {
        Instant inst = stringToInstant(dateString);
        LocalDate date = LocalDate.ofInstant(inst, zone);
        return date;
    }

    // Simple conversion from string to localdate
    public LocalDate stringToLocalDate(String dateString){
        LocalDate date = LocalDate.parse(dateString);
        return date;
    }
}
