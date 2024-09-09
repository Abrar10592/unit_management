package com.example.unit_management;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DELUSER {

    @FXML
    private TextField userIdField;

    @FXML
    private Button confirmButton;

    @FXML
    private Button closeButton;

    @FXML
    void confirmDeletion(ActionEvent event) {
        String userId = userIdField.getText();

        if (userId.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Please enter a user ID.");
            return;
        }

        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            // Disable auto-commit mode
            conn.setAutoCommit(false);

            // Delete from phoneno table first
            String deletePhonenoQuery = "DELETE FROM phoneno WHERE ID = ?";
            try (PreparedStatement deletePhonenoStmt = conn.prepareStatement(deletePhonenoQuery)) {
                deletePhonenoStmt.setString(1, userId);
                deletePhonenoStmt.executeUpdate();
                System.out.println("hi1");
            }

           

            // Delete from user_ table
            String deleteUserQuery = "DELETE FROM user_ WHERE ID = ?";
            try (PreparedStatement deleteUserStmt = conn.prepareStatement(deleteUserQuery)) {
                deleteUserStmt.setString(1, userId);
                deleteUserStmt.executeUpdate();
                System.out.println("hi3");
            }

            // Commit the transaction
            conn.commit();

            showAlert(Alert.AlertType.INFORMATION, "Deletion Successful", "User and related records deleted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            showAlert(Alert.AlertType.ERROR, "Deletion Error", "Failed to delete user and related records.");
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @FXML
    void closeWindow(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin_dash.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert2(Alert.AlertType.ERROR, "Loading Error", "Failed to load the new scene.");
        }
    }

    private void showAlert2(Alert.AlertType alertType, String loadingError, String s) {
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
