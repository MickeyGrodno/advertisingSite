package ru.senla.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest")
public class RestController1 {
    @GetMapping
    public String getText() {
        String text = "New Text!";
        return text;
    }
}
