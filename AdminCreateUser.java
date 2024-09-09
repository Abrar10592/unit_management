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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class AdminCreateUser {

    @FXML
    private Button crtacc;

    @FXML
    private Button refresh;

    @FXML
    private Button goback;

    @FXML
    private TextField nameField;

    @FXML
    private TextField USERTYPEFIELD;

    @FXML
    private TextField userIdField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField unitField;

    @FXML
    private AnchorPane createUserPane;

    @FXML
    private TextField acname;

    @FXML
    private TextField actail;

    @FXML
    private TextField country;

    @FXML
    private DatePicker indt;

    @FXML
    private TextField longi;

    @FXML
    private TextField svchr;

    @FXML
    private TextField typex;

    @FXML
    private TextField unit;

    @FXML
    private Button updt;

    @FXML
    void addaircraft(ActionEvent event) {
        String tailNo = actail.getText();
        String model = acname.getText();
        String type = typex.getText();
        String originCountry = country.getText();
        LocalDate dateOfInc = indt.getValue();
        String serviceHour = svchr.getText();
        String longivity = longi.getText();
        String unitfd = unitField.getText();
        String LONGII=longi.getText();

        // Validate inputs
        if (tailNo.isEmpty() || model.isEmpty() || type.isEmpty() || originCountry.isEmpty() ||
                dateOfInc == null || serviceHour.isEmpty() || longivity.isEmpty() ||LONGII.isEmpty() || unitfd.isEmpty()) {
            showAlert("Please fill in all fields.");
            return;
        }

        // Convert date to the required format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yy");
        String formattedDateOfInc = dateOfInc.format(formatter);
        String nextRepairDate = dateOfInc.plusMonths(Long.parseLong(longivity)).format(formatter);

        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "INSERT INTO aircraft (TAILNO, NAME_, SVC_HOUR, ACTIVE_HOUR, ORIGINYEAR, COUNTRY, TYPEX, STATUS, UNIT_ID,longivity)" +
                    " VALUES (?, ?, ?, 0, TO_DATE(?, 'DD-MON-YY'), ?, ?, 'ok', ?,?)";

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, tailNo);
            pstmt.setString(2, model);
            pstmt.setInt(3, Integer.parseInt(serviceHour));
            pstmt.setString(4, formattedDateOfInc);
            pstmt.setString(5, originCountry);
            pstmt.setString(6, type);
 //           pstmt.setString(7, nextRepairDate);
            pstmt.setInt(7, Integer.parseInt(unitfd));
            pstmt.setInt(8,Integer.parseInt(LONGII));

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                showAlert("Aircraft added successfully!");
                clearAircraftFields(); // Clear input fields after successful update
            } else {
                showAlert("Failed to add aircraft.");
            }

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            showAlert("Error occurred while adding aircraft: " + e.getMessage());
            e.printStackTrace();
        }
        updateAircraftStatus();
    }

    @FXML
    void createUser(ActionEvent event) {
        String name = nameField.getText();
        String userId = userIdField.getText();
        String password = passwordField.getText();
        String unitId = unitField.getText();
        String userType = USERTYPEFIELD.getText(); // Get the user type input

        // Validate inputs
        if (name.isEmpty() || userId.isEmpty() || password.isEmpty() || unitId.isEmpty() || userType.isEmpty()) {
            showAlert("Please fill in all fields.");
            return;
        }

        // Insert into database
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "INSERT INTO user_ (ID, DOB, NAME, EMAIL, PASSWORD, HOUSE, ROAD, DISTRICT, CITY, UNIT_ID, TYPE)" +
                    " VALUES (?, NULL, ?, NULL, ?, NULL, NULL, NULL, NULL, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userId);
            pstmt.setString(2, name);
            pstmt.setString(3, password);
            pstmt.setString(4, unitId);
            pstmt.setString(5, userType); // Set the user type
            pstmt.executeUpdate();

            showAlert("User created successfully!");
            clearUserFields(); // Clear input fields after successful insertion

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            showAlert("Error occurred while creating user: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void clearAircraftFields() {
        acname.clear();
        actail.clear();
        country.clear();
        indt.setValue(null);
        longi.clear();
        svchr.clear();
        typex.clear();
        unit.clear();
    }

    private void clearUserFields() {
        nameField.clear();
        userIdField.clear();
        passwordField.clear();
        unitField.clear();
        USERTYPEFIELD.clear();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void clearFields(ActionEvent event) {
        clearUserFields();
    }

    public void loadSceneBasedOnUserType(ActionEvent event) {
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

    private void showAlert2(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
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
