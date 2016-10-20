/** Class which is used to understand the operators within a Term.
 * @author Rohan
 *
 */
public class Operator {

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

    /** Calculates the depth of an operator (based on parentheses).
     *
     * @param s the Term object
     * @param index the index of the object
     * @return the depth of the operator
     */

    public int depth(String s, int index) {
        String sub = s.substring(0, index);
        int left = 0;
        int right = 0;

        for (int i = 0; i < sub.length(); i++) {
            if (sub.charAt(i) == '(') {
                left++;
            }
            if (sub.charAt(i) == ')') {
                right++;
            }
        }

        return left - right;
    }

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
}
