module CalculatorClient {
    requires javafx.controls;
    requires javafx.base;
    requires javafx.fxml;
    requires  org.json;

    opens sample;
    opens sample.controller;

}