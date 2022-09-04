
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.text.Element;
import java.awt.*;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Character.isDigit;
import static java.lang.Thread.sleep;

public class Main extends JFrame {
    private final JCheckBox btn1;
    private final JCheckBox btn2;
    private final JCheckBox btn3;
    public static final String MESSAGE_SENT = " נשלחה ";
    //;
    public static final String MESSAGE_SENT_RECEIVED = " נמסרה ";
    //;
    public static final String MESSAGE_SENT__RECEIVED_READ = " נקראה ";
    //;

    public Main() {
        this.setTitle("Whatsapp-Bot");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);

        JButton btn = new JButton("Click here for login to your whats'app number");

        this.setLayout(new FlowLayout(FlowLayout.RIGHT, 100, 10));

        JLabel phoneLabel = new JLabel("Phone number:");
        TextField phoneField = new TextField(10);
        phoneField.setText("0526645417");
        //0532206175


        // phoneLabel.setBounds(50,100,200,200);
        JLabel messageLabel = new JLabel("Enter a Message:");
        TextField messageField = new TextField(10);
        messageField.setText("///////");
        // messageLabel.setBounds(50,150,200,200);

        this.add(btn);
        this.add(phoneLabel);
        this.add(phoneField);
        this.add(messageLabel);
        this.add(messageField);

        ButtonGroup group = new ButtonGroup();
        btn1 = new JCheckBox(" send ");
        btn2 = new JCheckBox(" send ,  arrived ");
        btn3 = new JCheckBox(" send , arrived ,read");
        group.add(btn1);
        group.add(btn2);
        group.add(btn3);

        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\tasha\\IdeaProjects\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("user-data-dir=C:\\Users\\tasha\\AppData\\Local\\Google\\Chrome\\User Data\\Default");
        WebDriver driver = new ChromeDriver(chromeOptions);
        this.setVisible(true);

