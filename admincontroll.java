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

public class admincontroll {

    public static String user_type;

    @FXML
    private Button create_;
    @FXML
    private  Button phonedirec;
    @FXML
    private Button removeunit;
    @FXML
    private Button removeac;
    @FXML
    private Button deleteuser;
    @FXML
    private Button updatebutt;
    @FXML
    private Button addac;
    @FXML
    private Button GOBACK;
    @FXML
    private Button viewAircraft;

    @FXML
    private Button searchuser;
    @FXML
    private  TextField user;

    @FXML
    private AnchorPane create_user;

    @FXML
    private TableView<Aircraft> actable;
    @FXML
    private TableView<User> usertable;

    @FXML
    private TableColumn<Aircraft, String> tail;
    @FXML
    private TableColumn<Aircraft, String> model;
    @FXML
    private TableColumn<Aircraft, String> svchr;
    @FXML
    private TableColumn<Aircraft, String> actvhr;
    @FXML
    private TableColumn<Aircraft, String> incdate;
    @FXML
    private TableColumn<Aircraft, String> country;
    @FXML
    private TableColumn<Aircraft, String> type;
    @FXML
    private TableColumn<Aircraft, String> status;
    @FXML
    private TableColumn<Aircraft, String> unit;
    @FXML
    private TableColumn<Aircraft, String> nextRepair;
    @FXML
    private TextField ac;

    @FXML
    private TableColumn<User, String> id;
    @FXML
    private TableColumn<User, String> name;
    @FXML
    private TableColumn<User, String> dob;
    @FXML
    private TableColumn<User, String> unitid;
    @FXML
    private TableColumn<User, String> address;
    @FXML
    private TableColumn<User, String> tp;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private Button search;

    @FXML
    private Button viewuser;
    @FXML
    private Label USERINFO;

    @FXML
    private Label lbl1;

    @FXML
    public void initialize() {

    }

    @FXML
    private void loadCreateUserView(ActionEvent event) {
        try {actable.setVisible(false);
            usertable.setVisible(false);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin_create_user.fxml"));
            Parent createUserView = loader.load();
            create_user.getChildren().setAll(createUserView);
        } catch (IOException e) {
            System.err.println("Failed to load FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void loadacView(ActionEvent event) {
        usertable.setVisible(false);
        user.setVisible(false);
        searchuser.setVisible(false);
        try { actable.setVisible(false);
            usertable.setVisible(false);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ADD_AC.fxml"));
            Parent addAcView = loader.load();
            create_user.getChildren().setAll(addAcView);
        } catch (IOException e) {
            System.err.println("Error loading ADD_AC.fxml");
            e.printStackTrace();
        }
    }

    @FXML
    private void loadUpdateView(ActionEvent event) {
        try { actable.setVisible(false);
            usertable.setVisible(false);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("update.fxml"));
            Parent updateView = loader.load();
            create_user.getChildren().setAll(updateView);
        } catch (IOException e) {
            System.err.println("Error loading update.fxml");
            e.printStackTrace();
        }
    }

    @FXML
    private void loadAircraftData(ActionEvent event) { usertable.setVisible(false);

        user.setVisible(false);
        searchuser.setVisible(false);
        ObservableList<Aircraft> aircraftList = FXCollections.observableArrayList();

        // Setup table columns
        tail.setCellValueFactory(new PropertyValueFactory<>("tailNo"));
        model.setCellValueFactory(new PropertyValueFactory<>("model"));
        svchr.setCellValueFactory(new PropertyValueFactory<>("serviceHours"));
        actvhr.setCellValueFactory(new PropertyValueFactory<>("activeHours"));
        incdate.setCellValueFactory(new PropertyValueFactory<>("incidentDate"));
        country.setCellValueFactory(new PropertyValueFactory<>("country"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        unit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        nextRepair.setCellValueFactory(new PropertyValueFactory<>("nextRepair"));

        // Fetch data from the database
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM aircraft")) {

            while (rs.next()) {
                Aircraft aircraft = new Aircraft(
                        rs.getString("TAILNO"),
                        rs.getString("NAME_"),
                        rs.getString("SVC_HOUR"),
                        rs.getString("ACTIVE_HOUR"),
                        rs.getString("ORIGINYEAR"),
                        rs.getString("COUNTRY"),
                        rs.getString("TYPEX"),
                        rs.getString("NEXT_DATE"),
                        rs.getString("STATUS"),
                        rs.getString("UNIT_ID")
                );
                aircraftList.add(aircraft);
            }
        } catch (Exception e) {
            System.err.println("Error loading aircraft data from database");
            e.printStackTrace();
        }

        actable.setItems(aircraftList);
        actable.setVisible(true);
        lbl1.setVisible(true);
        GOBACK.setVisible(true);
        ac.setVisible(true);
        search.setVisible(true);
    }

    @FXML
    public void loadUserData(ActionEvent event) {
        actable.setVisible(false);
        search.setVisible(false);
        user.setVisible(true);
        searchuser.setVisible(true);
        lbl1.setVisible(false);
        ObservableList<User> userList = FXCollections.observableArrayList();

        // Setup table columns
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        tp.setCellValueFactory(new PropertyValueFactory<>("type"));
        unitid.setCellValueFactory(new PropertyValueFactory<>("unitId"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));

        // Fetch data from the database
        String query = "SELECT ID, NAME, DOB, TYPE, EMAIL, UNIT_ID, HOUSE || '/' || ROAD || ',' || CITY || ',' || DISTRICT AS ADDRESS FROM USER_";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String id = rs.getString("ID");
                String name = rs.getString("NAME");
                String dob = rs.getDate("DOB") != null ? rs.getDate("DOB").toString() : "N/A";
                String type = rs.getString("TYPE");
                String email = rs.getString("EMAIL");
                String unitId = rs.getString("UNIT_ID");
                String address = rs.getString("ADDRESS");

                User user = new User(id, name, dob, type, email, unitId, address);
                userList.add(user);
                System.out.println("User added: " + user.getName());
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error loading user data from database: " + e.getMessage());
            e.printStackTrace();
        }

        usertable.setItems(userList);
        usertable.setVisible(true); // Show the table view
        GOBACK.setVisible(true);
        USERINFO.setVisible(true);
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
public void loadpd(ActionEvent event)
{actable.setVisible(false);
    usertable.setVisible(false);
    try {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("phonedirectory.fxml"));
    Parent updateView = loader.load();
    create_user.getChildren().setAll(updateView);
} catch (IOException e) {
    System.err.println("Error loading update.fxml");
    e.printStackTrace();
}
}
    private void showAlert2(Alert.AlertType alertType, String loadingError, String s) {
    }

    public void searchac(ActionEvent event) {
        ObservableList<Aircraft> aircraftList = FXCollections.observableArrayList();
        String A = ac.getText().trim().toUpperCase(); // Convert input to uppercase and trim

        // Setup table columns
        tail.setCellValueFactory(new PropertyValueFactory<>("tailNo"));
        model.setCellValueFactory(new PropertyValueFactory<>("model"));
        svchr.setCellValueFactory(new PropertyValueFactory<>("serviceHours"));
        actvhr.setCellValueFactory(new PropertyValueFactory<>("activeHours"));
        incdate.setCellValueFactory(new PropertyValueFactory<>("incidentDate"));
        country.setCellValueFactory(new PropertyValueFactory<>("country"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        unit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        nextRepair.setCellValueFactory(new PropertyValueFactory<>("nextRepair"));

        // Fetch data from the database
        String query = "SELECT * FROM aircraft WHERE upper(tailno) like ? OR upper(NAME_) like ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            String sk2 = "%" + A+ "%";
            pstmt.setString(1, sk2);
            pstmt.setString(2, sk2);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Aircraft aircraft = new Aircraft(
                            rs.getString("TAILNO"),
                            rs.getString("NAME_"),
                            rs.getString("SVC_HOUR"),
                            rs.getString("ACTIVE_HOUR"),
                            rs.getString("ORIGINYEAR"),
                            rs.getString("COUNTRY"),
                            rs.getString("TYPEX"),
                            rs.getString("NEXT_DATE"),
                            rs.getString("STATUS"),
                            rs.getString("UNIT_ID")

                    );
                    aircraftList.add(aircraft);
                }
            }
        } catch (Exception e) {
            System.err.println("Error loading aircraft data from database");
            e.printStackTrace();
        }

        actable.setItems(aircraftList);
        actable.setVisible(true);
        lbl1.setVisible(true);
        GOBACK.setVisible(true);
    }

