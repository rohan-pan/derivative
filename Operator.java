/** Class which is used to understand the operators within a Term.
 * @author Rohan
 *
 */
public class Operator {

    final static String OPERATORS = "PQELSC";

    private char operation;

    // Constructor

    /** Default constructor.
     *
     */

    public Operator() {

    }

    /** Constructor with initialization.
     *
     * @param c char to store
     */

    public Operator(char c) {
        this.operation = c;
    }

    // Methods

    /** Getter.
     *
     * @return operation
     */

    public char getOperation() {
        return operation;
    }

    /** Setter.
     *
     * @param operation value to set to
     */

    public void setOperation(char operation) {
        this.operation = operation;
    }

    /** Getter.
     *
     * @return the string of final operators
     */

    public static String getOPERATORS() {
        return OPERATORS;
    }

}
