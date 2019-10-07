import java.util.*;
import java.awt.*;
public class Camera{
  PrecisePoint position;
  PrecisePoint direction;
  Ray[] rays;
  Pixel[] pixels;
  ArrayList<Model> models;
  Plane window;
  int width;
  int height;

  PrecisePoint lightSource;

  int lightHeight;

  public Camera(PrecisePoint p, PrecisePoint d,int w,int h){
    position=p;
    direction=d;
    lightHeight=0;
    models=new ArrayList<Model>();
    width=w;
    height=h;
    window=new Plane(new PrecisePoint[]{position.add(new PrecisePoint(-width/2,height/2,0)),position.add(new PrecisePoint(width/2,height/2,0)),position.add(new PrecisePoint(width/2,-height/2,0)),position.add(new PrecisePoint(-width/2,-height/2,0))});
    lightSource=position.clone().setZ(10).setY(position.getY()-lightHeight);
  }

  public void loadModel(Model m){
    models.add(m);
  }
  public void rayTrace(){

    rays=new Ray[width*height];
    pixels=new Pixel[width*height];
    for(int i=0; i<width; i++){
      for(int j=0; j<height; j++){
        rays[i+j*width]=new Ray(position);
        PrecisePoint destination=new PrecisePoint(-width/2+i,-height/2+j,0);
        //System.out.println("dest: "+destination);
        pixels[i+j*width]=new Pixel(destination);
        for(int k=0; k<models.size();k++){
          Triangle[] tris = models.get(k).getTriangles();
          for(int l=0; l<tris.length; l++){
            PrecisePoint tempDest = destination.clone().subtract(position);
            //System.out.println("tri center: "+tris[l].getCenter());
              PrecisePoint cameraToIntersection =tempDest.scale(
                tris[l].getNormal().dot(
                  tris[l].getVertices()[0].subtract(position)
                ) /  tris[l].getNormal().dot(tempDest)
              );
              PrecisePoint intersection = position.add(cameraToIntersection);

            //System.out.println("destination: "+destination);
            //System.out.println("intersection: "+intersection);

            if(tris[l].contains(intersection)){ //if the intersection is contained by the triangle and the normals of the triangle and the ray are opposite
              //System.out.println("normal dot v: "+tris[l].getNormal().dot(cameraToIntersection));
              if(tris[l].getNormal().dot(cameraToIntersection) < 0){

                rays[i+j*width].numberOfCollision+=1;
                rays[i+j*width].addCollisionPoint(intersection);
                PrecisePoint lightToIntersection = intersection.subtract(lightSource);
                //System.out.println(tris[l].getNormal());
                double I = -1*lightToIntersection.dot(tris[l].getNormal()) / ( lightToIntersection.magnitude()*tris[l].getNormal().magnitude() );
                //System.out.println(I);
                double min = 0.3;
                double max=1;
                if(I<min){
                  I=min;
                }
                else if(I>max){
                  I=max;
                }
                tris[l].color=new Color((float)I,(float)I,(float)I);
                pixels[i+j*width].setColor(tris[l].color);
              }
            }
          }
        }
        //rays[i].addCollisionPoint();
      }
    }
  }
}
