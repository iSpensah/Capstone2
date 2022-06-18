package pkg;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import dao.product.ProductInfo;
import dao.product.ProductJdbcTemplate;

@Controller
public class ProjectController {
	ProductJdbcTemplate objtemplate = new ProductJdbcTemplate();

	@RequestMapping("/home")
	public String mainPage() {
		return "homePage";
	}
	@RequestMapping("/showAllProducts")
	public String showAllCustomerDetails(Model model) {
		List<ProductInfo> list = objtemplate.getAllProducts();
		model.addAttribute("allproducts", list);
		model.addAttribute("tc", list.size());
		return "viewProducts";
	}
	@RequestMapping("/user-dashboard")
	public String showAllProduct(Model model) {
	List<ProductInfo> list = objtemplate.getAllProducts();
		model.addAttribute("allproducts", list);
		model.addAttribute("tc", list.size());
		return "user-dashboard";
	}
	@RequestMapping("Prod")
	public String showAllProducts(Model model) {
		List<ProductInfo> list = objtemplate.getAllProducts();
		model.addAttribute("allproducts", list);
		model.addAttribute("tc", list.size());
		return "Prod";
	}
	@RequestMapping("/ProductHori")
	public String showAllProductHorizontally(Model model) {
		List<String> categories = objtemplate.getAllCategories();
		List<ProductInfo> list = objtemplate.getAllProducts();

		model.addAttribute("allproducts", list);
		model.addAttribute("procat", categories);
		// model.addAttribute("tc", list.size());
		return "ProductHori";
	}
	@RequestMapping("/viewFilterProduct") // updated page
	public String showFilterPoduct(HttpServletRequest request, Model model) {
		StringBuilder sb = new StringBuilder();
		String filter = "";
		String[] categoryname = request.getParameterValues("cat"); // gets the values of the checkboxes
		int pprice = Integer.parseInt(request.getParameter("pprice"));
		if (categoryname != null && categoryname.length != 0 && pprice!=0) { // checks which checkboxes are checked and which is not
			for (int i = 0; i < categoryname.length; i++) {
				sb.append("'" + categoryname[i] + "',");
			}
			filter = sb.toString();
			filter = filter.substring(0, filter.length() - 1);
			System.out.println(filter);
			List<String> categories = objtemplate.getAllCategories();
			List<ProductInfo> list = objtemplate.getAllProducts(filter,pprice);
			model.addAttribute("allproducts", list);
			model.addAttribute("procat", categories);
		} else if (categoryname == null ) {
			return "redirect:/ProductHori";
		}
		return "ProductHori";
	}
}