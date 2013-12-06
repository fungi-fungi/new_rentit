package com.rentit.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.renit.rest.InvoiceToSendResource;

@Component
public class InvoiceService {

	private JavaMailSender mailSender;
	private String emailToSend;
	private String emailFrom;
	private String subject;
	private String text;
	private String fileName;
	private String multipartName;
	
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMultipartName() {
		return multipartName;
	}

	public void setMultipartName(String multipartName) {
		this.multipartName = multipartName;
	}	
	
	public String getEmailToSend() {
		return emailToSend;
	}

	public void setEmailToSend(String emailToSend) {
		this.emailToSend = emailToSend;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}


	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public String getEmailFrom() {
		return emailFrom;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}	
	
	
	public static void main(String[] args) throws Exception {
		
	}

	public void sendInvoice(InvoiceToSendResource invoice) {

		MimeMessage message = mailSender.createMimeMessage();
		try {

			message.setFrom(new InternetAddress(this.getEmailFrom()));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(this.getEmailToSend()));
			message.setSentDate(new Date());
			message.setSubject(this.getSubject());

			StringWriter result = new StringWriter();
			JAXBContext jaxbContext = JAXBContext.newInstance(InvoiceToSendResource.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(invoice, result);

			File temp = File.createTempFile("invoice", ".xml");
			temp.deleteOnExit();
			BufferedWriter out = new BufferedWriter(new FileWriter(temp));
			out.write(result.toString());
			out.close();

			MimeMultipart multipart = new MimeMultipart(this.getMultipartName());
			BodyPart messageBodyPart = new MimeBodyPart();

			// messageBodyPart.setText(result.toString());

			DataSource data = new FileDataSource(temp);
			messageBodyPart.setFileName(this.getFileName());
			messageBodyPart.setDataHandler(new DataHandler(data));
			multipart.addBodyPart(messageBodyPart);

			// put everything together
			message.setContent(multipart);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mailSender.send(message);
	}
}