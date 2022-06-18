package dao.product;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class ProductJdbcTemplate{
	public static JdbcTemplate getTemplate() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		JdbcTemplate temp = (JdbcTemplate) context.getBean("tmp");
		return temp;
	}
	public List<ProductInfo> getAllProducts() {
		JdbcTemplate temp = getTemplate();
		List<ProductInfo> list = temp.query(
				"Select p.*, c.categoryname from product_tbl p join ctgry_tbl c on p.categoryid = c.categoryid",
				new ProductMapper());
		return list;
	}
	public List<Integer> getAllCategoryId() {
		JdbcTemplate temp = getTemplate();
		List<Integer> list = temp.queryForList("Select unique(categoryid) from product_tbl", Integer.class);
		return list;
	}
	public List<ProductInfo> getAllProducts(String filter,int pprice) {
		JdbcTemplate temp = getTemplate();
		List<ProductInfo> list = temp.query("Select p.*, c.categoryname from product_tbl p join ctgry_tbl c on p.categoryid = c.categoryid where c.categoryname in (" + filter + ") and "
				+ "pprice < ?" ,
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
}