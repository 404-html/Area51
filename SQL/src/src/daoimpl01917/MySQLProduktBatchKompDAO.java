package src.daoimpl01917;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import src.connector01917.Connector;
import src.daointerfaces01917.DALException;
import src.daointerfaces01917.ProduktBatchKompDAO;
import src.dto01917.ProduktBatchKompDTO;

public class MySQLProduktBatchKompDAO implements ProduktBatchKompDAO {

	@Override
	public ProduktBatchKompDTO getProduktBatchKomp(int pbId,
			int rbId) throws DALException {
		ProduktBatchKompDTO p = null;
		try(ResultSet r = Connector.doQuery("SELECT * FROM produktbatchkomponent WHERE pb_id =" + pbId + ";")){
			r.next();
			p = new ProduktBatchKompDTO(r.getInt("pb_id"), r.getInt("rb_id"), r.getDouble("tara"), r.getDouble("netto"), r.getInt("opr_id"));
		} catch(SQLException e){
			System.out.println("SQL failed" + e);
		}
		return p;
	}

	@Override
	public List<src.dto01917.ProduktBatchKompDTO> getProduktBatchKompList(
			int pbId) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<src.dto01917.ProduktBatchKompDTO> getProduktBatchKompList()
			throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createProduktBatchKomp(
			src.dto01917.ProduktBatchKompDTO produktbatchkomponent)
			throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateProduktBatchKomp(
			src.dto01917.ProduktBatchKompDTO produktbatchkomponent)
			throws DALException {
		// TODO Auto-generated method stub

	}

}
