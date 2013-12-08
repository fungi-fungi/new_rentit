package com.rentit.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rentit.PurchaseOrder;
import com.rentit.PurchaseOrderStatuses;
import com.rentit.assembler.WebPurchaseOrderAssembler;
import com.rentit.dto.OneDate;
import com.rentit.repository.PurchaseOrderRepository;
import com.rentit.rest.WebPurchaseOrderResource;

@RequestMapping("/delivery/**")
@Controller
public class CheckDeliveryController {

	@Autowired
	PurchaseOrderRepository poRepository;

	@RequestMapping(method = RequestMethod.POST, value = "{id}")
	public void post(@PathVariable Long id, ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response) {
	}

	@RequestMapping
	public String index() {
		return "delivery/index";
	}

	void addDateTimeFormatPatterns(ModelMap modelMap) {
		modelMap.put(
				"plantDelivery_date_format",
				DateTimeFormat.patternForStyle("MM",
						LocaleContextHolder.getLocale()));
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showView(ModelMap map, HttpServletRequest request, HttpServletResponse response) {

		List<PurchaseOrder> purchaseOrders = poRepository.findPOSByDate(new Date(), PurchaseOrderStatuses.ACCEPTED);
		WebPurchaseOrderAssembler assembler = new WebPurchaseOrderAssembler();
		List<WebPurchaseOrderResource> po = assembler.toListResource(purchaseOrders);

		addDateTimeFormatPatterns(map);

		map.put("today", new DateTime().toString("dd-MM-yyyy"));
		map.put("po", po);
		map.put("querydate", new OneDate());

		return "delivery/show";
	}

	// TODO: fix POST
	@RequestMapping(method = RequestMethod.POST)
	public String handlePost(@Valid OneDate date, ModelMap map, HttpServletRequest request) {
		
		List<PurchaseOrder> purchaseOrders = poRepository.findPOSByDate(date.getDate().getTime(), PurchaseOrderStatuses.ACCEPTED);
		WebPurchaseOrderAssembler assembler = new WebPurchaseOrderAssembler();
		List<WebPurchaseOrderResource> po = assembler.toListResource(purchaseOrders);

		addDateTimeFormatPatterns(map);

		map.put("today", new DateTime(date.getDate().getTime()).toString("dd-MM-yyyy"));
		map.put("po", po);
		map.put("querydate", new OneDate());

		return "delivery/show"; 
		
	}

}
