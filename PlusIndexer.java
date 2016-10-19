import java.util.ArrayList;

public class PlusIndexer {

   // Constructors

   public PlusIndexer() {}

   // Methods

   private static ArrayList<Integer> indexPlusses(Explicit expl) {

      ArrayList<Integer> indexOfPlusses = new ArrayList<Integer>();

      for (int i = 0; i < expl.toString().length(); i++) {
         if (expl.toString().charAt(i) == '+') {
            indexOfPlusses.add(i);
         }
      }

      return indexOfPlusses;
   }

   private static ArrayList<Integer> indexParentPlusses(Explicit expl) {

      ArrayList<Integer> plusses = indexPlusses(expl);
      ArrayList<Integer> indexOfParentPlusses = new ArrayList<Integer>();

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

   public static String[] getTerms(Explicit expl) {

      ArrayList<Integer> parPlus = indexParentPlusses(expl);
      final ArrayList<Integer> PAR_PLUS = parPlus;

      String chop = expl.toString();

      String[] terms = new String[parPlus.size() + 1];
      for (int i = 0; i < parPlus.size(); i++) {
         terms[i] = chop.substring(0, parPlus.get(i));
         System.out.println(terms[i]);
         chop = chop.substring(parPlus.get(i) + 1);
         if (parPlus.size() - i != 1) {
            parPlus.set(i + 1, parPlus.get(i + 1) - PAR_PLUS.get(i) - 1);
         } else {
            terms[i + 1] = chop;
            System.out.println(terms[i + 1]);
         }
      }

      return terms;
   }
}
