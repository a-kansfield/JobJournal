package edu.wgu.jobjournalcapstone.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView response = new ModelAndView("index");
        return response;
    }
}
