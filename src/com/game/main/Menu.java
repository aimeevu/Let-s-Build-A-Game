//Menu system for the game

package com.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.game.main.Game.STATE;

public class Menu extends MouseAdapter{
		
		private Game game;
		private Handler handler;
		private HUD hud;
		private Random r = new Random();
	
		public Menu(Game game, Handler handler, HUD hud){
				this.game = game;
				this.handler = handler;
				this.hud = hud;
		}
	
		public void mousePressed(MouseEvent e){
				int mx = e.getX();
				int my = e.getY();
				
				if(Game.gameState == STATE.Menu){
						//play button
						if(mouseOver(mx, my, 210, 150, 200, 64)){
								Game.gameState = STATE.Game;
								handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
								handler.clearEnemy();
								handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
								
								AudioPlayer.getSound("menu_sound").play();
						}
						
						//help button
						if(mouseOver(mx, my, 210, 250, 200, 64)){
								Game.gameState = STATE.Help;
								
								AudioPlayer.getSound("menu_sound").play();
						}
						
						//quit button
						if(mouseOver(mx, my, 210, 350, 200, 64)){
								System.exit(1);
						}
				}
				//back button for help
				if(Game.gameState == STATE.Help){
						if(mouseOver(mx, my, 210, 350, 200, 64)){
								Game.gameState = STATE.Menu;
								AudioPlayer.getSound("menu_sound").play();
								return;
						}
				}
				
				//back button for try again
				if(Game.gameState == STATE.End){
						if(mouseOver(mx, my, 210, 350, 200, 64)){
								Game.gameState = STATE.Game;
								hud.setLevel(1);
								hud.setScore(0);
								handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
								handler.clearEnemy();
								handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
								AudioPlayer.getSound("menu_sound").play();
						}
				}
				
		}
		
		public void mouseReleased(MouseEvent e){
				
		}
		//mx and my means mouse x, mouse y
		private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
				//If mouse x is > x and if mouse x is < x plus the width
				if(mx > x && mx < x + width){
						//If mouse y is > y and if mouse y is < y plus the height
						if(my > y && my < y + height){
								return true;
								/*By returning true, it means that the mouse click was within
								 * the x and y boundaries*/
						}else
							return false;
				}else
					return false;
		}
	
		public void tick() {
				
		}
		
		public void render(Graphics g){
				if(Game.gameState == STATE.Menu){
						Font fnt = new Font("arial", 1, 50);
						Font fnt2 = new Font("arial", 1, 30);
						g.setColor(Color.white);
						
						g.setFont(fnt);
						g.drawString("Menu", 240, 70);
						
						g.setFont(fnt2);
						g.drawRect(210, 150, 200, 64);
						g.drawString("Play", 270, 190);
						
						g.drawRect(210, 250, 200, 64);
						g.drawString("Help", 270, 290);
						
						g.drawRect(210, 350, 200, 64);
						g.drawString("Quit", 270, 390);
				} else if (Game.gameState == STATE.Help){
					Font fnt = new Font("arial", 1, 50);
					Font fnt2 = new Font("arial", 1, 30);
					Font fnt3 = new Font("arial", 1, 20);
					
					g.setFont(fnt);
					g.setColor(Color.white);
					g.drawString("Help", 240, 70);
					
					g.setFont(fnt3);
					g.drawString("Use WASD keys to move player and dodge enemies", 50, 200);
					
					g.setFont(fnt2);
					g.drawRect(210, 350, 200, 64);
					g.drawString("Back", 270, 390);
				} else if (Game.gameState == STATE.End){
					Font fnt = new Font("arial", 1, 50);
					Font fnt2 = new Font("arial", 1, 30);
					Font fnt3 = new Font("arial", 1, 20);
					
					g.setFont(fnt);
					g.setColor(Color.white);
					g.drawString("Game Over", 180, 70);
					
					g.setFont(fnt3);
					g.drawString("You lost with a score of: " + hud.getScore(), 175, 200);
					
					g.setFont(fnt2);
					g.drawRect(210, 350, 200, 64);
					g.drawString("Try Again", 245, 390);
				}
		}
}
