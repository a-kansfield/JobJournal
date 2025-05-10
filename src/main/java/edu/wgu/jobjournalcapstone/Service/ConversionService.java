package edu.wgu.jobjournalcapstone.Service;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

@Service
public class ConversionService {

    public Instant stringToInstant(String dateString) {
        Instant inst = Instant.parse(dateString);
        return inst;
    }

}
