@todo: More succint/exact definition of Property/StreetProperty/RailwayProperty/UtilityProperty

@todo: StreetProperty should reference the functional requirement (1*9.?) that you must own the entire street to begin building houses/hotels.

@todo: Chance, Luxury tax, Income tax, GO, Jail/Just Visiting, Free Parking, and Go To Jail ActionSpaces descriptions.

@todo: Define 'Deed' for a Property

@todo: Define 'Rent'

Requirements Document
=====================

##Definitions
	* Board: Analogous to the board in a Monopoly game. Consists of 40 spaces.
	* Space: One of 40 sections surrounding the board that can consist of either a Property or an ActionSpace.
	* Property: A space one player can have ownership of by purchasing that property's deed. For players who do not own the property, a rent is paid to the owner each time any of these players lands on a Property. There are 28 properties on the board (22 streets, 4 railway stations, and 2 utilities).
	** StreetProperty: A property that the player can improve by adding Houses and/or Hotels.
	** RailwayProperty: A property whose rent is calculated based on the total number of railway properties owned.
	** UtilityProperty: A property whose rent is calculated by a combination of owned utilities and a roll of the dice.
	* ActionSpace: A space that permits or forces the player to perform an action
	** "Chance" ActionSpace: (Three total)
	** "CommunityChest" ActionSpace: (Three total)
	** "LuxuryTax" ActionSpace: (one total)
	** "IncomeTax" ActionSpace: (one total)
	** "GO" ActionSpace: The ActionSpace that the players start on. Once the game has begun, anytime this space is passed or landed upon by a player, that player shall receive monetary reiumbursement. (one)
	** "Jail/Just Visiting" ActionSpace: The ActionSpace that is reserved for holding a player in place until release requirements are met. (one total)
	** "Free Parking" ActionSpace: The ActionSpace that gives players the money placed in the center of the table when landed on. (one total)
	** "Go to Jail" ActionSpace: Instantly moves the player to jail without collecting the money usually accrued when passing by the "Go" ActionSpace. (one total)

##User Interface Requirments
All user interface requirements start with "UIR".
	* UIR-1: The program shall present the user with a graphical representation of a Monopoly board.  
	* UIR-2: The program shall provide a menu of options when it is the user's turn.
	* UIR-3: The program shall provide a menu of options when the user is offered a bid on a property.
	* UIR-4: The program shall display the positions of the players graphically on the board.
	* UIR-5: When a player lands on a ActionSpace, the program shall present the user with information
		relevant to the ActionSpace.
	* UIR-6: The program shall have a menubar that displays proper controls.
	* UIR-7: The program shall have a graphical method for manipulating key game parameters before a game
		is started.
	* UIR-8: The program shall have a method for entering a debugging mode where many of the game's
		parameters can be graphically manipulated during run time. 
   
##Functional Requirements
All functional Requirements start with "FUN".
	* FUN-1: The game shall enforce the basic rules of Monopoly, which are as follows:
		1. Each player shall recieve a set amount of money at the start of the game based on the difficulty level specified.
		2. Each player shall recieve a set amount of money each time that they land on the "Go" ActionSpace.
      3. Each player shall roll 2 6-sided dice to move their playing piece around the board when it is their turn.
      4. Each player shall only roll for movement once per turn.
      5. Each player shall have the option of purchasing property when landed on, unless it is already owned.
      6. Each player shall pay the owner of the property they land on the rental prices specified for the property landed on.
      7. Each player shall have the ability to improve their property if a player owns all ActionSpaces in a set for a certain fee.
      8. Each player shall not inmprove their properties beyond level 5.
      9. Each property shall have a level from 0 - 5
         a. Level 0 shall be represented by having no houses/hotels on the property.
         b. Level 1 - 4 shall be represented by having a house on each property for each level it has achieved.
         c. Level 5 shall be represented by having a hotel on the property.
      10. Each player shall purchase improvements for their property evenly. 
            i.e. If a player owns all three properties in a set, to improve to level 1, level 1 must be achieved
               for all properties at once.
      11. Each player shall only be able to purchase one level at a time per property group.

##Non-Functional Requirements
All Non-Functional Requirements start with "NFR".
   * NFR-1: 
   
##Domain Requirements
All Domain Requirements start with "DMR".
   * DMR-1: 
   