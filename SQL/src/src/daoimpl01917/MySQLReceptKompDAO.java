package src.daoimpl01917;

import java.util.List;

import src.daointerfaces01917.DALException;
import src.daointerfaces01917.ReceptKompDAO;

public class MySQLReceptKompDAO implements ReceptKompDAO {

	@Override
	public src.dto01917.ReceptKompDTO getReceptKomp(int receptId, int raavareId)
			throws DALException {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<src.dto01917.ReceptKompDTO> getReceptKompList(int receptId)
			throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<src.dto01917.ReceptKompDTO> getReceptKompList()
			throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createReceptKomp(src.dto01917.ReceptKompDTO receptkomponent)
			throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateReceptKomp(src.dto01917.ReceptKompDTO receptkomponent)
			throws DALException {
		// TODO Auto-generated method stub

	}

}
