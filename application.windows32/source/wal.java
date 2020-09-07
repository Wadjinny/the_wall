import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class wal extends PApplet {

PVector mouse;
wall w;
public void setup(){
  
  mouse=new PVector(width/2,height/2);
  w=new wall();
  stroke(255);
}
public void draw(){
  PVector pos=new PVector();
  loadPixels();
  for(float y=0;y<height;y++){
    for(float x=0;x<width;x++){
      pos.x=x;pos.y=y;
      boolean inter=w.is_shad(pos,mouse);
      pixels[i(x,y)]=(! inter)?color(0):color(136246/d2(pos,mouse));
    }
  }
    
  updatePixels();
  w.draw();
}
public int i(float x,float y){
  return PApplet.parseInt( y*width+x);
}
public float d2(PVector p1,PVector p2){
  return pow((p1.x-p2.x)*(p1.x-p2.x)+
         (p1.y-p2.y)*(p1.y-p2.y),8*pow(10,-1))+622;
}
public void mouseDragged(){
  mouse.x=mouseX;mouse.y=mouseY;
}
class wall{
  PVector p1=new PVector(100,100);
  PVector p2=new PVector(width-100,100);
  public void draw(){
    line(p1.x,p1.y,p2.x,p2.y);
  }
  public float[] coef(PVector pt1,PVector pt2){
    float a=pt1.y-pt2.y,b=pt2.x-pt1.x,c=pt1.y*(pt1.x-pt2.x)+pt1.x*(pt2.y-pt1.y);
    float [] list={a,b,c} ;
    return list;
  }
  public float delta(PVector pt1,PVector pt2,PVector b1,PVector b2){
    float[] c1=coef(pt1,pt2);
    float[] c2=coef(b1,b2);
    return c1[0]*c2[1]-c2[0]*c1[1];
  }
  public boolean is_shad(PVector pt0,PVector pt1){
    PVector pt2=p1, pt3=p2;
    PVector s1=new PVector(),s2=new PVector();
    s1.x = pt1.x - pt0.x;     s1.y = pt1.y - pt0.y;
    s2.x = pt3.x - pt2.x;     s2.y = pt3.y - pt2.y;

    float s, t;
    s = (-s1.y * (pt0.x - pt2.x) + s1.x * (pt0.y - pt2.y)) / (-s2.x * s1.y + s1.x * s2.y);
    t = ( s2.x * (pt0.y - pt2.y) - s2.y * (pt0.x - pt2.x)) / (-s2.x * s1.y + s1.x * s2.y);

    if (s >= 0 && s <= 1 && t >= 0 && t <= 1)
    {
        // Collision detected
        //if (i_x != NULL)
        //    *i_x = p0_x + (t * s1_x);
        //if (i_y != NULL)
        //    *i_y = p0_y + (t * s1_y);
        return false;
    }

    return true; // No collision
}
}
  public void settings() {  size(480,640,P2D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "wal" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
