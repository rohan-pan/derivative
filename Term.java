import java.lang.StringBuilder;
import java.util.ArrayList;

/** Class which interfaces with differentiable to compute derivatives.
 *  @author Rohan
 */

public class Term {

   boolean negative = false;
    String coefficient = "";
   // Operator operator = new Operator();
   char operator;
   String operand;

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



    public boolean isConstant(String s) {
        return true;
    }

   /** Calculates the derivative of a term. (WIP)
    *
    * @return the derivative
    */

   public Term[] derivative() {
       ArrayList<Term> deriv = new ArrayList<>();
       Term toDerive = this;
       while (toDerive.isChain()) {
           Term dtl = toDerive.derivTrigLog();
           if (dtl != null) {
               deriv.add(dtl);
           }
           Term[] dpr = toDerive.productRule();
           if (dpr != null) {
               deriv.add(dpr[1]);
               deriv.add(dpr[2]);
           }
           Term[] dqr = toDerive.productRule();
           if (dqr != null) {
               deriv.add(dqr[1]);
               deriv.add(dqr[2]);
            }

           toDerive = toDerive.operand;
       }


    return deriv.toArray(Term[] deriv);
    }

    Term derivTrigLog() {
        Term deriv = this;
        boolean derived = false;
        if (this.operator == 'S') {
            deriv.operator = 'C';
            derived = true;
        }
        if (this.operator == 'C') {
            deriv.operator = 'S';
            deriv.negative = !this.negative;
            derived = true;
        }
        if (this.operator == 'L') {
            deriv.operator = 'Q';
            deriv.operand = "1,P(L(e," + this.operand.substring(0, Indexer.parentComma(this.operand)) + ")" + ',' + this.operand.substring(Indexer.parentComma(this.operand) + 1) + ")";
            derived = true;
        }
        if (derived){
            return deriv;
        } else {
            return null;
        }
    }

    /** Performs the product rule of derivatives.
     *  @return an array of the two term derivative
     */

    Term[] productRule() {
      Term[] terms = new Term[2];
        boolean derived = false;
      if (this.operator == 'P') {
         String[] args = Indexer.findTwoArgs(this.operand);

         terms[1].negative = this.negative;
         terms[2].negative = this.negative;
         terms[1].coefficient = this.coefficient;
         terms[2].coefficient = this.coefficient;
         terms[1].operator = 'P';
         terms[2].operator = 'P';
         terms[1].operand = toTerm(args[1]).derivative().toString() + ',' + args[2];
         terms[2].operand = toTerm(args[2]).derivative().toString() + ',' + args[1];

          derived = true;
      }
        if (derived) {
            return terms;
        } else {
            return null;
        }
   }

    /** Performs the quotient rule of derivatives.
     *
     * @return an array of the two term derivative
     */

    Term[] quotientRule() {
        Term[] terms = new Term[2];
        boolean derived = false;
        if (this.operator == 'Q') {
            String[] args = Indexer.findTwoArgs(this.operand);

            terms[1].negative = this.negative;
            terms[2].negative = !(this.negative);
            terms[1].coefficient = this.coefficient;
            terms[2].coefficient = this.coefficient;
            terms[1].operator = 'Q';
            terms[2].operator = 'Q';
            terms[1].operand = "P(" + toTerm(args[1]).derivative().toString() + ',' + args[2] + "),2" + args[2];
            terms[2].operand = "P(" + toTerm(args[2]).derivative().toString() + ',' + args[1] + "),2" + args[2];

            derived = true;
        }
        if (derived) {
            return terms;
        } else {
            return null;
        }
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

    public boolean isChain() {
        if (Operator.OPERATORS.contains(operand.charAt(0) + "")) {
            return true;
        } else {
            return false;
        }
    }

}
