package fr.younes.persistance;

import java.sql.Connection;

import fr.younes.metier.Document;

public class ArchivDocumentDAO extends DAO<Document>{

	public ArchivDocumentDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Document obj) {
		// TODO Auto-generated method stub
		return false;
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

}
