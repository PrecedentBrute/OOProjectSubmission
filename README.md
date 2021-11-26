# OOProjectSubmission

## Classes - 

### App - Contains the main method and sets up the game, by starting the GUI thread, and starting the game.

### CitySquare - Extends PropertySquare, implements housing logic on top of it. Overrides the getRent() method, adds logic to increase rent based on houses, and owning a color group.

### CommunityCard - Interface for community chest cards, with a doAction(Player p) method.

### CommunityChest - Static array of community cards. Used by the community chest square.

### CoverMortgageCard - Implements CommunityCard. Allows the bearer to cover one mortgage(oldest owned property if multiple mortgages).

### Detention - Class with a static ArrayList that acts like the Jail in this version of the game. GameState class uses this to jail players.

### Dice - To generate random number between 1-6, having roll and getValue methods.

### GameBoard - Contains Square[] board, an array of size 24 housing all the squares in the game. All squares initialised in the constructor.

### GameIO - Handles input and output wrt console as well as GUI. Takes input from the second (GUI) thread or Scanner from the main thread depending on the user's choice. Outputs the log to both GUI and the console.

### GameState - Handles the main logic of passing turns, giving users choices, and ending the game when a player goes bankrupt. Has the startGame() method.

### LoanInstallmentCard - Implements the CommunityCard interface. User has to pay 200.

### MonopolyFrame - Implements runnable. Houses the code for the GUI, and coordinates the input from the GUI through the SyncInput class.

### Piece - Extends JPanel, represents a player on the board. (Buggy, not working for now).

### Player - Contains all the player information - name, money, inDetention, isBankrupt, squareIndexOnBoard, and ownedSquares in an arrayList. Provides functions to manage the state of the player.

### PropertySquare - Extends the abstract class square. Adds the properties of owner, price, rent, moortgage and functions to help with these operations.

### ScholarshipCard - Implements the CommunityCard interface. Person receives 500.

### SpecialSquare - Provides a concrete implementation of the abstract class square, which is then extended anonymously to create squares with distinct landedOn methods. Used in the GameBoard class.

### Square - Abstract class. Has an abstract method landedOn(Player p) which all children must implement.

### StationSquare - Extends the PropertySquare class, and adds the logic for the rent to increase based on number of stations owned. Overrides the getRent() method.

### SyncInput - Houses syncronised methods for producer/consumer style communication between input at the GUI and the input asked by any function from the main thread.

