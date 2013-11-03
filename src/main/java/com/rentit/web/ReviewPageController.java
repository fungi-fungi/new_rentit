package com.rentit.web;
import java.util.HashMap;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.rentit.PurchaseOrder;
import com.rentit.repository.PurchaseOrderRepository;
import com.rentit.soap.WebPurchaseOrderAssembler;
import com.rentit.soap.WebPurchaseOrderResource;

@RequestMapping("/purchaseorders/review/**")
@Controller
public class ReviewPageController {

	@Autowired 
	PurchaseOrderRepository poRepository;
	
    @RequestMapping(method = RequestMethod.POST, value = "{id}")
    public void post(@PathVariable Long id, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }

    @RequestMapping
    public String index() {
        return "purchaseorders/review/index";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String someAction(Map<String, Object> map, HttpServletRequest request) {

    	List<PurchaseOrder> purchaseOrders = poRepository.findAll();
		WebPurchaseOrderAssembler assembler = new WebPurchaseOrderAssembler();
		List<WebPurchaseOrderResource> po = assembler.toListResource(purchaseOrders);

        map.put("po", po);

        return "purchaseorders/review/show";
       }
    
  /*  @RequestMapping(method = RequestMethod.GET)
	public ModelAndView show(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
		
    	List<PurchaseOrder> purchaseOrders = poRepository.findAll();

		WebPurchaseOrderAssembler assembler = new WebPurchaseOrderAssembler();

		List<WebPurchaseOrderResource> po = assembler.toListResource(purchaseOrders);

		Map<String,String> model1 = new HashMap<String,String>();
        model1.put("hello", "hello");
    	
		modelMap.addAttribute("po", "hello");
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("MSG", "Thank u");
		
		return new ModelAndView(new RedirectView("purchaseorders/review/show"), model);
	}*/
}
