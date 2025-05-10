package edu.wgu.jobjournalcapstone.Service;

import java.time.Instant;

public class InstantConversionService extends ConversionService {

    @Override
    public Instant stringToInstant(String dateString) {
        Instant inst = Instant.parse(dateString);
        return inst;
    }
}
