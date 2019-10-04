public class Model{
  Triangle[] triangles;
  Boolean reflective;
  public Model(Triangle[] ts){
    triangles=ts;
    reflective=false;
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
  public Model scale(double s){
    Triangle[] tempTriangles = new Triangle[triangles.length];
    for(int i=0; i<getTriangles().length; i++){
      tempTriangles[i]=(Triangle)triangles[i].scale(s);
    }
    return new Model(tempTriangles);
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
  public Triangle triangleAt(PrecisePoint p){
    return null;
  }
  public Model clone(){
    return new Model(triangles.clone());
  }
}
