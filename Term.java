import java.lang.StringBuilder;

/** Class which interfaces with differentiable to compute derivatives.
 *  @author Rohan
 */

public class Term implements Differentiable {

   boolean sign;
   Operator operator = new Operator();
   String operand;

   String strong;

   // Constructors

   /** Default constructor.
    *
    */

   public Term() {}

   // Methods

   /** Turns a Pseudoterm object into a strongly typed Pseudoterm.
    *
    * @param pseudo the Pseudoterm object
    * @return the strongly typed Pseudoterm
    */

    static String strongPseudo(Pseudoterm pseudo) {

      StringBuilder modify = new StringBuilder(pseudo.toString());

      while (modify.indexOf("sin(") != -1) {
          modify.replace(modify.indexOf("sin("), modify.indexOf("sin(") + 4, "S(");
      }

      while (modify.indexOf("cos(") != -1) {
          modify.replace(modify.indexOf("cos("), modify.indexOf("cos(") + 4, "C(");
        }

      while (modify.indexOf("sin") != -1) {
         modify.insert(modify.indexOf("sin") + 4, ')');
         modify.insert(modify.indexOf("sin") + 3, '(');
         modify.replace(modify.indexOf("sin("), modify.indexOf("sin(") + 4, "S(");
      }

      while (modify.indexOf("cos") != -1) {
         modify.insert(modify.indexOf("cos") + 4, ')');
         modify.insert(modify.indexOf("cos") + 3, '(');
         modify.replace(modify.indexOf("cos("), modify.indexOf("cos(") + 4, "S(");
      }

      return modify.toString();
   }

   /** Calculates the derivative of a term. (WIP)
    *
    * @return the derivative
    */

   public Differentiable derivative() {
      return null;
   }

}
