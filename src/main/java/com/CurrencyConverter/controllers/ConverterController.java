package com.CurrencyConverter.controllers;

import com.CurrencyConverter.DTOs.ConvertResponse;
import com.CurrencyConverter.services.ConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/convert")
public class ConverterController {

    @Autowired
    private ConverterService service;

    @GetMapping
    public ConvertResponse converter(@RequestParam String from, @RequestParam String to, @RequestParam double amount) {
        return service.convertCurrency(from.toUpperCase(), to.toUpperCase(), amount);
    }
}
