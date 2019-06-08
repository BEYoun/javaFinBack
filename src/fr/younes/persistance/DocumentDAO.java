package fr.younes.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.younes.metier.Document;
import fr.younes.metier.Employee;
import fr.younes.metier.Etape;
import fr.younes.metier.Processus;
import fr.younes.presentation.model.DocumentTable;

public class DocumentDAO extends DAO<Document>{

	public DocumentDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Document obj) {
		// TODO Auto-generated method stub
		System.out.println(obj);
		String sql = "INSERT INTO public.documents( nom, etape_id, process_id)\r\n"
				+ "	VALUES ('" + obj.getNom() + "', " + obj.getId_etape()+ " , "+obj.getId_process()+");";
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
	public boolean update(Document obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Document find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Document> getAll() {
		ArrayList<Document> maList = new ArrayList<Document>();
		Document a;
		Etape e = null;
		Processus p;
		String sql = "SELECT * FROM documents";
		String sql_pro = "SELECT * FROM processus WHERE processus_id = ?";
		try {
			PreparedStatement statement = this.connect.prepareStatement(sql);
			//PreparedStatement statement_emp = this.connect.prepareStatement(sql_emp);
			PreparedStatement statement_pro = this.connect.prepareStatement(sql_pro);
			ResultSet rs = statement.executeQuery();
			ResultSet rs_emp;
			ResultSet rs_pro;
			while (rs.next()) {
				System.out.println(rs.getString(2));
				statement_pro.setInt(1, rs.getInt("process_id"));
				rs_pro = statement_pro.executeQuery();
				rs_pro.next();
				p=new Processus(rs_pro.getInt("processus_id"),rs_pro.getString("nom"));
				a = new Document(rs.getInt("etape_id"),rs.getString("nom"),e,p);
				maList.add(a);
				e=null;
			}
		} catch (SQLException s) {
			// TODO Auto-generated catch block
			s.printStackTrace();
		}
		return maList;
	}

}
