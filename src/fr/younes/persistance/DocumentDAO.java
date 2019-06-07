package fr.younes.persistance;

import java.sql.Connection;

import fr.younes.metier.Employee;

public class DocumentDAO extends DAO<Employee>{

	public DocumentDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Employee obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Employee obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Employee find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
