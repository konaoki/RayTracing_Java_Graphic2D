public class Plane{
  PrecisePoint[] vertices;
  public Plane(PrecisePoint[] ps){
    vertices=ps;
  }
  public Plane(PrecisePoint t1, PrecisePoint t2, PrecisePoint t3){
    vertices=new PrecisePoint[]{t1,t2,t3};
  }
  public void scale(double s){
    for(int i=0; i<getVertices().length; i++){
      vertices[i].scale(s);
    }
  }
  public PrecisePoint[] getVertices(){
    return vertices;
  }
  public PrecisePoint getNormal(){
    PrecisePoint v1=vertices[1].subtract(vertices[0]);
    PrecisePoint v2=vertices[2].subtract(vertices[1]);
    PrecisePoint cross = v1.cross(v2);
    cross.scale(1/cross.magnitude());
    return cross;
  }


}
