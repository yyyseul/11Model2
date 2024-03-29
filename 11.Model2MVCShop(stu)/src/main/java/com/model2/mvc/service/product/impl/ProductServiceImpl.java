package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.domain.Product;

//==> 상품관리 서비스 구현
@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {

	///Field
	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDao productDao;
	public void setProductDAO(ProductDao productDao) {
		this.productDao = productDao;
	}

	///Constructor
	public ProductServiceImpl() {
		//productDAO=new ProductDAO(); 이게 빠지는 이유는?
		System.out.println("여기는 ProductServiceImpl 생성자");
		System.out.println(this.getClass());
	}
	
	@Override
	public void addProduct(Product product) throws Exception {
		
		//tranNo="1" 판매중
		product.setProTranCode("1");
		
		productDao.addProduct(product);
	}

	@Override
	public Product getProduct(int prodNo) throws Exception {
		
		
		return productDao.getProduct(prodNo);
	}
	
	@Override
	public Map<String, Object> getProductList(Search search) throws Exception{
		List<Product> list = productDao.getProductList(search);
		int totalCount = productDao.getTotalCount(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}

	@Override
	public void updateProduct(Product product) throws Exception {
		productDao.updateProduct(product);
	}
	
	@Override
	public List<String> autocomplete() throws Exception {
		
		return productDao.autocomplete();
	}

}
