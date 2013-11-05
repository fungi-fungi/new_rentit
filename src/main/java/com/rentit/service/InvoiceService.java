package com.rentit.service;

import java.io.File;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;
import org.springframework.mail.SimpleMailMessage;

import com.renit.dto.InvoiceResource;

@Component
public class InvoiceService {
	@ServiceActivator
	public SimpleMailMessage sendInvoice(File invoice) throws JAXBException {
		SimpleMailMessage mailMessage = new SimpleMailMessage();

		//JAXBContext jaxbCtx = JAXBContext.newInstance(InvoiceResource.class);
		//InvoiceResource invoiceRes = (InvoiceResource) jaxbCtx.createUnmarshaller().unmarshal(invoice);

		mailMessage.setTo("psyfungus@gmail.com");
		mailMessage.setSentDate(new Date());
		mailMessage.setSubject("The payment is being processed");
		mailMessage.setText("Message here...");

		return mailMessage;
	}
}