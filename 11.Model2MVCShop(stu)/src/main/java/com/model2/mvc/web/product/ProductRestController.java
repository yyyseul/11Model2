package com.model2.mvc.web.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;

//==> ��ǰ���� RestController
@RestController
@RequestMapping("/product/*")
public class ProductRestController {
	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	///Constructor
	public ProductRestController() {
		System.out.println(this.getClass());
	}
	
	@RequestMapping(value="json/getProduct/{prodNo}", method = RequestMethod.GET)
	public Product getProduct( @PathVariable int prodNo) throws Exception{
		
		System.out.println("/product/json/getProduct : GET");
		
		return productService.getProduct(prodNo);
	}
	
	@RequestMapping(value="json/updateProduct", method = RequestMethod.POST)
	public Product updateProduct(@RequestBody Product product) throws Exception{
		
		System.out.println("/product/json/updateProduct : POST");
	
		productService.updateProduct(product);
		
		//Map<String, String> map = new HashMap<String,String>();
		//map.put("������Ʈ", "�ߴ�");
		return product; 
	}

	@RequestMapping(value = "json/addProduct", method = RequestMethod.POST)
	public Map<String,String> addProduct(@RequestBody Product product) throws Exception{
		
		System.out.println("/product/json/addProduct : POST");
		
		productService.addProduct(product);
		
		Map<String, String> map =  new HashMap<String, String>();
		map.put("��ǰ�߰�", "�ߴ�");
		return map;
	}
	
	@RequestMapping(value = "json/getProductList")
	public Search getProductList(@RequestBody Search search) throws Exception{
		
		System.out.println("/product/json/getProductList : GET/ POST");
		
		productService.getProductList(search);
		
		//Map<String, Object> map = new HashMap<String, Object>();
		//map.put("getProductList", "�ߴ�");
		
		//���ϰ��� �����ΰ�ü => json object�� �ٲ��ֱ� ������
		//List<Product>�� ���� ������ list�� totalCount�߿� list�� ���ž�
		return search;
	}
	
	@RequestMapping(value = "json/autocomplete")
	public List<String> autocomplete() throws Exception{
		
		System.out.println("/product/json/autocomplete : GET");
		
		List<String> list = productService.autocomplete();
		
		return list;
				
	}
	


}
