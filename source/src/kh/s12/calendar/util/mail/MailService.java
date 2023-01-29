package kh.s12.calendar.util.mail;

import java.io.*;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class MailService {
	public MailService() {}

	public static int mailCode(String memail, String code) {
		int result = 0;
		try{
			Properties prop = new Properties();
			prop.load(new FileInputStream("D:\\eclipse\\Semi_Project\\S12_ScoreMyDay\\source\\src\\kh\\s12\\calendar\\util\\mail\\gmail.properties"));
			
			String from = prop.getProperty("id");
			String to = memail;
			String subject = "[ScoreMyDate] 회원가입 인증코드 메일입니다.";
			String content = "다음 코드를 이메일 인증 코드 란에 입력해 주세요. \n" + "[ "+code+" ]";
			
			prop.put("mail.smtp.starttls.enable","true");
			prop.put("mail.smtp.user", from);
			prop.put("mail.smtp.host", "smtp.gmail.com");
			prop.put("mail.smtp.port", "465");
			prop.put("defaultEncoding", "utf-8");
			prop.put("mail.smtp.auth", "true");
			//prop.put("mail.smtp.debug", "true");
			
			prop.put("mail.smtp.socketFactory.port", "465");
			prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			prop.put("mail.smtp.socketFactory.fallback", "false");
			
			Authenticator auth = new Gmail();
			Session sess = Session.getInstance(prop, auth);
			sess.setDebug(true);
			MimeMessage msg = new MimeMessage(sess);
			
		    msg.setSubject(subject);
		    Address fromAddr = new InternetAddress(from);
		    msg.setFrom(fromAddr);
		    Address toAddr = new InternetAddress(to);
			//받는 사람의 주소.
		    msg.addRecipient(Message.RecipientType.TO, toAddr);
			//메일 안에 담길 내용. 인코딩해서 전송.
		    msg.setContent(content, "text/html;charset=UTF-8");
			//실제로 메세지를 보낸다.
		    Transport.send(msg);
		    
		    result = 1;
		} catch(Exception e){
			e.printStackTrace();
		}
		//result 1: 성공, 0: 실패
		return result;
	}
	
	public static int codeSend(String memail, String code) { //인증번호 전송 버튼 클릭
		System.out.println("codeSend 호출 성공");
		int result = 0;
		int mcs = mailCode(memail, code);
		
		if(mcs == 1) { //코드 전송 성공한 경우
			System.out.println("MailService.codeSend " + memail + "코드 전송 성공!");
			result = 1;
		} else { //코드 전송 실패한 경우. 메일이 유효한지 확인 메시지 출력
			System.out.println("MailService.codeSend " + memail + "코드 전송 실패!");
		}
		
		return result;
	}
	
	public static int codeChk(String code, String mailCode) {//확인 버튼 클릭
		int result = 0;
			if(code.equals(mailCode)) { // 메일 코드 일치
				System.out.println("코드 일치");
				result = 1;
			} else {
				System.out.println("코드 불일치");
			}
		
		return result;
	}
}
