package fr.younes.metier;

public class Processus {
	private int id;
	private String nom;
	private Employee emp;
	public Processus(int id, String nom, Employee emp) {
		this.id = id;
		this.nom = nom;
		this.emp = emp;
	}
	public Processus(int id, String nom) {
		this.id = id;
		this.nom = nom;
		this.emp = emp;
	}
	public Processus( String nom, Employee emp) {
		this.nom = nom;
		this.emp = emp;
	}
	public Processus( String nom) {
		this.nom = nom;
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
	
	
}
