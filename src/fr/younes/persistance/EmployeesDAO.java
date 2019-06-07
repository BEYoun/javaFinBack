package fr.younes.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.younes.metier.Employee;
import fr.younes.presentation.model.EmployerTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmployeesDAO extends DAO<Employee> {

	public EmployeesDAO(Connection sdzConnection) {
		super(sdzConnection);
		// TODO Auto-generated constructor stub
	}

	public List<Employee> listEmploye() {
		List<Employee> maList = new ArrayList<Employee>();
		Employee a;
		String sql = "SELECT * FROM employes";
		try {
			PreparedStatement statement = this.connect.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				a = new Employee(rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"),
						rs.getString("role"), Integer.parseInt(rs.getString("username")), rs.getString("address"));
				maList.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maList;
	}

	public ArrayList<Employee> archiveEmploye() {
		ArrayList<Employee> list = new ArrayList<Employee>();
		Employee user;
		String sql = "select * from  employes where archiver =true";
		try {
			PreparedStatement statement = this.connect.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				user = new Employee();
				user.setId(rs.getInt(1));
//				user.setUsername(rs.getString(2));
//				user.setPassword(rs.getString(3));
				user.setRole(rs.getString(4));

				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean editUser(Employee user) {
		String sql = "update employees set \"firstName\"=?, \"lastName\"=?, email=?, role=?, address=?, phone=?  where id=?";
		try {
			PreparedStatement statement = this.connect.prepareStatement(sql);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getRole());
			statement.setString(5, user.getAddress());
			statement.setInt(6, user.getPhone());

			statement.setInt(7, user.getId());
			System.out.println(user);
			return statement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean ArchiveUser(Employee user) {
		String sql = "update employes set archiver=?  where id=?";
		try {
			PreparedStatement statement = this.connect.prepareStatement(sql);
			statement.setBoolean(1, true);
			statement.setInt(2, user.getId());

			return statement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean create(Employee obj) {
		System.out.println(obj);
		String sql = "INSERT INTO public.employees(\"firstName\", \"lastName\", email, role, address, phone)\r\n"
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
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from employees where id = '" + id + "';";
		PreparedStatement statement;
		try {
			statement = this.connect.prepareStatement(sql);
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Employee obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Employee find(int id) {
		Employee user = new Employee();
		String sql = "select * from employees where id=?;";
		try {

			PreparedStatement statement = this.connect.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				user = new Employee();
				user.setId(rs.getInt(1));
				user.setFirstName(rs.getString(2));
				user.setLastName(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setRole(rs.getString(5));
				user.setAddress(rs.getString(6));
				user.setPhone(Integer.parseInt(rs.getString(7)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public List<Employee> listEmployeOnly() {
		List<Employee> maList = new ArrayList<Employee>();
		Employee a;
		String sql = "SELECT * FROM employes where role='employee'";
		try {
			PreparedStatement statement = this.connect.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
//				a=new Employee(rs.getInt("id"),
//						rs.getString("username"),
//						rs.getString("nom"),
//						rs.getString("prenom"),
//						rs.getString("password"),
//						rs.getString("role"));
				// maList.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maList;
	}

	public List<Employee> listChefOnly() {
		List<Employee> maList = new ArrayList<Employee>();
		Employee a;
		String sql = "SELECT * FROM employes where role='chef'";
		try {
			PreparedStatement statement = this.connect.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
//				a=new Employee(rs.getInt("id"),
//						rs.getString("username"),
//						rs.getString("nom"),
//						rs.getString("prenom"),
//						rs.getString("password"),
//						rs.getString("role"));
				// maList.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maList;
	}

	public ObservableList<EmployerTable> listDataEmployeeFromSqlAndAddToObservableList() {
		// TODO Auto-generated method stub
		ObservableList<EmployerTable> maList = FXCollections.observableArrayList();
		Employee a;
		String sql = "SELECT * FROM employees";
		try {
			PreparedStatement statement = this.connect.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				maList.add(new EmployerTable(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"),
						rs.getString("email"), Integer.parseInt(rs.getString("phone")), rs.getString("role"),
						rs.getString("address")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maList;
	}

	public int findByInfo(String firstName, String lastName, String email, String role) {
		Employee a = null;
		String sql = "SELECT * FROM employees WHERE \"firstName\"='"+firstName+"' and \"lastName\"='"+lastName+"' and email='"+email+"';";
		try {
			PreparedStatement statement = this.connect.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			rs.next();
			a = new Employee(rs.getInt("id"),rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"),
					rs.getString("role"), Integer.parseInt(rs.getString("phone")), rs.getString("address"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(a);
		return a.getId();
	}

	public ObservableList<EmployerTable> listDataEmployeeOnlyFromSqlAndAddToObservableList(String choix) {
		ObservableList<EmployerTable> maList = FXCollections.observableArrayList();
		Employee a;
		String sql = "SELECT * FROM employees WHERE role='"+choix+"'";
		try {
			PreparedStatement statement = this.connect.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				maList.add(new EmployerTable(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"),
						rs.getString("email"), Integer.parseInt(rs.getString("phone")), rs.getString("role"),
						rs.getString("address")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maList;
	}

}
