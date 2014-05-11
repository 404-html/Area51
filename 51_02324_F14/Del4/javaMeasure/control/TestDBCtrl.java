package javaMeasure.control;

import java.util.ArrayList;

import javaMeasure.Batch;
import javaMeasure.Measurement;
import javaMeasure.User;
import javaMeasure.control.interfaces.IDatabaseController.DataBaseException;

public class TestDBCtrl {

	public static void main(String[] args) throws DataBaseException {
		DataBaseController db = new DataBaseController();
		ArrayList<User> a = db.getUserList();
		for (User u : a){
			System.out.println(u);
		}
		
		
		// isUserInDB test - succes
		System.out.println(db.isUserNameInDB("cb"));
		System.out.println(db.isUserNameInDB("brian"));
		System.out.println(db.isUserNameInDB(null));
		//test get measurementsByBatch - works fine
		Batch b = new  Batch(1, "alala", -1);
		ArrayList<Measurement> m = db.getMeasurementsByBatch(b);
		for (Measurement m1 : m){
			System.out.println(m1.getElementNo());
			System.out.println(m1.getMeasurementType());
			System.out.println(m1.getMeasureValue());
			
		}
	}

}
