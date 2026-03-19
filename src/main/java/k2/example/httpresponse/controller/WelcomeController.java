package k2.example.httpresponse.controller;

import k2.example.httpresponse.entity.Welcome;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    private static final String template = "Welcome %s!";

    @GetMapping("/welcome")
    public ResponseEntity<Welcome> welcome(@RequestParam(required = false) String name) {
        if (name == null || name.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Welcome welcome = new Welcome(String.format(template, name));
        return ResponseEntity.ok(welcome);
    }
}
