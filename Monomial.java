/** A special case of Term which has a rigorous method for computing the derivative.
 * @author Rohan
 */

public class Monomial extends Term {

   private String coefficient;
   private String exponent;
   private String variable;
   private boolean minusCoeff;
   private boolean minusExp;
   private int caratIndex;

   // Constructors

   /** Default constructor.
    *
    */

   public Monomial() {}

   // Methods

   /** Given a string and variable, checks if the string is a monomial. NEEDS TO BE UPDATED
    * NEED TO PLAN FOR EXCEPTION CATCHING HERE
    *
    * @param mono the string to be evaluated
    * @param var the variable
    * @return the generated monomial
    */

   public static Monomial parseMonomial(String mono, String var) {

      Monomial m = new Monomial();

      // m.caratIndex =
      m.variable = var;

      if (!mono.contains(var)) { // constant

         m.exponent = "0";
         m.minusExp = false;

         if (mono.charAt(0) == '-') { // negative
            m.coefficient = mono.substring(1);
            m.minusCoeff = true;
         } else { // positive
            m.coefficient = mono;
            m.minusCoeff = false;
         }

      } else if (mono.indexOf('^') == -1) { // implicit 1st power

         m.exponent = "1";
         m.minusExp = false;

         if (mono.charAt(0) == '-') { // negative coefficient
            m.coefficient = mono.substring(1,mono.indexOf(var));
            m.minusCoeff = true;
         } else { // positive coefficient
            m.coefficient = mono.substring(0,mono.indexOf(var));
            m.minusCoeff = false;
         }

      } else { // non degenerate monomial

         if (mono.charAt(0) == '-') { // negative coefficient
            m.coefficient = mono.substring(1,mono.indexOf(var));
            m.minusCoeff = true;
         } else { // positive coefficient
            m.coefficient = mono.substring(0,mono.indexOf(var));
            m.minusCoeff = false;
         }

         if (mono.charAt(mono.indexOf('^') + 1) == '-') { // negative exponent
            m.exponent = mono.substring(mono.indexOf('^') + 2);
            m.minusExp = true;
         } else { // positive exponent
            m.exponent = mono.substring(mono.indexOf('^') + 1);
            m.minusExp = false;
         }
      }

      return m;
   }

   /** Converts a monomial to a human-readable string.
    *
    * @return the string
    */

   @Override
   public String toString() {

      String monomial;

      if (coefficient.equals("0")) {
         return "0";
      }
      if (minusCoeff) {
         monomial = "-";
      } else {
         monomial = "+";
      }
      if (!(coefficient.equals("1"))) {
         monomial += coefficient;
      }
      if (coefficient.equals("1")) {
         if (exponent.equals("0")) {
            monomial += coefficient;
         }
      }
      if (exponent.equals("0")) {
         return monomial;
      }
      monomial += variable;
      if (!(exponent.equals("1"))) {
         monomial += '^';
         if (minusExp) {
            monomial += '-';
         }
         monomial += exponent;
      }
      return monomial;
   }

   // Gets and Sets


}

   // public Monomial derivative() {
   //
   //    Monomial deriv = new Monomial();
   //
   //    deriv.coefficient.equals( = this.coefficient.equals( * this.exp;
   //    deriv.minusCoeff = this.minusCoeff ^ this.minusExp;
   //    deriv.variable = this.variable;
   //    if (this.minusExp) {
   //       deriv.exp = this.exp + 1;
   //       deriv.minusExp = true;
   //    } else {
   //       deriv.exp = this.exp - 1;
   //       deriv.minusExp = false;
   //       if (deriv.exp < 0) {
   //          deriv.exp *= -1;
   //          deriv.minusExp = true;
   //       }
   //    }
   //
   //    return deriv;
   // }

//    public static String removeZeroes(double d) {
//       if(d == (long) d)
//            return String.format("%d",(long)d);
//       else
//            return String.format("%s",d);
//    }
// }
