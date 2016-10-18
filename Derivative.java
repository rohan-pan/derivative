import java.util.Scanner;
import java.lang.StringBuffer;

public class Derivative {
   public static void main(String[] args) {
      Scanner keyboard = new Scanner(System.in);
      String expression;
      String variable;
      String output = "";
      String[] polynomial;
      Monomial[] monomials;
      Monomial[] derivatives;

      // Input

      System.out.print("Enter a polynomial: ");
      expression = keyboard.nextLine();
      System.out.print("Enter the variable: ");
      variable = keyboard.nextLine();

      // Processing

      Monomial mono = new Monomial();
      mono = Monomial.parseMonomial(expression, variable);
      output = mono.toString();

      // polynomial = explicit(expression).split("\\+");
      // monomials = new Monomial[polynomial.length];
      // derivatives = new Monomial[polynomial.length];
      // for (int i = 0; i < polynomial.length; i++) {
      //    monomials[i] = new Monomial(polynomial[i], variable);
      //    derivatives[i] = monomials[i].derivative();
      //    if (derivatives[i].toString() != "0") {
      //       output += derivatives[i].toString();
      //    }
      // }
      // if (output.charAt(0) == '+') {
      //    output = output.substring(1);
      // }

      // Output

      System.out.println(output);
   }

   public static String explicit(String userInput) {

      String expl = "";
      for (int i = 0; i < userInput.length(); i++) {
         if (userInput.charAt(i) == '-') {
            expl += "+-";
         } else {
            expl += userInput.charAt(i);
         }
      }

      if (expl.charAt(0) == '+') {
         expl = expl.substring(1);
      }

      return expl;
   }
}
