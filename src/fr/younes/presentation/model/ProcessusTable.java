package fr.younes.presentation.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ProcessusTable {
	private final SimpleIntegerProperty ProcessusTableNo;
	private final SimpleStringProperty EmployeeTableFirstName;
	private final SimpleStringProperty EmployeeTableLastName;
	private final SimpleStringProperty EmployeeTableEmail;
	private final SimpleIntegerProperty EmployeeTablePhone;
	private final SimpleStringProperty ProcessusTableNom;
	private final SimpleStringProperty EmployeeTableAddress;

	public ProcessusTable(int ProcessusTableNo, String EmployeeFirstName, String EmployeeLastName, String EmployeeEmail,
			Integer EmployeePhone, String ProcessusTableNom, String EmployeeAddress) {
		this.ProcessusTableNo = new SimpleIntegerProperty(ProcessusTableNo);
		this.EmployeeTableFirstName = new SimpleStringProperty(EmployeeFirstName);
		this.EmployeeTableLastName = new SimpleStringProperty(EmployeeLastName);
		this.EmployeeTablePhone = new SimpleIntegerProperty(EmployeePhone);
		this.EmployeeTableEmail = new SimpleStringProperty(EmployeeEmail);
		this.ProcessusTableNom = new SimpleStringProperty(ProcessusTableNom);
		this.EmployeeTableAddress = new SimpleStringProperty(EmployeeAddress);
	}

	public int getProcessusTableNo() {
		return ProcessusTableNo.get();
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
		return ProcessusTableNom.get();
	}

	public String getEmployeeTableAddress() {
		return EmployeeTableAddress.get();
	}
	public String getProcessusTableNom() {
		return ProcessusTableNom.get();
	}
}
