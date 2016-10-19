public class Explicit{

   String value;

   // Constructors

   public Explicit() {}

   // Methods

   public static Explicit explicate(String s) {

      Explicit e = new Explicit();

      e.value = s;

      for (int i = 0; i < s.length(); i++) {
         if (s.charAt(i) == '-') {
            e.value += "+-";
         } else {
            e.value += s.charAt(i);
         }
      }
      return e;
   }

   @Override
   public String toString() {
      return this.value;
   }
}
