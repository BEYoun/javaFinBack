package fr.younes.presentation.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DocumentTable {
	private final SimpleIntegerProperty DocumentTableNo;
	private final SimpleStringProperty DocumentTableName;
	private final SimpleStringProperty DocumentTablePName;
	
	
	
	public DocumentTable(int documentTableNo, String documentTableName,
			String documentTablePName) {
		super();
		DocumentTableNo = new SimpleIntegerProperty(documentTableNo);
		DocumentTableName =  new SimpleStringProperty(documentTableName);
		DocumentTablePName =  new SimpleStringProperty(documentTablePName);
	}
	public int getDocumentTableNo() {
		return DocumentTableNo.get();
	}
	public String getDocumentTableName() {
		return DocumentTableName.get();
	}
	public String getDocumentTablePName() {
		return DocumentTablePName.get();
	}
}
