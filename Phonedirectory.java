package com.example.unit_management;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import  javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Phonedirectory {

    @FXML
    private TextField entry;

    @FXML
    private Button goback;

    @FXML
    private TableView<PhoneEntry> phonetable;

    @FXML
    private Button search;

    @FXML
    private TableColumn<PhoneEntry, String> nameColumn;

    @FXML
    private TableColumn<PhoneEntry, String> phoneNoColumn;

    public void initialize() {
       nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneNoColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
    }

    public void search(ActionEvent event) {
        String input = entry.getText().trim();
        if (input.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Input Error", "Please enter a name or ID.");
            return;
        }

        Connection connection = null;
        try {
            connection = DatabaseConnection.getConnection();
            String query;
            if (isNumeric(input)) {
                query = "SELECT name, phoneno FROM phoneno JOIN user_ USING(id) WHERE id = ?";
            } else {
                query = "SELECT name, phoneno FROM phoneno JOIN user_ USING(id) WHERE UPPER(name) LIKE ?";
                input = "%" + input.toUpperCase() + "%";
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                if (isNumeric(input)) {
                    preparedStatement.setInt(1, Integer.parseInt(input));
                } else {
                    preparedStatement.setString(1, input);
                }
                ResultSet resultSet = preparedStatement.executeQuery();

                phonetable.getItems().clear();
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String phoneNo = resultSet.getString("phoneno");
                    phonetable.getItems().add(new PhoneEntry(name, phoneNo));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to retrieve data from database.");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void goback_(ActionEvent event) {
        System.out.println("Button clicked");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin_dash.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Loading Error", "Failed to load the new scene.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    public static class PhoneEntry {
        private String name;
        private String phoneNo;

        public PhoneEntry(String name, String phoneNo) {
            this.name = name;
            this.phoneNo = phoneNo;
        }

        public String getName() {
            return name;
        }

        public String getPhoneNo() {
            return phoneNo;
        }
    }
}
