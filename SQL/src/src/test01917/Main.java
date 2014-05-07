package src.test01917;

import src.daoimpl01917.MySQLOperatoerDAO;
import src.daoimpl01917.MySQLProduktBatchDAO;
import src.daoimpl01917.MySQLProduktBatchKompDAO;
import src.daointerfaces01917.DALException;
import src.dto01917.ProduktBatchDTO;
import src.dto01917.ProduktBatchKompDTO;

import java.sql.SQLException;
import java.util.List;

import src.connector01917.Connector;

public class Main {
	public static void main(String[] args) {
		try { new Connector(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
	
		System.out.println("Let the test begin!:");
		MySQLOperatoerDAO opr = new MySQLOperatoerDAO();
		MySQLProduktBatchDAO prodbatch = new MySQLProduktBatchDAO();
		MySQLProduktBatchKompDAO pbkdao= new MySQLProduktBatchKompDAO();
		
		// test af getProduktBatch(int pbId)
		try{System.out.println("test getproduktbatch:");
			ProduktBatchDTO pb1 = prodbatch.getProduktBatch(3);
			System.out.println("id: " + pb1.getPbId() + " receptid: " + pb1.getReceptId() + " status: " + pb1.getStatus());
		} catch (DALException dale){dale.printStackTrace();}
		
		// testing list of produktbatches
		try{System.out.println("test getProduktBatchList:");
			List<ProduktBatchDTO> list = prodbatch.getProduktBatchList();
			for(ProduktBatchDTO p : list){
				System.out.println("id: " + p.getPbId() + " receptid: " + p.getReceptId() + " status: " + p.getStatus());
			}
		} catch(DALException dale){dale.printStackTrace();}
		
		// testing updateProduktBatch(produktbatchDTO); and getting batch again
		ProduktBatchDTO produktbatchDTO = new ProduktBatchDTO(3, 1, 3);
		try{
			System.out.println("test updateProduktBatch:");
			prodbatch.updateProduktBatch(produktbatchDTO);
			ProduktBatchDTO pb1 = prodbatch.getProduktBatch(3);
			System.out.println("id: " + pb1.getPbId() + " receptid: " + pb1.getReceptId() + " status: " + pb1.getStatus());
			prodbatch.updateProduktBatch(new ProduktBatchDTO(3, 2, 2));
			ProduktBatchDTO pb2 = prodbatch.getProduktBatch(3);
			System.out.println("id: " + pb2.getPbId() + " receptid: " + pb2.getReceptId() + " status: " + pb2.getStatus());
		
		}catch(Exception e ){
			System.out.println("failed!!");
		}
		
		// testing create batch
		try{ System.out.println("test Create Batch");
			prodbatch.createProduktBatch(new ProduktBatchDTO(10, 12, 2));
		} catch (DALException dale){ dale.printStackTrace();}
		
//test of produktbatchKompDAO:
		//test getProduktBatch
		try { System.out.println("Test getProduktBatch");
			System.out.println(pbkdao.getProduktBatchKomp(2, 1));
		} catch (DALException e) {e.printStackTrace();}
		//test getProduktBatchList
		try {System.out.println("Test GetProduktbatchKomPlist"); 
			List<ProduktBatchKompDTO> l=pbkdao.getProduktBatchKompList();
			System.out.println("pb_id\trb_id\ttara\tnetto\topr_id");
			for (ProduktBatchKompDTO p : l){
				System.out.println(p);
			}
		} catch (DALException e) {e.printStackTrace();}
		//Test getProduktbatchList by pb_id
		try {System.out.println("Test GetProduktbatchKomPlist by pb_id(1)"); 
		List<ProduktBatchKompDTO> l=pbkdao.getProduktBatchKompList(1);
		System.out.println("pb_id\trb_id\ttara\tnetto\topr_id");
			for (ProduktBatchKompDTO p : l){
				System.out.println(p);
		}
		} catch (DALException e) {e.printStackTrace();}
		//Test updateProduktBatchKompDTO
		try {System.out.println("Testing updateProduktBatchKomp:");
			pbkdao.updateProduktBatchKomp(new ProduktBatchKompDTO(1, 1, 0.5, 10.5, 1));
		} catch (DALException e) {e.printStackTrace();}
		// Test createProduktBatchKomp
		try {System.out.println("testing createProduktBatchKompDTO");
			pbkdao.createProduktBatchKomp(new ProduktBatchKompDTO(4, 2, 10000000, 10, 1));
		} catch (DALException e) {e.printStackTrace();}
		
//		System.out.println("Operatoer nummer 3:");
//		MySQLOperatoerDAO opr = new MySQLOperatoerDAO();
//		try { System.out.println(opr.getOperatoer(3)); }
//		catch (DALException e) { System.out.println(e.getMessage()); }
//		
//		System.out.println("Indsaettelse af ny operatoer med opr_id =  4");
//		OperatoerDTO oprDTO = new OperatoerDTO(4,"Don Juan","DJ","000000-0000","iloveyou");
//		try { opr.createOperatoer(oprDTO); }
//		catch (DALException e) { System.out.println(e.getMessage()); }	
//		
//		System.out.println("Operatoer nummer 4:");
//		try { System.out.println(opr.getOperatoer(4)); }
//		catch (DALException e) { System.out.println(e.getMessage()); }
//		
//		System.out.println("Opdatering af initialer for operatoer nummer 4");
//		oprDTO.setIni("DoJu");
//		try { opr.updateOperatoer(oprDTO); }
//		catch (DALException e) { System.out.println(e.getMessage()); }
//		
//		System.out.println("Operatoer nummer 4:");
//		try { System.out.println(opr.getOperatoer(4)); }
//		catch (DALException e) { System.out.println(e.getMessage()); }
//		
//		System.out.println("Alle operatoerer:");
//		try { System.out.println(opr.getOperatoerList()); }
//		catch (DALException e) { System.out.println(e.getMessage()); }
//		
//		System.out.println("Operatoer nummer 5:");
//		try { System.out.println(opr.getOperatoer(5)); }
//		catch (DALException e) { System.out.println(e.getMessage()); }		
		
	}
}
