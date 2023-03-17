package com.model2.mvc.service.product.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;

//==> ��ǰ���� DAO CRUD ����
@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDao {
	
	///Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	///Constructor
	public ProductDaoImpl() {
		// TODO Auto-generated constructor stub
		System.out.println("����� ProductDaoImpl ������");
		System.out.println(this.getClass());
	}
	
	///Method
	@Override
	public void addProduct(Product product) throws Exception {
		sqlSession.insert("ProductMapper.addProduct",product);

	}

	@Override
	public Product getProduct(int prodNo) throws Exception {
		return sqlSession.selectOne("ProductMapper.getProduct",prodNo);
	}

	@Override
	public List<Product> getProductList(Search search) throws Exception {
		return  sqlSession.selectList("ProductMapper.getProductList",search);
	}

	@Override
	public void updateProduct(Product product) throws Exception {
		sqlSession.update("ProductMapper.updateProduct",product);

	}

	@Override
	// �Խ��� Page ó���� ���� ��ü Row(totalCount)  return
	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("ProductMapper.getTotalCount", search);
	}
	
	public List<String> autocomplete() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("ProductMapper.autocomplete");
	}

}
