//Creates the basic enemy

 package com.game.main;
 
 import java.awt.Color;
 import java.awt.Graphics;
 import java.awt.Rectangle;
import java.util.Random;
 
 public class EnemyBullet extends GameObject {
  
   private Handler handler;
   Random r = new Random();
 
   public EnemyBullet(float x, float y, ID id, Handler handler) {
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
     y += 10;
     
     /*When the box reaches the edge of the frame, it will reverse
     the velY (direction) that it is going
     if(y <= 0 || y >= Game.HEIGHT - 32)
       velY *= -1;
     if(x <= 0 || x >= Game.WIDTH - 16)
       velX *= -1;*/
     
     if(y >= Game.HEIGHT)
       handler.removeObject(this);
     
     //handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.02f, handler));
   } //end of tick() method
  
   public void render(Graphics g) {
     g.setColor(Color.red);
     g.fillRect((int)x, (int)y, 16, 16);
   } //end of render() method
  
 } //end of BasicEnemy class
