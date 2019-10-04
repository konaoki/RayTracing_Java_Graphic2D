import java.awt.*;
public class Pixel extends PrecisePoint{
  Color color;
  public Pixel(double x,double y,double z){
    super(x,y,z);
    color=Color.black;
  }
  public Pixel(PrecisePoint p){
    super(p);
    color=Color.black;
  }
  public void setColor(Color c){
    color=c;
  }
}
