import java.util.Scanner;

/** Natural-language derivative calculator.
 *  @author Rohan
 */

public class Derivative {

   /** Main method.
    *
    * @param args unused
    */
   public static void main(String[] args) {

      Scanner keyboard = new Scanner(System.in);
      String expression;
      String variable;

      Explicit ex;

      Pseudoterm[] outputArray;
      String debug1;

      // Input

      System.out.print("Enter an expression: ");
      expression = keyboard.nextLine();
      System.out.print("Enter the variable: ");
//      variable = keyboard.nextLine();

      // Processing and Output

      ex = Explicit.explicate(expression);
      outputArray = Indexer.getTerms(ex);

      for (Pseudoterm anOutputArray1 : outputArray) {
         debug1 = Term.strongPseudo(anOutputArray1);
         System.out.print(debug1 + " ");
         int debug2 = Indexer.parentOperator(debug1);
         System.out.println(debug2);
      }



      // Output


   }


   // public static Term[] explicit(String s) {
   //
   //    String expl = "";
   //    String chop = s;
   //    int count = 1;
   //    int counter = 0;
   //    int leftParen = 0;
   //    int rightParen = 0;
   //    int[] plusses;
   //    String[] addends;
   //
      // for (int i = 0; i < s.length(); i++) {
      //    if (s.charAt(i) == '-') {
      //       expl += "+-";
      //       count++;
      //    } else {
      //       expl += s.charAt(i);
      //    }
      // }
   //
   //    plusses = new int[count];
   //    for (int i = 0; i < s.length(); i++) {
   //       int index = 0;
   //       if (s.charAt(i) == '+') {
   //          plusses[count] = index;
   //          count++;
   //       }
   //    }
   //
   //    for (int i = 0; i < plusses.length; i++) {
   //       int j = 0;
   //       int[] parentPlusses = new int[plusses.length];
   //       for (j = 0; j < s.substring(0,plusses[i]).length(); j++) {
            // if (s.charAt(j) == '(') {
            //    leftParen++;
            // } else if (s.charAt(j) == ')') {
            //    rightParen++;
            // }
   //       }
   //       if (leftParen == rightParen) {
   //          parentPlusses[counter] = plusses[i];
   //          counter++;
   //       }
   //    }
   //
   //    for (int i = parentPlusses.length; i >= 0; i--) {
   //       addends[i] = chop.substring(parentPlusses[i] + 1);
   //       chop = chop.substring(0,parentPlusses[i]);
   //       System.out.println(addends[i]);
   //    }
   //
   //
   //
   //    return null;
   // }

   // public static ArrayList explicit(String s) {
   //    String expl = "";
   //    String chop;
   //    int count = 1;
   //    int leftParen = 0;
   //    int rightParen = 0;
   //    ArrayList<Integer> indexOfPlusses = new ArrayList<Integer>();
   //    ArrayList<Integer> indexOfParentPlusses = new ArrayList<Integer>();
   //    String[] terms;

      // for (int i = 0; i < s.length(); i++) {
      //    if (s.charAt(i) == '-') {
      //       expl += "+-";
      //       count++;
      //    } else {
      //       expl += s.charAt(i);
      //    }
      // } MOVED TO EXPLICIT CLASS

      // for (int i = 0; i < expl.length(); i++) {
      //    if (expl.charAt(i) == '+') {
      //       indexOfPlusses.add(i);
      //    }
      // } MOVED TO Indexer CLASS

      // for (int iterate : indexOfPlusses) {
      //    leftParen = 0;
      //    rightParen = 0;
      //    for (int i = 0; i < expl.substring(0,iterate).length(); i++) {
      //       if (expl.charAt(i) == '(') {
      //          leftParen++;
      //       } else if (expl.charAt(i) == ')') {
      //          rightParen++;
      //       }
      //    }
      //    if (leftParen == rightParen) {
      //       indexOfParentPlusses.add(iterate);
      //    }
      // } MOVED TO Indexer CLASS

      // for (int i = 0; i < indexOfParentPlusses.size(); i++) {
      //    System.out.println(indexOfParentPlusses.get(i));
      // }

      // chop = expl;
      // terms = new String[indexOfParentPlusses.size() + 1];
      // for (int i = 0; i < indexOfParentPlusses.size(); i++) {
      //    terms[i] = chop.substring(0, indexOfParentPlusses.get(i));
      //    chop = chop.substring(indexOfParentPlusses.get(i) + 1);
      //    System.out.println(terms[i]);
      // }
      // terms[terms.length - 1] = chop;
      // System.out.println(terms[terms.length - 1]);
      //
      // return null; MOVED TO Indexer CLASS
      // }
}
