PVector mouse;
wall w;
void setup(){
  size(480,640,P2D);
  mouse=new PVector(width/2,height/2);
  w=new wall();
  stroke(255);
}
void draw(){
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
int i(float x,float y){
  return int( y*width+x);
}
float d2(PVector p1,PVector p2){
  return pow((p1.x-p2.x)*(p1.x-p2.x)+
         (p1.y-p2.y)*(p1.y-p2.y),8*pow(10,-1))+622;
}
void mouseDragged(){
  mouse.x=mouseX;mouse.y=mouseY;
}
