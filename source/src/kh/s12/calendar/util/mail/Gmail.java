package kh.s12.calendar.util.mail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Gmail extends Authenticator {
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
    	Properties prop = new Properties();
    	try {
			prop.load(new FileInputStream("D:\\eclipse\\Semi_Project\\S12_ScoreMyDay\\source\\src\\kh\\s12\\calendar\\util\\mail\\gmail.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	String id=prop.getProperty("id");
    	String pw=prop.getProperty("pw");
    	System.out.println(id);
    	System.out.println(pw);
    	
        return new PasswordAuthentication(id, pw);
    }
}
