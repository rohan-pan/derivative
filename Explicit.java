/** Class responsible for abstraction of the user input string for processing.
 *  @author Rohan
 */

public class Explicit{

   private String value;

   // Constructors

   /** Default
    *
    */

   public Explicit() {}

   // Methods

   /** Method which generates an explicit object.
    * @param s the user input string
    * @return the explicit object
    */

    static Explicit explicate(String s) {

      Explicit e = new Explicit();

      e.value = "";

        if (s.charAt(0) != '+') {
            e.value += s.charAt(0);
        }

      for (int i = 1; i < s.length(); i++) {
         if (s.charAt(i) == '-') {
            e.value += "+-";
         } else {
            e.value += s.charAt(i);
         }
      }
      return e;
   }

   /** Method which converts an explicit back to a String for manipulation.
    *
    * @return the explicit string
    */

   @Override
   public String toString() {
      return this.value;
   }
}
