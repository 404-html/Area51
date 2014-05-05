package src.test01917;

import src.daoimpl01917.MySQLOperatoerDAO;
import src.daoimpl01917.MySQLProduktBatchDAO;
import src.daointerfaces01917.DALException;
import src.dto01917.OperatoerDTO;
import src.dto01917.ProduktBatchDTO;

import java.sql.SQLException;

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
		
		
		ProduktBatchDTO produktbatchDTO = new ProduktBatchDTO(3, 1, 3);
		try{
			MySQLProduktBatchDAO pb = new MySQLProduktBatchDAO();
			pb.updateProduktBatch(produktbatchDTO);
		
		}catch(Exception e ){
			System.out.println("failed!!");
		}
		
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
