@todo: More succint/exact definition of Property/StreetProperty/RailwayProperty/UtilityProperty

@todo: StreetProperty should reference the functional requirement (1*9.?) that you must own the entire street to begin building houses/hotels.

@todo: Chance, Luxury tax, Income tax, GO, Jail/Just Visiting, Free Parking, and Go To Jail ActionSpaces descriptions.

Requirements Document
=====================

##Definitions
* Board: Analogous to the board in a Monopoly game. Consists of 40 spaces.  
* Space: One of 40 sections surrounding the board that can consist of either a Property or an ActionSpace.  
* Property: A space one player can have ownership of by purchasing that property's deed. For players who do not own the property, a rent is paid to the owner each time any of these players lands on a Property. There are 28 properties on the board (22 streets, 4 railway stations, and 2 utilities).  
    + a. StreetProperty: A property that the player can improve by adding Houses and/or Hotels.  
    + b. RailwayProperty: A property whose rent is calculated based on the total number of railway properties owned.  
    + c. UtilityProperty: A property whose rent is calculated by a combination of owned utilities and a roll of the dice.  
* Rent: A sum of game money payed from one player who lands on a property to another player who owns the deed to that property.  
* Deed: An object that signifies ownership of a property. It specifies the rent associated with a property.
* Bank: A game entity that contains unlimited amounts of game money. No player controls it.
* ActionSpace: A space that permits or forces the player to perform an action  
	+ a. "Chance" ActionSpace: An ActionSpace where a card from a pool of cards is randomly selected. 
         These cards shall contain an action that is executed by the system. (Three total)  
	+ b. "CommunityChest" ActionSpace: (Three total)  
	+ c. "LuxuryTax" ActionSpace: (one total)  
	+ d. "IncomeTax" ActionSpace: (one total)  
	+ e. "GO" ActionSpace: The ActionSpace that the players start on. See G-14. (one total)  
	+ f. "Free Parking" ActionSpace: The ActionSpace that gives players the money reserved for Free Parking. See G-15. (one total)  
	+ g. "Jail/Just Visiting" ActionSpace: The ActionSpace that is reserved for holding a player in place until release requirements are met. See G-16. (one total)  
	+ h. "Go to Jail" ActionSpace: Sends the player to jail. See G-17. (one total)  

##Input Output Requirements
All user interface requirements start with "IO".  

* IO-1: The program shall present the user with a graphical representation of a Monopoly board.  
* IO-2: The program shall display the positions of the players graphically on the board.  
* IO-3: When a player lands on a ActionSpace, the program shall present the user with information
		relevant to the ActionSpace.  
* IO-4: The program shall have a graphical method for manipulating key game parameters before a game
		is started.  
   
##Game Play Requirements
All game play requirements start with "G".

* G-1. Each player shall receive a set amount of money at the start of the game.  
* G-2. Each player shall receive a set amount of money each time that they land on the "Go" ActionSpace.  
* G-3. Each player shall roll two 6-sided dice to move their playing piece around the board when it is their turn.  
* G-4. Each player shall only roll for movement once per turn.  
* G-5. Each player shall have the option of purchasing property when landed on, unless it is already owned.  
* G-6. Each player shall pay the owner of the property they land on the rental prices specified for the property landed on.   
* G-7. Each player shall have the ability to improve, for a set sum of money, their property if a player owns all Properties in a set.
       Improving a property shall increase the value of the rent, and there shall be multiple levels of improvement available.
* G-10. Each player shall purchase improvements for their property evenly.
* G-11. Each player shall only be able to purchase one improvement level at a time per property group.  
* G-12. When a player lands on a property, the system shall automatically charge the rent specified on the deed
        of the property to the player's account and give it to the owner of the deed.
    + a. In the event that a player lands on a property that the player holds the deed for, no rent shall be assessed.
    + b. In the event that no player holds the deed to the property, no rent shall be assessed.
* G-13. The deed for each property shall specify a price that must be paid to the bank by the player for the player to own a deed.
* G-14. Once the game has begun, the bank shall pay to a player a fixed sum of money every time that a player crosses the "GO" ActionSpace.
* G-15. The "Free Parking" ActionSpace:  
    + a. shall hold a sum of money. This money may be represented graphically by being in the center of the board.
    + b. shall pay the sum of money to the first player to land on the "Free Parking" ActionSpace.
    + c. shall have the sum of money be user-configurable.
    + d. shall give the user an option to make the sum of money be paid to a player each time that player lands on "Free Parking".
         In this scenario, the "Free Parking" space would have an unlimited supply of game money.
* G-16. The "Jail/Just Visiting" ActionSpace:  
    + a. shall do nothing when a player lands on it. In this situation the player would be considered "Just Visiting".  
    + b. shall hold a player if the player is in jail.  
    + c. shall only release a player if:  
        - i. The player pays the fine to the bank.
        - ii. The player rolls doubles.
        - iii. After a set number of unsuccessful rolls, the player must pay a set amount of game money to the bank and be released.
        - iv. The player uses a "Get Out of Jail Free" Chance or CommunityChest card.
* G-17. A player may be sent to jail if:
    + a. the player lands on the "Go to Jail" ActionSpace. The player shall not collect the game money usually associated with passing the "GO" ActionSpace.
    + b. a "Chance" or "CommunityChest" card instructs the user to go to jail.

##Other Requirements
None.
