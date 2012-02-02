# Design Whiteboard

This file is an area to discuss design ideas and issues.
To comment, just add a paragraph and sign your name after it.
-Aaron

## House rules

An idea that Mark had was to have configurable "house rules"
for the game.
House rules are rules that a user can add to change the gameplay.
I can think of two ways to do this:

1.  Create a dialog box with a set of pre-defined parameters that the 
	user can modify.
	This method would probably require a lower-level of programming expertise
	to pull off, but would limit the user to only the house rules that we gave
	them access to.

2.  Create a rules engine.
	This engine would probably read in and use rules files that would power
	even the normal gameplay.
	This could potentially take a lot more time and expertise to pull off.

The second option, the rules engine, seems to be the option that would need
mulled over more.
If indeed we did create a rules engine, would we want to do something like
creating python bindings to the relevant objects?
Or would we want to create a markup to interpret into already written logic?
I will investigate the viability of the python binding option.
-Aaron

----
The rules engine seems like a bit much...
...although we could just make _everything_ configurable via XML 
(or another, more sane document structure [JSON anyone?!]
, I believe Civ. V does this which allows modders to do a lot with the game, 
but really, who's going to change the Monopoly rules *that* much?

All we would need is a 'Preferences' class which would define all the default variables needed by the game. 
From there, we would simply need a dialog box to make it all configurable.
<pre>
- Preferences ---------------------------
|                                       |
|                                       |
| Starting Cash: $[    ]                |
| 'Go' Cash: $[200]                     |
| Free Parking: [x]                     |
| Random Seed: [   ] ( Generate )       |
|                                       |
|                                       |
-----------------------------------------
</pre>
etc. -- **mark**

--