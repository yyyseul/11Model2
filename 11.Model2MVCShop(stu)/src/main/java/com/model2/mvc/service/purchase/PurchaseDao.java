package com.model2.mvc.service.purchase;

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

//==> ���Ű������� CRUD �߻�ȭ/ĸ��ȭ�� DAO Interface Definition
public interface PurchaseDao {
	
	//INSERT
	public void addPurchase(Purchase purchase) throws Exception;
	
	//SELECT ONE
	public Purchase getPurchase(int tranNo) throws Exception;
	
	//SELECT LIST
	public List<Purchase> getPurchaseList(Search search, String userId) throws Exception;
	
	//SELECT LIST SaleList
	//UPDATE
	public void updatePurchase(Purchase purchase)throws Exception;
	
	//UPDATE BY TRANNO
	public void updateTranCode(Purchase purchase) throws Exception;
	
	//UPDATE BY PRODNO
	public void updateTranCodeByProd(Purchase purchase) throws Exception;
	
	//�Խ��� Page ó���� ���� ��ü Row(totalCount) return
	public int getTotalCount(String userId)throws Exception;
	
}