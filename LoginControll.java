package com.example.unit_management;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginControll {
    public static String idval;

    @FXML
    private TextField IDfield;

    @FXML
    private PasswordField PASSfield;

    @FXML
    private Button loginbutt;

    private Connection connection;
   public String user_type;

    // Method to initialize the database connection
    public void initialize() {
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to connect to database.");
        }
    }

    @FXML
    void dologin(ActionEvent event) {
        updateAircraftStatus();
        String username = IDfield.getText();
        String password = PASSfield.getText();
        idval=username;

        // Validate username and password
        if (authenticate(username, password)) {
            showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome " +  user_type+",click ok to go ahead");
            loadSceneBasedOnUserType(event); // Load the appropriate scene based on user type
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
        }
    }

    private boolean authenticate(String userId, String password) {
        String query = "SELECT password, type FROM user_ WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                user_type = resultSet.getString("type");
                // Replace with actual password hashing and comparison logic if passwords are hashed
                if (storedPassword.equals(password)) {
                    return true; // Login successful
                } else {
                    System.out.println("Wrong password for user ID: " + userId);
                    return false; // Wrong password
                }
            } else {
                System.out.println("Invalid user ID: " + userId);
                return false; // Invalid user ID
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void go_vote(ActionEvent actionEvent) {
        // Your existing logic for the go_vote method
    }

    // Method to load another FXML over the current one based on user type
    public void loadSceneBasedOnUserType(ActionEvent event) {
        String fxmlFile = "";
        switch (user_type) {
            case "admin":
                fxmlFile = "admin_dash.fxml"; // Replace with the actual FXML file for admin
                break;
            case "pilot":
                fxmlFile = "pilot.fxml"; // Replace with the actual FXML file for pilot
                break;
            case "PILOT":
                fxmlFile = "pilot.fxml";
                break;
            case "engineer":
                fxmlFile = "ENGINEER_DASH.fxml"; // Replace with the actual FXML file for engineer
                break;
            default:
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid user type.");
                return;
        }

        try {
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Loading Error", "Failed to load the new scene.");
        }
    }
    @FXML
    public void test(ActionEvent event)
    { updateAircraftStatus();try {
        // Load the new FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ENGINEER_DASH.fxml"));
        Parent root = loader.load();

        // Get the current stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the new scene
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } catch (Exception e) {
        e.printStackTrace();
        showAlert(Alert.AlertType.ERROR, "Loading Error", "Failed to load the new scene.");
    }}
    public static void updateAircraftStatus() {
        String updateQuery = "UPDATE aircraft " +
                "SET status = 'NEED OVERHAULTING' " +
                "WHERE active_hour >= svc_hour " +
                "OR next_date <= SYSDATE";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {

            int rowsUpdated = pstmt.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);

        } catch (SQLException e) {
            System.err.println("Error updating aircraft status");
            e.printStackTrace();
        }
    }
}
