package org.jeecg.modules.demo.test1.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.test1.entity.Biaodantestdemo1;
import org.jeecg.modules.demo.test1.service.IBiaodantestdemo1Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: biaodanTestDemo1
 * @Author: jeecg-boot
 * @Date:   2020-05-06
 * @Version: V1.0
 */
@Api(tags="biaodanTestDemo1")
@RestController
@RequestMapping("/test/biaodantestdemo1")
@Slf4j
public class Biaodantestdemo1Controller extends JeecgController<Biaodantestdemo1, IBiaodantestdemo1Service> {
	@Autowired
	private IBiaodantestdemo1Service biaodantestdemo1Service;
	
	/**
	 * 分页列表查询
	 *
	 * @param biaodantestdemo1
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "biaodanTestDemo1-分页列表查询")
	@ApiOperation(value="biaodanTestDemo1-分页列表查询", notes="biaodanTestDemo1-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Biaodantestdemo1 biaodantestdemo1,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Biaodantestdemo1> queryWrapper = QueryGenerator.initQueryWrapper(biaodantestdemo1, req.getParameterMap());
		Page<Biaodantestdemo1> page = new Page<Biaodantestdemo1>(pageNo, pageSize);
		IPage<Biaodantestdemo1> pageList = biaodantestdemo1Service.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param biaodantestdemo1
	 * @return
	 */
	@AutoLog(value = "biaodanTestDemo1-添加")
	@ApiOperation(value="biaodanTestDemo1-添加", notes="biaodanTestDemo1-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Biaodantestdemo1 biaodantestdemo1) {
		biaodantestdemo1Service.save(biaodantestdemo1);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param biaodantestdemo1
	 * @return
	 */
	@AutoLog(value = "biaodanTestDemo1-编辑")
	@ApiOperation(value="biaodanTestDemo1-编辑", notes="biaodanTestDemo1-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Biaodantestdemo1 biaodantestdemo1) {
		biaodantestdemo1Service.updateById(biaodantestdemo1);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "biaodanTestDemo1-通过id删除")
	@ApiOperation(value="biaodanTestDemo1-通过id删除", notes="biaodanTestDemo1-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		biaodantestdemo1Service.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "biaodanTestDemo1-批量删除")
	@ApiOperation(value="biaodanTestDemo1-批量删除", notes="biaodanTestDemo1-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.biaodantestdemo1Service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "biaodanTestDemo1-通过id查询")
	@ApiOperation(value="biaodanTestDemo1-通过id查询", notes="biaodanTestDemo1-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Biaodantestdemo1 biaodantestdemo1 = biaodantestdemo1Service.getById(id);
		if(biaodantestdemo1==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(biaodantestdemo1);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param biaodantestdemo1
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Biaodantestdemo1 biaodantestdemo1) {
        return super.exportXls(request, biaodantestdemo1, Biaodantestdemo1.class, "biaodanTestDemo1");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Biaodantestdemo1.class);
    }

}
