package com.rentit.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.renit.rest.InvoiceResource;
import com.renit.rest.WebPurchaseOrderResource;
import com.rentit.Invoice;
import com.rentit.InvoiceStatuses;
import com.rentit.PurchaseOrder;
import com.rentit.PurchaseOrderStatuses;
import com.rentit.assembler.InvoiceResourceAssembler;
import com.rentit.assembler.WebPurchaseOrderAssembler;
import com.rentit.repository.InvoiceRepository;
import com.rentit.repository.PurchaseOrderRepository;
import com.rentit.service.InvoiceService;
import com.rentit.soap.client.PoStatusUpdateRequest;

@RequestMapping("/sendinvoice/**")
@Controller
public class SendInvoiceController {

	@Autowired
	InvoiceRepository invoiceRepository;

	@RequestMapping(method = RequestMethod.POST, value = "{id}")
	public void post(@PathVariable Long id, ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response) {
	}

	@RequestMapping
	public String index() {
		return "sendinvoice/index";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showInvoices(Map<String, Object> map,
			HttpServletRequest request) {

		List<Invoice> invoices = invoiceRepository.findByStatus(InvoiceStatuses.PANDING);
		InvoiceResourceAssembler assembler = new InvoiceResourceAssembler();
		List<InvoiceResource> po = assembler.toResource(invoices);

		map.put("invoice", po);
		map.put("postatusupdate", new PoStatusUpdateRequest());

		return "sendinvoice/show";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String sendInvoice(@RequestParam("purchaseId") Long invoiceId, Map<String, Object> map, HttpServletRequest request) {

		Invoice invoice = invoiceRepository.findOne(invoiceId);
		

		InvoiceResourceAssembler invoiceAssembler = new InvoiceResourceAssembler();

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath*:/META-INF/spring/applicationContext-InvoiceProcessing.xml");

		InvoiceService invoiceSender = (InvoiceService) context.getBean("mailMail");
		invoiceSender.setEmailToSend(invoice.getEmail());
		invoiceSender.setSubject("Invoice from RentIt");
		
		invoiceSender.sendInvoice(invoiceAssembler.toEmailResource(invoice));

		return showInvoices(map, request);

	}
}
