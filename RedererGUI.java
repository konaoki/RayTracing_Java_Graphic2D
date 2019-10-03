import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

class RendererGUI extends JPanel implements ChangeListener
 {
 	RendererCanvas cc;
	JSlider xSlider;
 	public RendererGUI(RendererCanvas cc)
	{
		this.cc=cc;

		setLayout(new GridLayout(1,3,30,10));

    xSlider = new JSlider(JSlider.VERTICAL,-90,90,0);
		xSlider.setMajorTickSpacing(50);
		xSlider.setMinorTickSpacing(10);
		xSlider.setPaintTicks(true);
		xSlider.setPaintLabels(true);
		xSlider.addChangeListener(this);
		JLabel xlabel = new JLabel("X Angle");
		JPanel x = new JPanel();
		x.setLayout(new BoxLayout(x, BoxLayout.Y_AXIS));
		x.add(xlabel);
    x.add(xSlider);
		add(x);

   }//end contructor

   public void stateChanged(ChangeEvent ev)
   {
     //when state changes, change circle colors accordingly and repaint

     cc.model.rotateZ(xSlider.getValue(),new PrecisePoint(0,0,0));
     cc.repaint();
	 }//end stateChanged

}
