package com.lsk.netdisk.gateway.helper;

import com.lsk.netdisk.gateway.mapper.ParamMapper;
import com.lsk.netdisk.gateway.mapper.URLsMapper;
import com.lsk.netdisk.gateway.models.Param;
import com.lsk.netdisk.gateway.models.URL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class RequestHelper {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private URLsMapper urlMapper;
	@Autowired
	private ParamMapper paramMapper;

	public Object handleAuth(HttpServletRequest req, HttpServletResponse resp){
		String path = req.getPathInfo();
		URL url = urlMapper.queryURLByPath(path);
		Integer urlID = url.getId();
		List<Param> params = paramMapper.queryParamsByUrlID(urlID);
		Map<String,String> paramMap = new HashMap<>();
		for(Param param : params){
			String name = param.getName();
			String value = req.getParameter(name);
			paramMap.put(name,value);
		}
		return null;
	}
}
