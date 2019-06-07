package fr.younes.presentation.controller;

import java.net.URL;
import java.util.ResourceBundle;

import fr.younes.metier.EmployeeManagement;
import fr.younes.metier.EtapeManagement;
import fr.younes.presentation.model.EmployerTable;
import fr.younes.presentation.model.EtapeTable;
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
public class AffectationEtape implements Initializable {
	protected EmployeeManagement monEmployeeManagement;
	protected EtapeManagement monEtapeManagement;
	{
		this.monEmployeeManagement=new EmployeeManagement();
		this.monEtapeManagement= new EtapeManagement();
	}
	
    static Stage stage;
    public void setStage(Stage stage){
        this.stage=stage;
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
	TableView<EtapeTable> EtapeTableView;
	@FXML
	TableColumn<EtapeTable, Integer> EtapeTableNo;
	@FXML
	TableColumn<EtapeTable, String> EtapeTableNom;
	@FXML
	TableColumn<EtapeTable, String> EtapeProcedureNom;
	@FXML
	TableColumn<EtapeTable, Integer> EtapeOrdre;





   

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	EmployeeTableNo.setCellValueFactory(new PropertyValueFactory<EmployerTable,Integer>("EmployeeTableNo")); 
		EmployeeTableFirstName.setCellValueFactory(new PropertyValueFactory<>("EmployeeTableFirstName"));
		EmployeeTableLastName.setCellValueFactory(new PropertyValueFactory<>("EmployeeTableLastName"));
		EmployeeTableEmail.setCellValueFactory(new PropertyValueFactory<>("EmployeeTableEmail"));
		EmployeeTablePhone.setCellValueFactory(new PropertyValueFactory<>("EmployeeTablePhone"));
		EmployeeTableAddress.setCellValueFactory(new PropertyValueFactory<>("EmployeeTableAddress"));
		EtapeTableNo.setCellValueFactory(new PropertyValueFactory<>("EtapeTableNo"));
		EtapeTableNom.setCellValueFactory(new PropertyValueFactory<>("EtapeTableNom"));
		EtapeProcedureNom.setCellValueFactory(new PropertyValueFactory<>("EtapeProcedureNom"));
		EtapeOrdre.setCellValueFactory(new PropertyValueFactory<>("EtapeOrdre"));
		EmployeeTableView.setItems(monEmployeeManagement.getDataEmployeeOnlyFromSqlAndAddToObservableList());
		EtapeTableView.setItems(monEtapeManagement.getDataEtapeNonAffecterFromSqlAndAddToObservableList());
    }

    @FXML
    private void AffectationEmployee() {
    	EmployerTable monChoixEmpl = EmployeeTableView.getSelectionModel().getSelectedItem();
    	EtapeTable monChoixEtape = EtapeTableView.getSelectionModel().getSelectedItem();
    	monEtapeManagement.affecterEmployeeToEtape(monChoixEtape.getEtapeTableNo(),monChoixEmpl.getEmployeeTableNo());
    	EtapeTableView.setItems(monEtapeManagement.getDataEtapeNonAffecterFromSqlAndAddToObservableList());
    	System.out.println(monChoixEmpl.getEmployeeTableEmail());
    }



}
