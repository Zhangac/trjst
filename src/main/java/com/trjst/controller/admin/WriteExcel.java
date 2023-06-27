package com.trjst.controller.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class WriteExcel {

	public void exportOrder(String[] titles, ServletOutputStream out,List wa) throws Exception {
		try {
			// 第一步，创建一个workbook，对应一个Excel文件
			HSSFWorkbook workbook = new HSSFWorkbook();

			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet hssfSheet = workbook.createSheet("sheet1");

			// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short

			HSSFRow row = hssfSheet.createRow(0);
			// 第四步，创建单元格，并设置值表头 设置表头居中
			HSSFCellStyle hssfCellStyle = workbook.createCellStyle();

			// 居中样式
			hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			HSSFCell hssfCell = null;
			for (int i = 0; i < titles.length; i++) {
				hssfCell = row.createCell(i);// 列索引从0开始
				hssfCell.setCellValue(titles[i]);// 列名1
				hssfCell.setCellStyle(hssfCellStyle);// 列居中显示
			}

			for (int i = 0; i < wa.size(); i++) {
				Map map = (Map) wa.get(i);
				row = hssfSheet.createRow(i + 1);
				// 第六步，创建单元格，并设置值

				String delivery_name = "";
				if (String.valueOf(map.get("delivery_name"))!= null&&!String.valueOf(map.get("delivery_name")).equals("null")) {
					delivery_name = String.valueOf(map.get("delivery_name"));
				}
				row.createCell(0).setCellValue(delivery_name);

				String commodity_name = "";
				if (String.valueOf(map.get("commodity_name"))!= null&&!String.valueOf(map.get("commodity_name")).equals("null")) {
					commodity_name = String.valueOf(map.get("commodity_name"));
				}
				row.createCell(1).setCellValue(commodity_name);

				String phone = "";
				if (String.valueOf(map.get("phone"))!= null&&!String.valueOf(map.get("phone")).equals("null")) {
					phone = String.valueOf(map.get("phone"));
				}
				row.createCell(2).setCellValue(phone);

				String address = "";
				if (String.valueOf(map.get("address"))!= null&&!String.valueOf(map.get("address")).equals("null")) {
					address = String.valueOf(map.get("address"));
				}
				row.createCell(3).setCellValue(address);

				String detailed_address = "";
				if (String.valueOf(map.get("detailed_address"))!= null&&!String.valueOf(map.get("detailed_address")).equals("null")) {
					detailed_address = String.valueOf(map.get("detailed_address"));
				}
				row.createCell(4).setCellValue(detailed_address);

				String name = "";
				if (String.valueOf(map.get("name"))!= null&&!String.valueOf(map.get("name")).equals("null")) {
					name = String.valueOf(map.get("name"));
				}
				row.createCell(5).setCellValue(name);

				String jin_num = "";
				if (String.valueOf(map.get("jin_num"))!= null&&!String.valueOf(map.get("jin_num")).equals("null")) {
					jin_num = String.valueOf(map.get("jin_num"));
				}
				if(jin_num.equals("")) {
					Double b = 0.0;
					row.createCell(6).setCellValue(b);
				}else {
					//row.createCell(6).setCellValue(Double.valueOf(jin_num));
					row.createCell(6).setCellValue(jin_num);
				}

				String quantity = "";
				if (String.valueOf(map.get("quantity"))!= null&&!String.valueOf(map.get("quantity")).equals("null")) {
					quantity = String.valueOf(map.get("quantity"));
				}
				row.createCell(7).setCellValue(quantity);

				String unit_price = "";
				if (String.valueOf(map.get("unit_price"))!= null&&!String.valueOf(map.get("unit_price")).equals("null")) {
					unit_price = String.valueOf(map.get("unit_price"));
				}
				if(unit_price.equals("")) {
					Double b = 0.0;
					row.createCell(8).setCellValue(b);
				}else {
					row.createCell(8).setCellValue(Double.valueOf(unit_price));
				}

				String total_price = "";
				if (String.valueOf(map.get("total_price"))!= null&&!String.valueOf(map.get("total_price")).equals("null")) {
					total_price = String.valueOf(map.get("total_price"));
				}
				if(total_price.equals("")) {
					Double b = 0.0;
					row.createCell(9).setCellValue(b);
				}else {
					row.createCell(9).setCellValue(Double.valueOf(total_price));
				}

				String pay_price = "";
				if (String.valueOf(map.get("pay_price"))!= null&&!String.valueOf(map.get("pay_price")).equals("null")) {
					pay_price = String.valueOf(map.get("pay_price"));
				}
				if(pay_price.equals("")) {
					Double b = 0.0;
					row.createCell(10).setCellValue(b);
				}else {
					row.createCell(10).setCellValue(Double.valueOf(pay_price));
				}

				String pay_time = "";
				if (String.valueOf(map.get("pay_time"))!= null) {
					pay_time = String.valueOf(map.get("pay_time"));
				}
				row.createCell(11).setCellValue(pay_time);

				String create_time = "";
				if (String.valueOf(map.get("create_time"))!= null) {
					create_time = String.valueOf(map.get("create_time"));
				}
				row.createCell(12).setCellValue(create_time);

			}

//			row = hssfSheet.createRow(wa.size() + 1);
//			row.createCell(7).setCellValue("总计："+moneycount);

			// 第七步，将文件输出到客户端浏览器
			try {
				workbook.write(out);
				out.flush();
				out.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("导出信息失败！");

		}
	}
	
	public static String timeStamp2Date(String seconds,String format) {  
		if(seconds == null || seconds.isEmpty() || seconds.equals("null")){  
			return "";  
		}  
		if(format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";  
		SimpleDateFormat sdf = new SimpleDateFormat(format);  
		return sdf.format(new Date(Long.valueOf(seconds+"000")));  
	}



	//截取字符串工具
	private String Interceptstr(String text,String key1,String key2) {
		// TODO Auto-generated method stub
		String str = "";
		String[] str1 = text.split(key1);
		if(str1.length>=2) {
			System.out.println("str11="+str1[0]+",str12="+str1[1]);
			String[] str2 = str1[1].split(key2);
			if(str2.length>=2) {
				System.out.println("str21="+str2[0]+",str22="+str2[1]);
				str = str2[0];
			}else if(str2.length==1){
				str = str2[0];
			}
		}else if(str1.length==1){
			str = str1[0];
		}
		return str;
	}
}
