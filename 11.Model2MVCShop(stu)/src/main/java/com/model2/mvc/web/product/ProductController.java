package com.model2.mvc.web.product;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;

//==> ��ǰ���� Controller
@Controller
@RequestMapping("/product/*")
public class ProductController {
	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	///Constructor
	public ProductController() {
		System.out.println("����� ProductController ������");
		System.out.println(this.getClass());
	}
	
	//������ ����
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	//@RequestMapping("/addProductView.do")
	@RequestMapping(value = "addProduct",method = RequestMethod.GET)
	public String addProduct() throws Exception{
		
		System.out.println("/product/addProduct : GET");
		
		return "forward:/product/addProductView.jsp";
	}
	
	//@RequestMapping("/addProduct.do")
	@RequestMapping(value = "addProduct", method = RequestMethod.POST)
	public String addProduct(@ModelAttribute("product")Product product, @RequestParam("files") MultipartFile multipartFile) throws Exception{
		
		System.out.println("/product/addProduct : POST");
		
		String file = multipartFile.getOriginalFilename();
		//���� �����̸� �ο�
		file = System.currentTimeMillis()+"_"+file;
		product.setFileName(file);
		
		System.out.println("�����̸��� �����ΰ�?"+file);
		
		//tranNo="1" �Ǹ���
		product.setProTranCode("1");
		
		productService.addProduct(product);
			
		File saveFile = new File("C:\\Users\\YUN\\git\\11Model2\\11.Model2MVCShop(stu)\\src\\main\\webapp\\images\\uploadFiles",file);
		
		multipartFile.transferTo(saveFile);
		
		return "forward:/product/addProduct.jsp";
		
	}
	//@RequestMapping("/getProduct.do")
	@RequestMapping(value = "getProduct",method = RequestMethod.GET)
	public String getProduct(@RequestParam("prodNo") int prodNo, Model model, HttpServletRequest request) throws Exception{
		
		System.out.println("/product/getProduct : GET");
		//Business Logic
		Product product = productService.getProduct(prodNo);
		//Model�� View ����
		model.addAttribute("product", product);
		
		if(request.getParameter("menu") != null) {
			String menu= request.getParameter("menu");
			System.out.println(menu);
			request.setAttribute("menu", menu);		
		}
		
		return "forward:/product/getProduct.jsp";
	}
	
	//@RequestMapping("/listProduct.do")
	@RequestMapping(value = "listProduct")
	public String getProductList(@ModelAttribute("search")Search search, Model model, HttpServletRequest request) throws Exception{
		
		System.out.println("/product/listProduct : GET/POST");

		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic ����
		Map<String , Object> map=productService.getProductList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		// Model �� View ����
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/product/listProduct.jsp";
		
	}
	
	//@RequestMapping("/updateProductView.do")
	@RequestMapping(value="updateProduct", method = RequestMethod.GET)
	public String updateProduct(@RequestParam("prodNo") int prodNo, Model model) throws Exception {
		
		System.out.println("/product/updateProduct : GET");
		//Business Logic
		Product product = productService.getProduct(prodNo);
		//Model�� View ����
		model.addAttribute("product", product);
		
		return "forward:/product/updateProductView.jsp";
		
	}
	//@RequestMapping("/updateProduct.do")
	@RequestMapping(value = "updateProduct", method = RequestMethod.POST)
	public String updateProduct(@ModelAttribute("product") Product product, Model model, HttpSession session,  @RequestParam("files") MultipartFile multipartFile) throws Exception{
		
		System.out.println("/product/updateProduct : POST");
		
		String unchange = productService.getProduct(product.getProdNo()).getFileName();
		
		product.setFileName(unchange);
		
		String file = multipartFile.getOriginalFilename();
		if(file.length() >1) {
		//���� �����̸� �ο�
		
		file = System.currentTimeMillis()+"_"+file;
		product.setFileName(file);
		
		File saveFile = new File("C:\\Users\\YUN\\git\\11Model2\\11.Model2MVCShop(stu)\\src\\main\\webapp\\images\\uploadFiles",file);
		
		multipartFile.transferTo(saveFile);
		}
		
		System.out.println("�����̸��̿���������� "+ product.getFileName());
		productService.updateProduct(product);
		
		return "forward:/product/updateProduct.jsp";
	}

}
