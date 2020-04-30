package org.jeecg.modules.test;

import org.jeecg.common.api.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/test/jeecgTest")
@Slf4j
public class JeecgTestController {
	/**
	 * hello world
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/hello")
	public Result<String> hello() {
		Result<String> result = new Result<String>();
		result.setResult("Hello World!");
		result.setSuccess(true);
		log.info("-----------------------hello world---------------");//输出日志
		return result;
	}

}
