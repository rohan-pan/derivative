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

        // TRIG

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
            modify.insert(modify.indexOf("tan") + 4, ')');
            modify.insert(modify.indexOf("tan") + 3, '(');
            transfer = Indexer.matchParen(modify.toString(), modify.indexOf("cot(") + 3);
            modify.insert(modify.indexOf("cot(") + 3 + Indexer.matchParen(modify.toString(), modify.indexOf("cot(") + 3).length(), ')');
            modify.replace(modify.indexOf("cot("), modify.indexOf("cot(") + 4, "Q(C" + transfer + ",S(");
        }


        // LOGS

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

        // Exp, Mult, Div - Runs into errors with some complex non-binary. PROBABLY MUST REWRITE WITH SCALE IN MIND

        while (modify.indexOf("^") != -1) {
            if (modify.indexOf(")^(") != -1) {
                transfer = Indexer.matchParen(modify.toString(), modify.indexOf(")^("));
                modify.delete(modify.indexOf(")^(") + 1 - transfer.length(), modify.indexOf(")^("));
                modify.replace(modify.indexOf(")^("), modify.indexOf(")^(") + 3, "E(" + transfer + ",");
            } else if (modify.indexOf(")^") != -1) {
                int i = 1;
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
                    int i = 1;
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
                    int i = 1;
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

   public Differentiable derivative() {
      return null;
   }

}
