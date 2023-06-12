- Changed User and AI types to Player : Single-responsibility & allows for more extension in the future
- Removed Getters & non-Player interface methods from User and AI : allows the User and AI to follow the Player
interface.
- Changed User and AI constructors : Injection dependency so that the same board can be passed in
and mutated.
- Changed System.out.println() to PrintStream : Allows the method to be tested easily
- Added shipUpdate and shotCount methods to ShipController : allows the User and AI to follow
single-responsibility design
- Swapped height and width order : Changes coordinates from (y, x) to (x, y)