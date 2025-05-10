package edu.wgu.jobjournalcapstone.Service;

import edu.wgu.jobjournalcapstone.Data.DAO.ApplicationDAO;
import edu.wgu.jobjournalcapstone.Data.DAO.StatusDAO;
import edu.wgu.jobjournalcapstone.Data.DAO.UserDAO;
import edu.wgu.jobjournalcapstone.Data.Entity.Application;
import edu.wgu.jobjournalcapstone.Data.Entity.Status;
import edu.wgu.jobjournalcapstone.Data.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ParserService{

    @Autowired
    StatusDAO statusDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    ApplicationDAO applicationDAO;
    private InstantConversionService instConversionService = new InstantConversionService();
    private DateConversionService dateConversionService = new DateConversionService();

    public Application parseApplication(Map<String, Object> jsonPayload){
        Application application = new Application();
        String tempStringDate;
        DateTimeFormatter isoFormat = DateTimeFormatter.ISO_INSTANT;

        instConversionService.stringToInstant(jsonPayload.get("dateCreated").toString());

        application.setDateCreated(
                instConversionService.stringToInstant(jsonPayload.get("dateCreated").toString())
        );

        application.setDateUpdated(
                instConversionService.stringToInstant(jsonPayload.get("dateUpdated").toString())
        );

        if (jsonPayload.get("datePosted") != null) {
            application.setDatePosted(
                    dateConversionService.stringToLocalDate(jsonPayload.get("datePosted").toString())
            );
        } else {
            application.setDatePosted(null);
        }

        if (jsonPayload.get("dateApplied") != null) {
            application.setDateApplied(
                    dateConversionService.stringToLocalDate(jsonPayload.get("dateApplied").toString())
            );
        } else {
            application.setDateApplied(null);
        }


        if (jsonPayload.get("dateDue") != null) {
            application.setDateDue(
                    dateConversionService.stringToLocalDate(jsonPayload.get("dateDue").toString())
            );
        } else {
            application.setDateDue(null);
        }


        application.setEmployer(jsonPayload.get("employer").toString());
        application.setJobTitle(jsonPayload.get("jobTitle").toString());


        Status status = extractStatus(jsonPayload.get("status").toString());
        application.setStatus(status);

        Long userID = Long.parseLong(jsonPayload.get("userID").toString());
        User user = userDAO.findById(userID).get();

        application.setUser(user);
        applicationDAO.save(application);

        return application;
    }


    public Status extractStatus(String status){


        Long id = Long.parseLong(status);

        return statusDAO.findById(id).get();
    }

}
