package edu.wgu.jobjournalcapstone.Controller;

import edu.wgu.jobjournalcapstone.Data.DAO.UserDAO;
import edu.wgu.jobjournalcapstone.Data.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://a-kansfield.github.io/job-journal/"
})
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDAO userDAO;
//    @GetMapping
@GetMapping("/all")
public List<User> getUsers() {

    List<User> users = userDAO.findAll();

    return users;
}


//    @PostMapping
//
//    @PutMapping
//
//    @DeleteMapping
}
