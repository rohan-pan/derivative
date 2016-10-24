import java.util.Scanner;

/** Natural-language derivative calculator.
 *  @author Rohan
 */

public class Derivative {

    static char variable;

   /** Main method.
    *
    * @param args unused
    */
   public static void main(String[] args) {

      Scanner keyboard = new Scanner(System.in);
      String expression;

      Explicit ex;

      Pseudoterm[] outputArray;
      Term[] terms;
       Term[] derivs;
      String debug1;

      // Input

      System.out.print("Enter an expression: ");
      expression = keyboard.nextLine();
//      System.out.print("Enter the index: ");
//      variable = keyboard.nextLine();

      // Processing and Output

      ex = Explicit.explicate(expression);
      outputArray = Indexer.getTerms(ex);
       terms = new Term[outputArray.length];
       derivs = new Term[outputArray.length];

      for (int i = 0; i < outputArray.length; i++) {

          Term add = new Term();
          terms[i] = add.toTerm(Term.strongPseudo(outputArray[i]));
          derivs[i] = terms[i].derivTrigLog();
            System.out.println(derivs[i].toString());

      }
   }
}