        btn.addActionListener(e -> {
//selenium
//                    System.setProperty("webdriver.chrome.driver",
//                            "C:\\Users\\tasha\\IdeaProjects\\chromedriver_win32\\chromedriver.exe");
//                    ChromeOptions chromeOptions = new ChromeOptions();
//                    chromeOptions.addArguments("user-data-dir=C:\\Users\\tasha\\AppData\\Local\\Google\\Chrome\\User Data\\Default");
//                    WebDriver driver = new ChromeDriver(chromeOptions);
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

                        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                        List<WebElement> allMassages = new ArrayList<>();
                        SimpleDateFormat sdf = new SimpleDateFormat("[HH:mm, dd-MM-yyyy]");
                        Date date1;
                        Date date2;

                        allMassages = driver.findElements(By.xpath("//*[@id=\"main\"]/div[3]/div/div[2]/div[3]"));
                      //  int lastElementLocation = allMassages.size() - 1;
                        List<Object> w ;
                           w= List.of(allMassages.toArray());
                       // }
                        System.out.println(w.size());


                    /*   WebElement last = allMassages.get(lastElementLocation);
                        System.out.println(last.getText());
                        System.out.println(last.getSize());
                        try {
                            synchronized (last) {
                                last.wait(1000);
                            }
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }

                        String startTime = last.findElement(By.xpath("//*[@id=\"main\"]/div[3]/div/div[2]/div[3]/div[17]/div/div[1]/div[1]/div[2]/div/span")).getText();
                        System.out.println(startTime);                        String dateElementOfMe = last.findElement(By.xpath("/html/body/div[1]/div/div/div[4]/div/div[3]/div/div[2]/div[3]/div[17]/div/div[1]/div[1]/div[1]")).getAttribute("data-pre-plain-text");
                        String receiverDateElement ;
                        if (!isSentAfterMe(last)) {
                            receiverDateElement = dateElementOfMe;
                        } else {
//                            WebElement receiverLast = allMassages.get(lastElementLocation - 1);
//                            receiverDateElement = receiverLast.findElement(By.xpath("/html/body/div[1]/div/div/div[4]/div/div[3]/div/div[2]/div[3]/div[17]/div/div[1]/div[1]/div[1]")).getAttribute("data-pre-plain-text");
                        }

                       // System.out.println(startTime);
                        System.out.println(dateElementOfMe);

                        try {
                            date1 = sdf.parse(String.valueOf(correctDate(dateElementOfMe)));
                        } catch (ParseException ex) {
                            throw new RuntimeException(ex);
                        }
                        try {
                           // assert receiverDateElement != null;
                            date2 = sdf.parse(correctDate(receiverDateElement));
                        } catch (ParseException ex) {
                            throw new RuntimeException(ex);
                        }

                        while (date1.compareTo(date2) < 0) {
//                            WebElement formatTime=element.findElement(By.cssSelector("#main > div._2gzeB > div > div._33LGR > div._3K4-L > div:nth-child(16) > div > div.Nm1g1._22AX6 > div._22Msk > div.copyable-text"));

                            String d = last.findElement(By.cssSelector("span[class=\"l7jjieqr fewfhwl7\"]")).getText();
                            System.out.println("i think " + d);
                            boolean isItMe = last.findElement(By.cssSelector("#main > div._2gzeB > div > div._33LGR > div._3K4-L > div:nth-child(30) > div > div.Nm1g1._22AX6 > span:nth-child(1)")).getAttribute("aria-label").equals("את/ה:");
                            //  if (!isItMe&&()){}
                        }

                     */
                        // class="cvjcv _1Ilru _8YVHI"


                        allMassages.add(driver.findElement(By.cssSelector("div[tabindex=\"-1\"]")));
                        System.out.println(allMassages.size());


                        WebElement returnMassage = driver.findElement(By.cssSelector("div[class=_3K4-L]"));
                        returnMassage.getAttribute("data-id");
                        System.out.println(returnMassage.getAttribute("data-id"));

                        //////////////////////////////////////////////////////////////////////////////////////////////

                        for (WebElement webElement : textFieldElements) {
                            WebElement textField = driver.findElement(By.cssSelector("#main > footer > div._2BU3P.tm2tP.copyable-area > div > span:nth-child(2) > div > div._2lMWa > div._3HQNh._1Ae7k > button > span"));
                            webElement.sendKeys(messageField.getText());

                            WebElement sendButton = driver.findElement(By.cssSelector("#main > footer > div._2BU3P.tm2tP.copyable-area > div > span:nth-child(2) > div > div._2lMWa > div._3HQNh._1Ae7k > button > span"));

                            if (textField != null && sendButton.isEnabled()) {
                                sendButton.click();
                                String sendTime = driver.findElement(By.cssSelector("#main > div._2gzeB > div > div._33LGR > div._3K4-L > div:nth-child(20) > div > div.Nm1g1._22AX6 > div._22Msk > div.copyable-text")).getAttribute("data-pre-plain-text");
                                StringBuilder correctSentTime = new StringBuilder();
                                for (int i = 0; i < sendTime.length(); i++) {

                                    if (!(sendTime.charAt(i) == ','))
                                        break;
                                    else if (sendTime.charAt(i) == '[')
                                        continue;
                                    correctSentTime.append(sendTime.charAt(i));
                                    System.out.println(correctSentTime);
                                }

                            } else {
                                webElement.sendKeys("");
                            }
                        }

                        System.out.println(textFieldElements.size());


                        WebElement checkStatus = driver.findElement(By.cssSelector("div[class=\"do8e0lj9 l7jjieqr k6y3xtnu\"]"));
                        while (checkStatus.toString() == null) {
                            checkStatus = driver.findElement(By.cssSelector("div[class=\"do8e0lj9 l7jjieqr k6y3xtnu\"]"));
                        }
                        try {
                            synchronized (checkStatus) {
                                checkStatus.wait(500);
                            }
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                        //Date date=new Date(2022, Calendar.JANUARY,12);


                        String r = checkStatus.findElement(By.cssSelector("span[data-testid=msg-dblcheck]")).getAttribute("class");

                        String f = checkStatus.findElement(By.cssSelector("span[data-testid=msg-dblcheck]")).getAttribute("aria-label");

                        switch (f) {
                            case MESSAGE_SENT -> {
                                this.btn1.doClick();
                                btn2.setEnabled(false);
                                btn3.setEnabled(false);
                            }


                            case MESSAGE_SENT_RECEIVED -> {
                                btn2.setEnabled(true);
                                this.btn2.doClick();
                                btn3.setEnabled(false);
                                btn1.setEnabled(false);
                            }

//                              case MESSAGE_SENT__RECEIVED_READ -> {}
                        }
                        do {

                            if (r.equals("_3l4_3")) {

                                btn3.setEnabled(true);
                                this.btn3.doClick();
                                btn1.setEnabled(false);
                                btn2.setEnabled(false);
                            }
                            this.setVisible(true);

                            r = checkStatus.findElement(By.cssSelector("span[data-testid=msg-dblcheck]")).getAttribute("class");
                            if (r.equals("_3l4_3")) {
                                btn3.setEnabled(true);
                                this.btn3.doClick();
                                btn1.setEnabled(false);
                                btn2.setEnabled(false);

                            }
//                                WebElement returnMassage = driver.findElement(By.cssSelector("div[class=_3K4-L]"));
//                                returnMassage.getAttribute("data-id");
//                                System.out.println(returnMassage.getAttribute("data-id"));
                        } while (!f.equals(MESSAGE_SENT__RECEIVED_READ));

                    }
                    this.setVisible(true);
                }


                //    System.out.println(r);
//                        });
//                        t.start()

                // this.setVisible(true);

        );

