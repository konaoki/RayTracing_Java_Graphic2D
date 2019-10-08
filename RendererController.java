import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
public class RendererController{
  public static void main(String[] args){
    int width=300;
    int height=300;
    RendererCanvas canvas = new RendererCanvas(width,height);
    RendererGUI sliders = new RendererGUI(canvas);
    JFrame frame = new JFrame();
    frame.setTitle("Ray Tracer");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    canvas.setPreferredSize(new Dimension(width,height));
    frame.add(canvas, BorderLayout.CENTER);

    frame.add(sliders,BorderLayout.EAST);
    frame.pack();
    frame.setResizable(false);
    frame.setVisible(true);


    int delay = 20; // milliseconds
    ActionListener taskPerformer = new ActionListener() {
       public void actionPerformed(ActionEvent evt) {
         canvas.camera = new Camera(new PrecisePoint(0,0,1000),new PrecisePoint(0,0,-10),width,height);
         canvas.camera.lightSource=canvas.camera.position.clone().setZ(0).setY(canvas.camera.position.getY()+sliders.xSlider.getValue());
         canvas.transformedModel = canvas.transformedModel.rotateY(1,canvas.transformedModel.getCenter());
         canvas.repaint();
       }
    };
    new Timer(delay, taskPerformer).start();
  }
}
