package com.rentit.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rentit.Invoice;
import com.rentit.PurchaseOrder;
import com.rentit.Statuses;
import com.rentit.assembler.InvoiceResourceAssembler;
import com.rentit.repository.PurchaseOrderRepository;
import com.rentit.service.InvoiceService;
import com.rentit.soap.WebPurchaseOrderAssembler;
import com.rentit.soap.WebPurchaseOrderResource;
import com.rentit.soap.client.PoStatusUpdateRequest;
import com.rentit.soap.client.PurchaseOrderSOAPService;


@RequestMapping("/purchaseorders/review/**")
@Controller
public class ReviewPageController {

	@Autowired
	PurchaseOrderRepository poRepository;
	@Autowired 
	PurchaseOrderSOAPService poSOAPService;

	@RequestMapping(method = RequestMethod.POST, value = "{id}")
	public void post(@PathVariable Long id, ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response) {
	}

	@RequestMapping
	public String index() {
		return "purchaseorders/review/index";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String someAction(Map<String, Object> map, HttpServletRequest request) {
		
		List<PurchaseOrder> purchaseOrders = poRepository.findPandingPurchaseOrder(Statuses.PANDING);
		WebPurchaseOrderAssembler assembler = new WebPurchaseOrderAssembler();
		List<WebPurchaseOrderResource> po = assembler
				.toListResource(purchaseOrders);

		map.put("po", po);
		map.put("accept", com.rentit.soap.client.Statuses.ACCEPTED);
		map.put("reject", com.rentit.soap.client.Statuses.REJECTED);
		map.put("postatusupdate", new PoStatusUpdateRequest());

		return "purchaseorders/review/show";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String handlePost(@ModelAttribute("tempsolution") PoStatusUpdateRequest data, Map<String, Object> map, HttpServletRequest request){

		PurchaseOrder order = poRepository.findPOById(data.getPurchaseOrderId());
		if(data.getStatus().equals(com.rentit.soap.client.Statuses.ACCEPTED)){
			order.setStatus(Statuses.ACCEPT);
		}else{
			order.setStatus(Statuses.REJECTED);
		}
		order.persist();
		
		
		Invoice invoice = new Invoice();
		invoice.setPurchaseOrder(order);
		invoice.setStatus(Statuses.PANDING);
		invoice.setDueDate(order.getDueDate());
		
		InvoiceResourceAssembler invoiceAssembler = new InvoiceResourceAssembler();
		
		
		// Send SOAR request to BuilIt 
		poSOAPService.setPoStatus(data);


		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:/META-INF/spring/applicationContext-InvoiceProcessing.xml");
	 
		InvoiceService invoiceSender = (InvoiceService) context.getBean("mailMail");
		
		try {
			invoiceSender.sendInvoice(invoiceAssembler.toResource(invoice));
			System.out.print( "Success" );
		} catch (JAXBException e) {
			
			System.out.print( "Error" );
			e.printStackTrace();
		}
		
	    return "purchaseorders/review/index";

	} 
}
