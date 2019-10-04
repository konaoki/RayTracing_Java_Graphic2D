public class Plane{
  PrecisePoint[] vertices;
  public Plane(PrecisePoint[] ps){
    vertices=ps;
  }
  public Plane(PrecisePoint t1, PrecisePoint t2, PrecisePoint t3){
    vertices=new PrecisePoint[]{t1,t2,t3};
  }
  
  public PrecisePoint[] getVertices(){
    return vertices;
  }
  public PrecisePoint getNormal(){
    PrecisePoint v1=vertices[1].subtract(vertices[0]);
    PrecisePoint v2=vertices[2].subtract(vertices[0]);
    PrecisePoint cross = v1.cross(v2);
    return cross.scale(1/cross.magnitude());
  }
  public Plane clone(){
    PrecisePoint[] verts = vertices.clone();
    return new Plane(verts);
  }



}
