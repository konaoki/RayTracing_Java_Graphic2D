import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
public class RendererController{
  public static void main(String[] args){
    int width=600;
    int height=600;
    RendererCanvas canvas = new RendererCanvas(width,height);
    //RendererGUI sliders = new RendererGUI(canvas);
    JFrame frame = new JFrame();
    frame.setTitle("ray casting");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    canvas.setPreferredSize(new Dimension(width,height));
    frame.add(canvas, BorderLayout.CENTER);

    //frame.add(sliders,BorderLayout.EAST);
    frame.pack();
    frame.setResizable(false);
    frame.setVisible(true);
  }
}
