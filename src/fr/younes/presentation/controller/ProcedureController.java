package fr.younes.presentation.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import fr.younes.metier.Employee;
import fr.younes.metier.EmployeeManagement;
import fr.younes.metier.Processus;
import fr.younes.metier.ProcessusManagement;
import fr.younes.presentation.model.AbstractModel;
import fr.younes.presentation.model.EmployerTable;
import fr.younes.presentation.model.ProcessusTable;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProcedureController implements Initializable {
	protected AbstractModel art;
	protected ProcessusManagement monProcessusManagement;

	{
		this.art = art;
		this.monProcessusManagement = new ProcessusManagement();
	}

	private int id = 0;
	@FXML
	TableView<ProcessusTable> ProcessusTableView;
	@FXML
	TableColumn<ProcessusTable, Integer> ProcessusTableNo;
	@FXML
	TableColumn<ProcessusTable, String> EmployeeTableFirstName;
	@FXML
	TableColumn<ProcessusTable, String> EmployeeTableLastName;
	@FXML
	TableColumn<ProcessusTable, String> EmployeeTableEmail;
	@FXML
	TableColumn<ProcessusTable, Integer> EmployeeTablePhone;
	@FXML
	TableColumn<ProcessusTable, String> ProcessusTableNom;
	@FXML
	TableColumn<ProcessusTable, String> EmployeeTableAddress;
	@FXML
	AnchorPane rootPane;

	@FXML
	private JFXButton btn_employer, btn_procedure, btn_doc, btn_etap,Affectation;
	@FXML
	private TextField ProcessusName, EmployerFirstName, EmployerLastName, EmployerEmail, EmployerPhone, EmployerAddress;
	@FXML
	private ChoiceBox EmployerRole, EmployerProcedure, EmployerEtape;
	@FXML
	private Button adminClearButtonClick;
	@FXML
	private Button adminSaveButtonClick;
	@FXML
	private Button addWithEmp;

	private boolean isSetAdminAddNewButtonClick;
	private boolean isSetAdminEditButtonClick;
	private boolean isSetEmployeeWithProcessusButtonClick = false;

	@FXML
	private void handleButtonAction(javafx.event.ActionEvent e) {
		if (e.getSource() == btn_procedure) {
			loadNewFxml("/fr/younes/presentation/view/procedure/IndexProcedure.fxml", e);
		} else if (e.getSource() == btn_employer) {
			loadNewFxml("/fr/younes/presentation/view/employer/IndexEmployer.fxml", e);
		} else if (e.getSource() == btn_doc) {
			loadNewFxml("/fr/younes/presentation/view/document/IndexDocument.fxml", e);
		} else if (e.getSource() == btn_etap) {
			loadNewFxml("/fr/younes/presentation/view/etape/IndexEtape.fxml",e);
		} else if (e.getSource() == Affectation) {
			AffectationProcessus re = new AffectationProcessus();
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("/fr/younes/presentation/view/procedure/Registration.fxml"));
	        try {
				loader.load();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        Parent p = loader.getRoot();
	        Stage stage = new Stage();
	        stage.setScene(new Scene(p));
	        stage.setResizable(false);
	        stage.initModality(Modality.APPLICATION_MODAL);
	        AffectationEtape registrationController = new AffectationEtape();
	        registrationController.setStage(stage);
	        stage.show();
		}
	}

	@FXML
	private void setEmployerAddNewButtonClick(Event event) {
		adminSetAllEnable();
		setSetAdminAddNewButtonClick(true);
		isSetAdminAddNewButtonClick = true;
	}

	@FXML
	private void setEmployerEditButtonClick(Event event) {
		ProcessusTable getSelectedRow = ProcessusTableView.getSelectionModel().getSelectedItem();
		this.id=getSelectedRow.getProcessusTableNo();
//		Employee emp = monProcessusManagement.findById(getSelectedRow.getProcessusTableNo());
		ProcessusName.setText(getSelectedRow.getProcessusTableNom());
		EmployerFirstName.setText(getSelectedRow.getEmployeeTableFirstName());
		EmployerLastName.setText(getSelectedRow.getEmployeeTableLastName());
		EmployerEmail.setText(getSelectedRow.getEmployeeTableEmail());
		EmployerPhone.setText(getSelectedRow.getEmployeeTablePhone()+"");
		EmployerAddress.setText(getSelectedRow.getEmployeeTableAddress());
		if(getSelectedRow.getEmployeeTableEmail().equals("Non Affecter")==false) {
			employerTextFieldSetAllEnable();
		}
		adminSetAllEnable();

        isSetAdminEditButtonClick = true;
		//System.out.println(emp);
	}

	@FXML
	private void setEmployerDeleteButtonClick(Event event) {
//		EmployerTable getSelectedRow = ProcessusTableView.getSelectionModel().getSelectedItem();
//		monProcessusManagement.deleteEmplById(getSelectedRow.getEmployeeTableNo());
	}

	@FXML
	private void setProcessusSaveButtonClick(Event event) {
		Processus newProcess = new Processus(ProcessusName.getText());
		if (isSetAdminAddNewButtonClick) {
			if(isSetEmployeeWithProcessusButtonClick==false) {
				if (isSetAdminAddNewButtonClick) {
					monProcessusManagement.addProcessus(newProcess);
				}else if(isSetAdminEditButtonClick) {
					
				}
			}else {
				Employee newEmployer=new Employee(EmployerFirstName.getText(), 
		        		EmployerLastName.getText(), 
		        		EmployerEmail.getText(),
		        		EmployerAddress.getText(),
		        		Integer.parseInt(EmployerPhone.getText()), 
		        		"Chef Administratif");
				newProcess.setEmp(newEmployer);
				monProcessusManagement.addProcessus2(newProcess);
			}
		}else if (isSetAdminEditButtonClick) {
			Employee newEmployer=new Employee(EmployerFirstName.getText(), 
	        		EmployerLastName.getText(), 
	        		EmployerEmail.getText(),
	        		EmployerAddress.getText(),
	        		Integer.parseInt(EmployerPhone.getText()), 
	        		"Chef Administratif");
			newProcess.setId(id);
			newProcess.setEmp(newEmployer);
			monProcessusManagement.editProcess(newProcess);
		}
		
        ProcessusTableView.setItems(monProcessusManagement.getDataEmployeeFromSqlAndAddToObservableList());
		adminSetAllDisable();
		adminSetAllClear();
		employerTextFieldSetAllDisable();
		isSetAdminEditButtonClick = false;
		isSetAdminAddNewButtonClick = false;
	}

	@FXML
	private void setEmployerRefreshButtonClick(Event event) {
		 ProcessusTableView.setItems(monProcessusManagement.getDataEmployeeFromSqlAndAddToObservableList());

	}

	@FXML
	private void addProcessusWithEmp() {
		employerTextFieldSetAllEnable();
		this.isSetEmployeeWithProcessusButtonClick = true;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ProcessusTableNo.setCellValueFactory(new PropertyValueFactory<>("ProcessusTableNo")); 
		EmployeeTableFirstName.setCellValueFactory(new PropertyValueFactory<>("EmployeeTableFirstName"));
		EmployeeTableLastName.setCellValueFactory(new PropertyValueFactory<>("EmployeeTableLastName"));
		EmployeeTableEmail.setCellValueFactory(new PropertyValueFactory<>("EmployeeTableEmail"));
		EmployeeTablePhone.setCellValueFactory(new PropertyValueFactory<>("EmployeeTablePhone"));
		ProcessusTableNom.setCellValueFactory(new PropertyValueFactory<>("EmployeeTableRole"));
		EmployeeTableAddress.setCellValueFactory(new PropertyValueFactory<>("EmployeeTableAddress"));
		
		ProcessusTableView.setItems(monProcessusManagement.getDataEmployeeFromSqlAndAddToObservableList());

	}

	private void adminSetAllEnable() {
		ProcessusName.setDisable(false);

		adminSaveButtonClick.setDisable(false);
		adminClearButtonClick.setDisable(false);
		addWithEmp.setDisable(false);
	}

	private void employerTextFieldSetAllEnable() {
		// TODO Auto-generated method stub
		EmployerFirstName.setDisable(false);
		EmployerLastName.setDisable(false);
		EmployerEmail.setDisable(false);
		EmployerPhone.setDisable(false);
		EmployerAddress.setDisable(false);

	}
	private void employerTextFieldSetAllDisable() {
		// TODO Auto-generated method stub
		EmployerFirstName.setDisable(true);
		EmployerLastName.setDisable(true);
		EmployerEmail.setDisable(true);
		EmployerPhone.setDisable(true);
		EmployerAddress.setDisable(true);

	}

	private void adminSetAllClear() {
		ProcessusName.clear();
		EmployerFirstName.clear();
		EmployerLastName.clear();
		EmployerEmail.clear();
		EmployerPhone.clear();
		EmployerAddress.clear();
	}

	private void adminSetAllDisable() {
		ProcessusName.setDisable(true);

		adminSaveButtonClick.setDisable(true);
		adminClearButtonClick.setDisable(true);
		addWithEmp.setDisable(true);

	}

	public boolean isSetAdminAddNewButtonClick() {
		return isSetAdminAddNewButtonClick;
	}

	public void setSetAdminAddNewButtonClick(boolean isSetAdminAddNewButtonClick) {
		this.isSetAdminAddNewButtonClick = isSetAdminAddNewButtonClick;
	}

	private void loadNewFxml(String chemin, Event event) {
		try {
			Parent adminParent = FXMLLoader.load(getClass().getResource(chemin));
			Scene adminScene = new Scene(adminParent);
			Stage adminStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			adminStage.hide();
			adminStage.setScene(adminScene);
			adminStage.setTitle("Admin Panel");
			adminStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
