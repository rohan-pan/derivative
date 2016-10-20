/** Term interfaces with this (???)
 * @author Rohan
 */

public interface Differentiable {

   /** Method to calculate derivative of a term.
    *
    * @return another differentiable
    */

   Differentiable derivative();

   /** converts a differentiable to a String
    *
    * @return differentiable string
    */

   @Override
   String toString();
}
