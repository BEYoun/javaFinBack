package fr.younes.metier;

public class Document {
	private int id;
	private String nom;
	private Etape etape;
	private Processus process;
	private int id_etape;
	private int id_process;
	public Document(int id, String nom, Etape etape, Processus process) {
		super();
		this.id = id;
		this.nom = nom;
		this.etape = etape;
		this.process = process;
	}
	public Document(String nom, Etape etape, Processus process) {
		super();
		this.nom = nom;
		this.etape = etape;
		this.process = process;
	}
	public Document(String nom, int id_etape, int id_process) {
		super();
		this.nom = nom;
		this.id_etape = id_etape;
		this.id_process = id_process;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Etape getEtape() {
		return etape;
	}
	public void setEtape(Etape etape) {
		this.etape = etape;
	}
	public Processus getProcess() {
		return process;
	}
	public void setProcess(Processus process) {
		this.process = process;
	}
	public int getId_etape() {
		return id_etape;
	}
	public void setId_etape(int id_etape) {
		this.id_etape = id_etape;
	}
	public int getId_process() {
		return id_process;
	}
	public void setId_process(int id_process) {
		this.id_process = id_process;
	}
	
	
}
