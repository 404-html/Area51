package src.daoimpl01917;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import src.connector01917.Connector;
import src.daointerfaces01917.DALException;
import src.daointerfaces01917.ProduktBatchDAO;
import src.dto01917.ProduktBatchDTO;

public class MySQLProduktBatchDAO implements ProduktBatchDAO {

	@Override
	public src.dto01917.ProduktBatchDTO getProduktBatch(int pbId)
			throws DALException {
			ProduktBatchDTO p = null;
		try(ResultSet r = Connector.doQuery("SELECT * FROM produktbatch WHERE pb_id =" + pbId + ";")){
			r.next();
//			System.out.println(r.getInt("pb_id"));
//			System.out.println(r.getInt("status"));
//			System.out.println(r.getInt("recept_id"));
			p = new ProduktBatchDTO(r.getInt("pb_id"), r.getInt("status"), r.getInt("recept_id"));
			return p;
		} catch(SQLException e){
			System.out.println("No produktbatch with this id");
		}
		return p;
	}

	@Override
	public List<src.dto01917.ProduktBatchDTO> getProduktBatchList()
			throws DALException {
		ArrayList<src.dto01917.ProduktBatchDTO> list = new ArrayList<>();
		try(ResultSet r = Connector.doQuery("SELECT * FROM produktbatch;")){
			while(r.next()){
				list.add(new ProduktBatchDTO(r.getInt("pb_id"), r.getInt("status"), r.getInt("recept_id")));
			}
		}catch(SQLException e){
			System.out.println("there are no produktbatches in database");
		}
		return list;
	}

	@Override
	public void createProduktBatch(src.dto01917.ProduktBatchDTO produktbatch)
			throws DALException {
		try{
			Connector.doUpdate("INSERT INTO produktbatch"
					+ " VALUES(" + produktbatch.getPbId() + ","
					+ produktbatch.getStatus() + ","
					+ produktbatch.getReceptId() + ");");
		} catch(DALException e){
			System.out.println("produktbatch is not created properly");
		}

	}

	@Override
	public void updateProduktBatch(src.dto01917.ProduktBatchDTO produktbatch)
			throws DALException {
			try{
				Connector.doUpdate("UPDATE produktbatch SET status=" + produktbatch.getStatus() + ","
						+ "recept_id=" + produktbatch.getReceptId() + " "
						+ "WHERE pb_id=" + produktbatch.getPbId() + ";");
			} catch(DALException e){
				e.printStackTrace();
				System.out.println("update is not possible");
			}

	}

}
