package fr.younes.presentation.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import fr.younes.metier.EmployeeManagement;
import fr.younes.metier.ProcessusManagement;
import fr.younes.presentation.model.EmployerTable;
import fr.younes.presentation.model.EtapeTable;
import fr.younes.presentation.model.ProcessusTable;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * Created by Tanvir on 8/20/2016.
 */
public class AffectationProcessus implements Initializable {

	protected EmployeeManagement monEmployeeManagement;
	protected ProcessusManagement monProcessusManagement;
	{
		this.monEmployeeManagement=new EmployeeManagement();
		this.monProcessusManagement= new ProcessusManagement();
	}

    private static String ID,ChoosingSec,finalAllCourse="empty",courseCode;
    static Stage stage;
    public void setStage(Stage stage){
        this.stage=stage;
    }

    public void setID(String ID){
        this.ID = ID;
    }
    public void setChoosingSec(String ChoosingSec){
        this.ChoosingSec=ChoosingSec;
    }
    @FXML TableView<EmployerTable> EmployeeTableView;
	@FXML TableColumn<EmployerTable,Integer> EmployeeTableNo;
	@FXML TableColumn<EmployerTable,String> EmployeeTableFirstName;
	@FXML TableColumn<EmployerTable,String> EmployeeTableLastName;
	@FXML TableColumn<EmployerTable,String> EmployeeTableEmail;
	@FXML TableColumn<EmployerTable,Integer> EmployeeTablePhone;
	@FXML TableColumn<EmployerTable,String> EmployeeTableRole;
	@FXML TableColumn<EmployerTable,String> EmployeeTableAddress;

    @FXML
    TextField registrationTFSearch;
    @FXML
	TableView<ProcessusTable> ProcessusTableView;
	@FXML
	TableColumn<ProcessusTable, Integer> ProcessusTableNo;
	@FXML
	TableColumn<ProcessusTable, String> ProcessusNom;



    private static TableView tanother;
    public void setTanother(TableView a){
        this.tanother = a;
    }

    @FXML
    private void AffectationEmployee() {
    	EmployerTable monChoixEmpl = EmployeeTableView.getSelectionModel().getSelectedItem();
    	ProcessusTable monChoixProcessus = ProcessusTableView.getSelectionModel().getSelectedItem();
    	monProcessusManagement.affecterEmployeeToProcessus(monChoixProcessus.getProcessusTableNo(),monChoixEmpl.getEmployeeTableNo());
    	ProcessusTableView.setItems(monProcessusManagement.getDataProcessusNonAffecterFromSqlAndAddToObservableList());
    	System.out.println(monChoixEmpl.getEmployeeTableEmail());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	EmployeeTableNo.setCellValueFactory(new PropertyValueFactory<EmployerTable,Integer>("EmployeeTableNo")); 
		EmployeeTableFirstName.setCellValueFactory(new PropertyValueFactory<>("EmployeeTableFirstName"));
		EmployeeTableLastName.setCellValueFactory(new PropertyValueFactory<>("EmployeeTableLastName"));
		EmployeeTableEmail.setCellValueFactory(new PropertyValueFactory<>("EmployeeTableEmail"));
		EmployeeTablePhone.setCellValueFactory(new PropertyValueFactory<>("EmployeeTablePhone"));
		EmployeeTableAddress.setCellValueFactory(new PropertyValueFactory<>("EmployeeTableAddress"));
		ProcessusTableNo.setCellValueFactory(new PropertyValueFactory<>("ProcessusTableNo"));
		ProcessusNom.setCellValueFactory(new PropertyValueFactory<>("ProcessusTableNom"));
		EmployeeTableView.setItems(monEmployeeManagement.getDataChefOnlyFromSqlAndAddToObservableList());
		ProcessusTableView.setItems(monProcessusManagement.getDataProcessusNonAffecterFromSqlAndAddToObservableList());
    }



}
