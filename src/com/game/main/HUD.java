//Health bar
/*Can't change the amount of health because it is tied to the greenValue (changes the color of
 * the health bar as it decreases) and size of the health bar rectangle (decreases in size as health is
 * lost).*/

package com.game.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
		public static float HEALTH = 100;
		private float greenValue = 255;
		private Handler handler;
		
		private int score = 0;
		private int level = 1;
		
		public void tick(){
				HEALTH = Game.clamp(HEALTH, 0, 100);//caps the health between 0 and 100
				greenValue = Game.clamp(greenValue, 0, 255);
				
				greenValue = HEALTH*2;
				
				score++;
				
				
		} //end of tick() method
		
		public void render(Graphics g){
				g.setColor(Color.gray);
				g.fillRect(15, 15, 200, 32);
				g.setColor(new Color(75, (int)greenValue, 0));
				g.fillRect(15, 15, (int)HEALTH * 2, 32);//the health bar itself
				g.setColor(Color.white);
				g.drawRect(15, 15, 200, 32);//white border around health bar
				
				g.drawString("Snaazse", 15, 64);
				g.drawString("Score " + score, 15, 80);
				g.drawString("Level " + level, 15, 96);
				
				/*if(HEALTH == 0){
					g.setColor(Color.BLACK);
					g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
					g.setColor(Color.white);
					g.drawString("GAME OVER", (Game.WIDTH / 2) - 40, 150);
					level--;
				} //end of if
				
				if(level > 40){
					g.setColor(Color.BLACK);
					g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
					g.setColor(Color.white);
					g.drawString("YOU ARE A WINNER", (Game.WIDTH / 2) - 40, 150);
				} // end of if */
		} //end of render() method
		
		public void setScore(int score){
				this.score = score;
		}
		
		public int getScore(){
				return score;
		}
		
		public int getLevel(){
				return level;
		}
		
		public void setLevel(int level){
				this.level = level;
		}
} //end of HUD class
