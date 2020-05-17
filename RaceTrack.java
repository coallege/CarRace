import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;

@SuppressWarnings("serial")
public class RaceTrack extends JPanel implements ActionListener {
   private static final int buttonOffset = 100;
   private static final Dimension preferredSize = new Dimension(800, 600);
   
   private final int numberOfCars;
   private final Timer timer = new Timer(15, this);
   private final Car[] cars;

   private final JButton start = new JButton("Start");
   private final JButton reset = new JButton("Reset");

   RaceTrack() {
      var userInput = JOptionPane.showInputDialog("How many cars do you want to race?");
      if (userInput == null || userInput.length() == 0) {
         System.exit(1);
      }
      this.numberOfCars = Integer.parseInt(userInput);
      this.cars = new Car[numberOfCars];

      for (var i = 0; i < this.numberOfCars; ++i) {
         var carHeight = i * Car.carHeight + RaceTrack.buttonOffset;
         this.cars[i] = new Car(
            0, // default x position
            carHeight,
            "Car " + (i + 1) // title
         );
      }

      super.add(this.start);
      super.add(this.reset);
      super.setPreferredSize(RaceTrack.preferredSize);
      this.start.addActionListener((e) -> this.timer.start());
      this.reset.addActionListener((e) -> this.reset());
   }

   private void drawLines(Graphics g) {
      g.setColor(Color.black);
      var offset = RaceTrack.buttonOffset + Car.carHeight;
      for (var i = 0; i < this.numberOfCars; ++i) {
         var y = i * Car.carHeight + offset;
         g.drawLine(0, y, super.getWidth(), y);
      }
   }

   private void clear(Graphics g) {
      g.setColor(Color.WHITE);
      g.fillRect(0, RaceTrack.buttonOffset, super.getWidth(), super.getHeight());
   }

   private void drawCars(Graphics g) {
      for (var car : this.cars) {
         car.drawCar(g);
      }
   }

   private void drawRace(Graphics g) {
      this.clear(g);
      this.drawLines(g);
      this.drawCars(g);
   }

   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      this.drawRace(g);
   }

   private final Random rand0 = new Random();

   private double randomDoubleBetween1and5inclusive() {
      return rand0.nextDouble() * 5 + 1;
   }

   private void tickCars() {
      for (var car : this.cars) {
         car.x += this.randomDoubleBetween1and5inclusive();
      }
   }

   /**
    * Returns null if no car won
    */
   private Car carThatWon() {
      for (var car : this.cars) {
         if (car.x + Car.carWidth >= super.getWidth()) {
            return car;
         }
      }
      return null;
   }

   /**
    * basically the tick method
    */
   @Override
   public void actionPerformed(ActionEvent e) {
      var carThatWon = this.carThatWon();
      if (carThatWon != null) {
         System.out.println(carThatWon);
         this.timer.stop();
         return;
      }

      tickCars();
      var g = super.getGraphics();
      if (g != null) {
         this.drawRace(g);
      }
   }

   private void reset() {
      for (var car : this.cars) {
         car.x = 0;
      }
      var g = super.getGraphics();
      if (g != null) {
         this.drawRace(g);
      }
   }
}
