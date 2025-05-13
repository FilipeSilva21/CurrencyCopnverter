package com.CurrencyConverter.DTOs;

public record ConvertResponse(String from, String to, double originalValue, double convertedValue){}
