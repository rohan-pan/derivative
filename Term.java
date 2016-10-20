/** Class which interfaces with differentiable to compute derivatives.
 *  @author Rohan
 */

public class Term implements Differentiable {

   boolean sign;
   Operator operator = new Operator();
   String operand;

   // Constructors

   /** Default constructor.
    *
    */

   public Term() {}

   // Methods

   /** Turns a Pseudoterm (string/object) TBD into a Term object.
    *
    * @param pseudo the Pseudoterm string/object TBD
    * @return the Term object
    */

   public Term parseTerm(String pseudo) {

      Term t = new Term();



      return t;
   }

   /** Calculates the derivative of a term. (WIP)
    *
    * @return the derivative
    */

   public Differentiable derivative() {
      return null;
   }

}
