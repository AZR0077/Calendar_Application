import java.awt.*;
import javax.swing.*;



public class Day {

  private int dateVar;
  private String wordDate;
  private JLabel today;
  private JTextArea content;
  private int xVar;
  private int yVar;
  
  public Day(int date, String wordDate, int x, int y) {
    dateVar = date;
    this.wordDate = wordDate;
    xVar = x;
    yVar = y;
    
    // Date Box (Top)
    today = new JLabel(wordDate + " " + dateVar,  SwingConstants.CENTER);
    today.setBounds(xVar, yVar, 134, 30);
    today.setBorder(BorderFactory.createLineBorder(Color.black));
    
    // Text Box (Bottom)
    content = new JTextArea("");
    content.setEditable(false);
    content.setPreferredSize(new Dimension(50, 50));
    content.setBounds(xVar, yVar + 29, 134, 120);
    content.setBorder(BorderFactory.createLineBorder(Color.black));
  }

  public JLabel getToday() {
    return today;
  }

  public JTextArea getText() {
    return content;
  }

  public String toString() {
    return wordDate;
  }

  public void setEditableText() {
    content.setEditable(true);
  }

  public void changeText(String text) {
    content.setText(text);
  }
}