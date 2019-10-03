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
    return (A == A1 + A2 + A3);
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

  public void addVector(PrecisePoint p){
    for(int i=0; i<getVertices().length; i++){
      getVertices()[i].add(p);
    }
  }

  public double getArea(){
    return 0.5*getVertices()[2].subtract(getVertices()[0]).cross(getVertices()[1].subtract(getVertices()[0])).magnitude();
  }

}
