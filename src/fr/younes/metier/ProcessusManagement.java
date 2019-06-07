package fr.younes.metier;

import java.util.ArrayList;

import fr.younes.connection.SdzConnection;
import fr.younes.persistance.EmployeesDAO;
import fr.younes.persistance.ProcessusDAO;
import fr.younes.presentation.model.EmployerTable;
import fr.younes.presentation.model.EtapeTable;
import fr.younes.presentation.model.ProcessusTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProcessusManagement {
	private EmployeesDAO edao = new EmployeesDAO(SdzConnection.getInstance());
	private ProcessusDAO pdao = new ProcessusDAO(SdzConnection.getInstance());
	public void addProcessus(Processus newProcess) {
		// TODO Auto-generated method stub
		pdao.addNewProcessWithoutEmp(newProcess);
		
	}
	public ArrayList<Processus> getAll(){
		ArrayList<Processus> maList = pdao.getAll();
		System.out.println(maList.toString());
		return maList;
		
	}
	public void addProcessus2(Processus newProcess) {
		// TODO Auto-generated method stub
		Employee monEmp=newProcess.getEmp();
		edao.create(monEmp);
		int i = edao.findByInfo(monEmp.getFirstName(),monEmp.getLastName(),monEmp.getEmail(),monEmp.getRole());
		pdao.addNewProcessWithEmp(newProcess,i);
	}
	public ObservableList<ProcessusTable> getDataEmployeeFromSqlAndAddToObservableList() {
		ObservableList<ProcessusTable> adminTableData = FXCollections.observableArrayList();
		adminTableData = pdao.listDataProcessusFromSqlAndAddToObservableList();
		return adminTableData;
	}
	public ObservableList<String> getDataProcessusFromSqlToObsevableList() {
		// TODO Auto-generated method stub
		ObservableList<String> availableChoices = FXCollections.observableArrayList();
		System.out.println("ioh");
		availableChoices = pdao.getAllNom();
		return availableChoices;
	}
	public void editProcess(Processus newProcess) {
		// TODO Auto-generated method stub
		Employee myEmp=newProcess.getEmp();
		edao.editUser(myEmp);
		pdao.update(newProcess);
	}
	public ObservableList<ProcessusTable> getDataProcessusNonAffecterFromSqlAndAddToObservableList() {
		ObservableList<ProcessusTable> adminTableData = FXCollections.observableArrayList();
		adminTableData = pdao.listDataProcessusNonAffecterFromSqlAndAddToObservableList();
		return adminTableData;
	}

}
