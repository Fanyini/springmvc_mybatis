package cn.ztx.ssm.mapper;


import java.util.List;

import cn.ztx.ssm.po.ItemsCustom;
import cn.ztx.ssm.po.ItemsQueryVo;

public interface ItemsMapperCustom {
    //商品查询列表
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)throws Exception;
}