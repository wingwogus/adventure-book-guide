package findark.adventure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect:/regions";
    }

    @GetMapping("/search")
    public String search() {
        return "search-item-id";
    }

}
