package com.example.unit_management;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;


import java.sql.*;

public class ENGINEERDASH {
 update u=new update();
 @FXML
 private  Button pclr;
 @FXML
 private TextField pclrtxt;

    @FXML
    private TableColumn<P_REPAIR,Integer > P_ID;

    @FXML
    private TableColumn<P_REPAIR, String> P_INDATE;



    @FXML
    private TableColumn<P_REPAIR, String> P_MODEL;

    @FXML
    private TableColumn<P_REPAIR, String> P_OUTDATE;

    @FXML
    private TableColumn<P_REPAIR, String> P_REASON;

    @FXML
    private Button P_DOSEARCH;

    @FXML
    private TextField P_SEARCH;

    @FXML
    private TableView<P_REPAIR>P_TABLE;

    @FXML
    private TableColumn<P_REPAIR, String> P_TAIL;

    @FXML
    private TableColumn<P_REPAIR, String> P_TYPE;

    @FXML
    private TableColumn<P_REPAIR, String> P_UNIT;
    @FXML
    private  TableColumn<P_REPAIR,String> P_ENTRY;

 @FXML
 private AnchorPane P_HIS;
    private ObservableList<P_REPAIR> repairList2 = FXCollections.observableArrayList();
    private ObservableList<REPAIR> repairList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<REPAIR,Integer > R_ID;

    @FXML
    private TableColumn<REPAIR, String> R_INDATE;
    @FXML
    private TextField o_id1;

    @FXML
    private AnchorPane his_ac;
    @FXML
    private Button clr;
    @FXML
    private TextField clr_txt;


    @FXML
    private TableColumn<REPAIR, String> R_MODEL;

    @FXML
    private TableColumn<REPAIR, String> R_OUTDATE;

    @FXML
    private TableColumn<REPAIR, String> R_REASON;

    @FXML
    private Button R_SB;

    @FXML
    private TextField R_SEARCH;

    @FXML
    private TableView<REPAIR>R_TABLE;

    @FXML
    private TableColumn<REPAIR, String> R_TAIL;

    @FXML
    private TableColumn<REPAIR, String> R_TYPE;

    @FXML
    private TableColumn<REPAIR, String> R_UNIT;
    @FXML
    private TableView<AC> ac_tableview;
    @FXML
    private TextField AC_SEARCH;

    @FXML
    private TableColumn<AC, String> A_COMANY;
    @FXML
    private TableColumn<AC,String> remarks;

    @FXML
    private TableColumn<AC, String> A_HRL;

    @FXML
    private TableColumn<AC, String> A_LASTREASON;

    @FXML
    private TableColumn<AC, String> A_MODEL;

    @FXML
    private TableColumn<AC, String> A_NEXTREPAIR;

    @FXML
    private TableColumn<AC, String> A_STAT;

    @FXML
    private TableColumn<AC, String> A_TAIL;
    @FXML
    private Button SEARC_AC;
    @FXML
    private Button LOGOUT;

    @FXML
    private TextField c_email;
    @FXML
    private  AnchorPane ac_table;

    @FXML
    private TextField c_id;

    @FXML
    private TextField c_name;

    @FXML
    private TextField c_phn;

    @FXML
    private Button change_pass;

    @FXML
    private Button clear_ac;

    @FXML
    private DatePicker date_c;

    @FXML
    private TextField e_no;

    @FXML
    private Button goback;

    @FXML
    private Button history_ac;

    @FXML
    private Button history_parts;

    @FXML
    private TextField longi;

    @FXML
    private Button o_status;

    @FXML
    private TextField p_id;

    @FXML
    private Button p_status;

    @FXML
    private Button purchase;

    @FXML
    private AnchorPane purchase_anchor;

    @FXML
    private TextField s_hr;

    @FXML
    private TextField t_no;

    @FXML
    private Button update_info;

    @FXML
    private Button update_purchase;
    @FXML
    private  AnchorPane update_anchor;

    @FXML
    private AnchorPane parts_table;

    @FXML
    private TableView<PartStatus> PARTS_STAT_TABLE;

    @FXML
    private TableColumn<PartStatus, Integer> entry;

    @FXML
    private TableColumn<PartStatus, String> parts_tail;

    @FXML
    private TableColumn<PartStatus, String> modelname;

    @FXML
    private TableColumn<PartStatus, String> company;

    @FXML
    private TableColumn<PartStatus, String> next_r_d;

    @FXML
    private TableColumn<PartStatus, String> prchaser;

    @FXML
    private TableColumn<PartStatus, String> lasr_reason;

    @FXML
    private TableColumn<PartStatus, Integer> PHL;

    @FXML
    private TableColumn<PartStatus, String> P_STAT;

    @FXML
    private TextField parts_search;

    @FXML
    private Button search_parts;

    @FXML
    private TextField o_id;

    @FXML
    private Button over;

    private ObservableList<PartStatus> partStatusList = FXCollections.observableArrayList();

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

    public String idno=LoginControll.idval;





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

