/**
 * Created by dchiu on 7/13/17.
 */
public class DBTester {
    public static void main(String[] args) throws Exception {
        DavidDB db = new DavidDB("data/test_schema.txt");
		System.out.println(db.toString());		// print schema

		// populate relations
		Relation r1 = db.getRelation("R1");
		Relation r2 = db.getRelation("R2");
		Relation r3 = db.getRelation("R3");
		r1.read("data/R1.txt");
		r2.read("data/R2.txt");
		r3.read("data/R3.txt");

		System.out.println(r1);
		System.out.println(r2);
		System.out.println(r3);

		System.out.println("========================MY QUERIES");

		// Testing union
		Relation u1 = db.union(r1,r2);
		Relation u2 = db.union(u1,r2);
		System.out.println(u1);
		System.out.println(u2);

		// Testing intersect
		Relation i1 = db.intersect(r1,r2);
		Relation i2 = db.intersect(r2,r1);
		System.out.println(i1);
		System.out.println(i2);

		// Testing set diff
		Relation d1 = db.minus(r1,r2);
		Relation d2 = db.minus(r2,r1);	// set difference is not commutative
		Relation d3 = db.minus(r1,db.minus(r1,r2));	// should return r1 intersect r2, due to: A intersect B = A - (A - B)
		System.out.println(d1);
		System.out.println(d2);
		System.out.println(d3);

		// Testing cartesian product
		Relation p1 = db.times(r1,r1);		// should invoke pedantic attribute names
		Relation p2 = db.times(r1,r2);
		Relation p3 = db.times(r1,r3);
		Relation p4 = db.times(r2,r3);
		Relation p5 = db.times(null, r1);	// should return null
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
		System.out.println(p4);
		System.out.println(p5);

		// Testing incompatibility
		Relation e1 = db.union(r1,r3);	// should throw DBException
	}
}
