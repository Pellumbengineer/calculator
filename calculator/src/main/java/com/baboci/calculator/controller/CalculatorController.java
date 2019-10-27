package com.baboci.calculator.controller;

import com.baboci.calculator.model.Calculator;
import com.baboci.calculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService ;

    @RequestMapping(value = "/multiply", method = RequestMethod.POST)
    public Calculator multiplication (@RequestBody Calculator cal){
       return calculatorService.multiply(cal);
    }


}
