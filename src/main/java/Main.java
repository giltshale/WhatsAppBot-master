
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import static java.lang.Character.isDigit;

public class Main extends JFrame {
    public Main() {
        //Panel p=new Panel(new FlowLayout(FlowLayout.LEADING));
        this.setTitle("Whatsapp-Bot");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);

        JButton btn = new JButton("Click here for login to your whats'app number");


        this.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));

        JLabel phoneLabel = new JLabel("Phone number:");
        TextField phoneField = new TextField(10);

        // phoneLabel.setBounds(50,100,200,200);
        JLabel messageLabel = new JLabel("Enter a Message:");
        TextField messageField = new TextField(10);
        // messageLabel.setBounds(50,150,200,200);


        // TODO: add placeholders
        this.add(btn);
        this.add(phoneLabel);
        this.add(phoneField);
        this.add(messageLabel);
        this.add(messageField);
        this.setVisible(true);

        //selenium
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\tasha\\IdeaProjects\\chromedriver_win32\\chromedriver.exe");


        btn.addActionListener(e -> {
            if (phoneField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Phone field is empty ",
                        "Hey!", JOptionPane.ERROR_MESSAGE);
            } else if (!correcPhoneNumber(phoneField.getText())) {
                JOptionPane.showMessageDialog(null, "Invalid Phone number ",
                        "Hey!", JOptionPane.ERROR_MESSAGE);
            } else if (messageField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "massage field is empty ",
                        "Hey!", JOptionPane.ERROR_MESSAGE);
            } else if (e.getSource() == btn) {
                ChromeDriver driver = new ChromeDriver();
                driver.get("https://web.whatsapp.com/");
                driver.manage().window().maximize();
                while (true) {
                    if (driver.findElements(By.className("_3ArsE")).size() == 1) {
                        System.out.println("Login successfully");
                        JLabel successLabel = new JLabel("Login successfully");
                        this.add(successLabel);
                        this.setVisible(true);

                        break;

                    }
                }

            }
        });

    }

    private boolean correcPhoneNumber(String phoneNumber) {
         boolean correct=false;
        boolean onlyNumbers = false;
        for (int j = 0; j < phoneNumber.length(); j++) {
            if (isDigit(phoneNumber.charAt(j))) {
                onlyNumbers = true;
            }
        }

        if (onlyNumbers) {
         if(phoneNumber.startsWith("05")&&phoneNumber.length()==10){
           correct=true;
         }
        }
        return correct;
    }

    public static void main(String[] args) throws ParseException {
        new Main();


//        if (topArticle!=null) {
//            topArticle.click();
//        }
//        WebElement searchTextField=driver.findElement(By.id("one-search"));
//        if (searchTextField!=null){
//            searchTextField.sendKeys("Shai Givati");
//        }

    }
}
