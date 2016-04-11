package Kiosk.Controllers;

import Kiosk.Admin;
import Map.Destination;
import Map.LocationNode;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.control.Alert;
import javafx.util.converter.NumberStringConverter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AdminDepartmentPanelController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML // fx:id="deptName"
    private TableColumn<Admin, String> deptName;


    @FXML // fx:id="ycoord"
    private TableColumn<Admin, Number> ycoord;


    @FXML // fx:id="deptType"
    private TableColumn<Admin, String> deptType;

    @FXML // fx:id="deptTypeField"
    private TextField deptTypeField;


    @FXML // fx:id="yField"
    private TextField yField;


    private AdminPanelController control;
    @FXML
    private Button addToList;


    @FXML
    private TextField filterInput;

    @FXML // fx:id="deptBox"
    private ComboBox<String> deptBox;
    ObservableList<String> deptBoxdata = FXCollections.observableArrayList();

    @FXML
    private TableView<Admin> nodeTable;

    @FXML // fx:id="addBtn"
    private Button addBtn;

    @FXML // fx:id="deleteBtn"
    private Button deleteBtn;

    @FXML
    private MenuBar fileMenu;

    @FXML
    private Button back;

    ObservableList<Admin> observableStudentList = FXCollections.observableArrayList();
    private LocationNode currentNode;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //add Listener to filterField
        filterInput.textProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                filterStudentList((String) oldValue, (String) newValue);

            }
        });
        //initialize editable attributes
        nodeTable.setEditable(true);
        deptName.setOnEditCommit(e -> firstNameCol_OnEditCommit(e));
        ycoord.setOnEditCommit(e -> majorCol_OnEditCommit(e));
        deptType.setOnEditCommit(e -> genderCol_OnEditCommit(e));

        nodeTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        deptName.setCellFactory(TextFieldTableCell.forTableColumn());
        ycoord.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        deptType.setCellFactory(TextFieldTableCell.forTableColumn());


        deptName.setCellValueFactory(cellData -> cellData.getValue().deptTypeProperty());
        ycoord.setCellValueFactory(cellData -> cellData.getValue().floorProperty());
        deptType.setCellValueFactory(cellData -> cellData.getValue().deptProperty());


        //initialize gender ComboBox
        deptBoxdata.add(new String("PHYSICIAN"));
        deptBoxdata.add(new String("DEPARTMENT"));
        deptBoxdata.add(new String("KIOSK"));
        deptBoxdata.add(new String("BATHROOM"));
        deptBoxdata.add(new String("ELEVATOR"));
        deptBoxdata.add(new String("STAIR"));
        deptBox.setItems(deptBoxdata);


        addBtn.setDisable(true);
        deleteBtn.setDisable(true);
        nodeTable.setItems(observableStudentList);
        nodeTable.setEditable(true);
        nodeTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        nodeTable.setPlaceholder(new Label("Your Table is Empty"));

        deptTypeField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (deptTypeField.isFocused()) {
                    addBtn.setDisable(false);
                }
            }
        });
        nodeTable.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (nodeTable.isFocused()) {
                    deleteBtn.setDisable(false);
                }
            }
        });
    }//end initialize



    /*
    ----------------------------------------------Control handlers---------------------------------------------
     */
    public void handleAddButtonClick(ActionEvent event) {
        /*
        Get input from user and add to Table
         */


        if (observableStudentList.size() < 10) {

                Admin student = new Admin();
                AdminPanelController control = new AdminPanelController();


             if (isValidInput(event)) {
                System.out.println("node is not fucked");
                    student.setDeptType(deptTypeField.getText());
                    student.setFloor(Integer.parseInt(yField.getText()));
                    student.setDept(deptBox.getValue());
                    observableStudentList.add(student);


                    currentNode.addDestination(Destination.valueOf(deptBox.getValue()), deptTypeField.getText());

                }

                deptTypeField.clear();
                yField.clear();
                deptBox.setValue("Dept");

            }

        else {
            Alert sizeAlert = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            sizeAlert.setContentText("You may only hold 10 Nodes at this time");
            sizeAlert.initModality(Modality.APPLICATION_MODAL);
            sizeAlert.initOwner(owner);
            sizeAlert.showAndWait();
            if (sizeAlert.getResult() == ButtonType.OK) {
                sizeAlert.close();
                deptTypeField.clear();
                yField.clear();
                deptBox.setValue("Dept");
            }
        }
    }
    /*
    In case of empty fields. Gives alert for respective empty field and requests focus on that field.
     */
    private boolean isValidInput(ActionEvent event) {

        Boolean validInput = true;

        if(deptTypeField == null || deptTypeField.getText().trim().isEmpty()) {
            validInput = false;
            Alert emptyFirstName = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyFirstName.setContentText("Dept type is EMPTY");
            emptyFirstName.initModality(Modality.APPLICATION_MODAL);
            emptyFirstName.initOwner(owner);
            emptyFirstName.showAndWait();
            if(emptyFirstName.getResult() == ButtonType.OK) {
                emptyFirstName.close();
                deptTypeField.requestFocus();
            }
        }

        if(yField == null || yField.getText().trim().isEmpty()) {
            validInput = false;
            Alert emptyMajor = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyMajor.setContentText("Floor is EMPTY");
            emptyMajor.initModality(Modality.APPLICATION_MODAL);
            emptyMajor.initOwner(owner);
            emptyMajor.showAndWait();
            if (emptyMajor.getResult() == ButtonType.OK) {
                emptyMajor.close();
                yField.requestFocus();
            }
        }


        if(deptBox == null || deptBox.getValue().isEmpty()) {
            validInput = false;
            Alert emptyGender = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyGender.setContentText("Dept is EMPTY");
            emptyGender.initModality(Modality.APPLICATION_MODAL);
            emptyGender.initOwner(owner);
            emptyGender.showAndWait();
            if (emptyGender.getResult() == ButtonType.OK) {
                emptyGender.close();
                deptBox.requestFocus();
            }
        }
        return validInput;
    }
    /*
    handle column edits
     */
    public void firstNameCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<Admin, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<Admin, String>) e;
        Admin student = cellEditEvent.getRowValue();
        student.setDeptType(cellEditEvent.getNewValue());
    }
    public void lastNameCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<Admin, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<Admin, String>) e;
        Admin student = cellEditEvent.getRowValue();
        student.setDeptName(cellEditEvent.getNewValue());
    }


    public void majorCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<Admin, Integer> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<Admin, Integer>) e;
        Admin student = cellEditEvent.getRowValue();
        student.setFloor(cellEditEvent.getNewValue());
    }
    public void ageCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<Admin, Double> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<Admin, Double>) e;
        Admin student = cellEditEvent.getRowValue();
        student.setyCoordinate(cellEditEvent.getNewValue());
    }
    public void gpaCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<Admin, Double> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<Admin, Double>) e;
        Admin student = cellEditEvent.getRowValue();
        student.setGradepoint(cellEditEvent.getNewValue());
    }
    public void genderCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<Admin, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<Admin, String>) e;
        Admin student = cellEditEvent.getRowValue();
        student.setDept(cellEditEvent.getNewValue());
    }
    public void handleDeleteButtonClick(ActionEvent event) {
        if(!observableStudentList.isEmpty()) {
            System.out.println("Delete button clicked");
            Alert deleteAlert = new Alert(Alert.AlertType.WARNING, "Confirm", ButtonType.OK, ButtonType.CANCEL);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            deleteAlert.setContentText("Are you sure you want to delete this?\n\nTHIS CANNOT BE UNDONE.");
            deleteAlert.initModality(Modality.APPLICATION_MODAL);
            deleteAlert.initOwner(owner);
            deleteAlert.showAndWait();
            if(deleteAlert.getResult() == ButtonType.OK) {
                observableStudentList.removeAll(nodeTable.getSelectionModel().getSelectedItems());
                nodeTable.getSelectionModel().clearSelection();
            }
            else {
                deleteAlert.close();
            }
        }
    }
    public void handleClearButtonClick(ActionEvent event) {
        Admin student = new Admin();
        AdminPanelController control = new AdminPanelController();
        if (!(currentNode.getDestinations().get(0)).isEmpty()){
            System.out.println("node is alrady fucked");
            student.setDeptType(currentNode.getBuildingDestinations().get(0));
            observableStudentList.add(student);

        }
    }
    //filter table by first or last name
    public void filterStudentList(String oldValue, String newValue) {
        ObservableList<Admin> filteredList = FXCollections.observableArrayList();
        if(filterInput == null || (newValue.length() < oldValue.length()) || newValue == null) {
            nodeTable.setItems(observableStudentList);
        }
        else {
            newValue = newValue.toUpperCase();
            for(Admin students : nodeTable.getItems()) {
                String filterFirstName = students.getDeptType();
                String filterLastName = students.getDeptName();
                if(filterFirstName.toUpperCase().contains(newValue) || filterLastName.toUpperCase().contains(newValue)) {
                    filteredList.add(students);
                }
            }
            nodeTable.setItems(filteredList);
        }
    }
    public void handleSave(ActionEvent event) {
        Stage secondaryStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Kiosk.Kiosk.Kiosk.Admin Table");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        if(observableStudentList.isEmpty()) {
            secondaryStage.initOwner(this.fileMenu.getScene().getWindow());
            Alert emptyTableAlert = new Alert(Alert.AlertType.ERROR, "EMPTY TABLE", ButtonType.OK);
            emptyTableAlert.setContentText("You have nothing to save");
            emptyTableAlert.initModality(Modality.APPLICATION_MODAL);
            emptyTableAlert.initOwner(this.fileMenu.getScene().getWindow());
            emptyTableAlert.showAndWait();
            if(emptyTableAlert.getResult() == ButtonType.OK) {
                emptyTableAlert.close();
            }
        }
        else {
            File file = fileChooser.showSaveDialog(secondaryStage);
            if(file != null) {
                saveFile(nodeTable.getItems(), file);
            }
        }
    }
    public void saveFile(ObservableList<Admin> observableStudentList, File file) {
        try {
            BufferedWriter outWriter = new BufferedWriter(new FileWriter(file));

            for(Admin students : observableStudentList) {
                outWriter.write(students.toString());
                outWriter.newLine();
            }
            System.out.println(observableStudentList.toString());
            outWriter.close();
        } catch (IOException e) {
            Alert ioAlert = new Alert(Alert.AlertType.ERROR, "OOPS!", ButtonType.OK);
            ioAlert.setContentText("Sorry. An error has occurred.");
            ioAlert.showAndWait();
            if(ioAlert.getResult() == ButtonType.OK) {
                ioAlert.close();
            }
        }
    }
    public void closeApp(ActionEvent event) {
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm", ButtonType.OK, ButtonType.CANCEL);
        Stage stage = (Stage) fileMenu.getScene().getWindow();
        exitAlert.setContentText("Are you sure you want to exit?");
        exitAlert.initModality(Modality.APPLICATION_MODAL);
        exitAlert.initOwner(stage);
        exitAlert.showAndWait();

        if(exitAlert.getResult() == ButtonType.OK) {
            Platform.exit();
        }
        else {
            exitAlert.close();
        }
    }

    public void back(){
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }
    public void addTolist() {
        System.out.println("haha");
        Admin admin = new Admin();
        observableStudentList.add(admin);
        admin.printDept();
    }

    public void setNode(LocationNode node) {
        this.currentNode = node;
    }

}







