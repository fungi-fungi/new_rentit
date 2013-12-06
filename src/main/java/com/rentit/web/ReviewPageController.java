package com.rentit.web;

import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.renit.rest.InvoiceResource;
import com.renit.rest.WebPurchaseOrderResource;
import com.rentit.PurchaseOrder;
import com.rentit.PurchaseOrderStatuses;
import com.rentit.assembler.PurchaseOrderAssembler;
import com.rentit.assembler.WebPurchaseOrderAssembler;
import com.rentit.dto.ChangeStatusFormAnswer;
import com.rentit.repository.PurchaseOrderRepository;
import com.rentit.service.RESTRequestsService;
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
	public String showView(Map<String, Object> map, HttpServletRequest request) {

		// TODO: Maybe move WebResource in normal place
		// 		 Set correct status in query
		List<PurchaseOrder> purchaseOrders = poRepository.findAll();
		WebPurchaseOrderAssembler assembler = new WebPurchaseOrderAssembler();
		List<WebPurchaseOrderResource> po = assembler
				.toListResource(purchaseOrders);

		map.put("po", po);
		map.put("postatusupdate", new ChangeStatusFormAnswer());

		return "purchaseorders/review/show";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String handlePost(
			@ModelAttribute("tempsolution") ChangeStatusFormAnswer data,
			Map<String, Object> map, HttpServletRequest request) {

		PurchaseOrder order = poRepository.findOne(data.getPurchaseOrderId());

		try {

			if (data.getStatus() != null) {
				order.setStatus(data.getStatus());

				if (data.getStatus().equals(PurchaseOrderStatuses.RETURNED)) {

					PurchaseOrderAssembler assembler = new PurchaseOrderAssembler();
					InvoiceResource res = assembler.toInvoiceResource(order);

					StringWriter result = new StringWriter();

					JAXBContext jaxbContext = JAXBContext
							.newInstance(InvoiceResource.class);
					Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
					jaxbMarshaller.setProperty(
							Marshaller.JAXB_FORMATTED_OUTPUT, true);
					jaxbMarshaller.marshal(res, result);

					String bydy = result.toString();

					RESTRequestsService post = new RESTRequestsService();
					post.setPassword("admin");
					post.setUserName("admin");
					post.setUrl("http://localhost:8080/RentIt/rest/invoices");
					post.setBody(bydy);

					if (post.sendPost() != 201) {
						throw new Exception();
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "purchaseorders/review/error";
		}

		return showView(map, request);

	}
}
