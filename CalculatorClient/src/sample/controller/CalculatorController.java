package sample.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

public class CalculatorController implements Initializable {

    private int firstNumber = 0;
    private int secondNumber = 0;
    private int result = 0;

    @FXML
    private TextField firstNumbertxt;

    @FXML
    private TextField secondNumbertxt;

    @FXML
    private Label resultLabel;

    @FXML
    private Label warningLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        firstNumbertxt.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(8));
        secondNumbertxt.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(8));
    }

    private EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();
                if (txt_TextField.getText().length() >= max_Lengh) {
                    e.consume();
                }
                if(e.getCharacter().matches("[0-9.]")){
                    if(txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")){
                        e.consume();
                    }else if(txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")){
                        e.consume();
                    }
                }else{
                    e.consume();
                }
            }
        };
    }

    @FXML
    void clearClick(ActionEvent event) {
        firstNumbertxt.setText("");
        secondNumbertxt.setText("");
        resultLabel.setText("Result");
    }

    @FXML
    void multClick(ActionEvent event) {

        if (!firstNumbertxt.getText().trim().isEmpty() && !secondNumbertxt.getText().trim().isEmpty()) {
            firstNumber = Integer.valueOf(firstNumbertxt.getText());
            secondNumber = Integer.valueOf(secondNumbertxt.getText());
            try {
                multiplyPost(firstNumber, secondNumber);
            } catch (Exception e) {

            }
            warningLabel.setText("");
            resultLabel.setText(firstNumber + "*" + secondNumber + "=" + result);
        }else {
            System.out.println("please write the number you want to multiply");
            warningLabel.setText("Please write the number you want to multiply");
        }

    }

    public void multiplyPost(int firstNumber, int secondNumber) throws Exception {

        URL url = new URL("http://localhost:8080/multiply");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        conn.setDoOutput(true);

        String message;
        JSONObject json = new JSONObject();
        json.put("firstNumber", firstNumber);
        json.put("secondNumber", secondNumber);
        json.put("result", result);
        message = json.toString();

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = message.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            JSONObject obj = new JSONObject(response.toString());
            result = (Integer) obj.get("result");

            System.out.println(result);
        }
    }

}
