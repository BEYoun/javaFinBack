package fr.younes.presentation.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class EtapeTable {
	private final SimpleIntegerProperty EtapeTableNo;
	private final SimpleStringProperty EtapeTableNom;
	private final SimpleStringProperty EtapeProcedureNom;
	private final SimpleIntegerProperty EtapeOrdre;
	private final SimpleStringProperty EtapeEmployerNom;
	private final SimpleStringProperty EtapeEmployerPrenom;
	private final SimpleStringProperty EtapeEmployerEmail;

	public EtapeTable(int EtapeTableNo, String EtapeTableNom, String EtapeProcedureNom, int EtapeOrdre,
			String EtapeEmployerNom, String EtapeEmployerPrenom, String EtapeEmployerEmail) {
		this.EtapeTableNo = new SimpleIntegerProperty(EtapeTableNo);
		this.EtapeTableNom = new SimpleStringProperty(EtapeTableNom);
		this.EtapeProcedureNom = new SimpleStringProperty(EtapeProcedureNom);
		this.EtapeOrdre = new SimpleIntegerProperty(EtapeOrdre);
		this.EtapeEmployerNom = new SimpleStringProperty(EtapeEmployerNom);
		this.EtapeEmployerPrenom = new SimpleStringProperty(EtapeEmployerPrenom);
		this.EtapeEmployerEmail = new SimpleStringProperty(EtapeEmployerEmail);
	}

	public int getEtapeTableNo() {
		return EtapeTableNo.get();
	}

	public String getEtapeTableNom() {
		return EtapeTableNom.get();
	}

	public String getEtapeProcedureNom() {
		return EtapeProcedureNom.get();
	}

	public int getEtapeOrdre() {
		return EtapeOrdre.get();
	}

	public String getEtapeEmployerNom() {
		return EtapeEmployerNom.get();
	}

	public String getEtapeEmployerPrenom() {
		return EtapeEmployerPrenom.get();
	}

	public String getEtapeEmployerEmail() {
		return EtapeEmployerEmail.get();
	}
	
}
