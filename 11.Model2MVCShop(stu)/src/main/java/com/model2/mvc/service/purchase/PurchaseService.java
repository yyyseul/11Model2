package com.model2.mvc.service.purchase;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

//���Ű����� �߻�ȭ/ĸ��ȭ�� PurchaseService Interface
public interface PurchaseService {
	
	//���ų����߰�
	public void addPurchase(Purchase purchase) throws Exception;
	
	//����������ȸ
	public Purchase getPurchase(int tranNo) throws Exception;
	
	//���Ÿ����ȸ
	public Map<String, Object> getPurchaseList(Search search, String userId)throws Exception;
	
	//�ǸŸ����ȸ
	public Map<String, Object> getSaleList(Search search) throws Exception;
	
	//������������
	public void updatePurchase(Purchase purchase) throws Exception;
	
	//�����ڵ庯�� by ���Ź�ȣ "3"(�����)->"4"(��ۿϷ�)
	public void updateTranCode(Purchase purchase) throws Exception;
		
	//�����ڵ庯�� by ��ǰ��ȣ "2"(�ǸſϷ�)->"3"(�����)
	public void updateTranCodeByProd(Purchase purchase) throws Exception;

}