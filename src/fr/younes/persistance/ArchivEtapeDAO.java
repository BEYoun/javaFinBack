package fr.younes.persistance;

import java.sql.Connection;

import fr.younes.metier.Etape;

public class ArchivEtapeDAO extends DAO<Etape>{

	public ArchivEtapeDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Etape obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Etape obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Etape find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
