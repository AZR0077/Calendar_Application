import java.awt.*;
import javax.swing.*;
import java.time.LocalDate;   
import java.util.Calendar;
import java.util.Locale;
import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
 



public class Main {

  private static void createAndShowGUI() {
    // Frame
    JFrame jFrame = new JFrame("Calendar Application");
    jFrame.setLayout(null);
    jFrame.setSize(1020, 1020);
    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Array With Days
    Calendar c = Calendar.getInstance();
    JLabel monthTitle = new JLabel(c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH), SwingConstants.CENTER);
    jFrame.add(monthTitle);
    monthTitle.setBounds(435, 25, 134, 50);
    monthTitle.setBorder(BorderFactory.createLineBorder(Color.black));

    CalendarSet list = new CalendarSet();
    
    // Add Items to Frame
    for (int i = list.getFirstDay() - 1; i < list.getFirstDay() + LocalDate.now().lengthOfMonth() - 1; i++) {
      jFrame.add(list.getDayTitle(i));
      jFrame.add(list.getDayText(i));
    }

    jFrame.add(list.getSet());
    jFrame.add(list.getEdit());
    jFrame.add(list.getClear());

    // Save, Edit, and Write Day Text Boxes
    try {
      Path path = Paths.get("save.txt");
      File myObj = new File(path.toAbsolutePath().toString());
      Scanner myReader = new Scanner(myObj);
      for (int i = 0; i < 35; i++) {
        if (list.getFirstDay() - 1 <= i && i < list.getFirstDay() + LocalDate.now().lengthOfMonth() - 1) {
          myReader.nextLine();
          String data = myReader.nextLine();
          list.getDay(i).changeText(data);
        }
        else {
          myReader.nextLine();
          myReader.nextLine();
        }
      }    
      myReader.close();
    }   
    catch (FileNotFoundException a) {
      System.out.println("An error occurred.");
      a.printStackTrace();
    }

    jFrame.setVisible(true);
  }
  
  // Create GUI
  public static void main(String[] args) {
    createAndShowGUI();
  }
  
}