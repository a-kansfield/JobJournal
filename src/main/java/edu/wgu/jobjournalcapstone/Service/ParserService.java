package edu.wgu.jobjournalcapstone.Service;

import edu.wgu.jobjournalcapstone.Data.DAO.ApplicationDAO;
import edu.wgu.jobjournalcapstone.Data.DAO.StatusDAO;
import edu.wgu.jobjournalcapstone.Data.DAO.UserDAO;
import edu.wgu.jobjournalcapstone.Data.Entity.Application;
import edu.wgu.jobjournalcapstone.Data.Entity.Status;
import edu.wgu.jobjournalcapstone.Data.Entity.User;
import jakarta.validation.constraints.NotNull;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
//import org.springframework.security.crypto.password.PasswordEncoder;
@Service
public class ParserService{

//    @Autowired
//    PasswordEncoder passwordEncoder;
    @Autowired
    StatusDAO statusDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    ApplicationDAO applicationDAO;
    private InstantConversionService instConversionService = new InstantConversionService();
    private DateConversionService dateConversionService = new DateConversionService();
    private ValidatorService validatorService = new ValidatorService();

    public Application parseApplication(@NotNull Map<String, Object> jsonPayload){
        Application application;
        Long id = Long.parseLong(jsonPayload.get("id").toString());
        if (id != -1) {
            application = findExisting(jsonPayload);
        } else {
            application = new Application();
        }

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


        // DATE VALIDATION

        if (validatorService.isDateValid(application)){
            applicationDAO.save(application);
        } else {
            throw new UnsupportedOperationException("Dates are invalid");
        }

        return application;
    }
    // Don't need any of this because I already have the ID in the delete request fdgdfsgjfdislgjladfk
    public Application deleteApplication(Map<String, Object> jsonPayload){
        Application application = findExisting(jsonPayload);
        applicationDAO.delete(application);

        return application;
    }

    private Application findExisting(Map<String, Object> jsonPayload){
        Long id = Long.parseLong(jsonPayload.get("id").toString());
        return applicationDAO.findById(id).get();
    }


    public Status extractStatus(String status){

        Long id = Long.parseLong(status);
        return statusDAO.findById(id).get();
    }



    public User parseUser(@NotNull Map<String, Object> jsonPayload){
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        User user = new User();

        user.setFirstName(
                jsonPayload.get("firstName").toString()
        );
        user.setLastName(
                jsonPayload.get("lastName").toString()
        );

        user.setEmail(
                jsonPayload.get("email").toString()
        );
        //String encryptedPass = passwordEncoder.encode(jsonPayload.get("password").toString());
        //Plaintext for now

        String encryptedPassword = passwordEncryptor.encryptPassword(jsonPayload.get("password").toString());
        user.setPassword(
                encryptedPassword
        );


        userDAO.save(user);
        return user;
    }
}
