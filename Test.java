import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Test {
   public static void main(String[] a) {
      var rc = new RaceTrack();
      var frame = new JFrame();
      frame.setLayout(new BorderLayout());
      frame.add(rc, BorderLayout.CENTER);
      frame.pack();
      frame.setVisible(true);
      frame.createBufferStrategy(4); // stops the flickering
   }
}
