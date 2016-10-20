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
      final ArrayList<Integer> PAR_PLUS = parPlus;

      String chop = expl.toString();

      Pseudoterm[] pterms = new Pseudoterm[parPlus.size() + 1];
      for (int i = 0; i < parPlus.size(); i++) {
         pterms[i] = Pseudoterm.setPseudoterm(chop.substring(0, parPlus.get(i)));
         chop = chop.substring(parPlus.get(i) + 1);
         if (parPlus.size() - i != 1) {
            parPlus.set(i + 1, parPlus.get(i + 1) - PAR_PLUS.get(i) - 1);
         } else {
            pterms[i + 1] = Pseudoterm.setPseudoterm(chop);
         }
      }

      return pterms;
   }

   /** Method which takes a pseudoterm string and finds all operators.
    *
    * @param pseudo the pseudoterm string
    * @return an ArrayList of all the indices of the operators
    */

   public ArrayList<Integer> indexOperators(String pseudo) {
      return null;
   }

}
