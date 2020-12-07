package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GamingService {

    public List<String> getGames(){
        return Stream.of("CSGO", "NFS", "Prince Of Persia").collect(Collectors.toList());
    }
}
