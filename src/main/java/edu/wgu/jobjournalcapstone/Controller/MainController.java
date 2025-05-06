package edu.wgu.jobjournalcapstone.Controller;

import edu.wgu.jobjournalcapstone.Data.DAO.UserDAO;
import edu.wgu.jobjournalcapstone.Data.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://a-kansfield.github.io/job-journal/"
})
public class MainController {

//    @Autowired
//    private UserDAO userDAO;
//
//    @GetMapping("/")
//    public List<User> getUsers() {
//
//
//        List<User> users = userDAO.findAll();
//
//        return users;
//    }
}
