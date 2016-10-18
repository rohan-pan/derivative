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

      Monomial m = new Monomial();

      m.variable = var;

      if (mono.indexOf(var) == -1) { // constant

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

   @Override
   public String toString() {

      String monomial = "";

      if (coefficient.equals("0")) {
         return "0";
      }
      if (minusCoeff) {
         monomial = "-";
      } else {
         monomial = "+";
      }
      if (!(coefficient.equals(1))) {
         monomial += coefficient;
      }
      if (coefficient.equals(1)) {
         if (exponent.equals(0)) {
            monomial += coefficient;
         }
      }
      if (exponent.equals(0)) {
         return monomial;
      }
      monomial += variable;
      if (!(exponent.equals(1))) {
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
