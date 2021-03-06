package cn.com.fml.mvc.controller.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.fml.mvc.common.FmlConstants;
import cn.com.fml.mvc.service.impl.HouseTypeServiceImpl;
import cn.com.fml.mvc.service.intf.HouseTypeService;

/**
 * @author hasee
 * 主力户型接口
 *
 */
@Controller
@RequestMapping(value="/app")
public class HouseTypeController extends HouseTypeServiceImpl {
	
	@Autowired
	private HouseTypeService houseTypeService;
	
	@RequestMapping(value="/houseType")
	@ResponseBody
	public Map<String, Object> getHouseTypeList(HttpServletRequest request, HttpServletResponse resp) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String buildingId = request.getParameter("id");
		if (StringUtils.isBlank(buildingId)) {
			map.put("errorCode", FmlConstants.ERROR_CODE_TYPE1);
			return map;
		}
		List<Map<String, Object>> houseTypeList = houseTypeService.mainHouseTypeList(new Long(buildingId));
		if (StringUtils.isBlank(buildingId)) {
			map.put("errorCode", FmlConstants.ERROR_CODE_TYPE1);
			return map;
		}
		map.put("value", houseTypeList);
		map.put("success", "true");
		return map;
	}
}
