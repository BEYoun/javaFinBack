package fr.younes.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.younes.metier.Employee;
import fr.younes.metier.Processus;
import fr.younes.presentation.model.EmployerTable;
import fr.younes.presentation.model.ProcessusTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProcessusDAO extends DAO<Processus>{

	public ProcessusDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Processus obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Processus obj) {
		String sql = "update processus set nom=?  where processus_id=?";
		try {
			PreparedStatement statement = this.connect.prepareStatement(sql);
			statement.setString(1, obj.getNom());

			statement.setInt(2, obj.getId());
			return statement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Processus find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addNewProcessWithoutEmp(Processus obj) {
		System.out.println(obj);
		String sql = "INSERT INTO public.processus(\r\n" + 
				"	nom)"
				+ "	VALUES ('" + obj.getNom() + "');";
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

	public boolean addNewProcessWithEmp(Processus newProcess, int i) {
		// TODO Auto-generated method stub
		System.out.println(newProcess);
		String sql = "INSERT INTO public.processus(\r\n" + 
				"	nom,emp_id)"
				+ "	VALUES ('" + newProcess.getNom() + "', "+i+");";
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

	public ObservableList<ProcessusTable> listDataProcessusFromSqlAndAddToObservableList() {
		ObservableList<ProcessusTable> maList = FXCollections.observableArrayList();
		Processus a;
		String sql = "SELECT * FROM processus";
		String sql_emp = "SELECT * FROM employees WHERE id= ?";
		
		try {
			PreparedStatement statement = this.connect.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				PreparedStatement statement_emp = this.connect.prepareStatement(sql_emp);
				//System.out.println(rs.getInt("emp_id"));
				statement_emp.setInt(1, rs.getInt("emp_id"));
				ResultSet rs_emp = statement_emp.executeQuery();
				if(rs.getInt("emp_id")!=0) {
					System.out.println(rs.getInt("processus_id"));
					while(rs_emp.next()) {
						maList.add(new ProcessusTable(rs.getInt("processus_id"), rs_emp.getString("firstName"), rs_emp.getString("lastName"),
								rs_emp.getString("email"), Integer.parseInt(rs_emp.getString("phone")), rs.getString("nom"),
								rs_emp.getString("address")));
					}
				}else {
					maList.add(new ProcessusTable(rs.getInt("processus_id"), "Non Affecter", "Non Affecter",
							"Non Affecter", 0, rs.getString("nom"),
							"Non Affecter"));
				}
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maList;
	}

	public ObservableList<String> getAllNom() {
		// TODO Auto-generated method stub
		ObservableList<String> maList = FXCollections.observableArrayList();
		String sql = "SELECT * FROM processus";
		System.out.println(sql);
		try {
			PreparedStatement statement = this.connect.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				maList.add(rs.getString("nom")+"/"+rs.getString("processus_id"));
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maList;
	}

	public ArrayList<Processus> getAll() {
		// TODO Auto-generated method stub
		ArrayList<Processus> maList = new ArrayList<Processus>();
		String sql = "SELECT * FROM processus";
		System.out.println(sql);
		try {
			PreparedStatement statement = this.connect.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				maList.add(new Processus(rs.getString("nom")));
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maList;
	}

	public ObservableList<ProcessusTable> listDataProcessusNonAffecterFromSqlAndAddToObservableList() {
		ObservableList<ProcessusTable> maList = FXCollections.observableArrayList();
		Processus a;
		String sql = "SELECT * FROM processus WHERE emp_id IS NULL";
		String sql_emp = "SELECT * FROM employees WHERE id= ?";
		
		try {
			PreparedStatement statement = this.connect.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				PreparedStatement statement_emp = this.connect.prepareStatement(sql_emp);
				//System.out.println(rs.getInt("emp_id"));
				statement_emp.setInt(1, rs.getInt("emp_id"));
				ResultSet rs_emp = statement_emp.executeQuery();
				if(rs.getInt("emp_id")!=0) {
					System.out.println(rs.getInt("processus_id"));
					while(rs_emp.next()) {
						maList.add(new ProcessusTable(rs.getInt("processus_id"), rs_emp.getString("firstName"), rs_emp.getString("lastName"),
								rs_emp.getString("email"), Integer.parseInt(rs_emp.getString("phone")), rs.getString("nom"),
								rs_emp.getString("address")));
					}
				}else {
					maList.add(new ProcessusTable(rs.getInt("processus_id"), "Non Affecter", "Non Affecter",
							"Non Affecter", 0, rs.getString("nom"),
							"Non Affecter"));
				}
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maList;
	}

}
