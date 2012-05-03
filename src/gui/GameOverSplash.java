package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;
import player.Player;
import game.Game;

/**
 *
 * @author trulenjo
 */
public class GameOverSplash extends JFrame
{
   Game game;
   JButton okbutton;
   Container pane;
   Insets insets;
   JLabel titleName;
   JLabel titleTurns;
   JLabel titleMoney;
   JLabel names[];
   JLabel numTurns[];
   JLabel endingMoney[];
   JButton closeButton;
   int maxNameLength;
   int maxTurnLength;
   int maxMoneyLength;

   public GameOverSplash(LinkedList<Player> players, Game currentGame)
   {
      game = currentGame;
      setTitle("Game Over!");
      pane = getContentPane();
      insets = pane.getInsets();
      pane.setLayout(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      titleName = new JLabel("Player Name");
      titleTurns = new JLabel("Turns Played");
      titleMoney = new JLabel("Ending Bank");
      names = new JLabel[players.size()];
      numTurns = new JLabel[players.size()];
      endingMoney = new JLabel[players.size()];
      closeButton = new JButton("Close");

      this.initLabels(players);
      this.populateFrame();

      setVisible(true);

      closeButton.addActionListener(new closeButtonAction());
   }

   private void initLabels(LinkedList<Player> players)
   {
      maxNameLength = titleName.getPreferredSize().width;
      maxTurnLength = titleTurns.getPreferredSize().width;
      maxMoneyLength = titleMoney.getPreferredSize().width;

      for(int i = 0; i < names.length; i++)
      {
         Player temp = players.poll();

         this.names[i] = new JLabel(temp.getName());
         this.numTurns[i] = new JLabel(temp.getLastTurn() + "");
         this.endingMoney[i] = new JLabel(temp.getMoney() + "");

         if(this.names[i].getPreferredSize().width > maxNameLength)
            maxNameLength = this.names[i].getPreferredSize().width;

         if(this.numTurns[i].getPreferredSize().width > maxTurnLength)
            maxTurnLength = this.numTurns[i].getPreferredSize().width;

         if(this.endingMoney[i].getPreferredSize().width > maxMoneyLength)
            maxMoneyLength = this.endingMoney[i].getPreferredSize().width;
      }
   }

   private void populateFrame()
   {
      this.pane.add(titleName);
      titleName.setBounds(insets.left + 5, insets.top + 5,
              maxNameLength,
              titleName.getPreferredSize().height);

      this.pane.add(titleTurns);
      titleTurns.setBounds(titleName.getX() + titleName.getWidth() + 20,
              insets.top + 5,
              maxTurnLength,
              titleTurns.getPreferredSize().height);

      this.pane.add(titleMoney);
      titleMoney.setBounds(titleTurns.getX() + titleTurns.getWidth() + 20,
              insets.top + 5,
              maxMoneyLength,
              titleMoney.getPreferredSize().height);

      for(int i = 0; i < names.length; i++)
      {
         JLabel temp = ( i == 0 ? titleName : names[i - 1] );
         
         this.pane.add(names[i]);
         names[i].setBounds(insets.left + 5,
              temp.getY() + temp.getHeight() + 5,
              maxNameLength,
              names[i].getPreferredSize().height);

         this.pane.add(numTurns[i]);
         numTurns[i].setBounds(names[i].getX() + names[i].getWidth() + 20,
              temp.getY() + temp.getHeight() + 5,
              maxTurnLength,
              numTurns[i].getPreferredSize().height);

         this.pane.add(endingMoney[i]);
         endingMoney[i].setBounds(numTurns[i].getX() + numTurns[i].getWidth() + 20,
              temp.getY() + temp.getHeight() + 5,
              maxMoneyLength,
              endingMoney[i].getPreferredSize().height);
      }

      int index = names.length - 1;

      this.pane.add(closeButton);
      closeButton.setBounds( names[index].getX() + names[index].getWidth() + 20,
              endingMoney[index].getY() + endingMoney[index].getHeight() + 5,
              maxTurnLength,
              closeButton.getPreferredSize().height);

      int windowWidth = endingMoney[index].getX() + endingMoney[index].getWidth() + 20;
      int windowHeight = closeButton.getY() + closeButton.getHeight() + 5;

      setSize(windowWidth, windowHeight);
   }

   public class closeButtonAction implements ActionListener
   {
      public void actionPerformed( ActionEvent e )
      {
         game.endGame();
      }
   }
}