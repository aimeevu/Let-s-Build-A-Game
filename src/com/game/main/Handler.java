/*Maintains, updates and renders all of the objects in the game.
 * It loops through each individual object in the game, updates them,
 * and renders them onto the screen.*/
	
	package com.game.main;
	
	import java.awt.Graphics;
	import java.util.Vector;
	
	public class Handler {
			Vector<GameObject> object = new Vector<GameObject>();
			
			public void tick(){
					//Loops through every single game object
					for(int i = 0; i < object.size(); i++){
							GameObject tempObject = object.get(i);//object.get() is a function within the LinkedList. It loops through and gets what is in the list.
							
							tempObject.tick();
					} //end of for loop
			} //end of tick() method
			
			public void render(Graphics g){
						for(int i = 0; i < object.size(); i++){
								GameObject tempObject = object.get(i);
								
								tempObject.render(g);
						} //end of for loop
			} //end of render() method
			
			public void clearEnemy(){
					for(int i = 0; i < object.size(); i++){
						GameObject tempObject = object.get(i);	
						//Clears all objects and places the player back where the player was at
						if(tempObject.getId() == ID.Player){
								object.clear();
								if(Game.gameState != Game.STATE.End)
										addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), ID.Player, this));
						}
					}
			}
			
			//Adds objects to the object LinkedList
			public void addObject(GameObject object){
					this.object.add(object);
			} //end of addObject() method
			//Removes objects from the object LinkedList
			public void removeObject(GameObject object){
					this.object.remove(object);
			} //end of removeObject() method
	} //end of Handler class
