package com.baboci.calculator.model;

public class Calculator {
    private Integer firstNumber;
    private Integer secondNumber;
    private Integer result;

    public Calculator(){

    }

    public Calculator(Integer firstNumber, Integer secondNumber,Integer result) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.result = result;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(Integer firstNumber) {
        this.firstNumber = firstNumber;
    }

    public Integer getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(Integer secondNumber) {
        this.secondNumber = secondNumber;
    }
}
