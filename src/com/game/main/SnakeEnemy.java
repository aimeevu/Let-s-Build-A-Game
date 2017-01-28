//Creates the basic enemy

	package com.game.main;
	
	import java.awt.*;
	import java.util.Random;
	
	public class SnakeEnemy extends GameObject {
		
			private Handler handler;
			Random r = new Random();
			
			private boolean inGame = true;
	
			public SnakeEnemy(float x, float y, ID id, Handler handler) {
					super(x, y, id);
					
					this.handler = handler;
					
					//velX = 0;
					//velY = 5;
					
					new Thread(new Runnable(){

						@Override
						public void run() {
						while(true){
							int millis = ((int) Math.random() * 5000) + 1000;
							try {
								Thread.sleep(millis);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							
							chooseDirection();
						}	
						}
						
					}).start();
			} //end of BasicEnemy constructor
			
			public Rectangle getBounds(){
					return new Rectangle((int)x, (int)y, 16, 16);
			}
			
			//public void  grow(){
				//return new Rectangle(getX() + 10,  getY());
			//}
		
			public void tick() {
					//With every "tick", the object will move by calculation
					x += velX;
					y += velY;
					
					/*When the box reaches the edge of the frame, it will reverse
					the velY (direction) that it is going*/
					if(y <= 0 || y >= Game.HEIGHT - 32)
							velY *= -1;
							//chooseDirection(false);
					if(x <= 0 || x >= Game.WIDTH - 16)
							velX *= -1;
							//chooseDirection(true);
					
					
					//handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.02f, handler));
			} //end of tick() method
		
			public void render(Graphics g) {
					g.setColor(Color.red);
					//g.fillRect((int) x, (int) y, 16, 16);
					
					int squares = 3;
					int[] xC = new int[squares];
					int[] yC = new int[squares];
				    
				    for(int z = 0; z < squares; z++) {
				    	xC[z] = (int) (x - z * 20);
				    	yC[z] = (int) y;
				    }
				    
				    for (int z = 0; z < squares; z++) {
				        if(z == 0) {
				        	g.fillRect(xC[z], yC[z], 16, 16);
				        } else {
				        	g.fillRect(xC[z], yC[z], 16, 16);
				        }
				      }
			} //end of render() method
			
			public void up(){
					velX = 0;
					velY = 5;
			}
			public boolean isUp(){
					return velY > 0;
			}
			
			public void down(){
					velX = 0;
					velY = -5;
			}
			public boolean isDown(){
				return velY < 0;
			}
			
			public void right(){
					velX = 5;
					velY = 0;
			}
			public boolean isRight(){
				return velX > 0;
			}
			
			public void left(){
					velX = -5;
					velY = 0;
			}
			public boolean isLeft(){
				return velX < 0;
			}
			
			public void chooseDirection () {
					double random = Math.random();
					if (random < 0.25 && !isUp()){
							up();
					}
					else if(0.25 < random && random < .50  && !isDown()){
							down();
					}
					else if(0.50 < random && random < .75  && !isRight()){
							right();
					}
					else if(random > .75 && !isLeft()){
							left();
					}
					else
							chooseDirection();
			}
			
			/*public void chooseDirection(boolean isVert){
					double random = Math.random();
					if (random < 0.25 && !isUp() && isVert){
							up();
					}
					else if(0.25 < random && random < .50  && !isDown() && isVert){
							down();
					}
					else if(0.50 < random && random < .75  && !isRight() && !isVert){
							right();
					}
					else if(random > .75 && !isLeft() && !isVert){
							left();
					}
					else
							chooseDirection();
			}*/
		
	} //end of BasicEnemy class
