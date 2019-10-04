import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
public class RendererController{
  public static void main(String[] args){
    RendererCanvas canvas = new RendererCanvas(1000,1000);
    //RendererGUI sliders = new RendererGUI(canvas);
    JFrame frame = new JFrame();
    frame.setTitle("ray casting");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    canvas.setPreferredSize(new Dimension(1000,1000));
    frame.add(canvas, BorderLayout.CENTER);

    //frame.add(sliders,BorderLayout.EAST);
    frame.pack();
    frame.setResizable(false);
    frame.setVisible(true);
  }
}
