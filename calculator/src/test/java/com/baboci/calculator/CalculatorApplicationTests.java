package com.baboci.calculator;

import com.baboci.calculator.model.Calculator;
import com.baboci.calculator.service.CalculatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CalculatorApplicationTests {

	@Autowired
	private CalculatorService calculatorService;

	@Test
	void multiplicationTest() {

		Calculator calculator = new Calculator(3,5,15);
		Calculator calculatorTest = calculatorService.multiply(calculator);
		assert(calculatorTest.getResult().equals(15));

	}



}
