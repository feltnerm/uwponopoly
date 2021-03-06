package gui;

/**
 * The Player class describes one player in a game of UWPonopoly
 * Copyright Aaron Decker 2012
 */

import java.awt.Color;
//import java.awt.Font;
//import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.util.Random; //**ALWAYS REQUIRED**

import player.Player;

public class GUIPlayer {
	public static int TOKEN_SIZE = 10;
	private static int TOKEN_FONT_SIZE = 10;

	private GameBuffer token;
	private Color token_color;
	private GameBuffer animating_token;
	private boolean is_moving; // true if the player's token is currently in
								// animated transit
								// this was needed so that the token could flash
								// red etc. during
								// animation
	private Player player;

	/*public GUIPlayer() {
       super( 
	}*/

	public GUIPlayer(Player p) {
		this.player = p;
		this.token_color = generateColor(this.player.getPlayerNumber());
		this.token = generateToken(this.token_color);
		this.animating_token = generateToken(this.token_color.brighter());		
	}

	public Color getColor()
	{
		return this.token_color;
	}

	public Player getPlayer(){
		return this.player;
	}

	public GameBuffer getToken()
	{
		if(!this.is_moving)
			return token;
		return animating_token;
	}

	private GameBuffer generateToken(Color c)
	{
		GameBuffer tokenBuffer = new GameBuffer(TOKEN_SIZE, TOKEN_SIZE, 
			Color.WHITE);
		tokenBuffer.clear();
		Graphics g = tokenBuffer.getGraphics();
		g.setColor(Color.BLACK);
		g.drawOval(0,0,TOKEN_SIZE,TOKEN_SIZE);
		g.setColor(c);
		g.fillOval(0,0,TOKEN_SIZE,TOKEN_SIZE);
		return tokenBuffer;
	}

	/**
	 * This procedure will genrate a player's color. It uses the player number
	 * to attempt to differentiate players more.
	 */
	private Color generateColor(int player_number)
	{
		Random rg = new Random();
		float r;
		float g;
		float b;
		if (player_number == 0)
		{
			// More red
			r = rg.nextFloat();
			g = rg.nextFloat();
			b = rg.nextFloat();
		} else if (player_number > 0 && player_number % 2 == 0)
		{
			// More blue
			r = rg.nextFloat();
			g = rg.nextFloat();
			b = rg.nextFloat();
		} else {
			// More green
			r = rg.nextFloat();
			g = rg.nextFloat();
			b = rg.nextFloat();
		}
		return new Color(r, g, b);
	}

	/**
	public GUIPlayer(char token_char) {
        super( Character.toString(token_char) );
		this.token_char = token_char;
		token = generateTokenFromChar(token_char, Color.BLACK);
		animating_token = generateTokenFromChar(token_char, Color.RED);
	}
	*/

	/*
	 * I don't think that this method is appropriate -- Aaron public void
	 * advance( int num_spaces ) { // can't implement this until the Config is
	 * available }
	 */

	public int getAmountOfMoney() {
		return this.player.getMoney();
	}

	public void creditMoney(int amount) {
		this.player.addMoney(amount);
	}

	/**
	 * If an amount of money is charged which causes the player to go bankrupt,
	 * the full amount is not paid. Instead, all of the remaining money is paid.
	 */
	public int debitMoney(int amount) {
		if (this.player.getMoney() - amount > 0)
			return this.player.getMoney();
		else
			return this.player.getMoney() - amount;
	}

	/**
	private GameBuffer generateTokenFromChar(char token_char, Color color) {
		GameBuffer gbuffer = new GameBuffer(TOKEN_SIZE, TOKEN_SIZE, Color.WHITE);
		gbuffer.clear();
		Graphics g = gbuffer.getGraphics();
		g.setColor(color);

		// generate font
		Font font = new Font("Helvetica", Font.PLAIN, TOKEN_FONT_SIZE);
		FontMetrics fm = g.getFontMetrics(font);
		java.awt.geom.Rectangle2D rect = fm.getStringBounds(
				Character.toString(token_char), g);
		g.setFont(font);

		g.drawString(Character.toString(token_char), 0,
				(int) (3 * rect.getHeight() / 4));
		return gbuffer;
	}
	*/

	public int getPosition() {
		return this.player.getPosition();
	}

	public void setPosition(int new_position) {
		this.player.setPosition(new_position);
	}

	public boolean isMoving() {
		return is_moving;
	}

	public void setIsMoving(boolean moving) {
		is_moving = moving;
	}

}
