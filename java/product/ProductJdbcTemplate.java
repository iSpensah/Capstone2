package dao.product;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import pkg.Java;

public class ProductJdbcTemplate{
	public static JdbcTemplate getTemplate() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		JdbcTemplate temp = (JdbcTemplate) context.getBean("tmp");
		return temp;
	}
	public List<ProductInfo> getAllProducts() {
		JdbcTemplate temp = getTemplate();
		List<ProductInfo> list = temp.query(
				"Select p.*, c.categoryname from product_tbl p join ctgry_tbl c on p.categoryid = c.categoryid order by productname asc",
				new ProductMapper());
		return list;
	}
	public List<Integer> getAllCategoryId() {
		JdbcTemplate temp = getTemplate();
		List<Integer> list = temp.queryForList("Select unique(categoryid) from product_tbl", Integer.class);
		return list;
	}
	public List<ProductInfo> getAllProducts(String filter,int pprice) { //filters product based on price and category
		JdbcTemplate temp = getTemplate();
		List<ProductInfo> list = temp.query("Select p.*, c.categoryname from product_tbl p join ctgry_tbl c on p.categoryid = c.categoryid where c.categoryname in (" + filter + ") and "
				+ "pprice <= ? order by productname asc" ,
				new Object[] {pprice},new ProductMapper());
		return list;
	}
	public List<ProductInfo> getAllProducts(int pprice) { //filters product based on price only and no categories selected
		JdbcTemplate temp = getTemplate();
		List<ProductInfo> list = temp.query("Select p.*, c.categoryname from product_tbl p join ctgry_tbl c on p.categoryid = c.categoryid where pprice <=  ?" ,
				new Object[] {pprice},new ProductMapper());
		return list;
	}
	public List<ProductInfo> getAllProductCat(String categoryname) {
		JdbcTemplate temp = getTemplate();
		List<ProductInfo> list = temp.query(
				"Select p.*, c.categoryname from product_tbl p join ctgry_tbl c on p.categoryid = c.categoryid where c.categoryname=?",
				new Object[] { categoryname }, new ProductMapper());
		return list;
	}
	public List<String> getAllCategories() {
		JdbcTemplate temp = getTemplate();
		List<String> list = temp.queryForList(
				"Select unique(c.categoryname) from product_tbl p join ctgry_tbl c on p.categoryid = c.categoryid",
				String.class);
		return list;
	}
	public ProductInfo getProduct(int productid) { //filters product based on price and category
		JdbcTemplate temp = getTemplate();		
		ProductInfo product = temp.queryForObject("Select p.*, c.categoryname from product_tbl p join ctgry_tbl c on p.categoryid = c.categoryid where p.productid =  ?",new Object[] {productid},new ProductMapper());
		return product;
	} 
}