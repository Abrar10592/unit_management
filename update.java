package com.example.unit_management;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class update {

    @FXML
    public Button GOBACK;

    @FXML
   public TextField city;

    @FXML
  public TextField district;

    @FXML
   public DatePicker dob;

    @FXML
   public TextField email;

    @FXML
  public TextField house;

    @FXML
  public TextField phn1;

    @FXML
    public TextField phn2;

    @FXML
    public Button refresh;

    @FXML
    public TextField road;

    @FXML
    public Button updatebutt;

   public String idno;

    public update() {
        idno = LoginControll.idval;
    }

    @FXML
    public  void updateuser(ActionEvent event) {
        String cityValue = city.getText();
        String districtValue = district.getText();
        String dobValue = (dob.getValue() != null) ? Date.valueOf(dob.getValue()).toString() : null;
        String emailValue = email.getText();
        String houseValue = house.getText();
        String phn1Value = phn1.getText();
        String phn2Value = phn2.getText();
        String roadValue = road.getText();

        Connection connection = null;

        try {
            connection = DatabaseConnection.getConnection();
            connection.setAutoCommit(false);

            // Update the user_ table
            String updateUserQuery = "UPDATE user_ SET DOB = ?, EMAIL = ?, HOUSE = ?, ROAD = ?, DISTRICT = ?, CITY = ? WHERE ID = ?";
            try (PreparedStatement updateUserStmt = connection.prepareStatement(updateUserQuery)) {
                if (dobValue != null) {
                    updateUserStmt.setDate(1, Date.valueOf(dob.getValue()));
                } else {
                    updateUserStmt.setNull(1, java.sql.Types.DATE);
                }
                updateUserStmt.setString(2, emailValue);
                updateUserStmt.setString(3, houseValue);
                updateUserStmt.setString(4, roadValue);
                updateUserStmt.setString(5, districtValue);
                updateUserStmt.setString(6, cityValue);
                updateUserStmt.setString(7, idno);
                updateUserStmt.executeUpdate();
            }

            // Insert into the phoneno table
            String insertPhoneQuery = "INSERT INTO phoneno (PHONENO, ID) VALUES (?, ?)";
            try (PreparedStatement insertPhoneStmt = connection.prepareStatement(insertPhoneQuery)) {
                insertPhoneStmt.setString(1, phn1Value);
                insertPhoneStmt.setString(2, idno);
                insertPhoneStmt.executeUpdate();
                if (!phn2Value.isEmpty()) {
                    insertPhoneStmt.setString(1, phn2Value);
                    insertPhoneStmt.executeUpdate();
                }
            }

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException closeException) {
                    closeException.printStackTrace();
                }
            }
        }

        System.out.println(idno);
        showAlert("User created successfully!");
    }
   public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    public void clearfields(ActionEvent event) {
        city.clear();
        dob.setValue(null);
        district.clear();
        road.clear();
        email.clear();
        phn1.clear();
        phn2.clear();
        house.clear();
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
            showAlert2(Alert.AlertType.ERROR, "Loading Error", "Failed to load the new scene.");
        }
    }

   public void showAlert2(Alert.AlertType alertType, String loadingError, String s) {
    }

}
