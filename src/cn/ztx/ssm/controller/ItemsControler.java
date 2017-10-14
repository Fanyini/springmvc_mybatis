package cn.ztx.ssm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import cn.ztx.ssm.po.ItemsCustom;
import cn.ztx.ssm.po.ItemsQueryVo;
import cn.ztx.ssm.service.ItemsService;
import cn.ztx.ssm.validation.ValidationGroup1;

@Controller
public class ItemsControler {

	@Autowired
	private ItemsService itemsService;

	@RequestMapping("/queryItems")
	public ModelAndView queryItems(HttpServletRequest request, ItemsQueryVo itemsQueryVo) throws Exception {

		// 测试forward后request能否共享
//		System.out.println(request.getParameter("id"));
		// 商品列表
		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);

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

	//
	// 商品信息修改提交
	@RequestMapping("/items/editItemsSubmit")
	// 在需要校验的pojo前加上@Validated 注解，在需要校验的pojo后边加上BindingResult参数，
	// 注意：@Validated和BindingResult bindingResult是配对出现，并且形参顺序是固定的（一前一后）。
	// ValidationGroup1.class 指定使用validation1分组的校验
	public String editItemsSubmit(Model model, HttpServletRequest request, Integer id , @Validated(value={ValidationGroup1.class}) ItemsCustom itemsCustom, BindingResult bindingResult) throws Exception {

		// 获取错误信息
		if(bindingResult.hasErrors()){
			// 返回错误信息的集合
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			for(ObjectError objectError : allErrors){
				// 输出错误信息
				System.out.println(objectError.getDefaultMessage());
			}
			// 将错误信息传到页面上去
			model.addAttribute("allErrors", allErrors);
			// 出错的话，重新到商品的修改页面
			return "items/editItems";
		}
		
		// 调用service更新商品信息，页面需要将商品信息传入此方法
		itemsService.updateItems(id, itemsCustom);
		
		// 重定向到指定页面
		// 商品信息
		// return "redirect:/queryItems.action";
		//return "forward:/queryItems.action";
		return "success";
	}
	
	// 批量删除商品信息
	@RequestMapping("/deleteItem")
	public String deleteItems(Integer [] items_id) throws Exception{
		
		System.out.println(items_id.toString());
		// 调用service批量删除商品
		
		return "success";
	}
	
	// 批量修改商品页面，将商品信息查询出来，在页面中可以编辑商品信息
	@RequestMapping("/editItemsQuery")
	public ModelAndView editItemsQuery(HttpServletRequest request, ItemsQueryVo itemsQueryVo) throws Exception {

		// 商品列表
		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("itemsList", itemsList);
		modelAndView.setViewName("items/editItemsQuery");
		
		return modelAndView;
	}
	
	// 批量修改商品提交
	// 通过ItemsQueryVo批量提交商品信息，将商品信息存储到itemsQueryVo中itemsList
	@RequestMapping("/editItemsAllSubmit")
	public String editItemsAllSubmit(ItemsQueryVo itemsQueryVo ) throws Exception{
		
		
		
		return "success";
	}
	
	
}
