import java.util.List;

/**
 * This Class implements the Tuple class
 *
 * @author Nalin Richardson
 */
public class Tuple extends AbstractTuple {

    /**
     * Final variables
     */
    private final int COL_SPACING = 16;

    /* Fields */
    private AbstractRelation relation;
    private List<Comparable> data;


    /**
     * @param values
     * @param r
     */
    public Tuple(Comparable[] values, AbstractRelation r) {
        this.relation = r;
        for (Comparable value : values) {
            this.data.add(value);
        }
    }

    /**
     * Constructor for Tuple
     * @param values A list of data for to be stored
     * @param r A Relation stating how the data relates to certain attributes
     */
    public Tuple(List<Comparable> values, AbstractRelation r) {
        this.relation = r;
        this.data = values;
    }

    public List<Comparable> getData() {
        return data;
    }

    /**
     * Gives a boolean value representing whether or not the current Tuple is equal to another tuple
     * @param other reference to another tuple
     * @return A boolean
     */
    @Override
    public boolean equals(Object other) {
       Tuple otherTuple =  (Tuple) other;
       return this.relation == otherTuple.relation && this.data == otherTuple.data;

    }

    /**
     * @return A string representation of the Tuple
     */
    @Override
    public String toString() {
        StringBuilder finalString = new StringBuilder();
        for (Comparable value : this.data) {
            String stringValue = value.toString();
            int lengthOfValue = stringValue.length();
            finalString.append('|');
            if (lengthOfValue < COL_SPACING) {
                finalString.append(stringValue);
                int numOfSpaces = COL_SPACING - lengthOfValue;
                for (int i = 0; i < numOfSpaces; i++) {
                    finalString.append(' ');
                }
            } else {
                String subStringValue = stringValue.substring(0, 16);
                finalString.append(subStringValue);
            }
        }
        finalString.append('|');
        return finalString.toString();
    }



}
