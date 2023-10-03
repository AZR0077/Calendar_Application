import javax.swing.*;
import java.time.LocalDate;   
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;



public class CalendarSet {
  private Day[] arr;
  private int x;
  private int y;
  private String[] dates;
  private JButton set;
  private JButton edit;
  private JButton clear;

  public CalendarSet() {
    // Variables for Array
    x = 30;
    y = 180;

    arr = new Day[35];

    String[] dates_temp = {"Sun.", "Mon.", "Tue.", "Wed.", "Thu.", "Fri.", "Sat."};
    dates = dates_temp;
    
    Format f = new SimpleDateFormat("dd");
    String strDateNum = f.format(new Date());
    int dateNum = Integer.parseInt(strDateNum);

    // Create Calendar Array
    int tempCount = getFirstDay() - 1;
    for (int i = 1; i <= arr.length; i++) {
      if (i == dateNum + getFirstDay() - 1) {
        arr[i - 1] = new Day(i - (getFirstDay() - 1), dates[tempCount] + " *", x, y);
        tempCount++;
      }
      else if (i >= getFirstDay() && i < getFirstDay() + LocalDate.now().lengthOfMonth()) {
        arr[i - 1] = new Day(i - (getFirstDay() - 1), dates[tempCount], x, y);
        tempCount++; 
      }
      x += 135;
      if (i % 7 == 0) {
        tempCount = 0;
        x = 30;
        y += 150;
      }
    }
    
    // Saves Text to File
    set = new JButton("Save");
    set.setBounds(310, 100, 70, 30);
    set.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          try {
          Path path = Paths.get("save.txt");
          FileWriter myWriter = new FileWriter(path.toAbsolutePath().toString());
          for (int i = 0; i < 35; i++) {
            if (getFirstDay() - 1 <= i && i < getFirstDay() + LocalDate.now().lengthOfMonth() - 1) {
              myWriter.append("" + i + "\n");
              myWriter.append(arr[i].getText().getText() + "\n");
            }
            else {
              myWriter.append("" + i + "\n\n");
            }
          }
          myWriter.close();
          } 
          catch (IOException a) {
            System.out.println("An error occurred.");
            a.printStackTrace();
          }
        }
    });
    
    // Allows Editing of Text Boxes
    edit = new JButton("Edit");
    edit.setBounds(622, 100, 70, 30);
    edit.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          for (int i = getFirstDay() - 1; i < getFirstDay() + LocalDate.now().lengthOfMonth() - 1; i++) {
            arr[i].setEditableText();
          }
        }
    });

    // Clears Text Boxes
    clear = new JButton("Clear");
    clear.setBounds(468, 100, 70, 30);
    clear.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          for (int i = getFirstDay() - 1; i < getFirstDay() + LocalDate.now().lengthOfMonth() - 1; i++) {
            arr[i].changeText("");
          }
        }
    });

  }

  public JLabel getDayTitle(int day) {
    return arr[day].getToday();
  }

  public JTextArea getDayText(int day) {
    return arr[day].getText();
  }

  public Day getDay(int num) {
    return arr[num];
  }

  public int getFirstDay(){
    Calendar c=new GregorianCalendar();
    c.set(Calendar.DAY_OF_MONTH, 1);
    return c.get(Calendar.DAY_OF_WEEK);
  }

  public JButton getSet() {
    return set;
  }

  public JButton getEdit() {
    return edit;
  }

  public JButton getClear() {
    return clear;
  }
  
}