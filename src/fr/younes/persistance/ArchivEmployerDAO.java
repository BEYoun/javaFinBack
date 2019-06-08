package fr.younes.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;

import fr.younes.metier.Employee;

public class ArchivEmployerDAO extends DAO<Employee>{

	public ArchivEmployerDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Employee obj) {
		System.out.println(obj);
		String sql = "INSERT INTO public.archivEmployees(\"firstName\", \"lastName\", email, role, address, phone)\r\n"
				+ "	VALUES ('" + obj.getFirstName() + "', '" + obj.getLastName() + "', '" + obj.getEmail() + "', '"
				+ obj.getRole() + "', '" + obj.getAddress() + "', " + obj.getPhone() + ");";
		try {
			System.out.println(sql);
			PreparedStatement statement = this.connect.prepareStatement(sql);
			statement.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
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
