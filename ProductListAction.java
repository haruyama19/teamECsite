package com.internousdev.red.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.red.dao.MCategoryDAO;
import com.internousdev.red.dao.ProductInfoDAO;
import com.internousdev.red.dto.ProductInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class ProductListAction extends ActionSupport implements SessionAware{

	private List<ProductInfoDTO> productInfoDTOList = new ArrayList<ProductInfoDTO>();
	private Map<String , Object> session;
	private String title = "商品一覧画面";

	public String execute(){

		// セッション（カテゴリーリスト）が存在しない場合は再取得
		if(Objects.isNull(session.get("categoryList"))){
			MCategoryDAO dao = new MCategoryDAO();
			session.put("categoryList", dao.getCategoryInfo());
		}

		ProductInfoDAO dao = new ProductInfoDAO();
		productInfoDTOList = dao.getProductInfoList();

			if(!(productInfoDTOList.size() > 0)){
				productInfoDTOList = null;
			}

		String result = SUCCESS;
		return result;
	}

	public String getTitle(){
		return this.title;
	}
	public List<ProductInfoDTO> getProductInfoDTOList(){
		return this.productInfoDTOList;
	}
	public void setproductInfoDTOList(List<ProductInfoDTO> productInfoDTOList){
		this.productInfoDTOList = productInfoDTOList;
	}
	public Map<String , Object> getSession(){
		return this.session;
	}
	@Override
	public void setSession(Map<String , Object> session){
		this.session = session;
	}

}
