import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.lang.*;
import java.util.*;
public class RendererCanvas extends JPanel{
  Model model;
  Model transformedModel;
  Model secondModel;
  Light lightSource;
  Model ground;
  Camera camera;
  int width;
  int height;

  public RendererCanvas(int w,int h){
    width=w;
    height=h;

    double zOffset=-1;
    PrecisePoint v1 = new PrecisePoint(-1,-1,+zOffset);
    PrecisePoint v2 = new PrecisePoint(1,-1,+zOffset);
    PrecisePoint v3 = new PrecisePoint(1,1,+zOffset);
    PrecisePoint v4 = new PrecisePoint(1,-1,-2+zOffset);

    model=new Model(
      new Triangle[]{new Triangle(v1.clone(),v2.clone(),v3.clone()),new Triangle(v1.clone(),v4.clone(),v2.clone()),new Triangle(v1.clone(),v3.clone(),v4.clone()),new Triangle(v2.clone(),v4.clone(),v3.clone())}
    );
    //model=new Model(new Triangle[]{new Triangle(v1.clone(),v2.clone(),v3.clone())});

    model = model.scale(100);
    model = model.addVector(model.getCenter().scale(-1).setZ(0));
    transformedModel = model.rotateX(45,model.getCenter());
    transformedModel = model.rotateY(30,model.getCenter());
    //transformedModel = model.rotateZ(15,model.getCenter());

    camera = new Camera(new PrecisePoint(0,0,10000),new PrecisePoint(0,0,-10),w,h);
    //lightSource = new Light(camera.position);
  }
  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D)g;
    g.setColor(Color.black);
    g.fillRect(0, 0, getWidth(), getHeight());
    g2d.translate(getWidth()/2,getHeight()/2);
    g2d.scale(1,-1);
    
    camera.loadModel(transformedModel);
    camera.rayTrace();
    Draw(g2d,transformedModel);
  }
  public void Draw(Graphics2D g2d, Model m){
    Triangle[] tris = m.getTriangles();

    for(int i=0; i<tris.length; i++){
      int[] xs,ys;
      xs=new int[3];
      ys=new int[3];
      PrecisePoint[] points = tris[i].getVertices();
      for(int j=0; j<points.length; j++){
        xs[j]=points[j].toInt()[0];
        ys[j]=points[j].toInt()[1];
      }
      Polygon p = new Polygon(xs,ys,3);

      for(int j=0; j<camera.rays.length; j++){
        if(camera.rays[j].collisionPoints.size()>1){
          g2d.setColor(camera.pixels[j].color);
          g2d.drawLine(
            (int)Math.round(camera.pixels[j].getX()),
            (int)Math.round(camera.pixels[j].getY()),
            (int)Math.round(camera.pixels[j].getX()),
            (int)Math.round(camera.pixels[j].getY())
          );
        }
      }
    }
  }
}
