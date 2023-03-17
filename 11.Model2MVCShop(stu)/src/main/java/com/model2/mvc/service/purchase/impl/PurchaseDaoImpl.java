package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDao;

//==> �ǸŰ��� DAO CRUD ����
@Repository("purchaseDaoImpl")
public class PurchaseDaoImpl implements PurchaseDao {
	
	///Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	///Constructor
	public PurchaseDaoImpl() {
		System.out.println("����� PurchaseDaoImpl ������");
		System.out.println(this.getClass());
	}

	@Override
	public void addPurchase(Purchase purchase) throws Exception {
		sqlSession.insert("purchaseMapper.addPurchase", purchase);

	}

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		return sqlSession.selectOne("purchaseMapper.getPurchase",tranNo);
	}

	@Override
	public List<Purchase> getPurchaseList(Search search, String userId) throws Exception {
		System.out.println("userId�� �����ΰ�? "+userId);
		Map<String, Object> map = new HashMap<String, Object>();
		if(search != null) {
			System.out.println("search�� �����ΰ�? "+search);
			map.put("search", search);
			map.put("userId", userId);
		}		
		System.out.println("Map�� �����ΰ�?" + map);
		
	
		return sqlSession.selectList("purchaseMapper.getPurchaseList",map);
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		sqlSession.update("purchaseMapper.updatePurchase", purchase);

	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		sqlSession.update("purchaseMapper.updateTranCode", purchase);
	
	}

	@Override
	public void updateTranCodeByProd(Purchase purchase) throws Exception {
		sqlSession.update("purchaseMapper.updateTranCodeByProd", purchase);

	}

	@Override
	// �Խ��� Page ó���� ���� ��ü Row(totalCount)  return
	public int getTotalCount(String userId) throws Exception {
		return sqlSession.selectOne("purchaseMapper.getTotalCount", userId);
	}
}