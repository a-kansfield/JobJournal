package edu.wgu.jobjournalcapstone.Service;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Service
public class DateConversionService extends ConversionService{

    private ZoneId zone = ZoneId.systemDefault();

    public LocalDate stringToLocalDate(String dateString) {
        Instant inst = stringToInstant(dateString);
        LocalDate date = LocalDate.ofInstant(inst, zone);
        return date;
    }
}
