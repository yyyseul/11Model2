package com.model2.mvc.web.purchase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;

//==> ���Ű��� Controller
@Controller
@RequestMapping("/purchase/*")
public class PurchaseController {
	
	///Field
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	///Constructor
	public PurchaseController() {
		System.out.println("����� PurchaseController ������");
		System.out.println(this.getClass());
	}
	
	
	//������ ����
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	///Method
	//@RequestMapping("/addPurchaseView.do")
	@RequestMapping(value="addPurchase", method = RequestMethod.GET)
	public ModelAndView addPurchaseView(@RequestParam("prodNo") int prodNo, HttpSession session) throws Exception{
		
		System.out.println("/addPurchaseView.do");
		
		ModelAndView modelAndView = new ModelAndView();

		Product product = productService.getProduct(prodNo);
		
		User user = (User)session.getAttribute("user");
		
		Purchase purchase = new Purchase();
		
		purchase.setBuyer(user);
		purchase.setPurchaseProd(product);
		
		modelAndView.addObject("purchase", purchase);
		modelAndView.setViewName("forward:/purchase/addPurchaseView.jsp");
				
		return modelAndView;
	}
	
	//@RequestMapping("/addPurchase.do")
	@RequestMapping(value = "addPurchase", method = RequestMethod.POST)
	public ModelAndView addPurchase(@RequestParam("prodNo")int prodNo, @RequestParam("userId") String userId,
									HttpServletRequest request) throws Exception{
		
		System.out.println("/addPurchase.do");
		
		ModelAndView modelAndView = new ModelAndView();
		
		//user, product, purchase ������ ��ü �����
		User user = new User();
		Product product = new Product();
		Purchase purchase = new Purchase();
		
		//prodNo����
		product.setProdNo(prodNo);
		purchase.setPurchaseProd(product);
		
		//userId����
		user.setUserId(userId);
		purchase.setBuyer(user);
		
		//purchase ����
		//���Ź��, �޴»���̸�, ������ ����ó, ������ �ּ�, ���ſ�û����, ����������
		purchase.setPaymentOption(request.getParameter("paymentOption"));
		purchase.setReceiverName(request.getParameter("receiverName"));
		purchase.setReceiverPhone(request.getParameter("receiverPhone"));
		purchase.setDivyAddr(request.getParameter("divyAddr"));
		purchase.setDivyRequest(request.getParameter("divyRequest"));
		purchase.setDivyDate(request.getParameter("divyDate"));
		 
		purchaseService.addPurchase(purchase);
	
		modelAndView.addObject("purchase", purchase);
		modelAndView.setViewName("forward:/purchase/addPurchase.jsp");
		
		System.out.println("����� ����������? : "+purchase);
		
		return modelAndView;
	}
	
	//@RequestMapping("/getPurchase.do")
	@RequestMapping(value = "getPurchase", method = RequestMethod.GET)
	public ModelAndView getPurchase(@RequestParam("tranNo") int tranNo) throws Exception{
		
		System.out.println("/getPurchase.do");
		
		ModelAndView modelAndView = new ModelAndView();
		
		Purchase purchase = purchaseService.getPurchase(tranNo);
		
		modelAndView.addObject("purchase", purchase);
		modelAndView.setViewName("forward:/purchase/readPurchase.jsp");
		
		return modelAndView;
	}
	
	//@RequestMapping("/listPurchase.do")
	@RequestMapping(value = "listPurchase")
	public ModelAndView listPurchase(@ModelAttribute("search")Search search, HttpSession session) throws Exception {

		System.out.println("/listPurchase.do");
		
		ModelAndView modelAndView = new ModelAndView();
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		//���ǿ� ����� User ������ ��ü ��������
		User user = (User)session.getAttribute("user");
		String userId = user.getUserId();
		
		//Business logic ����
		Map<String, Object> map = purchaseService.getPurchaseList(search, userId);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		modelAndView.addObject("list", map.get("list"));
		modelAndView.addObject("resultPage", resultPage);
		modelAndView.addObject("search", search);
		modelAndView.addObject("user", user);
		
		modelAndView.setViewName("forward:/purchase/listPurchase.jsp");
		
		return modelAndView;
	}
	
	//@RequestMapping("/updatePurchaseView.do")
	@RequestMapping(value = "updatePurchase", method = RequestMethod.GET)
	public ModelAndView updatePurchaseView(@RequestParam("tranNo")int tranNo) throws Exception{
		
		System.out.println("/updatePurchaseView.do");
		
		ModelAndView modelAndView = new ModelAndView();
		
		Purchase purchase = purchaseService.getPurchase(tranNo);
		
		modelAndView.addObject("purchase", purchase);
		modelAndView.setViewName("forward:/purchase/readPurchase.jsp");

		return modelAndView;
	}
	
	//@RequestMapping("/updatePurchase.do")
	@RequestMapping(value = "updatePurchase",method = RequestMethod.POST)
	public ModelAndView updatePurchase(@ModelAttribute("purchase")Purchase purchase) throws Exception{
		
		System.out.println("/updatePurchase.do");
		
		ModelAndView modelAndView = new ModelAndView();
		
		purchaseService.updatePurchase(purchase);
		
		modelAndView.setViewName("forward:/purchase/readPurchase.jsp");
		
		return modelAndView;
	}
	
	//@RequestMapping("/updateTranCode.do")
	@RequestMapping(value = "updateTranCode",method = RequestMethod.POST)
	public ModelAndView updateTranCode(@ModelAttribute("purchase")Purchase purchase) throws Exception{
		
		System.out.println("/updateTranCode.do");
		
		ModelAndView modelAndView = new ModelAndView();
		
		if(purchase.getTranCode().trim().equals("3")) {
			
			purchase.setTranCode("4");
		}
		
		purchaseService.updateTranCode(purchase);
		
		modelAndView.setViewName("forward:/listPurchase.do");
		
		return modelAndView;
	}
	
	//@RequestMapping("/updateTranCodeByProd.do")
	@RequestMapping(value = "updateTranCodeByProd", method = RequestMethod.POST)
	public ModelAndView updateTranCodeByProd(@ModelAttribute("purchase")Purchase purchase, HttpServletRequest request) throws Exception{
		
		System.out.println("/updateTranCodeByProd.do");
		
		ModelAndView modelAndView = new ModelAndView();
		
		//product ��ü
		Product product = new Product();
		product = productService.getProduct(Integer.parseInt(request.getParameter("prodNo")));
		
		System.out.println("product ������? : "+product);
		
		if(product.getProTranCode().trim().equals("2")) {
			
			product.setProTranCode("3");
			
		}
		System.out.println("����� product ������? : "+product.getProTranCode());
		//purchase ��ü
		purchase.setPurchaseProd(product);
		
		purchaseService.updateTranCodeByProd(purchase);
		
		//modelAndView.setViewName("forward:/listProduct.do?menu=manage&currentPage="+request.getParameter("currentPage"));
		
		modelAndView.setViewName("forward:/listProduct.do?menu=manage");
		
		return modelAndView;
	}
		

}
