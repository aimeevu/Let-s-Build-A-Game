//Creates the particles on the menu

	package com.game.main;
	
	import java.awt.Color;
	import java.awt.Graphics;
	import java.awt.Rectangle;
import java.util.Random;
	
	public class MenuParticle extends GameObject {
		
			private Handler handler;
			
			Random r = new Random();
			private Color col;
	
			public MenuParticle(int x, int y, ID id, Handler handler) {
					super(x, y, id);
					
					this.handler = handler;
					//Generates a random speed between -7 and 7
					velX = (r.nextInt(7 - -7) + -7);
					velY = (r.nextInt(7 - -7) + -7);
					//Changes a speed of 0 to a speed of 1
					if(velX == 0)
							velX = 1;
					if(velY == 0)
							velY = 1;
					
					//generates a random color
					col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
					
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
					
					handler.addObject(new Trail(x, y, ID.Trail, col, 16, 16, 0.05f, handler));
			} //end of tick() method
		
			public void render(Graphics g) {
					g.setColor(col);
					g.fillRect((int)x, (int)y, 16, 16);
			} //end of render() method
		
	} //end of FastEnemy class
