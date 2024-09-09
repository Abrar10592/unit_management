package com.example.unit_management;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;


public class Pilot {
    @FXML
    private AnchorPane create_user;
    @FXML
    private TextField tailNoField;

    @FXML
    private TextField hoursOfFlyingField;

    @FXML
    private DatePicker dateField;

    @FXML
    private Button updatelog;
    public void logupdate(ActionEvent event){ try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FLYING_LOG.fxml"));
        Parent updateView = loader.load();
        create_user.getChildren().setAll(updateView);
    } catch (IOException e) {
        System.err.println("Error loading update.fxml");
        e.printStackTrace();
    }
    }

    @FXML
    public void updatelog(ActionEvent event) {
        String tailNo = tailNoField.getText();
        String hoursOfFlyingStr = hoursOfFlyingField.getText();
        try {
            int hoursOfFlying = Integer.parseInt(hoursOfFlyingStr);

            // Fetch current ACTIVE_HOUR
            String selectQuery = "SELECT ACTIVE_HOUR FROM aircraft WHERE TAILNO = ?";
            String updateQuery = "UPDATE aircraft SET ACTIVE_HOUR = ? WHERE TAILNO = ?";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement selectStmt = conn.prepareStatement(selectQuery)) {

                selectStmt.setString(1, tailNo);

                ResultSet rs = selectStmt.executeQuery();
                if (rs.next()) {
                    int currentActiveHour = rs.getInt("ACTIVE_HOUR");
                    int updatedActiveHour = currentActiveHour + hoursOfFlying;

                    // Update ACTIVE_HOUR
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                        updateStmt.setInt(1, updatedActiveHour);
                        updateStmt.setString(2, tailNo);
                        updateStmt.executeUpdate();
                    }

                    showAlert(Alert.AlertType.INFORMATION, "Success", "Aircraft log updated successfully.");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Aircraft with TAILNO " + tailNo + " not found.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to update aircraft log.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please enter a valid number for hours of flying.");
        }
        updateAircraftStatus();
    }



    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public void update(ActionEvent event) {
    }

    public void cp(ActionEvent event) {
    }
    public static void updateAircraftStatus() {
        String updateQuery = "UPDATE aircraft " +
                "SET status = 'NEED OVERHAULTING' " +
                "WHERE active_hour >= svc_hour " +
                "OR nextrepair_date <= SYSDATE";

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
