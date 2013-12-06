package com.rentit.web;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.renit.rest.WebPurchaseOrderResource;
import com.rentit.PurchaseOrder;
import com.rentit.assembler.WebPurchaseOrderAssembler;
import com.rentit.dto.ChangeStatusFormAnswer;
import com.rentit.repository.PurchaseOrderRepository;

@RequestMapping("/delivery/**")
@Controller
public class CheckDeliveryController {

	@Autowired
	PurchaseOrderRepository poRepository;
	
    @RequestMapping(method = RequestMethod.POST, value = "{id}")
    public void post(@PathVariable Long id, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }

    @RequestMapping
    public String index() {
        return "delivery/index";
    }
    
    @RequestMapping(method = RequestMethod.GET)
	public String showView(Map<String, Object> map, HttpServletRequest request) {

		List<PurchaseOrder> purchaseOrders = poRepository.findPOSByDate(new Date());
		WebPurchaseOrderAssembler assembler = new WebPurchaseOrderAssembler();
		List<WebPurchaseOrderResource> po = assembler.toListResource(purchaseOrders);

	//	map.put("today", new Date());
		map.put("po", po);
		map.put("postatusupdate", new ChangeStatusFormAnswer());

		return "delivery/show";
	}
}
