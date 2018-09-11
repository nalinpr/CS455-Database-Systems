import java.io.FileNotFoundException;

/**
 * DavidDB Tester
 *
 */
public class DBTester {
	public static void main(String[] args) {
		try {
			DavidDB db = new DavidDB("data/airport_schema.txt");

			// print schema
			System.out.println(db.toString());

			// populate relations
			Relation flights = db.getRelation("flights");
			Relation onboard = db.getRelation("onboard");
			Relation passengers = db.getRelation("passengers");
			Relation planes = db.getRelation("planes");

			flights.read("data/flights.txt");
			onboard.read("data/onboard.txt");
			passengers.read("data/passengers.txt");
			planes.read("data/planes.txt");

			// print tables
			System.out.println(flights.toString());
			System.out.println(onboard.toString());
			System.out.println(passengers.toString());
			System.out.println(planes.toString());
		}
		catch(FileNotFoundException e) {
			// couldn't find schema file, or one of the data files
			e.printStackTrace();
		}
	}
}
