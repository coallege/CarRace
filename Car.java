import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

class Car {
   static int carWidth = 100;
   static int carHeight = 50;
   double x;
   double y;
   String title;

   public Car(int x, int y, String title) {
      this.x = x;
      this.y = y;
      this.title = title;
   }

   private int carBottom() {
      return (int) this.y + Car.carHeight;
   }

   private int carRight() {
      return (int) this.x + Car.carWidth;
   }

   void drawCar(Graphics g) {
      g.setColor(Color.RED);
      var x = (int) this.x;
      var y = (int) this.y;
      g.fillRect(x, y + 10, Car.carWidth, Car.carHeight - 20);

      g.setColor(Color.BLACK);
      var wheelHeight = this.carBottom() - 10;
      g.fillOval(this.carRight() - 5, wheelHeight - 5, 10, 10);
      g.fillOval(x - 5, wheelHeight - 5, 10, 10);

      g.drawString(this.title, x + 10, y + 10);
   }
}
