public class Model{
  Triangle[] triangles;
  public Model(Triangle[] ts){
    triangles=ts;
  }
  public PrecisePoint[] getVertices(){
    PrecisePoint[] ps = new PrecisePoint[triangles.length*3];
    for(int i=0; i<triangles.length; i++){
      ps[3*i]=triangles[i].getVertices()[0];
      ps[3*i+1]=triangles[i].getVertices()[1];
      ps[3*i+2]=triangles[i].getVertices()[2];
    }
    return ps;
  }
  public void scale(double s){
    for(int i=0; i<getTriangles().length; i++){
      triangles[i].scale(s);
    }
  }
  public void rotateX(double angle, PrecisePoint c){
    for(int i=0; i<getTriangles().length; i++){
      triangles[i].rotateX(angle,c);

    }
  }
  public void rotateY(double angle, PrecisePoint c){
    for(int i=0; i<getTriangles().length; i++){
      triangles[i].rotateY(angle,c);
    }
  }
  public void rotateZ(double angle, PrecisePoint c){
    for(int i=0; i<getTriangles().length; i++){
      triangles[i].rotateZ(angle,c);
    }
  }
  public void addVector(PrecisePoint p){
    for(int i=0; i<getTriangles().length; i++){
      triangles[i].addVector(p);
    }
  }
  public Triangle[] getTriangles(){
    return triangles;
  }
  public Boolean contains(PrecisePoint p){
    return false;
  }
  public Triangle triangleAt(PrecisePoint p){
    return null;
  }
  public Model clone(){
    return new Model(triangles.clone());
  }
}
