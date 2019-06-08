package fr.younes.metier;

import java.util.ArrayList;

import fr.younes.connection.SdzConnection;
import fr.younes.persistance.DocumentDAO;
import fr.younes.presentation.model.DocumentTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DocumentManagement {
	private DocumentDAO ddao = new DocumentDAO(SdzConnection.getInstance());

	public void addDoc(Document monDoc) {
		// TODO Auto-generated method stub
		ddao.create(monDoc);
	}

	public ObservableList<DocumentTable> getDataEmployeeFromSqlAndAddToObservableList() {
		// TODO Auto-generated method stub
		ObservableList<DocumentTable> adminTableData = FXCollections.observableArrayList();
		ArrayList<Document> monArray = ddao.getAll();
		for (Document doc : monArray) {
			adminTableData.add(new DocumentTable(doc.getId(), doc.getNom(), doc.getProcess().getNom()));
		}
		return adminTableData;
	}

}
