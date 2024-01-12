package br.com.appfastfood;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/healthCheck")
public class HealthCheckController {

    @GetMapping
    public String healthCheck() {
        return "OK";
    }
}
