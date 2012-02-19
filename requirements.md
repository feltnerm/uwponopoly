@todo: More succint/exact definition of Property/StreetProperty/RailwayProperty/UtilityProperty

@todo: StreetProperty should reference the functional requirement (1*9.?) that you must own the entire street to begin building houses/hotels.

@todo: Chance, Luxury tax, Income tax, GO, Jail/Just Visiting, Free Parking, and Go To Jail ActionSpaces descriptions.

@todo: Define 'Deed' for a Property

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
* ActionSpace: A space that permits or forces the player to perform an action  
	+ a. "Chance" ActionSpace: (Three total)  
	+ b. "CommunityChest" ActionSpace: (Three total)  
	+ c. "LuxuryTax" ActionSpace: (one total)  
	+ d. "IncomeTax" ActionSpace: (one total)  
	+ e. "GO" ActionSpace: The ActionSpace that the players start on.
    Once the game has begun, anytime this space is passed or landed upon by a player, 
    that player shall receive monetary reimbursement. (one)  
	+ f. "Jail/Just Visiting" ActionSpace: The ActionSpace that is reserved for holding a player in place until release requirements are met. (one total)  
	+ g. "Free Parking" ActionSpace: The ActionSpace that gives players the money placed in the center of the table when landed on. (one total)  
	+ h. "Go to Jail" ActionSpace: Instantly moves the player to jail without collecting the money usually accrued when passing by the "Go" ActionSpace. (one total)  

##Input Output Requirements
All user interface requirements start with "IO".  

* IO-1: The program shall present the user with a graphical representation of a Monopoly board.  
* IO-2: The program shall provide a menu of options when it is the user's turn.  
* IO-3: The program shall provide a menu of options when the user is offered a bid on a property.  
* IO-4: The program shall display the positions of the players graphically on the board.  
* IO-5: When a player lands on a ActionSpace, the program shall present the user with information
		relevant to the ActionSpace.  
* IO-6: The program shall have a menubar that displays proper controls.  
* IO-7: The program shall have a graphical method for manipulating key game parameters before a game
		is started.  
* IO-8: The program shall have a method for entering a debugging mode where many of the game's
		parameters can be graphically manipulated during run time.  
   
##Game Play Requirements
All game play requirements start with "G".

* G-1: The game shall enforce the basic rules of Monopoly, which are as follows:  
* G-1. Each player shall receive a set amount of money at the start of the game based on the difficulty level specified.  
* G-2. Each player shall receive a set amount of money each time that they land on the "Go" ActionSpace.  
* G-3. Each player shall roll 2 6-sided dice to move their playing piece around the board when it is their turn.  
* G-4. Each player shall only roll for movement once per turn.  
* G-5. Each player shall have the option of purchasing property when landed on, unless it is already owned.  
* G-6. Each player shall pay the owner of the property they land on the rental prices specified for the property landed on.   
* G-7. Each player shall have the ability to improve their property if a player owns all ActionSpaces in a set for a certain fee.  
* G-8. Each player shall not improve their properties beyond level 5.  
* G-9. Each property shall have a level from 0 - 5  
    + a. Level 0 shall be represented by having no houses/hotels on the property.  
    + b. Level 1 - 4 shall be represented by having a house on each property for each level it has achieved.  
    + c. Level 5 shall be represented by having a hotel on the property.  
* G-10. Each player shall purchase improvements for their property evenly.
           i.e. If a player owns all three properties in a set, to improve to level 1, level 1 must be achieved
           for all properties at once.  
* G-11. Each player shall only be able to purchase one level at a time per property group.  

##Requirements

* R-1:  
