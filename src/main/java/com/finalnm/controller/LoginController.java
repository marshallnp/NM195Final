package com.finalnm.controller;

import com.finalnm.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.scene.control.Label;
import java.time.ZoneId;
import javafx.scene.control.Label;
import java.util.Locale;

public class LoginController {
    @FXML
    private TextField LoginField;
    @FXML
    private PasswordField PasswordField;
    @FXML
    private Label LocationLabel;
    @FXML
    private Label LanguageLabel;

    public void initialize() {
        displayUserTimeZone();
        displayUserLanguage();
    }

    private void displayUserTimeZone() {
        ZoneId zoneId = ZoneId.systemDefault();
        LocationLabel.setText("Location: " + zoneId.toString());
    }
    private void displayUserLanguage() {
        Locale locale = Locale.getDefault();
        String language = locale.getDisplayLanguage();
        LanguageLabel.setText("Language: " + language);
    }
    public void OnExitClick(ActionEvent actionEvent) {
        System.out.println("Exiting the application");
        System.exit(0);
    }

    public void LoginButtonClick(ActionEvent actionEvent) {
        String username = LoginField.getText();
        String password = PasswordField.getText();

        if (authenticateUser(username, password)) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("dashboard.fxml"));
                Parent root = fxmlLoader.load();

                Stage dashboardStage = new Stage();
                dashboardStage.setScene(new Scene(root));
                dashboardStage.show();

                Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                currentStage.close();
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception, maybe show an error message to the user
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password.");

            alert.showAndWait();
        }
    }

    private boolean authenticateUser(String username, String password) {
        String sql = "SELECT User_Name, Password FROM users WHERE User_Name = ? AND Password = ?";
        try (Connection conn = helper.JDBC.openConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                return true; // User found
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception
        }
        return false; // User not found or error occurred
    }
}