        // btn.doClick();

    }

    private boolean isSentAfterMe(WebElement webElement) {
        return webElement.findElement(By.xpath("//*[@id=\"main\"]/div[3]/div/div[2]/div[3]/div[12]/div/div[1]")).equals("את/ה:");
    }

    private String correctDate(String b) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < b.length(); i++) {
            if (b.charAt(i) == ']')
                break;
            s.append(b.charAt(i));
        }

        s.append(']');

        System.out.println(s);
        return String.valueOf(s);
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


    //<span data-testid="msg-dblcheck" aria-label=" נקראה " data-icon="msg-dblcheck" class="_3l4_3"><svg viewBox="0 0 16 15" width="16" height="15" class=""><path fill="currentColor" d="m15.01 3.316-.478-.372a.365.365 0 0 0-.51.063L8.666 9.879a.32.32 0 0 1-.484.033l-.358-.325a.319.319 0 0 0-.484.032l-.378.483a.418.418 0 0 0 .036.541l1.32 1.266c.143.14.361.125.484-.033l6.272-8.048a.366.366 0 0 0-.064-.512zm-4.1 0-.478-.372a.365.365 0 0 0-.51.063L4.566 9.879a.32.32 0 0 1-.484.033L1.891 7.769a.366.366 0 0 0-.515.006l-.423.433a.364.364 0 0 0 .006.514l3.258 3.185c.143.14.361.125.484-.033l6.272-8.048a.365.365 0 0 0-.063-.51z"></path></svg></span>
//<span data-testid="msg-dblcheck" aria-label=" נקראה " data-icon="msg-dblcheck" class="_3l4_3"><svg viewBox="0 0 16 15" width="16" height="15" class=""><path fill="currentColor" d="m15.01 3.316-.478-.372a.365.365 0 0 0-.51.063L8.666 9.879a.32.32 0 0 1-.484.033l-.358-.325a.319.319 0 0 0-.484.032l-.378.483a.418.418 0 0 0 .036.541l1.32 1.266c.143.14.361.125.484-.033l6.272-8.048a.366.366 0 0 0-.064-.512zm-4.1 0-.478-.372a.365.365 0 0 0-.51.063L4.566 9.879a.32.32 0 0 1-.484.033L1.891 7.769a.366.366 0 0 0-.515.006l-.423.433a.364.364 0 0 0 .006.514l3.258 3.185c.143.14.361.125.484-.033l6.272-8.048a.365.365 0 0 0-.063-.51z"></path></svg></span>
}
