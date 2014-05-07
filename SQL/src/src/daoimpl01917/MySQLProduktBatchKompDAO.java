package src.daoimpl01917;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		//Getting resultset from DB
		try(ResultSet r = Connector.doQuery("SELECT * FROM produktbatchkomponent WHERE pb_id =" + pbId + " AND rb_id ="+ rbId + ";")){
			//Moving cursor and parsing resultset
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
		List<ProduktBatchKompDTO> pList = new ArrayList<>();
		//Get resultset from db by pb_id:
		try(ResultSet r = Connector.doQuery("SELECT * FROM produktbatchkomponent WHERE pb_id = " + pbId +";")){
			//Parsing resultset and adding entries to list
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
		ArrayList<ProduktBatchKompDTO> pList = new ArrayList<>();
		//Get resultset from db
		try(ResultSet r = Connector.doQuery("SELECT * FROM produktbatchkomponent;")){
			//Parsing resultset
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
		//Creating new entry in DB 
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
		//Updating by pb_id and rb_id
		Connector.doUpdate("UPDATE produktbatchkomponent SET " + 
				"tara = " +	produktbatchkomponent.getTara() + "," +
				"netto = " + produktbatchkomponent.getNetto() + "," +
				"opr_id = " + produktbatchkomponent.getOprId() + 
				" WHERE pb_id =" + produktbatchkomponent.getPbId() + " AND " +
				" rb_id =" + produktbatchkomponent.getRbId() + ";"
				);

	}
	public static void main(String[] args) throws DALException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		new Connector();
		MySQLProduktBatchKompDAO m = new MySQLProduktBatchKompDAO();
		List<ProduktBatchKompDTO> l = m.getProduktBatchKompList();
		for (ProduktBatchKompDTO p : l){
			System.out.println(p);
		}
		System.out.println("---");
		List<ProduktBatchKompDTO> l2 = m.getProduktBatchKompList(1);
		for (ProduktBatchKompDTO p : l2){
			System.out.println(p);
		}
		System.out.println("---");
		System.out.println(m.getProduktBatchKomp(1, 1));
		System.out.println("---");
		m.updateProduktBatchKomp(new ProduktBatchKompDTO(1, 1, 0.5, 10.05, 1));
		m.createProduktBatchKomp(new ProduktBatchKompDTO(5, 1, 1.0, 2.0, 1));
	}
}
