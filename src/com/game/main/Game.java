	package com.game.main;
	
	import java.awt.Canvas;
	import java.awt.Color;
	import java.awt.Graphics;
	import java.awt.image.BufferStrategy;
	import java.util.Random;
	
	public class Game extends Canvas implements Runnable{
	
		private static final long serialVersionUID = -473349850293143017L;
		
		public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
		
		private Thread thread; //how the entire game runs; single threaded game
		private boolean running = false;
		
		private Random r;
		private Handler handler;
		private HUD hud;
		private Spawn spawner;
		private Menu menu;
		
		public enum STATE{
				Menu,
				Help,
				Game,
				End
		};
		
		public static  STATE gameState = STATE.Menu;
				
		public Game(){
				/*handler has to be above the Window that is created in order for the
				* game to "see" the handler before creating the Window. It's similar to
				* to when you are initializing the r variable as a Random. You can't use
				* r until you've initialized it as a Random. If you had the handler below
				* below the Window, an error would appear when you use the render()
				* method later on in the program. This is because the code compiles from
				* top to bottom.*/
				handler = new Handler();
				hud = new HUD();
				menu = new Menu(this, handler, hud);
				this.addKeyListener(new KeyInput(handler));
				this.addMouseListener(menu);
				
				AudioPlayer.load();
				
				AudioPlayer.getMusic("music").loop();
				
				new Window(WIDTH, HEIGHT, "Let's Build a Game!", this);
				
				spawner = new Spawn(handler, hud);
				r = new Random();
				
				if(gameState == STATE.Game){
						handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						//handler.addObject(new InvaderEnemy(Game.WIDTH / 2, -120, ID.InvaderEnemy, handler));
						//handler.addObject(new SnakeEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SnakeEnemy, handler));
				} else { //Creates particles on the menu screen
						for(int i = 0; i < 20; i++){
								handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT/2-32), ID.MenuParticle, handler));
						}
				}
		} //end of Game constructor
	
		public synchronized void start(){
				thread = new Thread(this);
				thread.start(); //starts the thread
				running = true;//the thread is running
		} //end of start() method
		
		public synchronized void stop(){
				//if it can run, then run. if not, then print the exception error
				try{
						thread.join();
						running = false;//the thread is not running
				}catch(Exception e){
					e.printStackTrace();
				} //end of try-catch
		} //end of stop() method
		
		//Game Loop: A loop that keeps a game running over and over again
		//until the user closes it
		public void run(){
				this.requestFocus();//allows key control input to happen automatically rather than having to click
				long lastTime = System.nanoTime();
				double amountOfTicks = 60.0;
				double ns = 1000000000 / amountOfTicks;
				double delta = 0;
				long timer = System.currentTimeMillis();
				int frames = 0;
				while(running){
						long now = System.nanoTime();
						delta += (now - lastTime) / ns;
						lastTime = now;
						while(delta >= 1){
								tick();
								delta--;
						} //end of while
						if(running)
								render();
						frames++;
						
						if(System.currentTimeMillis() - timer > 1000){
								timer += 1000;
								System.out.println("FPS: " + frames);
								frames = 0;
						} //end of if
				} //end of while
				stop();
		} //end of run() method
		
		//Updates the properties of the game itself
		private void tick(){
				handler.tick();
				if(gameState == STATE.Game){
						hud.tick();
						spawner.tick();	
						
						if(HUD.HEALTH <= 0){
								HUD.HEALTH = 100;
								gameState = STATE.End;
								handler.clearEnemy();
								for(int i = 0; i < 20; i++){
										handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT/2-32), ID.MenuParticle, handler));
								}
						}
						
				}else if(gameState == STATE.Menu || gameState == STATE.End){
						menu.tick();
				}
		} //end of tick() method
		
		//Displays the updates that were made from the tick method
		private void render(){
				BufferStrategy bs = this.getBufferStrategy();
				if(bs == null){
						this.createBufferStrategy(3);//how many buffers is created
						return;
				} //end of if
				
				Graphics g = bs.getDrawGraphics();
				
				g.setColor(Color.black);
				g.fillRect(0, 0, WIDTH, HEIGHT);
				
				handler.render(g);//renders all of the game objects from handler class
				
				if(gameState == STATE.Game){
						hud.render(g);//renders hud class, the hud will be on top of the game objects
				} else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End){
					menu.render(g);
				}
				
				g.dispose();
				bs.show();
		} //end of renders() method
		
		/*Say the var is the x value. If x is greater than the max (room width), 
		 * then return var as the room width. This will prevent the player from
		 * leaving the width of the frame. The min would be the room height*/
		public static float clamp(float var, float min, float max){
				if(var >= max)
						return var = max;
				else if (var <= min)
						return var = min;
				else
						return var;
		} //end of clamp() method
		
		//Main method
		public static void main(String args[]){
				new Game();
		} //end of main method
	} //end of Game class
