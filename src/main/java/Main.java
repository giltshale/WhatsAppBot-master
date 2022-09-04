import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebElement;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.isDigit;

public class Main extends JFrame {
    private final JCheckBox btn1;
    private final JCheckBox btn2;
    private final JCheckBox btn3;
    private WebDriver driver;

    public Main() {


        this.setTitle("Whatsapp-Bot");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);

        JButton btn = new JButton("Click here for login to your whats'app number");

        this.setLayout(new FlowLayout(FlowLayout.RIGHT, 100, 10));

        JLabel phoneLabel = new JLabel("Phone number:");
        TextField phoneField = new TextField(10);
        // phoneField.setText("0532206175");

        JLabel messageLabel = new JLabel("Enter a Message:");
        TextField messageField = new TextField(10);
        // messageField.setText("did you get it ? ");

        this.add(btn);
        this.add(phoneLabel);
        this.add(phoneField);
        this.add(messageLabel);
        this.add(messageField);

        ButtonGroup group = new ButtonGroup();
        btn1 = new JCheckBox("Message send ");
        btn2 = new JCheckBox("Message send and  arrived ");
        btn3 = new JCheckBox("Message send , arrived  and read ");
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
            this.driver = new ChromeDriver(chromeOptions);
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
                    if (driver.findElements(By.cssSelector("div[class=\"_1INL_ _1iyey A_WMk _1UG2S\"]"))
                            != null) {
//                                System.out.println("Login successfully");
                        JLabel login = new JLabel("Login successfully");
                        this.add(login);
                        break;
                    }
                }
                this.add(btn1);
                this.add(btn2);
                this.add(btn3);


                List<WebElement> textFieldElements = new ArrayList<>();
                while (textFieldElements.isEmpty()) {
                    textFieldElements = driver.findElements(By.cssSelector("p[class=\"selectable-text copyable-text\"]"));
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
                    synchronized (n) {
                        n.wait(3000);
                    }
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                Thread statusChecker = new Thread(() -> {
                    int count1 = 0;
                    int count2 = 0;
                    String checkOrDblCheck = null;

                    while (true) {
                        WebElement lastMsg = getLastMessage().findElement(By.cssSelector("div[data-testid=\"msg-container\"]"));

                        try {

                            WebElement msgCheck = lastMsg.findElement(By.xpath("//span[@data-testid=\"msg-check\"]"));

                            if (msgCheck != null) {
                                checkOrDblCheck = "check";
                            }
                        } catch (NoSuchElementException noSuchElementException) {
                            checkOrDblCheck = "dblcheck";
                        }

                        String msgStatus = lastMsg.findElement(By.cssSelector("span[data-testid=\"msg-" + checkOrDblCheck + "\"]")).getAttribute("aria-label");
                        if (msgStatus.equals(" נשלחה ")) {
                            if (count1 == 0) {
                                this.btn1.doClick();
                                btn1.setVisible(true);
                                btn2.setVisible(false);
                                btn3.setVisible(false);
                                count1++;
                            }
                        } else if (msgStatus.equals(" נמסרה ")) {
                            if (count2 == 0) {
                                this.btn2.doClick();
                                btn1.setVisible(false);
                                btn2.setVisible(true);
                                btn3.setVisible(false);
                                count2++;
                            }
//                                    System.out.println("not yet ");

                        } else {
//                                    System.out.println("received");
                            this.btn3.doClick();
                            btn1.setVisible(false);
                            btn2.setVisible(false);
                            btn3.setVisible(true);
                            driver.close();
                        }
                    }
                });
                statusChecker.start();

                this.setVisible(true);
            }
        });

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

    public WebElement getLastMessage() {
        boolean isFound = false;
        WebElement lastMassageElement = new RemoteWebElement();
        while (!isFound) {
            try {
                List<WebElement> myMassages = this.driver.findElements(By.cssSelector("div[class=\"_1-FMR message-out focusable-list-item\"]"));
                lastMassageElement = myMassages.get(myMassages.size() - 1);
                isFound = true;
            } catch (Exception ignored) {
            }
        }
        return lastMassageElement;
    }


    public static void main(String[] args) throws Exception {
        new Main();
    }


}