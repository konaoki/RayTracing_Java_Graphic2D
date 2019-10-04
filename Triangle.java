import java.util.*;
import java.awt.*;
public class Triangle extends Plane{
  Color color;
  String label;
  public Triangle(PrecisePoint t1, PrecisePoint t2, PrecisePoint t3){
    super(t1,t2,t3);
    color=Color.black;
  }
  public Boolean contains(PrecisePoint p){
    double A = new Triangle(getVertices()[0],getVertices()[1],getVertices()[2]).getArea();
    double A1 = new Triangle(getVertices()[0],getVertices()[1],p).getArea();
    double A2 = new Triangle(getVertices()[1],getVertices()[2],p).getArea();
    double A3 = new Triangle(getVertices()[2],getVertices()[0],p).getArea();
    Boolean r=false;
    if(A + 0.0001 > A1 + A2 + A3 && A - 0.0001 <A1 + A2 + A3){
      r=true;
    }
    return r;
  }
  public void rotateX(double angle, PrecisePoint c){
    for(int i=0; i<getVertices().length; i++){
      getVertices()[i].rotateX(angle,c);

    }
  }
  public void rotateY(double angle, PrecisePoint c){
    for(int i=0; i<getVertices().length; i++){
      getVertices()[i].rotateY(angle,c);
    }
  }
  public void rotateZ(double angle, PrecisePoint c){
    for(int i=0; i<getVertices().length; i++){
      getVertices()[i].rotateZ(angle,c);
    }
  }

  public Triangle scale(double s){
    PrecisePoint[] tempVertices = new PrecisePoint[vertices.length];
    for(int i=0; i<getVertices().length; i++){
      tempVertices[i]=vertices[i].scale(s);
    }
    return new Triangle(tempVertices[0],tempVertices[1],tempVertices[2]);
  }

  public void addVector(PrecisePoint p){
    for(int i=0; i<getVertices().length; i++){
      getVertices()[i].add(p);
    }
  }

  public PrecisePoint getCenter(){
    double ax=0;
    double ay=0;
    double az=0;
    for(int i=0; i<getVertices().length; i++){
      ax+=getVertices()[i].getX();
      ay+=getVertices()[i].getY();
      az+=getVertices()[i].getZ();
    }
    return new PrecisePoint(ax/getVertices().length,ay/getVertices().length,az/getVertices().length);
  }
  public double getArea(){
    return 0.5*getVertices()[2].subtract(getVertices()[0]).cross(getVertices()[1].subtract(getVertices()[0])).magnitude();
  }

}
