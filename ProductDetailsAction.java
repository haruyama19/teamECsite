package com.internousdev.red.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.red.dao.ProductInfoDAO;
import com.internousdev.red.dto.ProductInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class ProductDetailsAction extends ActionSupport implements SessionAware{

	private int productId;
	private List<Integer> productCountList;
	private List<ProductInfoDTO> relatedProductList;
	private ProductInfoDTO productInfoDTO = new ProductInfoDTO();
	private Map<String , Object> session;

	public String execute(){

		ProductInfoDAO productInfoDAO = new ProductInfoDAO();
		productInfoDTO = productInfoDAO.getProductInfoByProductId(productId);

		//ステータスが0、または商品IDが0だったら、DTOがnullになり
		//JSPでelseの時エラーメッセージを表示する
		if((productInfoDTO.getStatus() == 0) || (productInfoDTO.getProductId() == 0)){
			productInfoDTO = null;

		}
		else{

			//limit 0,3：0番目から3件データを取得する
			relatedProductList = productInfoDAO.getRelatedProductList(productInfoDTO.getCategoryId() , productInfoDTO.getProductId() , 0 , 3);

		}

		return SUCCESS;

	}

	public int getProductId(){
		return this.productId;
	}
	public void setProductId(int productId){
		this.productId = productId;
	}

	public List<Integer> getProductCountList(){
		return this.productCountList;
	}

	public void setProductCountList(List<Integer> productCountList){
		this.productCountList = productCountList;
	}

	public List<ProductInfoDTO> getRelatedProductList(){
		return this.relatedProductList;
	}

	public void setRelatedProductList(List<ProductInfoDTO> relatedProductList){
		this.relatedProductList = relatedProductList;
	}

	public ProductInfoDTO getProductInfoDTO(){
		return this.productInfoDTO;
	}

	public void setProductInfoDTO(ProductInfoDTO productInfoDTO){
		this.productInfoDTO = productInfoDTO;
	}

	public Map<String, Object> getSession(){
		return this.session;
	}
	public void setSession(Map<String, Object> session){
		this.session = session;
	}

}
