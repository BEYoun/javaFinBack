package fr.younes.presentation.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import fr.younes.metier.Employee;
import fr.younes.metier.EmployeeManagement;
import fr.younes.presentation.model.AbstractModel;
import fr.younes.presentation.model.EmployerTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.stage.Stage;

public class EmployerController  implements Initializable {
	protected AbstractModel art;
	protected EmployeeManagement monEmployeeManagement;
	
	
	{
		this.art=art;
		this.monEmployeeManagement= new EmployeeManagement();
	}
	
	private int id=0;
	@FXML TableView<EmployerTable> EmployeeTableView;
	@FXML TableColumn<EmployerTable,Integer> EmployeeTableNo;
	@FXML TableColumn<EmployerTable,String> EmployeeTableFirstName;
	@FXML TableColumn<EmployerTable,String> EmployeeTableLastName;
	@FXML TableColumn<EmployerTable,String> EmployeeTableEmail;
	@FXML TableColumn<EmployerTable,Integer> EmployeeTablePhone;
	@FXML TableColumn<EmployerTable,String> EmployeeTableRole;
	@FXML TableColumn<EmployerTable,String> EmployeeTableAddress;
	
	
	@FXML
	private JFXButton btn_employer,btn_procedure,btn_doc,btn_etap;
	@FXML
	private TextField EmployerFirstName,EmployerLastName,EmployerEmail,EmployerPhone,EmployerAddress;
	@FXML
	private ChoiceBox<String> EmployerRole,EmployerProcedure,EmployerEtape;
	@FXML
    private Button adminClearButtonClick;
    @FXML
    private Button adminSaveButtonClick;
	
	private boolean isSetAdminAddNewButtonClick;
	private boolean isSetAdminEditButtonClick;
	
	
	@FXML
	private void handleButtonAction(javafx.event.ActionEvent e) {
		if(e.getSource()==btn_procedure) {
			loadNewFxml("/fr/younes/presentation/view/procedure/IndexProcedure.fxml",e);
		}else if(e.getSource()==btn_employer) {
			loadNewFxml("/fr/younes/presentation/view/employer/IndexEmployer.fxml",e);
		}else if(e.getSource()==btn_doc) {
			loadNewFxml("/fr/younes/presentation/view/document/IndexDocument.fxml",e);
		}else if(e.getSource()==btn_etap) {
			loadNewFxml("/fr/younes/presentation/view/etape/IndexEtape.fxml",e);
		}
	}
	@FXML	
    private void setEmployerAddNewButtonClick(Event event){
        adminSetAllEnable();
        setSetAdminAddNewButtonClick(true);
        isSetAdminAddNewButtonClick = true;
    }
	@FXML
    private void setEmployerEditButtonClick(Event event){
		EmployerTable getSelectedRow = EmployeeTableView.getSelectionModel().getSelectedItem();
		this.id=getSelectedRow.getEmployeeTableNo();
		Employee emp = monEmployeeManagement.findById(getSelectedRow.getEmployeeTableNo());
		EmployerFirstName.setText(emp.getFirstName());
		EmployerLastName.setText(emp.getLastName());
		EmployerEmail.setText(emp.getEmail());
		EmployerRole.setValue(emp.getRole());
		EmployerPhone.setText(emp.getPhone()+"");
		EmployerAddress.setText(emp.getAddress());
		adminSetAllEnable();

        isSetAdminEditButtonClick = true;
		System.out.println(emp);
    }
	@FXML
    private void setEmployerDeleteButtonClick(Event event){
		EmployerTable getSelectedRow = EmployeeTableView.getSelectionModel().getSelectedItem();
		monEmployeeManagement.deleteEmplById(getSelectedRow.getEmployeeTableNo());
    }
	@FXML
    private void setEmployerSaveButtonClick(Event event){
        Employee newEmployer = new Employee(EmployerFirstName.getText(), 
        		EmployerLastName.getText(), 
        		EmployerEmail.getText(),
        		EmployerAddress.getText(),
        		Integer.parseInt(EmployerPhone.getText()), 
        		EmployerRole.getValue().toString());
        if(isSetAdminAddNewButtonClick) {
        	monEmployeeManagement.addEmp(newEmployer);
        }else if(isSetAdminEditButtonClick) {
        	newEmployer.setId(this.id);
        	monEmployeeManagement.editUser(newEmployer);
        }
        EmployeeTableView.setItems(monEmployeeManagement.getDataEmployeeFromSqlAndAddToObservableList());
        adminSetAllDisable();
        adminSetAllClear();
        isSetAdminEditButtonClick=false;
        isSetAdminAddNewButtonClick = false;
    }
	@FXML
	private void setEmployerRefreshButtonClick(Event event) {
		EmployeeTableView.setItems(monEmployeeManagement.getDataEmployeeFromSqlAndAddToObservableList());
       
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		EmployeeTableNo.setCellValueFactory(new PropertyValueFactory<EmployerTable,Integer>("EmployeeTableNo")); 
		EmployeeTableFirstName.setCellValueFactory(new PropertyValueFactory<>("EmployeeTableFirstName"));
		EmployeeTableLastName.setCellValueFactory(new PropertyValueFactory<>("EmployeeTableLastName"));
		EmployeeTableEmail.setCellValueFactory(new PropertyValueFactory<>("EmployeeTableEmail"));
		EmployeeTablePhone.setCellValueFactory(new PropertyValueFactory<>("EmployeeTablePhone"));
		EmployeeTableRole.setCellValueFactory(new PropertyValueFactory<>("EmployeeTableRole"));
		EmployeeTableAddress.setCellValueFactory(new PropertyValueFactory<>("EmployeeTableAddress"));
		
		EmployeeTableView.setItems(monEmployeeManagement.getDataEmployeeFromSqlAndAddToObservableList());
        
//		ObservableList<String> availableChoices = FXCollections.observableArrayList(); 
//		availableChoices.removeAll(availableChoices);
//		String a="obh";
//		String b="ohbh";
//		availableChoices.addAll(a,b);
//		EmployerProcedure.getItems().addAll(availableChoices);
		
	}
	
	private void adminSetAllEnable(){
		EmployerFirstName.setDisable(false);
		EmployerLastName.setDisable(false);
		EmployerRole.setDisable(false);
		EmployerEmail.setDisable(false);
		EmployerPhone.setDisable(false);
		EmployerAddress.setDisable(false);
		EmployerProcedure.setDisable(false);
		EmployerEtape.setDisable(false);
		
		
		adminSaveButtonClick.setDisable(false);
        adminClearButtonClick.setDisable(false);
    }
	private void adminSetAllClear(){
		EmployerFirstName.clear();
		EmployerLastName.clear();
		EmployerEmail.clear();
		EmployerPhone.clear();
		EmployerAddress.clear();
    }
	private void adminSetAllDisable(){
		EmployerFirstName.setDisable(true);
		EmployerLastName.setDisable(true);
		EmployerRole.setDisable(true);
		EmployerEmail.setDisable(true);
		EmployerPhone.setDisable(true);
		EmployerAddress.setDisable(true);
		EmployerProcedure.setDisable(true);
		EmployerEtape.setDisable(true);
		
		adminSaveButtonClick.setDisable(true);
        adminClearButtonClick.setDisable(true);

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
