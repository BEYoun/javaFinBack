package fr.younes.metier;

import java.util.ArrayList;

import javax.print.attribute.standard.PDLOverrideSupported;

import fr.younes.connection.SdzConnection;
import fr.younes.persistance.EtapeDAO;
import fr.younes.presentation.model.EmployerTable;
import fr.younes.presentation.model.EtapeTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EtapeManagement {
	private EtapeDAO etdao = new EtapeDAO(SdzConnection.getInstance());
	
	public EtapeManagement() {
		// TODO Auto-generated constructor stub
	}

	public void addEtape(Etape newEtape) {
		// TODO Auto-generated method stub
		System.out.println("hi");
		etdao.create(newEtape);
	}

	public ObservableList<EtapeTable> getDataEtapeFromSqlAndAddToObservableList() {
		// TODO Auto-generated method stub
		ObservableList<EtapeTable> adminTableData = FXCollections.observableArrayList();
		ArrayList<Etape> monArray  = etdao.getAll();
		for (Etape etape : monArray) {
			if(etape.getEmp()!=null) {
				//System.out.println(etape.getEmp().getFirstName());
				adminTableData.add(new EtapeTable(etape.getId(), etape.getNom(), etape.getProcess().getNom(), etape.getOrdre(), etape.getEmp().getFirstName(), etape.getEmp().getLastName(), etape.getEmp().getEmail()));
			}else {
				adminTableData.add(new EtapeTable(etape.getId(), etape.getNom(), etape.getProcess().getNom(), etape.getOrdre(), "non Affecter", "non Affecter", "non Affecter"));
			}
			
		}
		return adminTableData;
	}

	public void editUser(Etape newEtape) {
		// TODO Auto-generated method stub
		etdao.update(newEtape);
	}

	public void deleteEmplById(int etapeTableNo) {
		// TODO Auto-generated method stub
		etdao.delete(etapeTableNo);
	}

	public ObservableList<EtapeTable> getDataEmployeeFromSqlAndAddToObservableListById(int id) {
		ObservableList<EtapeTable> adminTableData = FXCollections.observableArrayList();
		ArrayList<Etape> monArray  = etdao.getWhere("processus_id","=",id);
		for (Etape etape : monArray) {
			if(etape.getEmp()!=null) {
				adminTableData.add(new EtapeTable(etape.getId(), etape.getNom(), etape.getProcess().getNom(), etape.getOrdre(), etape.getEmp().getFirstName(), etape.getEmp().getLastName(), etape.getEmp().getEmail()));
			}else {
				adminTableData.add(new EtapeTable(etape.getId(), etape.getNom(), etape.getProcess().getNom(), etape.getOrdre(), "non Affecter", "non Affecter", "non Affecter"));
			}
			
		}
		return adminTableData;
	}

	public ObservableList<EtapeTable> getDataEtapeNonAffecterFromSqlAndAddToObservableList() {
		ObservableList<EtapeTable> adminTableData = FXCollections.observableArrayList();
		ArrayList<Etape> monArray  = etdao.getAllNonAffecter();
		for (Etape etape : monArray) {
			if(etape.getEmp()!=null) {
				adminTableData.add(new EtapeTable(etape.getId(), etape.getNom(), etape.getProcess().getNom(), etape.getOrdre(), etape.getEmp().getFirstName(), etape.getEmp().getLastName(), etape.getEmp().getEmail()));
			}else {
				adminTableData.add(new EtapeTable(etape.getId(), etape.getNom(), etape.getProcess().getNom(), etape.getOrdre(), "non Affecter", "non Affecter", "non Affecter"));
			}
			
		}
		return adminTableData;
	}

	public void affecterEmployeeToEtape(int etapeTableNo, int employeeTableNo) {
		// TODO Auto-generated method stub
		etdao.affecterEmpToEtape(etapeTableNo,employeeTableNo);
	}
}
