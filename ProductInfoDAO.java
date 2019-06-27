package com.internousdev.red.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.red.dto.ProductInfoDTO;
import com.internousdev.red.util.DBConnector;

public class ProductInfoDAO{

	//商品情報をDBから取り出す記述

	public List<ProductInfoDTO> getProductInfoList(){

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		List<ProductInfoDTO> productInfoDTOList = new ArrayList<ProductInfoDTO>();

		String sql = "SELECT * FROM product_info WHERE status = 1";

		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				ProductInfoDTO dto = new ProductInfoDTO();
				dto.setId(rs.getInt("id"));
				dto.setProductId(rs.getInt("product_id"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductNameKana(rs.getString("product_name_kana"));
				dto.setProductDescription(rs.getString("product_description"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setPrice(rs.getInt("price"));
				dto.setImageFilePath(rs.getString("image_file_path"));
				dto.setImageFileName(rs.getString("image_file_name"));
				dto.setReleaseDate(rs.getDate("release_date"));
				dto.setReleaseCompany(rs.getString("release_company"));
				dto.setStatus(rs.getInt("status"));
				productInfoDTOList.add(dto);
			}

		}catch(SQLException e){
			e.printStackTrace();

		}finally{

			try{
				con.close();

			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return productInfoDTOList;
	}

	//商品IDで取り出す記述

	public ProductInfoDTO getProductInfoByProductId(int productId){

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		String sql = "SELECT * FROM product_info WHERE product_id = ? AND status = 1";
		ProductInfoDTO dto = new ProductInfoDTO();

		try{
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1 , productId);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				dto.setId(rs.getInt("id"));
				dto.setProductId(rs.getInt("product_id"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductNameKana(rs.getString("product_name_kana"));
				dto.setProductDescription(rs.getString("product_description"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setPrice(rs.getInt("price"));
				dto.setImageFilePath(rs.getString("image_file_path"));
				dto.setImageFileName(rs.getString("image_file_name"));
				dto.setReleaseDate(rs.getDate("release_date"));
				dto.setReleaseCompany(rs.getString("release_company"));
				dto.setStatus(rs.getInt("status"));
			}
		}catch(SQLException e){
			e.printStackTrace();

		}finally{

			try{
				con.close();

			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return dto;
	}

	//関連商品の情報を取り出す記述
		//not in()：()内を除く
		//rand()：表示順をランダムにする
		//limit 0,3：0番目から3件データを取得する
		//limitOffset：データを取得する開始位置
		//limitRowCount：データ取得件数

	public List<ProductInfoDTO> getRelatedProductList(int categoryId , int productId , int limitOffset , int limitRowCount){

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		List<ProductInfoDTO> relatedProductList = new ArrayList<ProductInfoDTO>();

		String sql = "SELECT * FROM product_info WHERE category_id = ? AND product_id NOT IN(?) AND status = 1 ORDER BY rand() limit ? , ?";

		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1 , categoryId);
			ps.setInt(2 , productId);
			ps.setInt(3 , limitOffset);
			ps.setInt(4 , limitRowCount);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){

				ProductInfoDTO dto = new ProductInfoDTO();
				dto.setId(rs.getInt("id"));
				dto.setProductId(rs.getInt("product_id"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductNameKana(rs.getString("product_name_kana"));
				dto.setProductDescription(rs.getString("product_description"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setPrice(rs.getInt("price"));
				dto.setImageFilePath(rs.getString("image_file_path"));
				dto.setImageFileName(rs.getString("image_file_name"));
				dto.setReleaseDate(rs.getDate("release_date"));
				dto.setReleaseCompany(rs.getString("release_company"));
				dto.setStatus(rs.getInt("status"));
				relatedProductList.add(dto);

			}

		}catch(SQLException e){
			e.printStackTrace();

		}finally{

			try{
				con.close();

			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return relatedProductList;
	}

	// 商品検索用
	public List<ProductInfoDTO> searchProduct(String sql){

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		List<ProductInfoDTO> dtoList = new ArrayList<ProductInfoDTO>();

		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){

				ProductInfoDTO dto = new ProductInfoDTO();

				dto.setProductId(rs.getInt("product_id"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductNameKana(rs.getString("product_name_kana"));
				dto.setProductDescription(rs.getString("product_description"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setPrice(rs.getInt("price"));
				dto.setImageFilePath(rs.getString("image_file_path"));
				dto.setImageFileName(rs.getString("image_file_name"));
				dto.setReleaseDate(rs.getDate("release_date"));
				dto.setReleaseCompany(rs.getString("release_company"));

				dtoList.add(dto);

			}
		}catch(SQLException e){
			e.printStackTrace();

		}finally{

			try{
				con.close();

			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return dtoList;
	}

}
