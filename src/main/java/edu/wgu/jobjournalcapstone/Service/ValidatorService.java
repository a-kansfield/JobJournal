package edu.wgu.jobjournalcapstone.Service;

import edu.wgu.jobjournalcapstone.Data.Entity.Application;
import org.apache.tomcat.util.digester.Rule;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ValidatorService {

    LocalDate currentDate;
    LocalDate dateApplied;
    LocalDate dateDue;
    LocalDate datePosted;
    boolean isDateValid(Application application) {
        currentDate = LocalDate.now();
        dateApplied = application.getDateApplied();
        dateDue = application.getDateDue();
        datePosted = application.getDatePosted();
        //Rule 1: Date applied cannot be past current date
        if (dateApplied != null && dateApplied.isAfter(currentDate)) {
            return false;
        }
        //Rule 2: Date applied cannot be earlier than the date the employer posted the listing
        if (dateApplied != null && datePosted != null && dateApplied.isBefore(datePosted)){
            return false;
        }
        //Rule 3: Date Due cannot be set before current date
        if (dateDue != null && dateDue.isBefore(currentDate)){
            return false;
        }
        //Rule 4: Date Due and Date Applied cannot both be set at the same time.
        if (dateDue != null && dateApplied != null){
            return false;
        }
        //Rule 5: Date Due and Date Applied cannot both be empty.
        if (dateDue == null && dateApplied == null){
            return false;
        }
        return true;
    }
}
