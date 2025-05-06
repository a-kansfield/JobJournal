package edu.wgu.jobjournalcapstone.Controller;

import edu.wgu.jobjournalcapstone.Data.DAO.StatusDAO;
import edu.wgu.jobjournalcapstone.Data.Entity.Status;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://a-kansfield.github.io/job-journal/"
})
@RequestMapping("/status")
public class StatusController {

    private final StatusDAO statusDAO;

    public StatusController(StatusDAO statusDAO) {
        this.statusDAO = statusDAO;
    }

    @GetMapping("/all")
    public List<Status> getAllStatuses(){
        List<Status> statuses;
        return statuses = statusDAO.findAll();
    }


}
