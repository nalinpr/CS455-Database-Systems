import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;

import exceptions.DBException;

/**
 * Class implements the database
 * @author Nalin Richardson
 */
public class DavidDB extends AbstractDB {

    public DavidDB(String filename) throws FileNotFoundException {
        super(filename);
    }

    @Override
    public void createRelations() throws FileNotFoundException, DBException {
        File fileToRead = new File(this.schema_file);
        Scanner schema = new Scanner(fileToRead);
        while (schema.hasNextLine()) {
            Scanner currentLine = new Scanner(schema.nextLine());
            currentLine.useDelimiter("[\\s(),]");
            Relation newRelation = new Relation(currentLine.next());

            ArrayList<Attribute> listOfAttributes = new ArrayList<Attribute>();

            while (currentLine.hasNext()) {

                Attribute newAttribute;
                String attributeName = currentLine.next();
                String attributeType = currentLine.next();
                if (currentLine.hasNext()) currentLine.next();

                if (attributeType.equals("TEXT")){
                    newAttribute = new Attribute(newRelation, Attribute.Type.TEXT , attributeName);
                } else if (attributeType.equals("NUMERIC")) {
                    newAttribute = new Attribute(newRelation, Attribute.Type.NUMERIC, attributeName);
                } else {
                    throw new DBException(attributeType + " is an unrecognized data type");
                }

                listOfAttributes.add(newAttribute);
            }

            newRelation.setAttributes(listOfAttributes);
            this.relations.put(newRelation.name, newRelation);
        }
    }

    @Override
    public Relation getRelation(String name) {
        return this.relations.get(name);
    }

    public String toString() {
        Collection<Relation> relationCollection = this.relations.values();
        Iterator<Relation> relationIterator = relationCollection.iterator();

        StringBuilder finalString = new StringBuilder();
        while (relationIterator.hasNext()) {
            Relation currentRelation = relationIterator.next();
            finalString.append(currentRelation.schemaToString());
            finalString.append("\n");
        }
        return finalString.toString();

    }
}
