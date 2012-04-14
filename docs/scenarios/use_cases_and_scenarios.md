Meetings: 
    - Tuesday Mar 6, Aaron and Jordan

## Use cases

#A. Move Token

1. Typical Dice roll during Pass'n'Play mode.
    1. The player has set up and started the game against several friends in "Pass'n'Play" mode.
    2. The player watches the other players take their turns at the computer.
    3. The system displays a message telling the player that it is the player's turn.
    4. Gleefully snatching the keyboard from the other players, the player is presented with a menu of options of actions involving property and with a dice control.
    5. The player decides to roll the dice. Each player is typically alloted one roll of the dice per turn.
    6. The dice control shows a "three" and a "one". The player's token is advanced four spaces by the system.
    7. The player's token lands on a property that is owned by another player.
    8. The system automatically calculates the rent for the property and transfers that amount from the player's money supply to the owner's.
    9. The player is presented with the same menu of options; however, since the player has already rolled, the dice control is deactivated.
    10. The player tells the system that the player is done with the player's turn and passes the keyboard to the next player prompted by the system.

2. Current Player rolls double.
    1. The player has set up and started the game against several friends in "Pass'n'Play" mode, and it is now the player's turn.
    4. The player is presented with a menu of options of actions involving property and with a dice control.
    5. The player uses the dice control to roll the dice.
    6. The dice control shows the player what the result of the psuedo-random roll is.
    7. In this case, the dice roll happened to be two "three's".
    8. The players token move forward six spaces on the board.
    9. After the token is moved to an unowned property. The user can purchase the property, modify existing properties, or, since the roll was a double, roll again.
    10. The player decides to roll again by using the dice control.
    11. The dice control indicates a "four" and a "five". If the player had rolled a double, the player would still not be able to take another turn.
    12. The player's token moves forward nine spaces.
    13. The player's token lands on another unowned property.
    14. The player now has the choice to purchase the property or modify existing properties.
    15. Since the player has already taken the turn, the dice control is shown to be deactivated.
    16. The player decides that the player's turn is over and uses a control to mark the turn as over.
    17. The system displays a message of which player's turn it is, and the player grudgingly relinquishes the keyboard.

3. Current Player passes the "Go" ActionSpace.
    1. The player has set up and started the game with the default "Passing Go" parameter set to $200, and the game is currently in progress.
    2. It is the beginning of the player's turn and the player's token is three spaces away from the "GO" ActionSpace.
    3. The player uses the dice control to roll the dice.
    4. The dice control shows a "five" and a "two".
    5. The player's token is advanced by seven spaces.
    6. Since the token passed the "Go" ActionSpace, the player is credited with $200 to the player's game money.
    7. The token lands on an unbought property.
    8. The player tells the system that the player is done with the player's turn.

4. Current Player lands on "Go to Jail" ActionSpace.
    1. The player has set up and started the game and the player's token is two spaces away from the "Go to Jail" ActionSpace.
    2. It is the player's turn and the player uses the dice control to roll.
    3. The dice control shows two "ones" and the player's token is advanced two spaces.
    4. The player's token lands on the "Go to Jail" ActionSpace and is subsequently moved to the "Jail" space. The player's token does not pass the "Go" ActionSpace.
    5. The dice control is deactivated.
    6. The player tells the system that the player's move is over.

5. Current Player is in jail and rolls doubles to get out.
   1. It is the beginning of the player's turn, and the player's token is sitting in jail.
   2. The player uses the dice control to roll the dice.
   3. The dice control shows two "fours".
   4. The player's token advances 8 spaces beyond jail.
   5. The player tells the system that the player is done with the player's turn.

6. Current Player is in jail and rolls unsuccessfully to get out.
   1. It is the beginning of the player's turn, and the player's token is sitting in jail.
   2. The player uses the dice control to roll the dice.
   3. The dice control shows a "four" and a "two".
   4. The player tells the system that the player is done with the player's turn.

7. Current Player lands on "Free Parking" and collects the money.
   1. It is the beginning of the player's turn, the player's token is sitting 7 spaces from the "Free Parking" space, and there is $350 in "Free Parking".
   2. The player uses the dice control to roll the dice.
   3. The dice control shows a "four" and a "three".
   4. The player's token advances 7 spaces to Free Parking.
   5. The player's bank gets increased by $350.
   6. The Free Parking is now empty and has a balance of $350.
   7. The player tells the system that the player is done with the player's turn.

8. Current Player is in jail and rolls unsuccessfully to get out three times in a row.
   1. The player has configured the game to force payment after 3 failed attempts to get out of jail.
   2. It is the beginning of the player's turn, and the player is sitting in jail.
   3. Since the player has failed three times to get out of jail, the player is charged a set sum of money, which is automatically deducted from the player's bank account.
   4. The player's token is then removed from jail and placed in the "Just Visiting" portion.
   5. The player tells the system that the player is done with the player's turn.

#B. Manage Property

1. The player wants to improve (i.e., build a hotel or house) on a property.
    1. The game has been set up and started.
    2. Current Player owns the deeds to each property in a set of same-colored properties.
    3. The player selects to improve the property, and based on the cost and amount of money in the player's bank, builds a new improvement on that property. 

#C. Start Game

1. Current Player starts game with default settings.
    1. The game loads the default settings and the user and computer are given default tokens. 
    2. The player selects the number and type of players.
    3. The game begins with the user's and computer's tokens on the 'Start' ActionSpace and the dice control is given to the user.

2. Current Player starts game with custom settings. 
    1. The game loads a form the user can use to manipulate the variables and settings of the game.
    2. The player selects the number and type of players.
    3. The game begins with the user's and computer's tokens on the 'Start' ActionSpace and the dice control is given to the user.

#D. Use Special Card

1. Current Player uses "Get Out of Jail Free" card.
   1. It is the beginning of the player's turn, the player's token is sitting in jail, and the player has one "Get Out Of Jail Free" card.
   2. The player decides to use their get out of jail free card by selecting the special card control.
   3. The player's token is removed from jail and is placed in the just visiting section of the jail space.
   4. The player uses the dice control to roll the dice.
   5. The dice control shows a "five" and a "1".
   6. The player's token is advanced 6 spaces.
   7. The player tells the system that the player is done with the player's turn.
