package com.jujulioed.ATMProgressCheckerAPI.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("leaderboards")
public class Leaderboards {

    @GetMapping("/alive")
    public String healthCheck() {
        return "The API is alive";
    }

    @GetMapping
    public ArrayList<String> leaderboards() {
        ArrayList<String> lista = new ArrayList();
        lista.add("Julio");
        lista.add("Clara");
        return lista;
    }
}