    @FXML
    void initialize() {
        entry.setCellValueFactory(new PropertyValueFactory<>("entryno"));
        parts_tail.setCellValueFactory(new PropertyValueFactory<>("tailno"));
        modelname.setCellValueFactory(new PropertyValueFactory<>("name_"));
        company.setCellValueFactory(new PropertyValueFactory<>("company_name"));
        next_r_d.setCellValueFactory(new PropertyValueFactory<>("next_repair_date"));
        prchaser.setCellValueFactory(new PropertyValueFactory<>("name"));
        lasr_reason.setCellValueFactory(new PropertyValueFactory<>("description"));
        PHL.setCellValueFactory(new PropertyValueFactory<>("hours_left"));
        P_STAT.setCellValueFactory(new PropertyValueFactory<>("status"));

        try {
            loadPartsStatusData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadPartsStatusData() throws SQLException {
        PARTS_STAT_TABLE.getItems().clear();;
        partStatusList.clear();

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query = " SELECT distinct p.entryno, p.tailno,a.name_ AS modelname, c.name AS company_name, p.next_repair_date, u.name  , h.description AS description, p.hours_left, p.po_status " +
                " FROM parts p " +
                " LEFT JOIN aircraft a ON p.tailno = a.tailno " +
                " LEFT JOIN company c ON p.companyid = c.companyid " +
                " LEFT JOIN history h ON p.entryno = h.entry_no " +
                " LEFT JOIN user_ u ON u.id = p.id " +
                " ORDER BY entryno ASC ";

        try (PreparedStatement pstmt = connectDB.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                PartStatus partStatus = new PartStatus();
                partStatus.setEntryno(rs.getInt("entryno"));
                partStatus.setTailno(rs.getString("tailno"));
                partStatus.setName_(rs.getString("modelname"));
                partStatus.setCompany_name(rs.getString("company_name"));
                partStatus.setNext_repair_date(rs.getString("next_repair_date"));
                partStatus.setName(rs.getString("name"));
                partStatus.setDescription(rs.getString("description"));
                partStatus.setHours_left(rs.getInt("hours_left"));
                partStatus.setStatus(rs.getString("po_status"));

                partStatusList.add(partStatus);
            }

            PARTS_STAT_TABLE.setItems(partStatusList);

        } catch (SQLException e) {
            System.err.println("Error loading parts status data: " + e.getMessage());
        } finally {
            if (connectDB != null) {
                connectDB.close();
            }
        }
    }

    @FXML
    void overhaul(ActionEvent event) {
        String A = o_id.getText();

        // Insert query
        String insertQuery = "INSERT INTO history (TYPE, DESCRIPTION, entry_no, INDATE) VALUES ('single component', 'REGULAR', ?, SYSDATE)";

        // Update query
        String updateQuery3 = "UPDATE parts SET STATUS = 'under overhauling' WHERE entryno = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Disable auto-commit
            conn.setAutoCommit(false);

            // Prepare and execute the insert statement
            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                insertStmt.setString(1, A);
                insertStmt.executeUpdate();
                System.out.println("Insert successful");
            }

            // Prepare and execute the update statement
            try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery3)) {
                updateStmt.setInt(1, Integer.parseInt(A));
                int rowsUpdated = updateStmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Update successful: " + rowsUpdated + " row(s) updated.");
                } else {
                    System.out.println("Update failed: No rows updated.");
                }
            }

            // Commit transaction
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void search_partsf(ActionEvent event) throws SQLException {
        purchase_anchor.setVisible(false);
        partStatusList.clear();
        String text = parts_search.getText().trim();

        String query;
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        if (isNumeric(text)) {
            query = " SELECT p.entryno, p.tailno,a.name_ AS modelname, c.name AS company_name, p.next_repair_date, u.name  , h.description AS description, p.hours_left, p.status " +
                    " FROM parts p " +
                    " LEFT JOIN aircraft a ON p.tailno = a.tailno " +
                    " LEFT JOIN company c ON p.companyid = c.companyid " +
                    " LEFT JOIN history h ON p.entryno = h.entry_no " +
                    " LEFT JOIN user_ u ON u.id = p.id WHERE p.entryno = ?" +
                    " ORDER BY p.entryno ASC ";
        } else {
            query = " SELECT p.entryno, p.tailno,a.name_ AS modelname, c.name AS company_name, p.next_repair_date, u.name  , h.description AS description, p.hours_left, p.status " +
                    " FROM parts p " +
                    " LEFT JOIN aircraft a ON p.tailno = a.tailno " +
                    " LEFT JOIN company c ON p.companyid = c.companyid " +
                    " LEFT JOIN history h ON p.entryno = h.entry_no " +
                    " LEFT JOIN user_ u ON u.id = p.id WHERE UPPER(p.tailno) LIKE ? OR UPPER(a.name_) LIKE ?" +
                    " ORDER BY p.entryno ASC ";
        }

        try (PreparedStatement pstmt = connectDB.prepareStatement(query)) {
            if (isNumeric(text)) {
                pstmt.setInt(1, Integer.parseInt(text));
            } else {
                String upperText = "%" + text.toUpperCase() + "%";
                pstmt.setString(1, upperText);
                pstmt.setString(2, upperText);
            }

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                PartStatus partStatus = new PartStatus();
                partStatus.setEntryno(rs.getInt("entryno"));
                partStatus.setTailno(rs.getString("tailno"));
                partStatus.setName_(rs.getString("modelname"));
                partStatus.setCompany_name(rs.getString("company_name"));
                partStatus.setNext_repair_date(rs.getString("next_repair_date"));
                partStatus.setName(rs.getString("name"));
                partStatus.setDescription(rs.getString("description"));
                partStatus.setHours_left(rs.getInt("hours_left"));
                partStatus.setStatus(rs.getString("status"));

                partStatusList.add(partStatus);
            }

            PARTS_STAT_TABLE.setItems(partStatusList);

        } catch (SQLException e) {
            System.err.println("Error loading parts status data: " + e.getMessage());
        } finally {
            if (connectDB != null) {
                connectDB.close();
            }
        }
    }

    @FXML
    void gobackf(ActionEvent event) {
        // Implement logic for go back button
    }

    @FXML
    void show_change_pass(ActionEvent event) {
        // Implement logic for show change password
    }

    @FXML
    void show_clear_ac(ActionEvent event) {
        // Implement logic for show clear AC
    }

    @FXML
    void show_history_parts(ActionEvent event) {
        R_TABLE.getItems().clear();
        update_anchor.setVisible(false);
        purchase_anchor.setVisible(false);
        parts_table.setVisible(false);
        ac_table.setVisible(false);
        his_ac.setVisible(false);
        his_ac.setVisible(false);
        P_HIS.setVisible(false);
        update_anchor.setVisible(false);
        purchase_anchor.setVisible(false);
        parts_table.setVisible(false);

        P_HIS.setVisible(true);
        P_ID.setCellValueFactory(new PropertyValueFactory<>("P_ID"));
        P_TAIL.setCellValueFactory(new PropertyValueFactory<>("P_TAIL"));
        P_MODEL.setCellValueFactory(new PropertyValueFactory<>("P_MODEL"));
        P_TYPE.setCellValueFactory(new PropertyValueFactory<>("P_TYPE"));
        P_UNIT.setCellValueFactory(new PropertyValueFactory<>("P_UNIT"));
        P_INDATE.setCellValueFactory(new PropertyValueFactory<>("P_INDATE"));
        P_OUTDATE.setCellValueFactory(new PropertyValueFactory<>("P_OUTDATE"));
        P_REASON.setCellValueFactory(new PropertyValueFactory<>("P_REASON"));
        P_ENTRY.setCellValueFactory(new PropertyValueFactory<>("P_ENTRY"));

        String query = "SELECT H.R_ID,H.ENTRY_NO,A.tailno, A.NAME_ AS MODEL, H.INDATE, H.OUTDATE, H.TYPE, H.DESCRIPTION AS REASON, U.NAME AS UNIT " +
                "FROM HISTORY H " +
                " left join parts P ON H.entry_no=P.entryno LEFT JOIN AIRCRAFT A ON P.tailno = A.tailno " +
                "LEFT JOIN UNIT U ON U.UNIT_ID = A.UNIT_ID " +
                "";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                P_REPAIR repair = new P_REPAIR();
                repair.setP_ID(rs.getInt("R_ID"));
                repair.setP_TAIL(rs.getString("TAILNO"));
                repair.setP_MODEL(rs.getString("MODEL"));
                repair.setP_INDATE(rs.getString("INDATE"));
                repair.setP_OUTDATE(rs.getString("OUTDATE"));
                repair.setP_TYPE(rs.getString("TYPE"));
                repair.setP_REASON(rs.getString("REASON"));
                repair.setP_UNIT(rs.getString("UNIT"));
                repair.setP_ENTRY(rs.getString("ENTRY_NO"));

                repairList2.add(repair);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        P_TABLE.setItems(repairList2);// Implement logic for show history AC
    }

    @FXML
    void show_hstory_ac(ActionEvent event) {
        R_TABLE.getItems().clear();
        update_anchor.setVisible(false);
        purchase_anchor.setVisible(false);
        parts_table.setVisible(false);
        ac_table.setVisible(false);
        his_ac.setVisible(false);
        P_HIS.setVisible(false);
        update_anchor.setVisible(false);
        purchase_anchor.setVisible(false);
        parts_table.setVisible(false);
        P_HIS.setVisible(false);
      his_ac.setVisible(true);
        R_ID.setCellValueFactory(new PropertyValueFactory<>("R_ID"));
        R_TAIL.setCellValueFactory(new PropertyValueFactory<>("R_TAIL"));
        R_MODEL.setCellValueFactory(new PropertyValueFactory<>("R_MODEL"));
        R_TYPE.setCellValueFactory(new PropertyValueFactory<>("R_TYPE"));
        R_UNIT.setCellValueFactory(new PropertyValueFactory<>("R_UNIT"));
        R_INDATE.setCellValueFactory(new PropertyValueFactory<>("R_INDATE"));
        R_OUTDATE.setCellValueFactory(new PropertyValueFactory<>("R_OUTDATE"));
        R_REASON.setCellValueFactory(new PropertyValueFactory<>("R_REASON"));

        String query = "SELECT H.R_ID, H.TAILNO, A.NAME_ AS MODEL, H.INDATE, H.OUTDATE, H.TYPE, H.DESCRIPTION AS REASON, U.NAME AS UNIT " +
                "FROM HISTORY_AC H " +
                "LEFT JOIN AIRCRAFT A ON H.TAILNO = A.TAILNO " +
                "LEFT JOIN UNIT U ON U.UNIT_ID = A.UNIT_ID " +
                "";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                REPAIR repair = new REPAIR();
                repair.setR_ID(rs.getInt("R_ID"));
                repair.setR_TAIL(rs.getString("TAILNO"));
                repair.setR_MODEL(rs.getString("MODEL"));
                repair.setR_INDATE(rs.getString("INDATE"));
                repair.setR_OUTDATE(rs.getString("OUTDATE"));
                repair.setR_TYPE(rs.getString("TYPE"));
                repair.setR_REASON(rs.getString("REASON"));
                repair.setR_UNIT(rs.getString("UNIT"));
                repairList.add(repair);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        R_TABLE.setItems(repairList);// Implement logic for show history AC
    }

    @FXML
    void show_logout(ActionEvent event) {
        // Implement logic for show logout
    }

    public void show_o_status(ActionEvent event) {
  his_ac.setVisible(false);
  P_HIS.setVisible(false);
        update_anchor.setVisible(false);
        purchase_anchor.setVisible(false);
        parts_table.setVisible(false);
        ac_table.setVisible(true);

        // Setup the table columns
        A_TAIL.setCellValueFactory(new PropertyValueFactory<>("tailNo"));
        A_MODEL.setCellValueFactory(new PropertyValueFactory<>("model"));
        A_HRL.setCellValueFactory(new PropertyValueFactory<>("hoursleft"));
        A_COMANY.setCellValueFactory(new PropertyValueFactory<>("unitname"));
        A_NEXTREPAIR.setCellValueFactory(new PropertyValueFactory<>("nextRepair"));
        A_STAT.setCellValueFactory(new PropertyValueFactory<>("status"));
        A_LASTREASON.setCellValueFactory(new PropertyValueFactory<>("lastreason"));
       // remarks.setCellValueFactory(new PropertyValueFactory<>("remarks"));

        // Fetch data from the database and populate the table
        ObservableList<AC> acList = FXCollections.observableArrayList();

        try {
            // Database connection
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
            Statement statement = connection.createStatement();

            // SQL query to fetch data with required joins and filters
            String query = "SELECT distinct a.tailno, a.name_, a.hours_left, u.name, a.next_date, a.status, h.description " +
                    "FROM aircraft a " +
                    "LEFT JOIN unit u ON a.unit_id = u.unit_id " +
                    "LEFT JOIN history_ac h ON a.tailno = h.tailno " +
                    "WHERE   UPPER(a.status) LIKE '%NEED%' or upper(a.status) like '%UNDER%' ";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String tailNo = resultSet.getString("tailno");
                String model = resultSet.getString("name_");
                String hoursleft = resultSet.getString("hours_left");
                String unitname = resultSet.getString("name");
                String nextRepair = resultSet.getString("next_date");
                String status = resultSet.getString("status");
                String lastreason = resultSet.getString("description");
               // String remarks = resultSet.getString("o_status");

                AC ac = new AC(tailNo, model, hoursleft, unitname, status, lastreason, nextRepair);
                acList.add(ac);
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ac_tableview.setItems(acList);
    }

    public void SBYSK(ActionEvent event) { R_TABLE.getItems().clear();;
        if (isNumeric(R_SEARCH.getText())) {
            R_ID.setCellValueFactory(new PropertyValueFactory<>("R_ID"));
            R_TAIL.setCellValueFactory(new PropertyValueFactory<>("R_TAIL"));
            R_MODEL.setCellValueFactory(new PropertyValueFactory<>("R_MODEL"));
            R_TYPE.setCellValueFactory(new PropertyValueFactory<>("R_TYPE"));
            R_UNIT.setCellValueFactory(new PropertyValueFactory<>("R_UNIT"));
            R_INDATE.setCellValueFactory(new PropertyValueFactory<>("R_INDATE"));
            R_OUTDATE.setCellValueFactory(new PropertyValueFactory<>("R_OUTDATE"));
            R_REASON.setCellValueFactory(new PropertyValueFactory<>("R_REASON"));

            String query = "SELECT H.R_ID, H.TAILNO, A.NAME_ AS MODEL, H.INDATE, H.OUTDATE, H.TYPE, H.DESCRIPTION AS REASON, U.NAME AS UNIT " +
                    "FROM HISTORY_AC H " +
                    "LEFT JOIN AIRCRAFT A ON H.TAILNO = A.TAILNO " +
                    "LEFT JOIN UNIT U ON U.UNIT_ID = A.UNIT_ID WHERE   H.R_ID = ?";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {

                // Set the value of the parameter `?`
                pstmt.setInt(1, Integer.parseInt(R_SEARCH.getText()));

                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        REPAIR repair = new REPAIR();
                        repair.setR_ID(rs.getInt("R_ID"));
                        repair.setR_TAIL(rs.getString("TAILNO"));
                        repair.setR_MODEL(rs.getString("MODEL"));
                        repair.setR_INDATE(rs.getString("INDATE"));
                        repair.setR_OUTDATE(rs.getString("OUTDATE"));
                        repair.setR_TYPE(rs.getString("TYPE"));
                        repair.setR_REASON(rs.getString("REASON"));
                        repair.setR_UNIT(rs.getString("UNIT"));
                        repairList.add(repair);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            R_TABLE.setItems(repairList);
        }
        else
        {  //R_SEARCH.getText().trim().toUpperCase();
            R_ID.setCellValueFactory(new PropertyValueFactory<>("R_ID"));
            R_TAIL.setCellValueFactory(new PropertyValueFactory<>("R_TAIL"));
            R_MODEL.setCellValueFactory(new PropertyValueFactory<>("R_MODEL"));
            R_TYPE.setCellValueFactory(new PropertyValueFactory<>("R_TYPE"));
            R_UNIT.setCellValueFactory(new PropertyValueFactory<>("R_UNIT"));
            R_INDATE.setCellValueFactory(new PropertyValueFactory<>("R_INDATE"));
            R_OUTDATE.setCellValueFactory(new PropertyValueFactory<>("R_OUTDATE"));
            R_REASON.setCellValueFactory(new PropertyValueFactory<>("R_REASON"));

            String query = "SELECT H.R_ID, H.TAILNO, A.NAME_ AS MODEL, H.INDATE, H.OUTDATE, H.TYPE, H.DESCRIPTION AS REASON, U.NAME AS UNIT " +
                    "FROM HISTORY_AC H " +
                    "LEFT JOIN AIRCRAFT A ON H.TAILNO = A.TAILNO " +
                    "LEFT JOIN UNIT U ON U.UNIT_ID = A.UNIT_ID WHERE   UPPER(A.tailno) like ? or upper(a.NAME_) like ?";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {

                // Set the value of the parameter `?`
                pstmt.setString(1,"%" +R_SEARCH.getText().trim().toUpperCase()+"%");
                pstmt.setString(2,"%" + R_SEARCH.getText().trim().toUpperCase() +"%");

                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        REPAIR repair = new REPAIR();
                        repair.setR_ID(rs.getInt("R_ID"));
                        repair.setR_TAIL(rs.getString("TAILNO"));
                        repair.setR_MODEL(rs.getString("MODEL"));
                        repair.setR_INDATE(rs.getString("INDATE"));
                        repair.setR_OUTDATE(rs.getString("OUTDATE"));
                        repair.setR_TYPE(rs.getString("TYPE"));
                        repair.setR_REASON(rs.getString("REASON"));
                        repair.setR_UNIT(rs.getString("UNIT"));
                        repairList.add(repair);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            R_TABLE.setItems(repairList);
        }
    }


    public void S_AC(ActionEvent event) {
        his_ac.setVisible(false);
        P_HIS.setVisible(false);
        update_anchor.setVisible(false);
        purchase_anchor.setVisible(false);
        parts_table.setVisible(false);
        ac_table.setVisible(true);
        ac_table.setVisible(true); // Implement logic for show operation status

        String sk = AC_SEARCH.getText().toUpperCase();

        // Setup the table columns
        A_TAIL.setCellValueFactory(new PropertyValueFactory<>("tailNo"));
        A_MODEL.setCellValueFactory(new PropertyValueFactory<>("model"));
        A_HRL.setCellValueFactory(new PropertyValueFactory<>("hoursleft"));
        A_COMANY.setCellValueFactory(new PropertyValueFactory<>("unitname"));
        A_NEXTREPAIR.setCellValueFactory(new PropertyValueFactory<>("nextRepair"));
        A_STAT.setCellValueFactory(new PropertyValueFactory<>("status"));
        A_LASTREASON.setCellValueFactory(new PropertyValueFactory<>("lastreason"));
       // remarks.setCellValueFactory(new PropertyValueFactory<>("remarks"));

        // Fetch data from the database and populate the table
        ObservableList<AC> acList = FXCollections.observableArrayList();

        try {
            // Database connection
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");

            // SQL query to fetch data with required joins and filters
            String query = "SELECT a.tailno, a.name_, a.hours_left, u.name, a.next_date, a.status, h.description " +
                    "FROM aircraft a " +
                    "LEFT JOIN unit u ON a.unit_id = u.unit_id " +
                    "LEFT JOIN history h ON a.tailno = h.tailno " +
                    "WHERE (a.o_status = 'under overhauling' OR UPPER(a.status) LIKE '%NEED%') " +
                    "AND (UPPER(a.tailno) LIKE ? OR UPPER(a.name_) LIKE ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + sk + "%");
            preparedStatement.setString(2, "%" + sk + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String tailNo = resultSet.getString("tailno");
                String model = resultSet.getString("name_");
                String hoursleft = resultSet.getString("hours_left");
                String unitname = resultSet.getString("name");
                String nextRepair = resultSet.getString("next_date");
                String status = resultSet.getString("status");
                String lastreason = resultSet.getString("description");
               // String remarks = resultSet.getString("o_status");

                AC ac = new AC(tailNo, model, hoursleft, unitname, status, lastreason, nextRepair);
                acList.add(ac);
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ac_tableview.setItems(acList);
    }


    @FXML
    void show_p_status(ActionEvent event) {
        his_ac.setVisible(false);
        P_HIS.setVisible(false);
        update_anchor.setVisible(false);
        purchase_anchor.setVisible(false);
        parts_table.setVisible(false);
        ac_table.setVisible(false); // Hide purchase anchor pane
        parts_table.setVisible(true); // Show parts table anchor pane
        try {
            loadPartsStatusData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void show_purchase(ActionEvent event) {
        his_ac.setVisible(false);
        P_HIS.setVisible(false);
        update_anchor.setVisible(false);
        purchase_anchor.setVisible(false);
        parts_table.setVisible(false);
        ac_table.setVisible(false);
        purchase_anchor.setVisible(true); // Show purchase anchor pane
    }

    @FXML
    void show_update_info(ActionEvent event) {
        purchase_anchor.setVisible(false);
        parts_table.setVisible(false);
        ac_table.setVisible(false);
        update_anchor.setVisible(false);
       // purchase_anchor.setVisible(false);
      //  parts_table.setVisible(false);
       // ac_table.setVisible(false);
        his_ac.setVisible(false);
        his_ac.setVisible(false);
        P_HIS.setVisible(false);
        update_anchor.setVisible(false);
        purchase_anchor.setVisible(false);
        parts_table.setVisible(false);
      //  ac_table.setVisible(true);
        update_anchor.setVisible(true);// Implement logic for show update info
    }

    @FXML
    void update_purchasef(ActionEvent event) throws SQLException {
        String companyId = c_id.getText();
        String companyName = c_name.getText();
        String companyEmail = c_email.getText();
        String companyPhone = c_phn.getText();
        String entryNo = e_no.getText();
        String serviceHour = s_hr.getText();
        String activeHour = "0"; // Default to 0 as per the instructions
        String tailNo = t_no.getText();
        String longivity = longi.getText();
        String originDate = date_c.getValue().toString();
        String partId = p_id.getText();

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        try {
            // Check if company ID exists
            String checkCompanyQuery = "SELECT COUNT(*) FROM company WHERE COMPANYID = ?";
            PreparedStatement checkCompanyStmt = connectDB.prepareStatement(checkCompanyQuery);
            checkCompanyStmt.setString(1, companyId);

            ResultSet rs = checkCompanyStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if (count == 0) {
                // Company ID does not exist, insert into company table
                String insertCompanyQuery = "INSERT INTO company (COMPANYID, NAME, EMAIL, PHONE) VALUES (?, ?, ?, ?)";
                PreparedStatement insertCompanyStmt = connectDB.prepareStatement(insertCompanyQuery);
                insertCompanyStmt.setString(1, companyId);
                insertCompanyStmt.setString(2, companyName);
                insertCompanyStmt.setString(3, companyEmail);
                insertCompanyStmt.setString(4, companyPhone);
                insertCompanyStmt.executeUpdate();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Company information inserted successfully.");
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Information", "Company ID already exists. No need to insert company information.");
            }

            // Fetch purchaser name from the user table based on part ID
            String purchaserQuery = "SELECT name FROM user_ WHERE id = ?";
            PreparedStatement purchaserStmt = connectDB.prepareStatement(purchaserQuery);
            purchaserStmt.setString(1, partId);
            ResultSet purchaserRs = purchaserStmt.executeQuery();
            String purchaserName = purchaserRs.next() ? purchaserRs.getString("name") : "";

            // Insert into parts table
            String insertPartsQuery = "INSERT INTO parts (ENTRYNO, SERVICE_HOUR, ACTIVE_HOUR, TAILNO, ID, COMPANYID, LONGIVITY, ORIGIN) VALUES (?, ?, ?, ?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'))";
            PreparedStatement insertPartsStmt = connectDB.prepareStatement(insertPartsQuery);
            insertPartsStmt.setString(1, entryNo);
            insertPartsStmt.setString(2, serviceHour);
            insertPartsStmt.setString(3, activeHour);
            insertPartsStmt.setString(4, tailNo);
            insertPartsStmt.setString(5, partId);
            insertPartsStmt.setString(6, companyId);
            insertPartsStmt.setString(7, longivity);
            insertPartsStmt.setString(8, originDate);
            insertPartsStmt.executeUpdate();

            showAlert(Alert.AlertType.INFORMATION, "Success", "Part information inserted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to update purchase information: " + e.getMessage());
        } finally {
            try {
                if (connectDB != null) {
                    connectDB.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }





    public void overhaul1(ActionEvent event) {
        String A = o_id1.getText().toUpperCase();

        // Insert query for history_ac table
        String insertQueryAircraft = "INSERT INTO history_ac (TYPE, DESCRIPTION, TAILNO, INDATE) VALUES ('schedule maintenance', 'REGULAR', ?, SYSDATE)";

        // Update query for aircraft table
        String updateQueryAircraft = "UPDATE aircraft SET STATUS = 'under overhauling' WHERE tailno = ?";

        // Query to get entryno of parts belonging to the aircraft
        String selectPartsQuery = "SELECT entryno FROM parts WHERE tailno = ?";

        // Insert query for history table
        String insertQueryParts = "INSERT INTO history (TYPE, DESCRIPTION, entry_no, INDATE) VALUES ('with aircraft', 'REGULAR by aircraft', ?, SYSDATE)";

        // Update query for parts table
        String updateQueryParts = "UPDATE parts SET STATUS = 'under overhauling' WHERE entryno = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Disable auto-commit
            conn.setAutoCommit(false);

            // Prepare and execute the insert statement for aircraft
            try (PreparedStatement insertStmtAircraft = conn.prepareStatement(insertQueryAircraft)) {
                insertStmtAircraft.setString(1, A);
                insertStmtAircraft.executeUpdate();
            }

            // Prepare and execute the update statement for aircraft
            try (PreparedStatement updateStmtAircraft = conn.prepareStatement(updateQueryAircraft)) {
                updateStmtAircraft.setString(1, A);
                updateStmtAircraft.executeUpdate();
            }

            // Prepare and execute the select statement for parts
            try (PreparedStatement selectStmtParts = conn.prepareStatement(selectPartsQuery)) {
                selectStmtParts.setString(1, A);
                try (ResultSet rs = selectStmtParts.executeQuery()) {
                    while (rs.next()) {
                        int entryNo = rs.getInt("entryno");

                        // Prepare and execute the insert statement for parts
                        try (PreparedStatement insertStmtParts = conn.prepareStatement(insertQueryParts)) {
                            insertStmtParts.setInt(1, entryNo);
                            insertStmtParts.executeUpdate();
                        }

                        // Prepare and execute the update statement for parts
                        try (PreparedStatement updateStmtParts = conn.prepareStatement(updateQueryParts)) {
                            updateStmtParts.setInt(1, entryNo);
                            updateStmtParts.executeUpdate();
                        }
                    }
                }
            }

            // Commit transaction
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void PBYSK(ActionEvent event){ P_TABLE.getItems().clear();;
        if (isNumeric(P_SEARCH.getText())) {
            P_ID.setCellValueFactory(new PropertyValueFactory<>("P_ID"));
            P_TAIL.setCellValueFactory(new PropertyValueFactory<>("P_TAIL"));
            P_MODEL.setCellValueFactory(new PropertyValueFactory<>("P_MODEL"));
            P_TYPE.setCellValueFactory(new PropertyValueFactory<>("P_TYPE"));
            P_UNIT.setCellValueFactory(new PropertyValueFactory<>("P_UNIT"));
            P_INDATE.setCellValueFactory(new PropertyValueFactory<>("P_INDATE"));
            P_OUTDATE.setCellValueFactory(new PropertyValueFactory<>("P_OUTDATE"));
            P_REASON.setCellValueFactory(new PropertyValueFactory<>("P_REASON"));
            P_ENTRY.setCellValueFactory(new PropertyValueFactory<>("P_ENTRY"));

            String query = "SELECT H.R_ID,H.ENTRY_NO, A.TAILNO, A.NAME_ AS MODEL, H.INDATE, H.OUTDATE, H.TYPE, H.DESCRIPTION AS REASON, U.NAME AS UNIT " +
                    "FROM HISTORY H " +
                    "LEFT JOIN PARTS P ON P.ENTRYNO=H.ENTRY_NO LEFT JOIN AIRCRAFT A ON P.TAILNO = A.TAILNO " +
                    "LEFT JOIN UNIT U ON U.UNIT_ID = A.UNIT_ID WHERE   H.R_ID = ?";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {

                // Set the value of the parameter `?`
                pstmt.setInt(1, Integer.parseInt(P_SEARCH.getText()));

                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        P_REPAIR repair = new P_REPAIR();
                        repair.setP_ID(rs.getInt("R_ID"));
                        repair.setP_TAIL(rs.getString("TAILNO"));
                        repair.setP_MODEL(rs.getString("MODEL"));
                        repair.setP_INDATE(rs.getString("INDATE"));
                        repair.setP_OUTDATE(rs.getString("OUTDATE"));
                        repair.setP_TYPE(rs.getString("TYPE"));
                        repair.setP_REASON(rs.getString("REASON"));
                        repair.setP_UNIT(rs.getString("UNIT"));
                        repair.setP_ENTRY(rs.getString("ENTRY_NO"));
                        repairList2.add(repair);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            P_TABLE.setItems(repairList2);
        }
        else
        {  //R_SEARCH.getText().trim().toUpperCase();
            P_ID.setCellValueFactory(new PropertyValueFactory<>("P_ID"));
            P_TAIL.setCellValueFactory(new PropertyValueFactory<>("P_TAIL"));
            P_MODEL.setCellValueFactory(new PropertyValueFactory<>("P_MODEL"));
            P_TYPE.setCellValueFactory(new PropertyValueFactory<>("P_TYPE"));
            P_UNIT.setCellValueFactory(new PropertyValueFactory<>("P_UNIT"));
            P_INDATE.setCellValueFactory(new PropertyValueFactory<>("P_INDATE"));
            P_OUTDATE.setCellValueFactory(new PropertyValueFactory<>("P_OUTDATE"));
            P_REASON.setCellValueFactory(new PropertyValueFactory<>("P_REASON"));
            P_ENTRY.setCellValueFactory(new PropertyValueFactory<>("P_ENTRY"));

            String query = "SELECT H.R_ID,H.ENTRY_NO, A.TAILNO, A.NAME_ AS MODEL, H.INDATE, H.OUTDATE, H.TYPE, H.DESCRIPTION AS REASON, U.NAME AS UNIT " +
                    "FROM HISTORY H " +
                    "LEFT JOIN PARTS P ON P.ENTRYNO=H.ENTRY_NO LEFT JOIN AIRCRAFT A ON P.TAILNO = A.TAILNO " +
                    "LEFT JOIN UNIT U ON U.UNIT_ID = A.UNIT_ID WHERE   UPPER(A.tailno) like ? or upper(a.NAME_) like ?";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {

                // Set the value of the parameter `?`
                pstmt.setString(1,"%" +P_SEARCH.getText().trim().toUpperCase()+"%");
                pstmt.setString(2,"%" + P_SEARCH.getText().trim().toUpperCase() +"%");

                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        P_REPAIR repair = new P_REPAIR();
                        repair.setP_ID(rs.getInt("R_ID"));
                        repair.setP_TAIL(rs.getString("TAILNO"));
                        repair.setP_MODEL(rs.getString("MODEL"));
                        repair.setP_INDATE(rs.getString("INDATE"));
                        repair.setP_OUTDATE(rs.getString("OUTDATE"));
                        repair.setP_TYPE(rs.getString("TYPE"));
                        repair.setP_REASON(rs.getString("REASON"));
                        repair.setP_UNIT(rs.getString("UNIT"));
                        repair.setP_ENTRY(rs.getString("ENTRY_NO"));
                        repairList2.add(repair);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            P_TABLE.setItems(repairList2);
        }
    }

    @FXML
    public void clear_ac(ActionEvent event) {
        String repairId = clr_txt.getText().toUpperCase();

        String tailNo = null;

        // Query to get the tail number from the repair ID
        String selectTailNoQuery = "SELECT tailno FROM history_ac WHERE r_id = ?";

        // Update query for the history_ac table
        String updateHistoryQuery = "UPDATE history_ac SET outdate = SYSDATE WHERE r_id = ? AND outdate IS NULL";

        // Update queries for the aircraft table
        String updateAircraftQuery = "UPDATE aircraft SET originyear = SYSDATE WHERE tailno = ?";
        String updateAircraftQuery2 = "UPDATE aircraft SET STATUS = 'ok' WHERE tailno = ?";
        String updateAircraftQuery3 = "UPDATE aircraft SET active_hour = 0 WHERE tailno = ?";

        // Query to get entryno of parts belonging to the aircraft
        String selectPartsQuery = "SELECT entryno FROM parts WHERE tailno = ?";

        // Update queries for the parts table
        String updateHistoryPartsQuery = "UPDATE history SET outdate = SYSDATE WHERE entry_no = ? AND outdate IS NULL";
        String updatePartsQuery = "UPDATE parts SET active_hour = 0, origin = SYSDATE, po_status = 'ok' WHERE entryno = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Disable auto-commit
            conn.setAutoCommit(false);

            // Retrieve the tail number corresponding to the repair ID
            try (PreparedStatement selectTailNoStmt = conn.prepareStatement(selectTailNoQuery)) {
                selectTailNoStmt.setString(1, repairId);
                try (ResultSet rs = selectTailNoStmt.executeQuery()) {
                    if (rs.next()) {
                        tailNo = rs.getString("tailno");
                    } else {
                        System.out.println("No aircraft found for repair ID: " + repairId);
                        return;
                    }
                }
            }

            // Prepare and execute the update statement for the history_ac table
            try (PreparedStatement updateHistoryStmt = conn.prepareStatement(updateHistoryQuery)) {
                updateHistoryStmt.setString(1, repairId);
                updateHistoryStmt.executeUpdate();
            }

            // Prepare and execute the update statements for the aircraft table
            try (PreparedStatement updateAircraftStmt1 = conn.prepareStatement(updateAircraftQuery)) {
                updateAircraftStmt1.setString(1, tailNo);
                updateAircraftStmt1.executeUpdate();
            }
            try (PreparedStatement updateAircraftStmt2 = conn.prepareStatement(updateAircraftQuery2)) {
                updateAircraftStmt2.setString(1, tailNo);
                updateAircraftStmt2.executeUpdate();
            }
            try (PreparedStatement updateAircraftStmt3 = conn.prepareStatement(updateAircraftQuery3)) {
                updateAircraftStmt3.setString(1, tailNo);
                updateAircraftStmt3.executeUpdate();
            }

            // Prepare and execute the select statement for parts
            try (PreparedStatement selectStmtParts = conn.prepareStatement(selectPartsQuery)) {
                selectStmtParts.setString(1, tailNo);
                try (ResultSet rs = selectStmtParts.executeQuery()) {
                    while (rs.next()) {
                        int entryNo = rs.getInt("entryno");

                        // Prepare and execute the update statement for parts history
                        try (PreparedStatement updateHistoryPartsStmt = conn.prepareStatement(updateHistoryPartsQuery)) {
                            updateHistoryPartsStmt.setInt(1, entryNo);
                            updateHistoryPartsStmt.executeUpdate();
                        }

                        // Prepare and execute the update statement for parts
                        try (PreparedStatement updatePartsStmt = conn.prepareStatement(updatePartsQuery)) {
                            updatePartsStmt.setInt(1, entryNo);
                            updatePartsStmt.executeUpdate();
                        }
                    }
                }
            }

            // Commit transaction
            conn.commit();
            System.out.println("Clear aircraft and parts successful");

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                Connection conn = null;
                conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }
    }



    @FXML
    private void dopclr(ActionEvent event) {
        String repairId = pclrtxt.getText();
        if (repairId.isEmpty()) {
            System.out.println("Repair ID is empty");
            return;
        }

        String entryNo = null;

        // SQL queries
        String selectEntryNoQuery = "SELECT entry_no FROM history WHERE r_id = ?";
        String updateHistoryQuery = "UPDATE history SET outdate = SYSDATE WHERE r_id = ?";
        String updatePartsQuery = "UPDATE parts SET active_hour = 0, origin = SYSDATE, po_status = 'ok' WHERE entryno = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            // Retrieve entry_no from history table
            try (PreparedStatement selectStmt = conn.prepareStatement(selectEntryNoQuery)) {
                selectStmt.setString(1, repairId);
                try (ResultSet rs = selectStmt.executeQuery()) {
                    if (rs.next()) {
                        entryNo = rs.getString("entry_no");
                    } else {
                        System.out.println("No entry found for repair ID: " + repairId);
                        return;
                    }
                }
            }

            // Update outdate in history table
            try (PreparedStatement updateHistoryStmt = conn.prepareStatement(updateHistoryQuery)) {
                updateHistoryStmt.setString(1, repairId);
                updateHistoryStmt.executeUpdate();
            }

            // Update parts table using entry_no
            try (PreparedStatement updatePartsStmt = conn.prepareStatement(updatePartsQuery)) {
                updatePartsStmt.setString(1, entryNo);
                updatePartsStmt.executeUpdate();
            }

            conn.commit();
            System.out.println("Update successful");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
