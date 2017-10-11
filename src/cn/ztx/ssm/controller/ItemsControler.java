package cn.ztx.ssm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import cn.ztx.ssm.po.ItemsCustom;
import cn.ztx.ssm.service.ItemsService;

@Controller
public class ItemsControler {

	@Autowired
	private ItemsService itemsService;

	@RequestMapping("/queryItems")
	public ModelAndView queryItems(HttpServletRequest request) throws Exception {

		// 测试forward后request能否共享
		System.out.println(request.getParameter("id"));
		// 商品列表
		List<ItemsCustom> itemsList = itemsService.findItemsList(null);

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("itemsList", itemsList);
		modelAndView.setViewName("items/itemsList");
		return modelAndView;
	}

	// @RequestMapping("/editItem")
	// public ModelAndView editItems() throws Exception{
	//
	// // 调用service提供商品id查询商品信息
	// ItemsCustom itemsCustom = itemsService.findItemsById(1);
	//
	// // 返回ModelAndView
	// ModelAndView modelAndView = new ModelAndView();
	//
	// // 将查询到的信息放到model中
	// modelAndView.addObject("itemsCustom", itemsCustom);
	//
	// // 跳转修改页面
	// modelAndView.setViewName("items/editItems");
	// return modelAndView;
	// }

	@RequestMapping(value = { "/editItem" }, method = { RequestMethod.POST, RequestMethod.GET })
	// @RequestParam 里边指定request传入参数名称和形参进行绑定
	// required 用来表示 该参数是否必须传入，true 为必须传入
	//  defaultValue 默认值
	public String editItems(Model model, @RequestParam(value = "id", required=true, defaultValue="") Integer id) throws Exception {

		// 调用service提供商品id查询商品信息
		ItemsCustom itemsCustom = itemsService.findItemsById(id);

		// 将查询到的信息放到model中
		model.addAttribute("itemsCustom", itemsCustom);

		// 跳转路径
		return "items/editItems";
	}

	// 商品信息修改提交
	@RequestMapping("/items/editItemsSubmit")
	public String editItemsSubmit(HttpServletRequest request, Integer id , ItemsCustom itemsCustom) throws Exception {

		// 调用service更新商品信息，页面需要将商品信息传入此方法
		itemsService.updateItems(id, itemsCustom);
		
		// 重定向到指定页面
		// 商品信息
		// return "redirect:/queryItems.action";
		//return "forward:/queryItems.action";
		return "success";
	}
}
