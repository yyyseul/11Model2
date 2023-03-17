package com.model2.mvc.service.product;

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

//==> ��ǰ�������� CRUD �߻�ȭ/ĸ��ȭ�� DAO Interface Definition
public interface ProductDao {
	
	//INSERT <== insertProduct
	public void addProduct(Product product) throws Exception;
	
	//SELECT ONE <== findProduct
	public Product getProduct(int prodNo) throws Exception;
	
	//SELECT LIST <== getProductList
	public List<Product> getProductList(Search search) throws Exception;
	
	//UPDATE <== updateProduct
	public void updateProduct(Product product) throws Exception;
	
	// �Խ��� Page ó���� ���� ��üRow(totalCount)  return
	public int getTotalCount(Search search) throws Exception ;

	//autocomplete
	public List<String> autocomplete() throws Exception;

}
