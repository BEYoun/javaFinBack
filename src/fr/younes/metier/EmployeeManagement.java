package fr.younes.metier;

import java.util.ArrayList;
import java.util.List;

import fr.younes.connection.SdzConnection;
import fr.younes.persistance.EmployeesDAO;
import fr.younes.presentation.model.EmployerTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmployeeManagement {
	private EmployeesDAO edao = new EmployeesDAO(SdzConnection.getInstance());
	public EmployeeManagement() {
	}
	
	public List<Employee> listEmploye() {
		return edao.listEmploye();
	}
	
	
	public void addUser(Employee user) {
		edao.create(user);
	}
	
	public boolean editUser(Employee user) {
		return edao.editUser(user);
	}
	
	public boolean ArchiveUser(Employee user) {
		return edao.ArchiveUser(user);
	}
	public ArrayList<Employee> archiveEmploye() 
	{	return edao.archiveEmploye();
		
	}

	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		return edao.listEmploye();
	}

	public List<Employee> getEmployeesOnly() {
		// TODO Auto-generated method stub
		return edao.listEmployeOnly();
	}

	public List<Employee> getChefOnly() {
		// TODO Auto-generated method stub
		return edao.listChefOnly();
	}

	public boolean addEmp(Employee e) {
		// TODO Auto-generated method stub
		return edao.create(e);
		
	}

	public ObservableList<EmployerTable> getDataEmployeeFromSqlAndAddToObservableList() {
		// TODO Auto-generated method stub
		ObservableList<EmployerTable> adminTableData = FXCollections.observableArrayList();
		adminTableData = edao.listDataEmployeeFromSqlAndAddToObservableList();
		return adminTableData;
	}

	public Employee findById(int employeeTableNo) {
		return edao.find(employeeTableNo);
	}

	public boolean deleteEmplById(int employeeTableNo) {
		// TODO Auto-generated method stub
		return edao.delete(employeeTableNo);
		
	}

	public ObservableList<EmployerTable> getDataEmployeeOnlyFromSqlAndAddToObservableList() {
		ObservableList<EmployerTable> adminTableData = FXCollections.observableArrayList();
		adminTableData = edao.listDataEmployeeOnlyFromSqlAndAddToObservableList("Employer");
		return adminTableData;
	}

	public ObservableList<EmployerTable> getDataChefOnlyFromSqlAndAddToObservableList() {
		ObservableList<EmployerTable> adminTableData = FXCollections.observableArrayList();
		adminTableData = edao.listDataEmployeeOnlyFromSqlAndAddToObservableList("Chef Administratif");
		return adminTableData;
	}
}
