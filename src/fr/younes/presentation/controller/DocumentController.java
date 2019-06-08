package fr.younes.presentation.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import fr.younes.metier.Document;
import fr.younes.metier.DocumentManagement;
import fr.younes.metier.EmployeeManagement;
import fr.younes.metier.EtapeManagement;
import fr.younes.metier.ProcessusManagement;
import fr.younes.presentation.model.AbstractModel;
import fr.younes.presentation.model.DocumentTable;
import fr.younes.presentation.model.EmployerTable;
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
import javafx.stage.Stage;

public class DocumentController  implements Initializable {
	protected AbstractModel art;
	protected EmployeeManagement monEmployeeManagement;
	protected ProcessusManagement monProcessusManagement;
	protected DocumentManagement monDocumentManagement;
	protected EtapeManagement monEtapeManagement;
	
	
	{
		this.art=art;
		this.monEmployeeManagement= new EmployeeManagement();
		this.monProcessusManagement = new ProcessusManagement();
		this.monDocumentManagement = new DocumentManagement();
		this.monEtapeManagement = new EtapeManagement();
	}
	
	private int id=0;
	@FXML TableView<DocumentTable> DocumentTableView;
	@FXML TableColumn<DocumentTable,Integer> DocumentTableNo;
	@FXML TableColumn<DocumentTable,String> DocumentTableName;
	@FXML TableColumn<DocumentTable,String> DocumentTablePName;
	
	
	@FXML
	private JFXButton btn_employer,btn_procedure,btn_doc,btn_etap;
	@FXML
	private TextField ProcedureNom;
	@FXML
	private ChoiceBox<String> choixSeeProcedure,choixSelectProcedure,choixSelectEtape;
	@FXML
    private Button adminClearButtonClick;
    @FXML
    private Button adminSaveButtonClick,viewProcedure;
	
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
//		EmployerTable getSelectedRow = EmployeeTableView.getSelectionModel().getSelectedItem();
//		this.id=getSelectedRow.getEmployeeTableNo();
//		Employee emp = monEmployeeManagement.findById(getSelectedRow.getEmployeeTableNo());
//		ProcedureNom.setText(emp.getFirstName());
//		EmployerLastName.setText(emp.getLastName());
//		EmployerEmail.setText(emp.getEmail());
//		EmployerRole.setValue(emp.getRole());
//		EmployerPhone.setText(emp.getPhone()+"");
//		EmployerAddress.setText(emp.getAddress());
//		adminSetAllEnable();
//
//        isSetAdminEditButtonClick = true;
//		System.out.println(emp);
    }
	@FXML
    private void setEmployerDeleteButtonClick(Event event){
		DocumentTable getSelectedRow = DocumentTableView.getSelectionModel().getSelectedItem();
		//monEmployeeManagement.deleteEmplById(getSelectedRow.getDocumentTableNo());
    }
	@FXML
    private void setEmployerSaveButtonClick(Event event){
		
		String choixEtape,choixProcess;
		choixEtape = choixSelectEtape.getValue().toString();
		choixProcess = choixSelectProcedure.getValue().toString();
		String[] monString = choixEtape.split("/");
		String[] monString2 = choixProcess.split("/");
		int id_etape=Integer.parseInt(monString[1]);
		int id_process=Integer.parseInt(monString2[1]);
		
        Document monDoc = new Document(ProcedureNom.getText(),id_etape, id_process);
        if(isSetAdminAddNewButtonClick) {
        	monDocumentManagement.addDoc(monDoc);
        }else if(isSetAdminEditButtonClick) {
//        	newEmployer.setId(this.id);
//        	monEmployeeManagement.editUser(newEmployer);
        }
        DocumentTableView.setItems(monDocumentManagement.getDataEmployeeFromSqlAndAddToObservableList());
        adminSetAllDisable();
        adminSetAllClear();
        isSetAdminEditButtonClick=false;
        isSetAdminAddNewButtonClick = false;
    }
	@FXML
	private void setEmployerRefreshButtonClick(Event event) {
		//DocumentTableView.setItems(monEmployeeManagement.getDataEmployeeFromSqlAndAddToObservableList());
       
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		DocumentTableNo.setCellValueFactory(new PropertyValueFactory<>("DocumentTableNo")); 
		DocumentTableName.setCellValueFactory(new PropertyValueFactory<>("DocumentTableName"));
		DocumentTablePName.setCellValueFactory(new PropertyValueFactory<>("DocumentTablePName"));
		
		DocumentTableView.setItems(monDocumentManagement.getDataEmployeeFromSqlAndAddToObservableList());
		
		//choixEtape.getItems().addAll(monProcessusManagement.getDataProcessusFromSqlToObsevableList());
		choixSelectEtape.getItems().addAll(monEtapeManagement.getDataProcessusFromSqlToObsevableList());
		choixSeeProcedure.getItems().addAll(monProcessusManagement.getDataProcessusFromSqlToObsevableList());
		choixSelectProcedure.getItems().addAll(monProcessusManagement.getDataProcessusFromSqlToObsevableList());
		
	}
	
	private void adminSetAllEnable(){
		ProcedureNom.setDisable(false);
		choixSelectProcedure.setDisable(false);
		choixSelectEtape.setDisable(false);
		
		adminSaveButtonClick.setDisable(false);
        adminClearButtonClick.setDisable(false);
    }
	private void adminSetAllClear(){
		ProcedureNom.clear();
    }
	private void adminSetAllDisable(){
		ProcedureNom.setDisable(true);
		choixSelectProcedure.setDisable(true);
		choixSelectEtape.setDisable(true);
		
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
