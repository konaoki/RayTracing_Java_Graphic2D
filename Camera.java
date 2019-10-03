import java.util.*;
import java.awt.*;
public class Camera{
  PrecisePoint position;
  PrecisePoint direction;
  Ray[] rays;
  ArrayList<Model> models;
  Plane window;
  public Camera(PrecisePoint p, PrecisePoint d){
    position=p;
    direction=d;
    models=new ArrayList<Model>();
    window=new Plane(new PrecisePoint[]{position.add(new PrecisePoint(-100,100,-200)),position.add(new PrecisePoint(100,100,-200)),position.add(new PrecisePoint(100,-100,-200)),position.add(new PrecisePoint(-100,-100,-200))});
  }

  public void loadModel(Model m){
    models.add(m);
  }
  public void rayTrace(){
    int size=50;
    rays=new Ray[size*size];
    for(int i=0; i<size; i++){
      for(int j=0; j<size; j++){
        rays[i+j*size]=new Ray(position);
        PrecisePoint dir=new PrecisePoint(-100+200/size*i,-100+200/size*j,-10);
        for(int k=0; k<models.size();k++){
          Triangle[] tris = models.get(k).getTriangles();
          for(int l=0; l<tris.length; l++){
            PrecisePoint tempDir = dir.clone();
            tempDir.scale(
              tris[l].getNormal().dot(
                tris[l].getVertices()[0].subtract(position)
              ) /  tris[l].getNormal().dot(tempDir)
            );

            PrecisePoint intersection = position.add(tempDir);
            rays[i+j*size].addCollisionPoint(intersection);


            if(tris[l].contains(intersection)){ //if the intersection is contained by the triangle and the normals of the triangle and the ray are opposite
              if(tris[l].getNormal().dot(tempDir) > 0){

                rays[i+j*size].numberOfCollision+=1;
                if(tris[l].label!=null){
                  System.out.println("tri normal: " + tris[l].getNormal());
                  System.out.println("camera normal: " + dir);
                  rays[i+j*size].label="bottom";
                }
                Random rand = new Random();
                float r = rand.nextFloat();
                float g = rand.nextFloat();
                float b = rand.nextFloat();
                tris[l].color=new Color(r,g,b);
              }
            }
          }
        }
        //rays[i].addCollisionPoint();
      }
    }
  }
}
