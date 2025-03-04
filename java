import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
public class ParkingManagementSystem extends JFrame {
 private Map<Integer, String> parkingSlots;
 private JLabel timeLabel;
 private JLabel statusLabel;
 private JButton parkButton;
 private JButton exitButton;
 private JButton infoButton;
 private JButton payButton;
 public ParkingManagementSystem() {
 setTitle("Parking Management System");
 setSize(400, 300);
 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 setLayout(new FlowLayout());
 parkingSlots = new HashMap<>();
 for (int i = 1; i <= 200; i++) {
 parkingSlots.put(i, null);
 }
 timeLabel = new JLabel();
 statusLabel = new JLabel();
 parkButton = new JButton("Park Car");
 exitButton = new JButton("Exit");
 infoButton = new JButton("Information");
 payButton = new JButton("Pay");
 parkButton.addActionListener(new ActionListener() {
 @Override
 public void actionPerformed(ActionEvent e) {
 String vehicleNumber = JOptionPane.showInputDialog("Enter Vehicle
Number:");
 int slotNumber = findAvailableSlot();
 if (slotNumber != -1) {
 parkingSlots.put(slotNumber, vehicleNumber);
 JOptionPane.showMessageDialog(null, "Vehicle parked in slot " +
slotNumber);
 } else {
 JOptionPane.showMessageDialog(null, "No available slots");
 }
 }
 });
 exitButton.addActionListener(new ActionListener() {
 @Override
 public void actionPerformed(ActionEvent e) {
 String vehicleNumber = JOptionPane.showInputDialog("Enter Vehicle
Number:");
 int slotNumber = findSlotByVehicleNumber(vehicleNumber);
 if (slotNumber != -1) {
 parkingSlots.put(slotNumber, null);
 int hoursParked = calculateHoursParked(slotNumber);
 int amountToPay = calculateAmountToPay(hoursParked);
 JOptionPane.showMessageDialog(null, "Vehicle " + vehicleNumber +
" exited from slot " + slotNumber + "\nAmount to pay: " + amountToPay);
 } else {
 JOptionPane.showMessageDialog(null, "Vehicle not found");
 }
 }
 });
 infoButton.addActionListener(new ActionListener() {
 @Override
 public void actionPerformed(ActionEvent e) {
 StringBuilder sb = new StringBuilder();
 for (int slotNumber : parkingSlots.keySet()) {
 String vehicleNumber = parkingSlots.get(slotNumber);
 if (vehicleNumber != null) {
 sb.append("Slot ").append(slotNumber).append(":
").append(vehicleNumber).append("\n");
 } else {
 sb.append("Slot ").append(slotNumber).append(": Empty\n");
 }
 }
 JOptionPane.showMessageDialog(null, sb.toString());
 }
 });
 payButton.addActionListener(new ActionListener() {
 @Override
 public void actionPerformed(ActionEvent e) {
 Random random = new Random();
 int counterNumber = random.nextInt(3) + 1;
 JOptionPane.showMessageDialog(null, "Please go to counter " +
counterNumber + " for payment");
 }
 });
 add(timeLabel);
 add(statusLabel);
 add(parkButton);
 add(exitButton);
 add(infoButton);
 add(payButton);
 Timer timer = new Timer(1000, new ActionListener() {
 @Override
 public void actionPerformed(ActionEvent e) {
 updateTime();
 }
 });
 timer.start();
 }
 private int findAvailableSlot() {
 for (int slotNumber : parkingSlots.keySet()) {
 if (parkingSlots.get(slotNumber) == null) {
 return slotNumber;
 }
 }
 return -1;
 }
 private int findSlotByVehicleNumber(String vehicleNumber) {
 for (int slotNumber : parkingSlots.keySet()) {
 if (vehicleNumber.equals(parkingSlots.get(slotNumber))) {
 return slotNumber;
 }
 }
 return -1;
 }
 private int calculateHoursParked(int slotNumber) {
 // Calculate hours parked based on current time and time parked
 return 0;
 }
 private int calculateAmountToPay(int hoursParked) {
 int amount = 30; // Rate for the first hour
 if (hoursParked > 1) {
 amount += (hoursParked - 1) * 10; // Additional rate for each additional
hour
 }
 return amount;
 }
 private void updateTime() {
 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd
HH:mm:ss");
 String currentTime = sdf.format(new Date());
 timeLabel.setText("Current Time: " + currentTime);
 }
 public static void main(String[] args) {
 SwingUtilities.invokeLater(new Runnable() {
 @Override
 public void run() {
 new ParkingManagementSystem().setVisible(true);
 }
 });
 }
} 
