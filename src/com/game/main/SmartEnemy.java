//Creates the smart enemy

	package com.game.main;
	
	import java.awt.Color;
	import java.awt.Graphics;
	import java.awt.Rectangle;
	
	public class SmartEnemy extends GameObject {
		
			private Handler handler;
			private GameObject player;
	
			public SmartEnemy(int x, int y, ID id, Handler handler) {
					super(x, y, id);
					this.handler = handler;
					
					/*Running through the array to check if the variable is the player. 
					 * It will set the object ID.Player to player variable*/
					for(int i = 0; i < handler.object.size(); i++){
							if(handler.object.get(i).getId() == ID.Player){
									player = handler.object.get(i);
							}
					}
					
			} //end of SmartEnemy constructor
			
			public Rectangle getBounds(){
					return new Rectangle((int)x, (int)y, 16, 16);
		}
		
			public void tick() {
					//With every "tick", the object will move by calculation
					x += velX;
					y += velY;
					
					/*Difference between the enemy location and the player location.
					 * -8 is using the location from the middle of the player*/
					float diffX = x - player.getX() - 8;
					float diffY = y - player.getY() - 8;
					float distance = (float) Math.sqrt((x-player.getX()) * (x-player.getX()) + (y-player.getY()) * (y-player.getY()));
					//The new velX and velY will now follow the player's location
					velX = (float) ((-1.0/distance) * diffX);
					velY = (float) ((-1.0/distance) * diffY);
					
					/*When the box reaches the edge of the frame, it will reverse
					the velY (direction) that it is going*/
					if(y <= 0 || y >= Game.HEIGHT - 32)
							velY *= -1;
					if(x <= 0 || x >= Game.WIDTH - 16)
							velX *= -1;
					
					handler.addObject(new Trail(x, y, ID.Trail, Color.green, 16, 16, 0.02f, handler));
			} //end of tick() method
		
			public void render(Graphics g) {
					g.setColor(Color.green);
					g.fillRect((int)x, (int)y, 16, 16);
			} //end of render() method
		
	} //end of SmartEnemy class
