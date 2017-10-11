package cn.ztx.ssm.service;

import java.util.List;

import cn.ztx.ssm.po.ItemsCustom;
import cn.ztx.ssm.po.ItemsQueryVo;

/**
 * 商品查询列表
 * 
 * @author dell
 *
 */
public interface ItemsService {

	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;

	public ItemsCustom findItemsById(Integer id) throws Exception;

	public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception;
}
