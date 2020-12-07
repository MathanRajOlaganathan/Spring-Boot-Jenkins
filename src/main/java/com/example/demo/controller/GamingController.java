package com.example.demo.controller;

import com.example.demo.service.GamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GamingController {

    @Autowired
    private GamingService gamingService;


    @GetMapping("/games")
    public List<String> getGames(){
            return gamingService.getGames();
    }


}
