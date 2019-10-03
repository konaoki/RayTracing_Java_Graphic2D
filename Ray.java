import java.util.*;
public class Ray{
  ArrayList<PrecisePoint> collisionPoints;
  String label;
  double intensity;
  int numberOfCollision;
  public Ray(){
    collisionPoints = new ArrayList<PrecisePoint>();
    numberOfCollision=0;
  }
  public Ray(PrecisePoint[] ps){
    collisionPoints = new ArrayList<PrecisePoint>();
    for(int i=0; i<ps.length; i++){
      collisionPoints.add(ps[i]);
    }
    numberOfCollision=0;
  }
  public Ray(PrecisePoint p){
    collisionPoints = new ArrayList<PrecisePoint>();
    collisionPoints.add(p);
  }
  public void addCollisionPoint(PrecisePoint p){
    collisionPoints.add(p);
  }
}
