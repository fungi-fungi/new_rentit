package com.rentit.web;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rentit.Invoice;
import com.rentit.InvoiceStatuses;
import com.rentit.assembler.InvoiceResourceAssembler;
import com.rentit.repository.InvoiceRepository;
import com.rentit.rest.WebInvoiceResource;
import com.rentit.service.InvoiceService;

@RequestMapping("/sendReminder/**")
@Controller
public class SendReminderController {
	
	@Autowired
	InvoiceRepository invoiceRepository;

    @RequestMapping(method = RequestMethod.POST, value = "{id}")
    public void post(@PathVariable Long id, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }

    @RequestMapping
    public String index() {
        return "sendReminder/index";
    }
    
    
    @RequestMapping(method = RequestMethod.GET)
	public String showLatePaymant(Map<String, Object> map, HttpServletRequest request) {

		List<Invoice> invoices = invoiceRepository.findLate(new Date(), InvoiceStatuses.INVOICED);
		
		
		InvoiceResourceAssembler assembler = new InvoiceResourceAssembler();
		List<WebInvoiceResource> in = assembler.toWebResource(invoices);

		map.put("io", in);


		return "sendReminder/show";
	}
    
    
    @RequestMapping(method = RequestMethod.POST)
	public String sendMail(@RequestParam("invoiceId") Long invoiceId, Map<String, Object> map, HttpServletRequest request) {
    	
    	
    	Invoice invoice = invoiceRepository.findOne(invoiceId);
		

		InvoiceResourceAssembler invoiceAssembler = new InvoiceResourceAssembler();

    	ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath*:/META-INF/spring/applicationContext-InvoiceProcessing.xml");

		InvoiceService invoiceSender = (InvoiceService) context.getBean("mailMail");
		invoiceSender.setEmailToSend(invoice.getEmail());
		invoiceSender.setSubject("Remainder of invoice RentIt");
		invoiceSender.setFileName("invoice.xml");
		invoiceSender.setMultipartName("invoice");
		invoiceSender.setEmailFrom("rentit02@gmail.com");
		
		invoiceSender.sendInvoice(invoiceAssembler.toEmailResource(invoice));

		((ConfigurableApplicationContext)context).close();
		
		invoice.setStatus(InvoiceStatuses.REMAIDER_HAS_BEEN_SEND);
		invoice.persist();

		return showLatePaymant(map, request);
	}
}
