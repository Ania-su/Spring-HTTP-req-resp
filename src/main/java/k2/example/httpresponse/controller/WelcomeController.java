package k2.example.httpresponse.controller;

import k2.example.httpresponse.entity.Welcome;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    private static final String template = "Welcome %s!";

    @GetMapping("/welcome")
    public Welcome welcome(@RequestParam(defaultValue = "") String name) {
        return new Welcome(String.format(template, name));
    }
}
