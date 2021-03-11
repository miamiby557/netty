package com.szcinda.express.controller;

import com.szcinda.express.controller.dto.Result;
import com.szcinda.express.service.UserIdentity;
import com.szcinda.express.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/home")
public class HomeController {

    /*private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String hello() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @PostMapping("/authenticate")
    public Result<UserIdentity> login(@RequestParam("account") String account, @RequestParam("password") String password) {
        UserIdentity identity = userService.getUserIdentity(account, password);
        String token = userService.getToken(identity.getId(), identity.getPassword());
        identity.setToken(token);
        return Result.success(identity);
    }*/
}
