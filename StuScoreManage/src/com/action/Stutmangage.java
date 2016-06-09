package com.action;

import java.io.File;
import java.util.List;









import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bing.excel.core.BingExcel;
import com.bing.excel.core.BingExcelBuilder;
import com.bing.excel.core.impl.BingExcelImpl.SheetVo;
import com.dao.StuDao;
import com.dao.StuDaoImp;
import com.entity.Stu;

/**
 * 
 * @author nuoio  
 *
 *
 *
 */
@Controller
public class Stutmangage {

	/**
	 *查询所有的stu
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/find/stufindall.action")
	@ResponseBody
	public List<Stu> findall()throws Exception
	{      
		StuDao studao=new StuDaoImp();
		List liststu=null;
		try {
			liststu=studao.findAll();
		} catch (Exception e) {
			System.out.println("查询失败");
		}
		return liststu;//发送json 由框架 让jackjson转
	}
	/**
	 * 删除byid
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete/deletebyid.action")	
	public void deletebyid(@RequestParam("stuid") long id)
	{
		StuDao studao=new StuDaoImp();
		try {
			Boolean m=studao.delById(id);
			if(m){
				System.out.println("删除成功");

			}
			else{
				System.out.println("删除失败");

			}
		} catch (Exception e) {	
			System.out.println("springmvc的删除by_id控制器中出错");

		}
	}
	/**
	 *http://localhost:8080/StuScoreManage/delete/deletebyname.action?name=mxy
	 * 删除byname
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/delete/deletebyname.action")	
	public void deletebyname(@RequestParam("stuname")String name)
	{
		StuDao studao=new StuDaoImp();
		//		System.out.println("Qweqr");
		try {
			Boolean m=studao.delByName(name);
			if(m){
				System.out.println("删除成功");

			}
			else{
				System.out.println("删除失败");

			}
		} catch (Exception e) {	
			System.out.println("springmvc的删除by_name控制器中出错");

		}
	}

	@RequestMapping(value="/add/addstu.action", method = RequestMethod.POST )
	public  void addstu(@RequestBody Stu stux)
	{
		StuDao studao=new StuDaoImp();
		//System.out.println(stux);
		try {
			Boolean m=studao.add(stux);
			if(m){
				System.out.println("添加成功");

			}
			else{
				System.out.println("添加失败");

			}
		} catch (Exception e) {	
			System.out.println("springmvc的增加控制器里面抛出异常");

		}
	}
	@RequestMapping(value="/updata/upallstu", method = RequestMethod.POST )
	public void updataall(@RequestBody Stu stus)
	{
		StuDao studao=new StuDaoImp();
		try {
			Boolean m=studao.update(stus);
			if(m){
				System.out.println("updates成功");

			}
			else{
				System.out.println("updates失败");

			}
		} catch (Exception e) {	
			System.out.println("springmvc的update控制器里面抛出异常");

		}
	}
	@RequestMapping(value="/analyze/analyzestu.action", method = RequestMethod.POST )
	public void analyze()
	{
		System.out.println("开始解析了");
		File f= new File("d:/stuexcel.xlsx");
		StuDao studao=new StuDaoImp();
		BingExcel bing = BingExcelBuilder.toBuilder().builder();
		try {
			SheetVo<Stu> vo = bing.readFile(f, Stu.class, 1);
			List<Stu> objectList = vo.getObjectList();
			for (Stu stu : objectList) {
				System.out.println(stu);		
				if(studao.judgeidexist(stu.getId())){
					studao.update(stu);
				}else{
					studao.add(stu);
				}				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@RequestMapping(value="/analyze/outputstu.action", method = RequestMethod.POST )
	public void OutPutStudent(){
		System.out.println("开始输出excel");
		StuDao studao=new StuDaoImp();
		BingExcel bing = BingExcelBuilder.toBuilder().builder();
		List liststu=null;
		try {
			liststu=studao.findAll();
		} catch (Exception e) {
			System.out.println("查询在输出excel控制器中失败");
		}		
		bing.writeExcel("d:/outputstu.xlsx", liststu);

	}





	/**
	 * 测试404错误
	 */

	@RequestMapping(value="/lovebug.action" )
	public  void addstu()
	{
		System.out.println("qeeqr");//测试404错误的来源
		//说明如果页面上没有东西 angular 请求肯定是404 但是数据成功传输，目的达到
		//if status ==404

	}





}
