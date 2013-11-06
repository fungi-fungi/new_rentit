package com.rentit.service;

import java.io.StringWriter;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.rentit.soap.InvoiceResource;


@Component
public class InvoiceService {
	
	private MailSender mailSender;
	 
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	@ServiceActivator
	public void sendInvoice(InvoiceResource invoice) throws JAXBException {
		SimpleMailMessage mailMessage = new SimpleMailMessage();

		StringWriter result = new StringWriter();
        
		JAXBContext jaxbContext = JAXBContext.newInstance(InvoiceResource.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
 
		// output pretty printed
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
 
		jaxbMarshaller.marshal(invoice, result);
		String xml = result.toString();


		mailMessage.setFrom("rentit02@gmail.com");
		mailMessage.setTo("buildit02@gmail.com");
		mailMessage.setSentDate(new Date());
		mailMessage.setSubject("The payment is being processed");
		mailMessage.setText(xml);

		mailSender.send(mailMessage);
	}
}