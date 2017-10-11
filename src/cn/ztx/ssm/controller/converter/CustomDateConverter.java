package cn.ztx.ssm.controller.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 自定义日期绑定
 * @author dell
 *
 */
public class CustomDateConverter implements Converter<String, Date>{

	@Override
	public Date convert(String source) {
		// 实现将日期串转化成日期类型（格式为：yyyy-MM-dd HH:mm:ss）
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try{
			// 参数绑定成功的话直接返回
			return dateFormat.parse(source);
		}catch (Exception e) {
			e.printStackTrace();
		}
		// 参数绑定失败的话，直接返回null
		return null;
	}

}
