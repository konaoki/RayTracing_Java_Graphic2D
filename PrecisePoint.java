import java.util.*;
public class PrecisePoint{
  double x;
  double y;
  double z;

  public PrecisePoint(double x,double y,double z){
    this.x=x;
    this.y=y;
    this.z=z;
  }
  public void rotateX(double angle, PrecisePoint center){
    double[] tempPrecisePoint = new double[3];
    double rad = Math.toRadians(angle);
    double tx=getX()-center.getX();
    double ty=getY()-center.getY();
    double tz=getZ()-center.getZ();
    tempPrecisePoint[0]=tx;
    tempPrecisePoint[1]=ty*Math.cos(rad)-tz*Math.sin(rad);
    tempPrecisePoint[2]=ty*Math.sin(rad)+tz*Math.cos(rad);
    x=tempPrecisePoint[0]+center.getX();
    y=tempPrecisePoint[1]+center.getY();
    z=tempPrecisePoint[2]+center.getZ();
  }
  public void rotateY(double angle, PrecisePoint center){
    double[] tempPrecisePoint = new double[3];
    double rad = Math.toRadians(angle);
    double tx=getX()-center.getX();
    double ty=getY()-center.getY();
    double tz=getZ()-center.getZ();
    tempPrecisePoint[0]=tx*Math.cos(rad)+tz*Math.sin(rad);
    tempPrecisePoint[1]=ty;
    tempPrecisePoint[2]=-1*tx*Math.sin(rad)+tz*Math.cos(rad);
    x=tempPrecisePoint[0]+center.getX();
    y=tempPrecisePoint[1]+center.getY();
    z=tempPrecisePoint[2]+center.getZ();
  }
  public void rotateZ(double angle, PrecisePoint center){
    double[] tempPrecisePoint = new double[3];
    double rad = Math.toRadians(angle);
    double tx=getX()-center.getX();
    double ty=getY()-center.getY();
    double tz=getZ()-center.getZ();
    tempPrecisePoint[0]=tx*Math.cos(rad)-ty*Math.sin(rad);
    tempPrecisePoint[1]=tx*Math.sin(rad)+ty*Math.cos(rad);
    tempPrecisePoint[2]=ty;
    x=tempPrecisePoint[0]+center.getX();
    y=tempPrecisePoint[1]+center.getY();
    z=tempPrecisePoint[2]+center.getZ();
  }
  public void scale(double s){
    double[] tempPrecisePoint = new double[3];
    tempPrecisePoint[0]=getX()*s;
    tempPrecisePoint[1]=getY()*s;
    tempPrecisePoint[2]=getZ()*s;
    x=tempPrecisePoint[0];
    y=tempPrecisePoint[1];
    z=tempPrecisePoint[2];
  }
  public PrecisePoint add(PrecisePoint p){
    return new PrecisePoint(x+p.getX(),y+p.getY(),z+p.getZ());
  }
  public PrecisePoint subtract(PrecisePoint p){
    return new PrecisePoint(x-p.getX(),y-p.getY(),z-p.getZ());
  }
  public double dot(PrecisePoint p){
    return x*p.getX()+y*p.getY()+z*p.getZ();
  }
  public PrecisePoint cross(PrecisePoint p){
    return new PrecisePoint(getY()*p.getZ()-getZ()*p.getY(), getZ()*p.getX()-getX()*p.getZ(), getX()*p.getY()-getY()*p.getX());
  }

  public double getX(){
    return this.x;
  }
  public double getY(){
    return this.y;
  }
  public double getZ(){
    return this.z;
  }
  public double magnitude(){
    return Math.sqrt(Math.pow(getX(),2)+Math.pow(getY(),2)+Math.pow(getZ(),2));
  }

  public PrecisePoint clone(){
    return new PrecisePoint(getX(),getY(),getZ());
  }

  public String toString(){
    return getX()+", "+getY()+", "+getZ();
  }
  public int[] toInt(){
    return new int[]{
      (int)Math.round(getX()), (int)Math.round(getY()), (int)Math.round(getZ())
    };
  }
}
