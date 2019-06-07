package fr.younes.presentation.controller;
import fr.younes.metier.EmployeeManagement;
import fr.younes.presentation.model.AbstractModel;

public abstract class AbstractControler {

	protected AbstractModel art;
	protected EmployeeManagement monEmployeeManagement;
	public AbstractControler(AbstractModel art) {
		this.art=art;
		this.monEmployeeManagement= new EmployeeManagement();
	}
	 abstract void control();
	
}
