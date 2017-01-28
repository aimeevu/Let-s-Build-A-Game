//Creates the player

	package com.game.main;
	
	import java.awt.Color;
	import java.awt.Graphics;
	import java.awt.Graphics2D;
	import java.awt.Rectangle;
	import java.util.Random;
	
	public class Player extends GameObject{
		
		Random r = new Random();
		Handler handler;

		public Player(int x, int y, ID id, Handler handler) {
				super(x, y, id);
				this.handler = handler;
		} //end of Player constructor
		
		//returns the bounds of the player.
		public Rectangle getBounds(){
				return new Rectangle((int)x, (int)y, 32, 32);
		}
		
		public void tick(){
				x += velX;
				y += velY;
				
				//Prevents the player from going beyond the frame.
				x = Game.clamp(x, 0, Game.WIDTH - 37);
				y = Game.clamp(y, 0, Game.HEIGHT - 60);
				
				handler.addObject(new Trail(x, y, ID.Trail, Color.white, 32, 32, 0.05f, handler));
				
				collision();
		} //end of tick() method
		
		private void collision(){
				for(int i = 0; i < handler.object.size(); i++){
						
						GameObject tempObject = handler.object.get(i);
						
						
						if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy
								|| tempObject.getId() == ID.SnakeEnemy){
								if(getBounds().intersects(tempObject.getBounds())){
										//collision code
										HUD.HEALTH -= 1;
								}
						}
				}
		}
		
		public void render(Graphics g){
			
				Graphics2D g2d = (Graphics2D) g; 
				
				//Draws a red line around the white box
				//g.setColor(Color.red);
				//g2d.draw(getBounds()); //draws the collision bounding
				
				//Draws the player.
				g.setColor(Color.white);
				g.fillRect((int)x, (int)y, 32, 32);
		} //end of render() method
	} //end of Player class
