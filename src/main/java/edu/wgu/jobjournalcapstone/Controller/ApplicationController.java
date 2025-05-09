package edu.wgu.jobjournalcapstone.Controller;

import edu.wgu.jobjournalcapstone.Data.DAO.ApplicationDAO;
import edu.wgu.jobjournalcapstone.Data.DAO.StatusDAO;
import edu.wgu.jobjournalcapstone.Data.DAO.UserDAO;
import edu.wgu.jobjournalcapstone.Data.Entity.Application;
import edu.wgu.jobjournalcapstone.Data.Entity.Status;
import edu.wgu.jobjournalcapstone.Data.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
    public Application getApplicationByID(@PathVariable long userID, @PathVariable long appID) {
        Application application = applicationDAO.findById(appID).get();
        return application;
    }
    @PostMapping("/user-{userID}/application-new")
    public ResponseEntity<Void> newApplication(
            @PathVariable long userID,
            @RequestBody Application application){

        Application savedApp = applicationDAO.save(application);

        //Generate response entitiy
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(savedApp)
                .toUri();

        return ResponseEntity.created(uri).build();
    }




}
