package edu.wgu.jobjournalcapstone.Controller;

import com.opencsv.CSVWriter;
import edu.wgu.jobjournalcapstone.Data.DAO.ApplicationDAO;
import edu.wgu.jobjournalcapstone.Data.DAO.StatusDAO;
import edu.wgu.jobjournalcapstone.Data.DAO.UserDAO;
import edu.wgu.jobjournalcapstone.Data.Entity.Application;
import edu.wgu.jobjournalcapstone.Data.Entity.Status;
import edu.wgu.jobjournalcapstone.Data.Entity.User;
import edu.wgu.jobjournalcapstone.Service.ParserService;
import edu.wgu.jobjournalcapstone.Service.URIBuilderService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Formatter;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://a-kansfield.github.io/job-journal/"
})
@RequestMapping("/applications")
public class ApplicationController {

    @Autowired
    ApplicationDAO applicationDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private StatusDAO statusDAO;

    @Autowired
    private ParserService parserService;

    @Autowired
    private URIBuilderService uriBuilderService;

//    @GetMapping("/all")
//    public List<Application> getAllApplications() {
//        System.out.println("Test");
//        return applicationDAO.findAll();
//    }

    //Example: http://localhost:8080/applications/all?id=1
    @GetMapping("/all/user-{id}")
    public List<Application> getApplicationsByUser(@PathVariable Long id) {
        User user = userDAO.findById(id).get();
        List<Application> applications = applicationDAO.getApplicationsByUser(user);
        return applications;
    }

    //http://localhost:8080/applications/all/by-status?userID=1&statusID=5
    @GetMapping("/all/user-{userID}/status-{statusID}")
    public List<Application> getApplicationsByStatus(@PathVariable Long userID, @PathVariable Long statusID) {
        User user = userDAO.findById(userID).get();
        Status status = statusDAO.findById(statusID).get();
        List<Application> applications = applicationDAO.getApplicationsByUserAndStatus(user, status);

        return applications;
    }

    //Search applications by job title and employer
    @GetMapping("/user-{userID}/search")
    public List<Application> getApplicationsBySearch(@PathVariable long userID, @RequestParam String query){
        List<Application> applications;
        applications = applicationDAO.searchByString(userID, query);

        return applications;
    }

    @GetMapping("/application-{appID}")
    public Application getApplicationByID(@PathVariable long appID) {
        Application application = applicationDAO.findById(appID).get();
        return application;
    }
    @PostMapping("/user-{userID}/application-new")
    public ResponseEntity<Void> newApplication(
            @PathVariable long userID,
            @RequestBody Map<String, Object> payload){


        Application application = parserService.parseApplication(payload);
        URI uri = uriBuilderService.buildURI(application);

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/user-{userID}/application-{applicationID}")
    public ResponseEntity<Void> updateApplication(
            @PathVariable long userID,
            @PathVariable long applicationID,
            @RequestBody Map<String, Object> payload
    ) {
        Application application = parserService.parseApplication(payload);
        URI uri = uriBuilderService.buildURI(application);

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/user-{userID}/application-{applicationID}")
    public ResponseEntity<Void> deleteApplication(
            @PathVariable long userID,
            @PathVariable long applicationID) {

        Application application = applicationDAO.findById(applicationID).get();
        applicationDAO.delete(applicationID);
        URI uri = uriBuilderService.buildURI(application);

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/user-{userID}/download-applications",
            method = RequestMethod.GET, produces="text/csv")
    @ResponseBody()
    public String downloadApplications(
            @PathVariable Long userID
    ){
        User user = userDAO.findById(userID).get();
        List<Application> applications = applicationDAO.getApplicationsByUser(user);


        StringBuilder sb = new StringBuilder();
        String format = "\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"\n";
        sb.append("Job Title,Employer,Date Applied,Date Posted,Date Due,Status,Date Updated,\n,\n");
        for (Application application : applications) {


            String appRow = String.format(format,
                    application.getJobTitle(),
                    application.getEmployer(),
                    application.getDateApplied(),
                    application.getDatePosted(),
                    application.getDateDue(),
                    application.getStatus().getStatus(),
                    application.getDateUpdated()
                    );
            sb.append(appRow);
        }

        //String str = "Download File Test";

        System.out.println(sb);
        return sb.toString();
    }
}


