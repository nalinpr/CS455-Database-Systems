import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import exceptions.DBException;

/**
 * This class implements Relation
 *
 * @author Nalin Richardson
 */
public class Relation extends AbstractRelation {

    private final int COL_SPACING = 16;

    public Relation() {
        super();
    }

    public Relation(String name) {
        super(name);
    }

    private String checkType(Attribute.Type type) {
        if (type == Attribute.Type.NUMERIC) return "Double";
        else if (type == Attribute.Type.TEXT) return "String";
        else return "Error";
    }


    /**
     * Reads a data file and populates the Relation
     * @param infile the name of the data file
     * @throws FileNotFoundException Error thrown if File doesn't exist
     * @throws DBException Error thrown if there is a Type Mismatch
     */
    @Override
    public void read(String infile) throws FileNotFoundException, DBException {

        File fileToRead = new File(infile);
        Scanner sc = new Scanner(fileToRead);
        while (sc.hasNextLine()) {

            Scanner currString = new Scanner(sc.nextLine());
            currString.useDelimiter("[|]");

            Iterator<Attribute> listIterator = this.attribute_list.iterator();
            ArrayList<Comparable> tupleData = new ArrayList<>();


            while (currString.hasNext()) {
                Attribute attribute = listIterator.next();
                Attribute.Type type = attribute.getType();
                String stringType = checkType(type);

                if (currString.hasNextDouble()) {
                    if (!(stringType.equals("Double"))) throw new DBException("Type Mismatch for " + type + " attribute: "+ currString.next());
                } else if (!(stringType.equals("String"))) throw new DBException();

                tupleData.add(currString.next());
            }

            Tuple newTuple = new Tuple(tupleData, this);
            tuples.add(newTuple);
        }
    }


    /**
     * @param new_tuple the tuple to be added to the relation
     * @throws DBException Error thrown if tuple data doesn't match attribute list
     */
    @Override
    public void addTuple(Tuple new_tuple) throws DBException{

        List<Comparable> data = new_tuple.getData();
        Iterator dataIterator = data.iterator();
        Iterator<Attribute> attributeIterator = this.attribute_list.iterator();

        while (dataIterator.hasNext()) {
            Object entry = dataIterator.next();

            Attribute.Type type = attributeIterator.next().getType();
            String stringType = checkType(type);

            if (stringType.equals("Double")) {
                if (!(entry instanceof Double)) throw new DBException();
            } else if (stringType.equals("String")) {
                if (!(entry instanceof String)) throw new DBException();
            }
        }

        this.tuples.add(new_tuple);
    }

    /**
     * @return A representation of the Relation as a Schema
     */
    @Override
    public String schemaToString() {
        StringBuilder finalString = new StringBuilder();
        Iterator<Attribute> attributeIterator = this.attribute_list.iterator();
        finalString.append(this.name);
        finalString.append("(");
        while (attributeIterator.hasNext()) {
            Attribute currentAttribute = attributeIterator.next();
            finalString.append("\n\t");
            finalString.append(currentAttribute.getName());
            finalString.append(" ");
            finalString.append(currentAttribute.getType());
            finalString.append(",");
        }
        finalString.append("\n)");
        return finalString.toString();
    }

    @Override
    public String toString(){
        StringBuilder finalString = new StringBuilder();
        int length = (attribute_list.size() * COL_SPACING) + attribute_list.size();

        finalString.append(this.name);

        finalString.append("\n-");
        for (int i = 0; i < length; i++) {
            finalString.append("-");
        }
        finalString.append("\n");

        Iterator<Attribute> attributeIterator = this.attribute_list.iterator();
        while (attributeIterator.hasNext()) {
            String name = attributeIterator.next().getName();
            int nameLength = name.length();
            finalString.append('|');
            finalString.append(name);
            int numOfSpaces = COL_SPACING - nameLength;
            for (int i = 0; i < numOfSpaces; i++) {
                finalString.append(' ');
            }
        }
        finalString.append('|');

        finalString.append("\n-");
        for (int i = 0; i < length; i++) {
            finalString.append("-");
        }
        finalString.append("\n");


        Iterator<Tuple> tupleIterator = this.tuples.iterator();

        while (tupleIterator.hasNext()) {
            Tuple currentTuple = tupleIterator.next();
            finalString.append(currentTuple.toString());
            finalString.append("\n");
        }

        finalString.append("-");
        for (int i = 0; i < length; i++) {
            finalString.append("-");
        }
        finalString.append("\n");

        return finalString.toString();
    }
}

