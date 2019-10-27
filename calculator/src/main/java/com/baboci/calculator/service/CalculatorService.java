package com.baboci.calculator.service;

import com.baboci.calculator.model.Calculator;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public Calculator multiply(Calculator calculator) {

        calculator.setResult(calculator.getFirstNumber() * calculator.getSecondNumber());

        return calculator;
    }

}
