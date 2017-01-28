/*What is referred to as all of the game objects. Like the player and enemy objects 
 * would be considered the game object. All objects will inherit this class*/
	
	package com.game.main;
	
	import java.awt.Graphics;
	import java.awt.Rectangle;
	
	public abstract class GameObject {
		
		//Protected: accessed by objects that inherit the GameObject.
		//When the class extends the GameObject, it will be able to 
		//access protected variables.
		protected float x, y;
		protected ID id;
		protected float velX, velY;//speed in the x and y direction
		
		public GameObject(float x, float y, ID id){
				this.x = x;			//this is setting the protected variables
				this.y = y;			//to what is in the parameters. Refers to 
				this.id = id;			//"this" instance of the variable.
		} //end of GameObject constructor
		
		public abstract void tick();
		public abstract void render(Graphics g);
		public abstract Rectangle getBounds();//used to handler the collision
		
		//Getter and setter methods
		public void setX(float x){
				this.x = x;
		}
		public void setY(float y){
				this.y = y;
		}
		public float getX(){
				return x;
		}
		public float getY(){
				return y;
		}
		public void setId(ID id){
				this.id = id;
		}
		public ID getId(){
				return id;
		}
		public void setVelX(float velX){
				this.velX = velX;
		}
		public void setVelY(float velY){
				this.velY = velY;
		}
		public float getVelX(){
				return velX;
		}
		public float getVelY(){
				return velY;
		}//end of all getter and setter methods
	} //end of GameObject class
