package edu.wgu.jobjournalcapstone.Controller;

import edu.wgu.jobjournalcapstone.Data.DAO.UserDAO;
import edu.wgu.jobjournalcapstone.Data.Entity.User;
import edu.wgu.jobjournalcapstone.Service.ParserService;
import edu.wgu.jobjournalcapstone.Service.URIBuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://a-kansfield.github.io/job-journal/"
})
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private URIBuilderService uriBuilderService;
    @Autowired
    private ParserService parserService;

    @GetMapping("/user")
    public User getUser(
            @RequestBody User user
    ) {
        return user;
    }
    //    @GetMapping
    @GetMapping("/all")
    public List<User> getUsers() {

        List<User> users = userDAO.findAll();

        return users;
    }


    @PostMapping("/new")
        public ResponseEntity<Void> createUser(
            @RequestBody Map<String, Object> payload
            ) {

        User user = parserService.parseUser(payload);
        URI uri = uriBuilderService.buildURI(user);
        return ResponseEntity.created(uri).build();
    }

//    @PutMapping
//
//    @DeleteMapping
}
