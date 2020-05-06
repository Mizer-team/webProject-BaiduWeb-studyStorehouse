package org.jeecg.modules.demo.test3.controller;

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
import org.jeecg.modules.demo.test3.entity.Biaodantest2;
import org.jeecg.modules.demo.test3.service.IBiaodantest2Service;

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
 * @Description: biaodanTest2
 * @Author: jeecg-boot
 * @Date:   2020-05-06
 * @Version: V1.0
 */
@Api(tags="biaodanTest2")
@RestController
@RequestMapping("/test3/biaodantest2")
@Slf4j
public class Biaodantest2Controller extends JeecgController<Biaodantest2, IBiaodantest2Service> {
	@Autowired
	private IBiaodantest2Service biaodantest2Service;
	
	/**
	 * 分页列表查询
	 *
	 * @param biaodantest2
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "biaodanTest2-分页列表查询")
	@ApiOperation(value="biaodanTest2-分页列表查询", notes="biaodanTest2-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Biaodantest2 biaodantest2,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Biaodantest2> queryWrapper = QueryGenerator.initQueryWrapper(biaodantest2, req.getParameterMap());
		Page<Biaodantest2> page = new Page<Biaodantest2>(pageNo, pageSize);
		IPage<Biaodantest2> pageList = biaodantest2Service.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param biaodantest2
	 * @return
	 */
	@AutoLog(value = "biaodanTest2-添加")
	@ApiOperation(value="biaodanTest2-添加", notes="biaodanTest2-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Biaodantest2 biaodantest2) {
		biaodantest2Service.save(biaodantest2);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param biaodantest2
	 * @return
	 */
	@AutoLog(value = "biaodanTest2-编辑")
	@ApiOperation(value="biaodanTest2-编辑", notes="biaodanTest2-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Biaodantest2 biaodantest2) {
		biaodantest2Service.updateById(biaodantest2);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "biaodanTest2-通过id删除")
	@ApiOperation(value="biaodanTest2-通过id删除", notes="biaodanTest2-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		biaodantest2Service.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "biaodanTest2-批量删除")
	@ApiOperation(value="biaodanTest2-批量删除", notes="biaodanTest2-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.biaodantest2Service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "biaodanTest2-通过id查询")
	@ApiOperation(value="biaodanTest2-通过id查询", notes="biaodanTest2-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Biaodantest2 biaodantest2 = biaodantest2Service.getById(id);
		if(biaodantest2==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(biaodantest2);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param biaodantest2
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Biaodantest2 biaodantest2) {
        return super.exportXls(request, biaodantest2, Biaodantest2.class, "biaodanTest2");
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
        return super.importExcel(request, response, Biaodantest2.class);
    }

}
