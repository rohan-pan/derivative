public class Monomial {

   String coefficient;
   String exponent;
   String variable;
   boolean minusCoeff;
   boolean minusExp;

   // Constructors

   public Monomial() {}

   // Methods

   public static Monomial parseMonomial(String mono, String var) {

      variable = var;

      if (mono.indexOf(var) == -1) { // constant

         exp = 0;
         minusExp = false;

         if (mono.charAt(0) == '-') { // negative
            coefficient = mono.substring(1);
            minusCoeff = true;
         } else { // positive
            coefficient = mono;
            minusCoeff = false;
         }

      } else if (mono.indexOf('^') == -1) { // implicit 1st power

         exp = 1;
         minusExp = false;

         if (mono.charAt(0) == '-') { // negative coefficient
            coefficient = mono.substring(1,mono.indexOf(var));
            minusCoeff = true;
         } else { // positive coefficient
            coefficient = mono.substring(0,mono.indexOf(var));
            minusCoeff = false;
         }

      } else { // non degenerate monomial

         if (mono.charAt(0) == '-') { // negative coefficient
            coefficient = mono.substring(1,mono.indexOf(var));
            minusCoeff = true;
         } else { // positive coefficient
            coefficient = mono.substring(0,mono.indexOf(var));
            minusCoeff = false;
         }

         if (mono.charAt(mono.indexOf('^') + 1) == '-') { // negative exponent
            exponent = mono.substring(mono.indexOf('^') + 2);
            minusExp = true;
         } else { // positive exponent
            exponent = mono.substring(mono.indexOf('^') + 1);
            minusExp = false;
         }
      }
   }

   @Override
   public String toString() {

      String monomial = "";

      if (coeff == 0) {
         return "0";
      }
      if (minusCoeff) {
         monomial = "-";
      } else {
         monomial = "+";
      }
      if (coeff != 1) {
         monomial += removeZeroes(coeff);
      }
      if (coeff == 1) {
         if (exp == 0) {
            monomial += removeZeroes(coeff);
         }
      }
      if (exp == 0) {
         return monomial;
      }
      monomial += variable;
      if (exp != 1) {
         monomial += '^';
         if (minusExp) {
            monomial += '-';
         }
         monomial += removeZeroes(exp);
      }
      return monomial;
   }

   public Monomial derivative() {

      Monomial deriv = new Monomial();

      deriv.coeff = this.coeff * this.exp;
      deriv.minusCoeff = this.minusCoeff ^ this.minusExp;
      deriv.variable = this.variable;
      if (this.minusExp) {
         deriv.exp = this.exp + 1;
         deriv.minusExp = true;
      } else {
         deriv.exp = this.exp - 1;
         deriv.minusExp = false;
         if (deriv.exp < 0) {
            deriv.exp *= -1;
            deriv.minusExp = true;
         }
      }

      return deriv;
   }

   public static String removeZeroes(double d) {
      if(d == (long) d)
           return String.format("%d",(long)d);
      else
           return String.format("%s",d);
   }
}
