/** Class which holds Terms which have not yet been converted to alternative notation.
 * @author Rohan
 */
public class Pseudoterm {

    private String value;

    // Constructors

    /** Default constructor.
     *
     */

    public Pseudoterm() {}

    // Methods

    /** Converts a Pseudoterm string to a Pseudoterm object.
     *
     * @param s Pseudoterm string
     * @return Pseudoterm object
     */

    public static Pseudoterm setPseudoterm(String s) {
        Pseudoterm p = new Pseudoterm();
        p.value = s;
        return p;
    }

    /** Converts a Pseudoterm object to a Pseudoterm string.
     *
     * @return Pseudoterm object
     */

    @Override
    public String toString() {
        return this.value;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
