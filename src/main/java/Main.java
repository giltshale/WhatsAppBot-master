
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.isDigit;

public class Main extends JFrame {
    private final JRadioButton btn1;
    private final JRadioButton btn2;
    private final JRadioButton btn3;

    public Main() {


        this.setTitle("Whatsapp-Bot");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);

        JButton btn = new JButton("Click here for login to your whats'app number");

        this.setLayout(new FlowLayout(FlowLayout.RIGHT, 100, 10));

        JLabel phoneLabel = new JLabel("Phone number:");
        TextField phoneField = new TextField(10);
        phoneField.setText("0526645417");


        // phoneLabel.setBounds(50,100,200,200);
        JLabel messageLabel = new JLabel("Enter a Message:");
        TextField messageField = new TextField(10);
        messageField.setText("fuck you!!");
        // messageLabel.setBounds(50,150,200,200);

        this.add(btn);
        this.add(phoneLabel);
        this.add(phoneField);
        this.add(messageLabel);
        this.add(messageField);

        ButtonGroup group = new ButtonGroup();
        btn1 = new JRadioButton("Message send ");
        btn2 = new JRadioButton("Message send and  arrived at her destination");
        btn3 = new JRadioButton("Message send , arrived at her destination and red ");
        group.add(btn1);
        group.add(btn2);
        group.add(btn3);


        this.setVisible(true);

        btn.addActionListener(e -> {
//selenium
                    System.setProperty("webdriver.chrome.driver",
                            "C:\\Users\\tasha\\IdeaProjects\\chromedriver_win32\\chromedriver.exe");
                    ChromeOptions chromeOptions = new ChromeOptions();
                    //  chromeOptions.addArguments("user-data-dir=C:\\Users\\tasha\\AppData\\Local\\Google\\Chrome\\User Data\\Default");

                    WebDriver driver = new ChromeDriver(chromeOptions);
                    driver.get("https://api.whatsapp.com/send?phone=972" + phoneField.getText().substring(1));

                    if (phoneField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Phone field is empty ",
                                "Hey!", JOptionPane.ERROR_MESSAGE);
                    } else if (!correctPhoneNumber(phoneField.getText())) {
                        JOptionPane.showMessageDialog(null, "Invalid Phone number ",
                                "Hey!", JOptionPane.ERROR_MESSAGE);
                    } else if (messageField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "massage field is empty ",
                                "Hey!", JOptionPane.ERROR_MESSAGE);
                    } else if (e.getSource() == btn) {


                        driver.findElement(By.id("action-button")).click();

                        driver.manage().window().maximize();

                        while (true) {
                            if (driver.findElements(By.id("_3arsE"))
                                    != null) {
                                System.out.println("Login successfully");
                                JLabel login = new JLabel("Login successfully");
                                this.add(login);
                                break;
                            }
                        }
                        this.add(btn1);
                        this.add(btn2);
                        this.add(btn3);

                        ///*  JOptionPane.showMessageDialog(null, "Login successfully",
                        //                              "Hey!", JOptionPane.PLAIN_MESSAGE);
                        //                        */
                        List<WebElement> textFieldElements = new ArrayList<>();
                        while (textFieldElements.isEmpty()) {
                            textFieldElements = driver.findElements(By.xpath("/html/body/div[1]/div/div/div[4]/div/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[2]"));
                        }

                        for (WebElement webElement : textFieldElements) {
                            WebElement textField = driver.findElement(By.cssSelector("#main > footer > div._2BU3P.tm2tP.copyable-area > div > span:nth-child(2) > div > div._2lMWa > div._3HQNh._1Ae7k > button > span"));
                            webElement.sendKeys(messageField.getText());
                            WebElement sendButton = driver.findElement(By.cssSelector("#main > footer > div._2BU3P.tm2tP.copyable-area > div > span:nth-child(2) > div > div._2lMWa > div._3HQNh._1Ae7k > button > span"));

                            if (textField != null && sendButton.isEnabled()) {
                                sendButton.click();
                                break;
                            } else {
                                webElement.sendKeys("");
                            }
                        }



                            WebElement n = driver.findElement(By.cssSelector("div[class=\"do8e0lj9 l7jjieqr k6y3xtnu\"]"));
                            while (n.toString() == null) {
                                n = driver.findElement(By.cssSelector("div[class=\"do8e0lj9 l7jjieqr k6y3xtnu\"]"));
                            }
                            try {
                                synchronized(n){
                                            n.wait(5000);
                                        }
                            } catch (InterruptedException ex) {
                                throw new RuntimeException(ex);
                            }

                            String f = n.findElement(By.cssSelector("span[data-testid=msg-dblcheck]")).getAttribute("aria-label");

                            switch (f) {
                                case " נשלחה " -> {
                                    System.out.println("נשלחה");
                                    this.btn1.doClick();
                                }
                                case " נמסרה " -> {
                                    System.out.println("נמסרה");
                                    this.btn2.doClick();
                                }
                                case " נקראה " -> {
                                    System.out.println("נקראה");
                                    this.btn3.doClick();
                                }
                            }

                                  }
                    this.setVisible(true);
                }
        );
        // btn.doClick();

    }


    private boolean correctPhoneNumber(String phoneNumber) {
        boolean correct = false;
        boolean onlyNumbers = false;
        for (int j = 0; j < phoneNumber.length(); j++) {
            if (isDigit(phoneNumber.charAt(j))) {
                onlyNumbers = true;
            }
        }

        if (onlyNumbers) {
            if (phoneNumber.startsWith("05") && phoneNumber.length() == 10) {
                correct = true;
            }
        }
        return correct;
    }

    public static void main(String[] args) throws Exception {
        new Main();


    }
}
