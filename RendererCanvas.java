import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.lang.*;
import java.util.*;
public class RendererCanvas extends JPanel{
  Model model;
  Model secondModel;
  Light lightSource;
  Model ground;
  Camera camera;
  public RendererCanvas(){
    setPreferredSize(new Dimension(2000,1000));
    double zOffset=1;
    PrecisePoint v1 = new PrecisePoint(-1,-1,-zOffset);
    PrecisePoint v2 = new PrecisePoint(1,-1,-zOffset);
    PrecisePoint v3 = new PrecisePoint(1,1,-zOffset);
    PrecisePoint v4 = new PrecisePoint(1,-1,-1-zOffset);

    model=new Model(
      new Triangle[]{new Triangle(v1.clone(),v2.clone(),v3.clone()),new Triangle(v1.clone(),v4.clone(),v2.clone()),new Triangle(v1.clone(),v3.clone(),v4.clone()),new Triangle(v2.clone(),v4.clone(),v3.clone())}
    );

    model.scale(200);
    model.rotateX(30,new PrecisePoint(0,0,0));
    model.rotateY(30,new PrecisePoint(0,0,0));
    model.rotateZ(180+30,new PrecisePoint(0,0,0));

    secondModel = model.clone();
    secondModel.addVector(new PrecisePoint(-300,0,0));
    //model=new Model(new Triangle[]{new Triangle(v1,v2,v3)} );

    lightSource = new Light(-1,-1,10);

    camera = new Camera(new PrecisePoint(0,100,100),new PrecisePoint(0,-10,-10));

  }
  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D)g;
    g2d.translate(getWidth()/2,getHeight()/2);
    g2d.scale(1,-1);


    camera.loadModel(model);
    camera.loadModel(secondModel);
    camera.rayTrace();
    Draw(g2d,model);
    Draw(g2d,secondModel);
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
      if(tris[i].color!=Color.black){
        g2d.setColor(tris[i].color);
        g2d.fill(p);
      }

      g2d.setColor(Color.black);
      g2d.draw(p);
      g2d.setColor(Color.gray);

      for(int j=0; j<camera.rays.length; j++){
        //System.out.println(camera.rays[i].collisionPoints.get(0));
        //System.out.println(camera.rays[i].collisionPoints.get(1));
        if(camera.rays[j].numberOfCollision>0){
          g2d.drawLine(
            (int)Math.round(camera.rays[j].collisionPoints.get(0).getX()),
            (int)Math.round(camera.rays[j].collisionPoints.get(0).getY()),
            (int)Math.round(camera.rays[j].collisionPoints.get(1).getX()),
            (int)Math.round(camera.rays[j].collisionPoints.get(1).getY())
          );
        }
      }
    }
  }
}
