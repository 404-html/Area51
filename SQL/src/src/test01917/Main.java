package src.test01917;

import src.daoimpl01917.MySQLOperatoerDAO;
import src.daoimpl01917.MySQLProduktBatchDAO;
import src.daointerfaces01917.DALException;
import src.dto01917.OperatoerDTO;
import src.dto01917.ProduktBatchDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import src.connector01917.Connector;

public class Main {
	public static void main(String[] args) {
		try { new Connector(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
		
		
		System.out.println("update produktbatch:");
		MySQLOperatoerDAO opr = new MySQLOperatoerDAO();
		MySQLProduktBatchDAO prodbatch = new MySQLProduktBatchDAO();
		
		// test af getProduktBatch(int pbId)
		try{
			System.out.println("test 1:");
			ProduktBatchDTO pb1 = prodbatch.getProduktBatch(3);
			System.out.println("id: " + pb1.getPbId() + " receptid: " + pb1.getReceptId() + " status: " + pb1.getStatus());
		
		} catch (DALException dale){
			
		}
		
		// testing list of produktbatches
		try{
			System.out.println("test 2:");
			List<ProduktBatchDTO> list = prodbatch.getProduktBatchList();
			for(ProduktBatchDTO p : list){
				System.out.println("id: " + p.getPbId() + " receptid: " + p.getReceptId() + " status: " + p.getStatus());
			}
		} catch(DALException dale){
			
		}
		
		// testing updateProduktBatch(produktbatchDTO); and getting batch again
		ProduktBatchDTO produktbatchDTO = new ProduktBatchDTO(3, 1, 3);
		try{
			System.out.println("test 3:");
			prodbatch.updateProduktBatch(produktbatchDTO);
			ProduktBatchDTO pb1 = prodbatch.getProduktBatch(3);
			System.out.println("id: " + pb1.getPbId() + " receptid: " + pb1.getReceptId() + " status: " + pb1.getStatus());
			prodbatch.updateProduktBatch(new ProduktBatchDTO(3, 2, 2));
			ProduktBatchDTO pb2 = prodbatch.getProduktBatch(3);
			System.out.println("id: " + pb2.getPbId() + " receptid: " + pb2.getReceptId() + " status: " + pb2.getStatus());
		
		}catch(Exception e ){
			System.out.println("failed!!");
		}
		
		// testing create batch. create, then getting and then deleting again
		
		
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
