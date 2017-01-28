//Creates the fast enemy

	package com.game.main;
	
	import java.awt.Color;
	import java.awt.Graphics;
	import java.awt.Rectangle;
	
	public class FastEnemy extends GameObject {
		
			private Handler handler;
	
			public FastEnemy(int x, int y, ID id, Handler handler) {
					super(x, y, id);
					
					this.handler = handler;
					
					velX = 2;
					velY = 9;
			} //end of FastEnemy constructor
			
			public Rectangle getBounds(){
					return new Rectangle((int)x, (int)y, 16, 16);
		}
		
			public void tick() {
					//With every "tick", the object will move by calculation
					x += velX;
					y += velY;
					
					/*When the box reaches the edge of the frame, it will reverse
					the velY (direction) that it is going*/
					if(y <= 0 || y >= Game.HEIGHT - 32)
							velY *= -1;
					if(x <= 0 || x >= Game.WIDTH - 16)
							velX *= -1;
					
					//handler.addObject(new Trail(x, y, ID.Trail, Color.cyan, 16, 16, 0.02f, handler));
			} //end of tick() method
		
			public void render(Graphics g) {
					g.setColor(Color.cyan);
					g.fillRect((int)x, (int)y, 16, 16);
			} //end of render() method
		
	} //end of FastEnemy class