    public void searchuser_(ActionEvent event) {
        String sk = user.getText().trim().toUpperCase(); // Trim and convert input to uppercase
        lbl1.setVisible(false);
        ObservableList<User> userList = FXCollections.observableArrayList();

        // Setup table columns
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        tp.setCellValueFactory(new PropertyValueFactory<>("type"));
        unitid.setCellValueFactory(new PropertyValueFactory<>("unitId"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));

        // Determine if the input is numeric
        boolean isNumeric = sk.matches("\\d+");
        String query;

        if (isNumeric) {
            // Input is numeric, use ID in the query
            query = "SELECT ID, NAME, DOB, TYPE, EMAIL, UNIT_ID, HOUSE || '/' || ROAD || ',' || CITY || ',' || DISTRICT AS ADDRESS " +
                    "FROM USER_ WHERE ID = ?";
        } else {
            // Input is not numeric, use NAME in the query
            query = "SELECT ID, NAME, DOB, TYPE, EMAIL, UNIT_ID, HOUSE || '/' || ROAD || ',' || CITY || ',' || DISTRICT AS ADDRESS " +
                    "FROM USER_ WHERE UPPER(NAME) LIKE ?";
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            if (isNumeric) {
                pstmt.setString(1, sk);
            } else {
                String sk2 = "%" + sk + "%";
                pstmt.setString(1, sk2);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("ID");
                    String name = rs.getString("NAME");
                    String dob = rs.getDate("DOB") != null ? rs.getDate("DOB").toString() : "N/A";
                    String type = rs.getString("TYPE");
                    String email = rs.getString("EMAIL");
                    String unitId = rs.getString("UNIT_ID");
                    String address = rs.getString("ADDRESS");

                    User user = new User(id, name, dob, type, email, unitId, address);
                    userList.add(user);
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error loading user data from database: " + e.getMessage());
            e.printStackTrace();
        }

        usertable.setItems(userList);
        usertable.setVisible(true); // Show the table view
        GOBACK.setVisible(true);
        USERINFO.setVisible(true);
        ac.setVisible(false);
        search.setVisible(false);
        user.setVisible(true);
        searchuser.setVisible(true);
    }



    public void deleteuser(ActionEvent event) {
        actable.setVisible(false);
        usertable.setVisible(false);try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DEL_user.fxml"));
        Parent updateView = loader.load();
        create_user.getChildren().setAll(updateView);
    } catch (IOException e) {
        System.err.println("Error loading update.fxml");
        e.printStackTrace();
    }}

    public void removeac(ActionEvent event) {
    }

    public void deleteunit(ActionEvent event) {
    }

    public void addunit(ActionEvent event) {
    }
}
