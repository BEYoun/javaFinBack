package fr.younes.presentation.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import fr.younes.metier.EmployeeManagement;
import fr.younes.metier.Etape;
import fr.younes.metier.EtapeManagement;
import fr.younes.metier.ProcessusManagement;
import fr.younes.presentation.model.AbstractModel;
import fr.younes.presentation.model.EtapeTable;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EtapeController implements Initializable {
	protected AbstractModel art;
	protected EmployeeManagement monEmployeeManagement;
	protected ProcessusManagement monProcessusManagement;
	protected EtapeManagement monEtapeManagement;

	{
		this.art = art;
		this.monEmployeeManagement = new EmployeeManagement();
		this.monProcessusManagement = new ProcessusManagement();
		this.monEtapeManagement = new EtapeManagement();
	}

	private int id = 0;
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
	@FXML
	TableColumn<EtapeTable, String> EtapeEmployerNom;
	@FXML
	TableColumn<EtapeTable, String> EtapeEmployerPrenom;
	@FXML
	TableColumn<EtapeTable, String> EtapeEmployerEmail;

	@FXML
	private JFXButton btn_employer, btn_procedure, btn_doc, btn_etap, Affectation;
	@FXML
	private TextField EtapeNom;
	@FXML
	private ChoiceBox<String> EtapeChoixProcedureNom, EtapeSeeProcedureNom;
	@FXML
	private Button adminClearButtonClick;
	@FXML
	private Button adminSaveButtonClick;

	private boolean isSetAdminAddNewButtonClick;
	private boolean isSetAdminEditButtonClick;

	@FXML
	private void handleButtonAction(javafx.event.ActionEvent e) {
		if (e.getSource() == btn_procedure) {
			loadNewFxml("/fr/younes/presentation/view/procedure/IndexProcedure.fxml", e);
		} else if (e.getSource() == btn_employer) {
			loadNewFxml("/fr/younes/presentation/view/employer/IndexEmployer.fxml", e);
		} else if (e.getSource() == btn_doc) {
			loadNewFxml("/fr/younes/presentation/view/document/IndexDocument.fxml", e);
		} else if (e.getSource() == btn_etap) {
			loadNewFxml("/fr/younes/presentation/view/etape/IndexEtape.fxml", e);
		} else if (e.getSource() == Affectation) {
			AffectationEtape re = new AffectationEtape();
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("/fr/younes/presentation/view/etape/Registration.fxml"));
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
		EtapeTable getSelectedRow = EtapeTableView.getSelectionModel().getSelectedItem();
		this.id = getSelectedRow.getEtapeTableNo();
		EtapeNom.setText(getSelectedRow.getEtapeTableNom());
		EtapeChoixProcedureNom.setValue(getSelectedRow.getEtapeProcedureNom());
		adminSetAllEnable();

		isSetAdminEditButtonClick = true;
	}

	@FXML
	private void setEmployerDeleteButtonClick(Event event) {
		EtapeTable getSelectedRow = EtapeTableView.getSelectionModel().getSelectedItem();
		monEtapeManagement.deleteEmplById(getSelectedRow.getEtapeTableNo());
	}
	
	@FXML
	private void setEmployerSaveButtonClick(Event event) {
		String choixProcessus;
		choixProcessus = EtapeChoixProcedureNom.getValue().toString();
		String[] monString = choixProcessus.split("/");
		Etape newEtape = new Etape(EtapeNom.getText(), Integer.parseInt(monString[1]));

		if (isSetAdminAddNewButtonClick) {
			monEtapeManagement.addEtape(newEtape);
		} else if (isSetAdminEditButtonClick) {
        	newEtape.setId(this.id);
        	monEtapeManagement.editUser(newEtape);
		}
		EtapeTableView.setItems(monEtapeManagement.getDataEtapeFromSqlAndAddToObservableList());
		adminSetAllDisable();
		adminSetAllClear();
		isSetAdminEditButtonClick = false;
		isSetAdminAddNewButtonClick = false;
	}

	@FXML
	private void setEmployerRefreshButtonClick(Event event) {
		EtapeTableView.setItems(monEtapeManagement.getDataEtapeFromSqlAndAddToObservableList());

	}
	@FXML
	private void showSelectedProcedure(Event event) {
		String choixProcessus;
		choixProcessus = EtapeSeeProcedureNom.getValue().toString();
		String[] monString = choixProcessus.split("/");
		int id = Integer.parseInt(monString[1]);
		EtapeTableView.setItems(monEtapeManagement.getDataEmployeeFromSqlAndAddToObservableListById(id));
	}
	@FXML
	private void setButtonGiveOrder(Event event) {
		EtapeTable getSelectedRow = EtapeTableView.getSelectionModel().getSelectedItem();
		TextInputDialog dialog = new TextInputDialog("0");
		dialog.setTitle("Select Order for Etapes");
		dialog.setHeaderText("Order for Etapes");
		dialog.setContentText("Please enter your order:");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		    System.out.println("Your order: " + result.get());
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		EtapeTableNo.setCellValueFactory(new PropertyValueFactory<>("EtapeTableNo"));
		EtapeTableNom.setCellValueFactory(new PropertyValueFactory<>("EtapeTableNom"));
		EtapeProcedureNom.setCellValueFactory(new PropertyValueFactory<>("EtapeProcedureNom"));
		EtapeOrdre.setCellValueFactory(new PropertyValueFactory<>("EtapeOrdre"));
		EtapeEmployerNom.setCellValueFactory(new PropertyValueFactory<>("EtapeEmployerNom"));
		EtapeEmployerPrenom.setCellValueFactory(new PropertyValueFactory<>("EtapeEmployerPrenom"));
		EtapeEmployerEmail.setCellValueFactory(new PropertyValueFactory<>("EtapeEmployerEmail"));

		EtapeTableView.setItems(monEtapeManagement.getDataEtapeFromSqlAndAddToObservableList());

		EtapeChoixProcedureNom.getItems().addAll(monProcessusManagement.getDataProcessusFromSqlToObsevableList());
		EtapeSeeProcedureNom.getItems().addAll(monProcessusManagement.getDataProcessusFromSqlToObsevableList());

	}

	private void adminSetAllEnable() {
		EtapeNom.setDisable(false);
		EtapeChoixProcedureNom.setDisable(false);

		adminSaveButtonClick.setDisable(false);
		adminClearButtonClick.setDisable(false);
	}

	private void adminSetAllClear() {
		EtapeNom.clear();
	}

	private void adminSetAllDisable() {
		EtapeNom.setDisable(true);
		EtapeChoixProcedureNom.setDisable(true);
		EtapeChoixProcedureNom.setValue("Select Processus");
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
