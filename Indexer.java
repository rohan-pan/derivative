import java.util.ArrayList;

/** Class which handles most of the natural language processing, grouping, breakup.
 * @author Rohan
 */

public class Indexer {

   // Constructors

   /** Default constructor.
    *
    */

   public Indexer() {}

   // Methods

   /** Finds all plusses in the explicit object. MAY NEED OPTIMIZATION.
    *
    * @param expl explicit object to be searched
    * @return ArrayList of integers containing indices of plusses
    */

   private static ArrayList<Integer> indexPlusses(Explicit expl) {

      ArrayList<Integer> indexOfPlusses = new ArrayList<>();

      for (int i = 0; i < expl.toString().length(); i++) {
         if (expl.toString().charAt(i) == '+') {
            indexOfPlusses.add(i);
         }
      }

      return indexOfPlusses;
   }

   /** Finds all parent level plusses in the explicit object. MAY NEED OPTIMIZATION.
    *
    * @param expl explicit object to be searched
    * @return ArrayList of integers containing indices of parent plusses
    */

   private static ArrayList<Integer> indexParentPlusses(Explicit expl) {

      ArrayList<Integer> plusses = indexPlusses(expl);
      ArrayList<Integer> indexOfParentPlusses = new ArrayList<>();

      for (int iterate : plusses) {
         int leftParen = 0;
         int rightParen = 0;
         for (int i = 0; i < expl.toString().substring(0,iterate).length(); i++) {
            if (expl.toString().charAt(i) == '(') {
               leftParen++;
            } else if (expl.toString().charAt(i) == ')') {
               rightParen++;
            }
         }
         if (leftParen == rightParen) {
            indexOfParentPlusses.add(iterate);
         }
      }

      return indexOfParentPlusses;
   }

   /** Splits the explicit at each parent plus into pseudoterms.
    *
    * @param expl explicit object to be searched
    * @return Array of Pseudoterms
    */

   static Pseudoterm[] getTerms(Explicit expl) {

        ArrayList<Integer> parPlus = indexParentPlusses(expl);
        ArrayList<Integer> ppCopy = new ArrayList<>(parPlus);

      String chop = expl.toString();

      Pseudoterm[] pterms = new Pseudoterm[parPlus.size() + 1];
      for (int i = 0; i < parPlus.size(); i++) {
         pterms[i] = Pseudoterm.setPseudoterm(chop.substring(0, parPlus.get(i)));
         chop = chop.substring(parPlus.get(i) + 1);
         if (parPlus.size() - i != 1) {
            parPlus.set(i + 1, parPlus.get(i + 1) - ppCopy.get(i) - 1);
         }
      }

       pterms[pterms.length - 1] = Pseudoterm.setPseudoterm(chop);

      return pterms;
   }

   /** Method which takes a pseudoterm string and finds all operators.
    *
    * @param strong the strongly typed pseudoterm string
    * @return an ArrayList of all the indices of the operators
    */

    private static ArrayList<Integer> indexOperators(String strong) {

      ArrayList<Integer> indexOfOperators = new ArrayList<>();

      for (int i = 0; i < strong.length(); i++) {
         if (Operator.OPERATORS.contains(strong.charAt(i) + "")) {
            indexOfOperators.add(i);
         }
      }

      return indexOfOperators;
   }

   /** Method which finds the parent operator within a strongly typed pseudoterm string.
    *
    * @param strong the strongly typed pseudoterm string
    * @return the index of the parent operator
    */

    static int parentOperator(String strong) { // DEBUG THIS TOMORROW

      ArrayList<Integer> ops = indexOperators(strong);
      int parent = ops.get(0);

      for (int i = 1; i < ops.size(); i++) {
         if (depth(strong, ops.get(i)) < depth(strong, parent)) {
            parent = ops.get(i);
         }
      }

      return parent;

   }

   /** Calculates the depth of a character (based on parentheses).
    *
    * @param s the string
    * @param index the index of the character
    * @return the depth of the char
    */

    private static int depth(String s, int index) {
      String sub = s.substring(0, index);
      int left = 0;
      int right = 0;

      for (int i = 0; i < sub.length(); i++) {
         if (sub.charAt(i) == '(') {
            left++;
         }
         if (sub.charAt(i) == ')') {
            right++;
         }
      }

      return left - right;
   }

    static String matchParen(String strong, int index) {

       int depth;
       String sub;
       String matched = "(";
        if (strong.charAt(index) == '(') {
            sub = strong.substring(index);
            depth = Indexer.depth(sub, 0);
            for (int i = 1; i < sub.length(); i++) {
                if (Indexer.depth(sub, i) > depth) {
                    matched += sub.charAt(i);
                } else {
                    return matched;
                }
            }
        } else if (strong.charAt(index) == ')') {
            sub = strong.substring(0,index + 1);
            depth = Indexer.depth(sub, sub.length() - 1);
            for (int i = 1; i < sub.length(); i++) {
                if (Indexer.depth(sub, i) >= depth) {
                    matched += sub.charAt(i);
                } else {
                    matched = "(";
                }
            }

       }

       return matched;
   }

}
