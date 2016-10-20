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
        String transfer;
        char trans;

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
         modify.replace(modify.indexOf("cos("), modify.indexOf("cos(") + 4, "C(");
      }

      while (modify.indexOf("ln(") != -1) {
          modify.replace(modify.indexOf("ln("), modify.indexOf("ln(") + 3, "L(e,");
      }

      while (modify.indexOf("ln") != -1) {
            modify.insert(modify.indexOf("ln") + 3, ')');
            modify.insert(modify.indexOf("ln") + 2, '(');
            modify.replace(modify.indexOf("ln("), modify.indexOf("ln(") + 3, "L(e,");
        }

        while (modify.indexOf("log(") != -1) {
            modify.replace(modify.indexOf("log("), modify.indexOf("log(") + 4, "L(");
        }

        while (modify.indexOf("log") != -1) {
            modify.insert(modify.indexOf("log") + 4, ')');
            modify.insert(modify.indexOf("log") + 3, '(');
            modify.replace(modify.indexOf("log("), modify.indexOf("log(") + 4, "L(10,");
        }

        while (modify.indexOf(")^(") != -1) {
            transfer = Indexer.matchParen(modify.toString(), modify.indexOf(")^("));
            modify.delete(modify.indexOf(")^(") + 1 - transfer.length(), modify.indexOf(")^("));
            modify.replace(modify.indexOf(")^("), modify.indexOf(")^(") + 3, "E(" + transfer + ",");
        }

        while (modify.indexOf("^(") != -1) {
            trans = modify.charAt(modify.indexOf("^(") - 1);
            modify.deleteCharAt(modify.indexOf("^(") - 1);
            modify.replace(modify.indexOf("^("), modify.indexOf("^(") +2, "E(" + trans + ",");
        }

        while (modify.indexOf(")^") != -1) {
            modify.insert(modify.indexOf(")^") + 3, ')');
            modify.insert(modify.indexOf(")^") + 2, '(');
            transfer = Indexer.matchParen(modify.toString(), modify.indexOf(")^("));
            modify.delete(modify.indexOf(")^(") + 1 - transfer.length(), modify.indexOf(")^("));
            modify.replace(modify.indexOf(")^("), modify.indexOf(")^(") + 3, "E(" + transfer + ",");
        }

        while (modify.indexOf("^") != -1) {
            trans = modify.charAt(modify.indexOf("^") - 1);
            modify.deleteCharAt(modify.indexOf("^") - 1);
            modify.insert(modify.indexOf("^") + 2, ')');
            modify.insert(modify.indexOf("^") + 1, '(');
            modify.replace(modify.indexOf("^("), modify.indexOf("^(") + 2, "E(" + trans + ",");
        }

        while (modify.indexOf(")*(") != -1) {
            transfer = Indexer.matchParen(modify.toString(), modify.indexOf(")*("));
            modify.delete(modify.indexOf(")*(") + 1 - transfer.length(), modify.indexOf(")*("));
            modify.replace(modify.indexOf(")*("), modify.indexOf(")*(") + 3, "P(" + transfer + ",");
        }

        while (modify.indexOf("*(") != -1) {
            trans = modify.charAt(modify.indexOf("*(") - 1);
            modify.deleteCharAt(modify.indexOf("*(") - 1);
            modify.replace(modify.indexOf("*("), modify.indexOf("*(") +2, "P(" + trans + ",");
        }

        while (modify.indexOf(")*") != -1) {
            modify.insert(modify.indexOf(")*") + 3, ')');
            modify.insert(modify.indexOf(")*") + 2, '(');
            transfer = Indexer.matchParen(modify.toString(), modify.indexOf(")*("));
            modify.delete(modify.indexOf(")*(") + 1 - transfer.length(), modify.indexOf(")*("));
            modify.replace(modify.indexOf(")*("), modify.indexOf(")*(") + 3, "P(" + transfer + ",");
        }

        while (modify.indexOf("*") != -1) {
            trans = modify.charAt(modify.indexOf("*") - 1);
            modify.deleteCharAt(modify.indexOf("*") - 1);
            modify.insert(modify.indexOf("*") + 2, ')');
            modify.insert(modify.indexOf("*") + 1, '(');
            modify.replace(modify.indexOf("*("), modify.indexOf("*(") + 2, "P(" + trans + ",");
        }

        while (modify.indexOf(")/(") != -1) {
            transfer = Indexer.matchParen(modify.toString(), modify.indexOf(")/("));
            modify.delete(modify.indexOf(")/(") + 1 - transfer.length(), modify.indexOf(")/("));
            modify.replace(modify.indexOf(")/("), modify.indexOf(")/(") + 3, "Q(" + transfer + ",");
        }

        while (modify.indexOf("/(") != -1) {
            trans = modify.charAt(modify.indexOf("/(") - 1);
            modify.deleteCharAt(modify.indexOf("/(") - 1);
            modify.replace(modify.indexOf("/("), modify.indexOf("/(") +2, "Q(" + trans + ",");
        }

        while (modify.indexOf(")/") != -1) {
            modify.insert(modify.indexOf(")/") + 3, ')');
            modify.insert(modify.indexOf(")/") + 2, '(');
            transfer = Indexer.matchParen(modify.toString(), modify.indexOf(")/("));
            modify.delete(modify.indexOf(")/(") + 1 - transfer.length(), modify.indexOf(")/("));
            modify.replace(modify.indexOf(")/("), modify.indexOf(")/(") + 3, "Q(" + transfer + ",");
        }

        while (modify.indexOf("/") != -1) {
            trans = modify.charAt(modify.indexOf("/") - 1);
            modify.deleteCharAt(modify.indexOf("/") - 1);
            modify.insert(modify.indexOf("/") + 2, ')');
            modify.insert(modify.indexOf("/") + 1, '(');
            modify.replace(modify.indexOf("/("), modify.indexOf("/(") + 2, "Q(" + trans + ",");
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
