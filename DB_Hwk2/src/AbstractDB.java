import exceptions.DBException;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * DavidDB: The finest relational database system written in the common era.
 *
 * @author David
 * @version 5/25/18
 */
public abstract class AbstractDB {
	protected String schema_file;
	protected Map<String,AbstractRelation> relations;

	/**
	 * Creates a new instance of DavidDB.
	 * @param filename	path to the schema file.
	 */
	public AbstractDB(String filename) throws FileNotFoundException {
		this.schema_file = filename;
		this.relations = new HashMap<>();
		this.createRelations();
	}

	/**
	 * Creates (but does not populate) the relations specified in the schema file.
	 * @throws FileNotFoundException if the schema file does not exist
	 * @throws DBException if the an unrecognized data type is detected
	 */
	public abstract void createRelations() throws FileNotFoundException;

	/**
	 * Gets a reference to the stored relation with the given name
	 * @param name	the name of the relation (case sensitive)
	 * @return	relation with the given name, or null if not exists
	 */
	public abstract AbstractRelation getRelation(String name);

	/**
	 * Generates and returns a string containing all the relations defined
	 * in this database in no particular order.
	 *
	 * @return string containing R1(a1,..) followed by R2(a1,..), etc.
	 */
	@Override
	public abstract String toString();

	/**
	 * (Hwk 2 addition)
	 * Performs intersection between two relations
	 * @param first		one relation
	 * @param second	second relation
	 * @return a relation containing the intersection
	 * @throws DBException if relations are incompatible
	 */
	public abstract Relation intersect(Relation first, Relation second) throws DBException;

	/**
	 * (Hwk 2 addition)
	 * Performs union between this and the given relation
	 * @param first		one relation
	 * @param second	second relation
	 * @return a relation containing the union
	 * @throws DBException if relations are incompatible
	 */
	public abstract Relation union(Relation first, Relation second) throws DBException;

	/**
	 * (Hwk 2 addition)
	 * Performs a set difference between this and the given relation
	 * @param first		one relation
	 * @param second	second relation
	 * @return a relation containing the difference
	 * @throws DBException if relations are incompatible
	 */
	public abstract Relation minus(Relation first, Relation second) throws DBException;

	/**
	 * (Hwk 2 addition)
	 * Performs a cartesian product between two relations
	 * @param first		one relation
	 * @param second	second relation
	 * @return a relation containing the cartesian product, or null if either is null
	 */
	public abstract Relation times(Relation first, Relation second);
}