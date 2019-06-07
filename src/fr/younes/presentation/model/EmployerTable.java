package fr.younes.presentation.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class EmployerTable {
	private final SimpleIntegerProperty EmployeeTableNo;
	private final SimpleStringProperty EmployeeTableFirstName;
	private final SimpleStringProperty EmployeeTableLastName;
	private final SimpleStringProperty EmployeeTableEmail;
	private final SimpleIntegerProperty EmployeeTablePhone;
	private final SimpleStringProperty EmployeeTableRole;
	private final SimpleStringProperty EmployeeTableAddress;

	public EmployerTable(int EmployeeNo, String EmployeeFirstName, String EmployeeLastName, String EmployeeEmail,
			Integer EmployeePhone, String EmployeeRole, String EmployeeAddress) {
		this.EmployeeTableNo = new SimpleIntegerProperty(EmployeeNo);
		this.EmployeeTableFirstName = new SimpleStringProperty(EmployeeFirstName);
		this.EmployeeTableLastName = new SimpleStringProperty(EmployeeLastName);
		this.EmployeeTablePhone = new SimpleIntegerProperty(EmployeePhone);
		this.EmployeeTableEmail = new SimpleStringProperty(EmployeeEmail);
		this.EmployeeTableRole = new SimpleStringProperty(EmployeeRole);
		this.EmployeeTableAddress = new SimpleStringProperty(EmployeeAddress);
	}

	public int getEmployeeTableNo() {
		return EmployeeTableNo.get();
	}

	public String getEmployeeTableFirstName() {
		return EmployeeTableFirstName.get();
	}

	public String getEmployeeTableLastName() {
		return EmployeeTableLastName.get();
	}

	public String getEmployeeTableEmail() {
		return EmployeeTableEmail.get();
	}

	public int getEmployeeTablePhone() {
		return EmployeeTablePhone.get();
	}

	public String getEmployeeTableRole() {
		return EmployeeTableRole.get();
	}

	public String getEmployeeTableAddress() {
		return EmployeeTableAddress.get();
	}

}
