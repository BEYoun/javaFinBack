package fr.younes.metier;

public class Etape {
	private int id;
	private String nom;
	private Employee emp;
	private int ordre;
	private int id_process;
	private Processus process;
	public Etape(int id, String nom, Employee emp, int ordre,Processus process) {
		super();
		this.id = id;
		this.nom = nom;
		this.ordre=ordre;
		this.emp = emp;
		this.process = process;
	}
	public Etape(String nom, Employee emp, Processus process) {
		super();
		this.nom = nom;
		this.emp = emp;
		this.process = process;
	}
	public Etape(String nom, Processus process) {
		super();
		this.nom = nom;
		this.process = process;
	}
	public Etape(String nom, int process) {
		super();
		this.nom = nom;
		this.id_process = process;
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
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	public Processus getProcess() {
		return process;
	}
	public void setProcess(Processus process) {
		this.process = process;
	}
	public int getId_process() {
		return id_process;
	}
	public void setId_process(int id_process) {
		this.id_process = id_process;
	}
	public int getOrdre() {
		return ordre;
	}
	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}
	
	
	
	
	

}
