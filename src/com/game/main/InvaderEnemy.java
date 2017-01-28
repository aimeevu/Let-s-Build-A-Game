//Creates the basic enemy

	package com.game.main;
	
	import java.awt.Color;
	import java.awt.Graphics;
	import java.awt.Rectangle;
	import java.util.Random;
	
	public class InvaderEnemy extends GameObject {
		
			private Handler handler;
			Random r = new Random();
			
			private int timer = 80;
			private int timer2 = 50;
	
			public InvaderEnemy(float x, float y, ID id, Handler handler) {
					super(x, y, id);
					
					this.handler = handler;
					
					velX = 0;
					velY = 2;
			} //end of BasicEnemy constructor
			
			public Rectangle getBounds(){
					return new Rectangle((int)x, (int)y, 16, 16);
		}
		
			public void tick() {
					//With every "tick", the object will move by calculation
					x += velX;
					y += velY;
					
					if(timer <= 0)
							velY = 0;
					else
							timer--;
					
					if(timer <= 0)
						timer2--;
					if(timer2 <= 0){
							//if(velX == 0)
									//velX = 2;
							
						//Makes object move horizontally smoothly
							//if(velX > 0)
									//velX += 0.005f;
							//else if(velX < 0)
									//velX -= 0.005f;
							
							//velX = Game.clamp(velX, -10, 10);
							
							int spawn = r.nextInt(1);
							if(spawn == 0)
									handler.addObject(new EnemyBullet((Game.WIDTH / 2), 50, ID.Particle, handler));
					}
					
					/*When the box reaches the edge of the frame, it will reverse
					the velY (direction) that it is going
					if(y <= 0 || y >= Game.HEIGHT - 32)
							velY *= -1;
					if(x <= 0 || x >= Game.WIDTH - 96)
							velX *= -1;*/
					
					//handler.addObject(new Trail(x, y, ID.Trail, Color.red, 96, 96, 0.008f, handler));
			} //end of tick() method
		
			public void render(Graphics g) {
					g.setColor(Color.red);
					g.fillRect((int)x, (int)y, 16, 16);
			} //end of render() method
		
	} //end of BasicEnemy class
