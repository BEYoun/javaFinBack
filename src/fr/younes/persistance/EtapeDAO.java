package fr.younes.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.younes.metier.Employee;
import fr.younes.metier.Etape;
import fr.younes.metier.Processus;
import fr.younes.presentation.model.EtapeTable;
import javafx.collections.ObservableList;

public class EtapeDAO extends DAO<Etape>{

	public EtapeDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Etape obj) {
		// TODO Auto-generated method stub
		
		System.out.println(obj);
		String sql = "INSERT INTO public.etapes( nom, processus_id, ordre)\r\n"
				+ "	VALUES ('" + obj.getNom() + "', " + obj.getId_process()+ " , 0);";
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
		String sql = "delete from etapes where etape_id = " + id + ";";
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
	public boolean update(Etape obj) {
		// TODO Auto-generated method stub
		String sql = "update etapes set nom=?, processus_id=?  where etape_id=?";
		try {
			PreparedStatement statement = this.connect.prepareStatement(sql);
			statement.setString(1, obj.getNom());
			statement.setInt(2, obj.getId_process());

			statement.setInt(3, obj.getId());
			return statement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Etape find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Etape> getAll() {
		ArrayList<Etape> maList = new ArrayList<Etape>();
		Etape a;
		Employee e = null;
		Processus p;
		String sql = "SELECT * FROM etapes";
		String sql_emp = "SELECT * FROM employees WHERE id = ?";
		String sql_pro = "SELECT * FROM processus WHERE processus_id = ?";
		try {
			PreparedStatement statement = this.connect.prepareStatement(sql);
			PreparedStatement statement_emp = this.connect.prepareStatement(sql_emp);
			PreparedStatement statement_pro = this.connect.prepareStatement(sql_pro);
			ResultSet rs = statement.executeQuery();
			ResultSet rs_emp;
			ResultSet rs_pro;
			while (rs.next()) {
				if(rs.getInt("employee_id")!=0) {
					System.out.println(rs.getInt("employee_id"));
					statement_emp.setInt(1, rs.getInt("employee_id"));
					rs_emp= statement_emp.executeQuery();
					rs_emp.next();
					e=new Employee(rs_emp.getInt("id"),rs_emp.getString("firstName"), rs_emp.getString("lastName"), rs_emp.getString("email"),
							rs_emp.getString("role"), Integer.parseInt(rs_emp.getString("phone")), rs_emp.getString("address"));
				}
				statement_pro.setInt(1, rs.getInt("processus_id"));
				rs_pro = statement_pro.executeQuery();
				rs_pro.next();
				p=new Processus(rs_pro.getInt("processus_id"),rs_pro.getString("nom"));
				a = new Etape(rs.getInt("etape_id"),rs.getString("nom"),e,rs.getInt("ordre"),p);
				maList.add(a);
				e=null;
			}
		} catch (SQLException s) {
			// TODO Auto-generated catch block
			s.printStackTrace();
		}
		return maList;
	}

	public ArrayList<Etape> getWhere(String string, String string2, int id) {
		// TODO Auto-generated method stub
		ArrayList<Etape> maList = new ArrayList<Etape>();
		Etape a;
		Employee e = null;
		Processus p;
		String sql = "SELECT * FROM etapes WHERE "+string+" "+string2+" "+id+";";
		String sql_emp = "SELECT * FROM employees WHERE id = ?";
		String sql_pro = "SELECT * FROM processus WHERE processus_id = ?";
		try {
			PreparedStatement statement = this.connect.prepareStatement(sql);
			PreparedStatement statement_emp = this.connect.prepareStatement(sql_emp);
			PreparedStatement statement_pro = this.connect.prepareStatement(sql_pro);
			ResultSet rs = statement.executeQuery();
			ResultSet rs_emp;
			ResultSet rs_pro;
			while (rs.next()) {
				if(rs.getInt("employee_id")!=0) {
					
					statement_emp.setInt(1, rs.getInt("employee_id"));
					rs_emp= statement_emp.executeQuery();
					rs_emp.next();
					e=new Employee(rs_emp.getInt("id"),rs_emp.getString("firstName"), rs_emp.getString("lastName"), rs_emp.getString("email"),
							rs_emp.getString("role"), Integer.parseInt(rs_emp.getString("phone")), rs_emp.getString("address"));
				}
				statement_pro.setInt(1, rs.getInt("processus_id"));
				rs_pro = statement_pro.executeQuery();
				rs_pro.next();
				p=new Processus(rs_pro.getInt("processus_id"),rs_pro.getString("nom"));
				a = new Etape(rs.getInt("etape_id"),rs.getString("nom"),e,rs.getInt("ordre"),p);
				maList.add(a);
			}
		} catch (SQLException s) {
			// TODO Auto-generated catch block
			s.printStackTrace();
		}
		return maList;
	}

	public ArrayList<Etape> getAllNonAffecter() {
		ArrayList<Etape> maList = new ArrayList<Etape>();
		Etape a;
		Employee e = null;
		Processus p;
		String sql = "SELECT * FROM etapes WHERE employee_id IS NULL";
		String sql_emp = "SELECT * FROM employees WHERE id = ?";
		String sql_pro = "SELECT * FROM processus WHERE processus_id = ?";
		try {
			PreparedStatement statement = this.connect.prepareStatement(sql);
			PreparedStatement statement_emp = this.connect.prepareStatement(sql_emp);
			PreparedStatement statement_pro = this.connect.prepareStatement(sql_pro);
			ResultSet rs = statement.executeQuery();
			ResultSet rs_emp;
			ResultSet rs_pro;
			while (rs.next()) {
				if(rs.getInt("employee_id")!=0) {
					
					statement_emp.setInt(1, rs.getInt("employee_id"));
					rs_emp= statement_emp.executeQuery();
					rs_emp.next();
					e=new Employee(rs_emp.getInt("id"),rs_emp.getString("firstName"), rs_emp.getString("lastName"), rs_emp.getString("email"),
							rs_emp.getString("role"), Integer.parseInt(rs_emp.getString("username")), rs_emp.getString("address"));
				}
				statement_pro.setInt(1, rs.getInt("processus_id"));
				rs_pro = statement_pro.executeQuery();
				rs_pro.next();
				p=new Processus(rs_pro.getInt("processus_id"),rs_pro.getString("nom"));
				a = new Etape(rs.getInt("etape_id"),rs.getString("nom"),e,rs.getInt("ordre"),p);
				maList.add(a);
			}
		} catch (SQLException s) {
			// TODO Auto-generated catch block
			s.printStackTrace();
		}
		return maList;
	}

	public boolean affecterEmpToEtape(int etapeTableNo, int employeeTableNo) {
		// TODO Auto-generated method stub
		String sql = "update etapes set employee_id=?  where etape_id=?";
		try {
			PreparedStatement statement = this.connect.prepareStatement(sql);
			statement.setInt(1, employeeTableNo);
			statement.setInt(2, etapeTableNo);
			return statement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
