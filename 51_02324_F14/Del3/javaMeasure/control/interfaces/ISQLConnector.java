package javaMeasure.control.interfaces;

import java.sql.PreparedStatement;
import java.sql.Statement;

import javaMeasure.DataBaseException;

public interface ISQLConnector {

	@SuppressWarnings("serial")
	public class DBConnectFailedException extends Exception {

	}

	Statement getStatement() throws DataBaseException;
	
	PreparedStatement getPreparedStatement(String sqlStatement) throws DataBaseException;
	
	PreparedStatement getPreparedStatement(String sqlStatement, int extraArg) throws DataBaseException;

}