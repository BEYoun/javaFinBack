package fr.younes.persistance;

import java.sql.Connection;

import fr.younes.metier.Processus;

public class ArchivProcessusDAO extends DAO<Processus>{

	public ArchivProcessusDAO(Connection conn) {
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Processus find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
