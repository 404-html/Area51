package src.daoimpl01917;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import src.connector01917.Connector;
import src.daointerfaces01917.DALException;
import src.daointerfaces01917.ProduktBatchKompDAO;
import src.dto01917.ProduktBatchKompDTO;

public class MySQLProduktBatchKompDAO implements ProduktBatchKompDAO {

	@Override
	public ProduktBatchKompDTO getProduktBatchKomp(int pbId,
			int rbId) throws DALException {
		ProduktBatchKompDTO p = null;
		try(ResultSet r = Connector.doQuery("SELECT * FROM produktbatchkomponent WHERE pb_id =" + pbId + "AND rb_id ="+ rbId + ";")){
			r.next();
			p = new ProduktBatchKompDTO(r.getInt("pb_id"), r.getInt("rb_id"), r.getDouble("tara"), r.getDouble("netto"), r.getInt("opr_id"));
		} catch(SQLException e){
			System.out.println("SQL failed" + e);
		}
		return p;
	}

	@Override
	public List<ProduktBatchKompDTO> getProduktBatchKompList(
			int pbId) throws DALException {
		// TODO Auto-generated method stub
		List<ProduktBatchKompDTO> pList = new ArrayList<>();
		try(ResultSet r = Connector.doQuery("SELECT * FROM produktbatchkomponent WHERE pb_id = " + pbId +";")){
			while (r.next()){
			pList.add(new ProduktBatchKompDTO(r.getInt("pb_id"), r.getInt("rb_id"), r.getDouble("tara"), r.getDouble("netto"), r.getInt("opr_id")));
			}
		} catch(SQLException e){
			System.out.println("SQL failed" + e);
		}
		return pList;
	}

	@Override
	public List<ProduktBatchKompDTO> getProduktBatchKompList()
			throws DALException {
		List<ProduktBatchKompDTO> pList = new ArrayList<>();
		try(ResultSet r = Connector.doQuery("SELECT * FROM produktbatchkomponent ;")){
			while (r.next()){
			pList.add(new ProduktBatchKompDTO(r.getInt("pb_id"), r.getInt("rb_id"), r.getDouble("tara"), r.getDouble("netto"), r.getInt("opr_id")));
			}
		} catch(SQLException e){
			System.out.println("SQL failed" + e);
		}
		return pList;
	}

	@Override
	public void createProduktBatchKomp(
			ProduktBatchKompDTO produktbatchkomponent)
			throws DALException {
		Connector.doUpdate("INSERT INTO produktbatchkomponent (pb_id, rb_id, tara, netto, opr_id) VALUES (" +
			produktbatchkomponent.getPbId() + "," +
			produktbatchkomponent.getRbId() + "," +
			produktbatchkomponent.getTara() + "," +
			produktbatchkomponent.getNetto() + "," +
			produktbatchkomponent.getOprId() + ");"	);

	}

	@Override
	public void updateProduktBatchKomp(
			ProduktBatchKompDTO produktbatchkomponent)
			throws DALException {
		Connector.doUpdate("UPDATE produktbatchkomponent SET " + 
				"rb_id = " + produktbatchkomponent.getRbId() + "," +
				"tara = " +	produktbatchkomponent.getTara() + "," +
				"netto = " + produktbatchkomponent.getNetto() + "," +
				"opr_id = " + produktbatchkomponent.getOprId() + 
				"WHERE pb_id =" + produktbatchkomponent.getPbId() + ";"
				);

	}

}
