import java.lang.StringBuilder;

/** Class which interfaces with differentiable to compute derivatives.
 *  @author Rohan
 */

public class Term implements Differentiable {

   private boolean negative = false;
    private String coefficient;
   // Operator operator = new Operator();
    private char operator;
   private String operand;


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

        String strong;

        strong = strongTrig(pseudo);
        strong = strongLog(Pseudoterm.setPseudoterm(strong));
        strong = strongExpDivMult(Pseudoterm.setPseudoterm(strong));
        return strong;

   }

    /** First part of the process of creating a strong pseudoterm
     *
     * @param pseudo the pseudoterm to modify
     * @return trig functions strong
     */

   private static String strongTrig(Pseudoterm pseudo) {

       StringBuilder modify = new StringBuilder(pseudo.toString());
       String transfer;

       while (modify.indexOf("sin(") != -1) {
           modify.replace(modify.indexOf("sin("), modify.indexOf("sin(") + 4, "S(");
       }

       while (modify.indexOf("cos(") != -1) {
           modify.replace(modify.indexOf("cos("), modify.indexOf("cos(") + 4, "C(");
       }

       while (modify.indexOf("csc(") != -1) {
           modify.insert(modify.indexOf("csc(") + 3 + Indexer.matchParen(modify.toString(), modify.indexOf("csc(") + 3).length(), ')');
           modify.replace(modify.indexOf("csc("), modify.indexOf("csc(") + 4, "Q(1,S(");
       }

       while (modify.indexOf("sec(") != -1) {
           modify.insert(modify.indexOf("sec(") + 3 + Indexer.matchParen(modify.toString(), modify.indexOf("sec(") + 3).length(), ')');
           modify.replace(modify.indexOf("sec("), modify.indexOf("sec(") + 4, "Q(1,C(");
       }

       while (modify.indexOf("tan(") != -1) {
           transfer = Indexer.matchParen(modify.toString(), modify.indexOf("tan(") + 3);
           modify.insert(modify.indexOf("tan(") + 3 + Indexer.matchParen(modify.toString(), modify.indexOf("tan(") + 3).length(), ')');
           modify.replace(modify.indexOf("tan("), modify.indexOf("tan(") + 4, "Q(S" + transfer + ",C(");
       }
       while (modify.indexOf("cot(") != -1) {
           transfer = Indexer.matchParen(modify.toString(), modify.indexOf("cot(") + 3);
           modify.insert(modify.indexOf("cot(") + 3 + Indexer.matchParen(modify.toString(), modify.indexOf("cot(") + 3).length(), ')');
           modify.replace(modify.indexOf("cot("), modify.indexOf("cot(") + 4, "Q(C" + transfer + ",S(");
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

       while (modify.indexOf("csc") != -1) {
           modify.insert(modify.indexOf("csc") + 4, ')');
           modify.insert(modify.indexOf("csc") + 3, '(');
           modify.insert(modify.indexOf("csc(") + 3 + Indexer.matchParen(modify.toString(), modify.indexOf("csc(") + 3).length(), ')');
           modify.replace(modify.indexOf("csc("), modify.indexOf("csc(") + 4, "Q(1,S(");
       }

       while (modify.indexOf("sec") != -1) {
           modify.insert(modify.indexOf("sec") + 4, ')');
           modify.insert(modify.indexOf("sec") + 3, '(');
           modify.insert(modify.indexOf("sec(") + 3 + Indexer.matchParen(modify.toString(), modify.indexOf("sec(") + 3).length(), ')');
           modify.replace(modify.indexOf("sec("), modify.indexOf("sec(") + 4, "Q(1,C(");
       }

       while (modify.indexOf("tan") != -1) {
           modify.insert(modify.indexOf("tan") + 4, ')');
           modify.insert(modify.indexOf("tan") + 3, '(');
           transfer = Indexer.matchParen(modify.toString(), modify.indexOf("tan(") + 3);
           modify.insert(modify.indexOf("tan(") + 3 + Indexer.matchParen(modify.toString(), modify.indexOf("tan(") + 3).length(), ')');
           modify.replace(modify.indexOf("tan("), modify.indexOf("tan(") + 4, "Q(S" + transfer + ",C(");
       }
       while (modify.indexOf("cot") != -1) {
           modify.insert(modify.indexOf("cot") + 4, ')');
           modify.insert(modify.indexOf("cot") + 3, '(');
           transfer = Indexer.matchParen(modify.toString(), modify.indexOf("cot(") + 3);
           modify.insert(modify.indexOf("cot(") + 3 + Indexer.matchParen(modify.toString(), modify.indexOf("cot(") + 3).length(), ')');
           modify.replace(modify.indexOf("cot("), modify.indexOf("cot(") + 4, "Q(C" + transfer + ",S(");
       }

       return modify.toString();
   }

    /** Second part of the process of creating a strong pseudoterm
     *
     * @param pseudo the pseudoterm to modify
     * @return logs strong
     */

   private static String strongLog (Pseudoterm pseudo) {

        StringBuilder modify = new StringBuilder(pseudo.toString());

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



       return modify.toString();
   }

    /** Third part of the process of creating a strong pseudoterm
     *
     * @param pseudo the pseudoterm to modify
     * @return EDM strong
     */

   private static String strongExpDivMult (Pseudoterm pseudo) {

       StringBuilder modify = new StringBuilder(pseudo.toString());
       String transfer;
       char trans;

       while (modify.indexOf("^") != -1) {
           if (modify.indexOf(")^(") != -1) {
               transfer = Indexer.matchParen(modify.toString(), modify.indexOf(")^("));
               modify.delete(modify.indexOf(")^(") + 1 - transfer.length(), modify.indexOf(")^("));
               modify.replace(modify.indexOf(")^("), modify.indexOf(")^(") + 3, "E(" + transfer + ",");
           } else if (modify.indexOf(")^") != -1) {
               int i = 2;
               boolean ops = false;
               while (Operator.OPERATORS.contains(modify.charAt(modify.indexOf(")^") + i) + "")) {
                   i++;
                   ops = true;
               }
               if (ops) {
                   modify.insert(modify.indexOf(")^") + Indexer.matchParen(modify.toString(), modify.indexOf(")^") + i).length() + 1, ")");
               } else {
                   modify.insert(modify.indexOf(")^") + 3, ')');
               }
               modify.insert(modify.indexOf(")^") + 2, '(');
               transfer = Indexer.matchParen(modify.toString(), modify.indexOf(")^"));
               modify.delete(modify.indexOf(")^") + 1 - transfer.length(), modify.indexOf(")^"));
               modify.replace(modify.indexOf(")^"), modify.indexOf(")^") + 3, "E(" + transfer + ",");
           } else if (modify.indexOf("^(") != -1) {
               trans = modify.charAt(modify.indexOf("^(") - 1);
               modify.deleteCharAt(modify.indexOf("^(") - 1);
               modify.replace(modify.indexOf("^("), modify.indexOf("^(") +2, "E(" + trans + ",");
           } else if (modify.indexOf("^") != -1) {

               trans = modify.charAt(modify.indexOf("^") - 1);
               modify.deleteCharAt(modify.indexOf("^") - 1);
               int i = 1;
               boolean ops = false;
               while (Operator.OPERATORS.contains(modify.charAt(modify.indexOf("^") + i) + "")) {
                   i++;
                   ops = true;
               }
               if (ops) {
                   modify.insert(modify.indexOf("^") + Indexer.matchParen(modify.toString(), modify.indexOf("^") + i).length() + 1, ")");
               } else {
                   modify.insert(modify.indexOf("^") + 2, ')');
               }
               modify.insert(modify.indexOf("^") + 1, '(');
               modify.replace(modify.indexOf("^("), modify.indexOf("^(") + 2, "E(" + trans + ",");
           }
       }

       while (modify.indexOf("*") != -1 || modify.indexOf("/") != -1) {
           if (modify.indexOf("*") != -1) {
               if (modify.indexOf(")*(") != -1) {
                   transfer = Indexer.matchParen(modify.toString(), modify.indexOf(")*("));
                   modify.delete(modify.indexOf(")*(") + 1 - transfer.length(), modify.indexOf(")*("));
                   modify.replace(modify.indexOf(")*("), modify.indexOf(")*(") + 3, "P(" + transfer + ",");
               } else if (modify.indexOf(")*") != -1) {
                   int i = 2;
                   boolean ops = false;
                   while (Operator.OPERATORS.contains(modify.charAt(modify.indexOf(")*") + i) + "")) {
                       i++;
                       ops = true;
                   }
                   if (ops) {
                       modify.insert(modify.indexOf(")*") + Indexer.matchParen(modify.toString(), modify.indexOf(")*") + i).length() + 1, ")");
                   } else {
                       modify.insert(modify.indexOf(")*") + 3, ')');
                   }
                   modify.insert(modify.indexOf(")*") + 2, '(');
                   transfer = Indexer.matchParen(modify.toString(), modify.indexOf(")*"));
                   modify.delete(modify.indexOf(")*") + 1 - transfer.length(), modify.indexOf(")*"));
                   modify.replace(modify.indexOf(")*"), modify.indexOf(")*") + 3, "P(" + transfer + ",");
               } else if (modify.indexOf("*(") != -1) {
                   trans = modify.charAt(modify.indexOf("*(") - 1);
                   modify.deleteCharAt(modify.indexOf("*(") - 1);
                   modify.replace(modify.indexOf("*("), modify.indexOf("*(") +2, "P(" + trans + ",");
               } else if (modify.indexOf("*") != -1) {

                   trans = modify.charAt(modify.indexOf("*") - 1);
                   modify.deleteCharAt(modify.indexOf("*") - 1);
                   int i = 1;
                   boolean ops = false;
                   while (Operator.OPERATORS.contains(modify.charAt(modify.indexOf("*") + i) + "")) {
                       i++;
                       ops = true;
                   }
                   if (ops) {
                       modify.insert(modify.indexOf("*") + Indexer.matchParen(modify.toString(), modify.indexOf("*") + i).length() + 1, ")");
                   } else {
                       modify.insert(modify.indexOf("*") + 2, ')');
                   }
                   modify.insert(modify.indexOf("*") + 1, '(');
                   modify.replace(modify.indexOf("*("), modify.indexOf("*(") + 2, "P(" + trans + ",");
               }
           }

           if (modify.indexOf("/") != -1) {
               if (modify.indexOf(")/(") != -1) {
                   transfer = Indexer.matchParen(modify.toString(), modify.indexOf(")/("));
                   modify.delete(modify.indexOf(")/(") + 1 - transfer.length(), modify.indexOf(")/("));
                   modify.replace(modify.indexOf(")/("), modify.indexOf(")/(") + 3, "Q(" + transfer + ",");
               } else if (modify.indexOf(")/") != -1) {
                   int i = 2;
                   boolean ops = false;
                   while (Operator.OPERATORS.contains(modify.charAt(modify.indexOf(")/") + i) + "")) {
                       i++;
                       ops = true;
                   }
                   if (ops) {
                       modify.insert(modify.indexOf(")/") + Indexer.matchParen(modify.toString(), modify.indexOf(")/") + i).length() + 1, ")");
                   } else {
                       modify.insert(modify.indexOf(")/") + 3, ')');
                   }
                   modify.insert(modify.indexOf(")/") + 2, '(');
                   transfer = Indexer.matchParen(modify.toString(), modify.indexOf(")/"));
                   modify.delete(modify.indexOf(")/") + 1 - transfer.length(), modify.indexOf(")/"));
                   modify.replace(modify.indexOf(")/"), modify.indexOf(")/") + 3, "Q(" + transfer + ",");
               } else if (modify.indexOf("/(") != -1) {
                   trans = modify.charAt(modify.indexOf("/(") - 1);
                   modify.deleteCharAt(modify.indexOf("/(") - 1);
                   modify.replace(modify.indexOf("/("), modify.indexOf("/(") +2, "Q(" + trans + ",");
               } else if (modify.indexOf("/") != -1) {

                   trans = modify.charAt(modify.indexOf("/") - 1);
                   modify.deleteCharAt(modify.indexOf("/") - 1);
                   int i = 1;
                   boolean ops = false;
                   while (Operator.OPERATORS.contains(modify.charAt(modify.indexOf("/") + i) + "")) {
                       i++;
                       ops = true;
                   }
                   if (ops) {
                       modify.insert(modify.indexOf("/") + Indexer.matchParen(modify.toString(), modify.indexOf("/") + i).length() + 1, ")");
                   } else {
                       modify.insert(modify.indexOf("/") + 2, ')');
                   }
                   modify.insert(modify.indexOf("/") + 1, '(');
                   modify.replace(modify.indexOf("/("), modify.indexOf("/(") + 2, "Q(" + trans + ",");
               }
           }
       }

       return modify.toString();

   }

    /** Converts a strongly typed pseudoterm into a Term.
     *
     * @param strong strongly types pseudoterm
     * @return an explicit Term.
     */

    Term toTerm(String strong) {

       String breakup = strong;

       if (strong.charAt(0) == '-') {
           this.negative = true;
           breakup = strong.substring(1);
       }

       if (Indexer.parentOperator(breakup) != -1) {
           this.coefficient = breakup.substring(0,Indexer.parentOperator(breakup));
           breakup = strong.substring(Indexer.parentOperator(strong));
          // this.operator = new Operator(breakup.charAt(0));
           this.operator = breakup.charAt(0);
           this.operand = breakup.substring(2, breakup.length() - 1);
       } else {
           this.coefficient = "";
           this.operator = '0';
           this.operand = breakup;
       }






       return this;

   }



    /** Checks if a character is a constant according to the strong.
     *
     * @param c character to check
     * @return whether it is a constant
     */

    public boolean isConstant(char c) {
        if (Character.isLetterOrDigit(c))
            if (Character.isDigit(c) || !Operator.OPERATORS.contains(c + "") && c != Derivative.variable) return true;
        return false;
    }

   /** Calculates the derivative of a term. (WIP)
    *
    * @return the derivative
    */

   public Term derivative() {



        return null;
    }

    Term derivTrigLog() {
        Term deriv = new Term();
        if (this.operator == 'S') {
            deriv.operator = 'C';
            deriv.negative = this.negative;
            deriv.coefficient = this.coefficient;
            deriv.operand = this.operand;
        }
        if (this.operator == 'C') {
            deriv.operator = 'S';
            deriv.negative = !this.negative;
            deriv.coefficient = this.coefficient;
            deriv.operand = this.operand;
        }
        if (this.operator == 'L') {
            deriv.operator = 'Q';
            deriv.coefficient = this.coefficient;
            deriv.operand = "1,P(L(e," + this.operand.substring(0, Indexer.parentComma(this.operand)) + ")" + ',' + this.operand.substring(Indexer.parentComma(this.operand) + 1) + ")";

        }

        return deriv;
    }

    public boolean isMonomial() {
        return false;
    }

    @Override
    public String toString() {
        String output = "";

        if (this.negative) {
            output += "-";
        }
        output += coefficient;
        output += operator;
        output += '(' + operand + ')';

        return output;
    }

}
