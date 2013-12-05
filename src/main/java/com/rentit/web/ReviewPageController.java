package com.rentit.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rentit.PurchaseOrder;
import com.rentit.PurchaseOrderStatuses;
import com.rentit.dto.ChangeStatusFormAnswer;
import com.rentit.repository.PurchaseOrderRepository;
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
		
		//TODO: Maybe move WebResource in normal place
		//TODO: Set correct status in query
		List<PurchaseOrder> purchaseOrders = poRepository.findAll();
		WebPurchaseOrderAssembler assembler = new WebPurchaseOrderAssembler();
		List<WebPurchaseOrderResource> po = assembler.toListResource(purchaseOrders);

		map.put("po", po);
		map.put("postatusupdate", new ChangeStatusFormAnswer());

		return "purchaseorders/review/show";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String handlePost(@ModelAttribute("tempsolution") ChangeStatusFormAnswer data, Map<String, Object> map, HttpServletRequest request){

		PurchaseOrder order = poRepository.findOne(data.getPurchaseOrderId());
		if(data.getStatus().equals(com.rentit.soap.client.Statuses.ACCEPTED)){
			order.setStatus(PurchaseOrderStatuses.ACCEPTED);
		}else{
			order.setStatus(PurchaseOrderStatuses.CANCELED);
		}
		order.persist();
		
		// Send SOAR request to BuilIt 
	//	poSOAPService.setPoStatus(data);

	    return "purchaseorders/review/index";

	} 
}
