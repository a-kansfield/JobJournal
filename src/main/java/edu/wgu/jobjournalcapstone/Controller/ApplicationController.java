package edu.wgu.jobjournalcapstone.Controller;

import edu.wgu.jobjournalcapstone.Data.DAO.ApplicationDAO;
import edu.wgu.jobjournalcapstone.Data.Entity.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/all")
    public List<Application> getAllApplications() {
        System.out.println("Test");
        return applicationDAO.findAll();
    }

}
