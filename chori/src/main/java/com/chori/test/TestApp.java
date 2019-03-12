package com.chori.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.chori.configuration.AppConfig;
import com.chori.dao.CurrencyexchangeDao;
import com.chori.entity.Currencyexchange;
import com.chori.service.ExternalAccessoryStockService;
import com.chori.service.ImportPIFileService;
import com.chori.service.WarningMessageService;

public class TestApp {

	public static void main(String args[]) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(
				AppConfig.class);

		// SizeDao ser = (SizeDao) context.getBean("sizeDao");
		//
		// List<Size> lst= ser.getListSizeByCustomer("Saquiri");
		// for (Size garmentstyle : lst) {
		// System.err.println(garmentstyle.getSizename());
		// }

		 CurrencyexchangeDao ser = (CurrencyexchangeDao) context.getBean("currencyexchangeDao");
		 List<Currencyexchange> lst= ser.getAll();
		 
		 for (Currencyexchange name : lst){
//			 if(ser.findById(10).getExchangedate().compareTo(name.getExchangedate()) == 0)
			 if((ser.findById(10).getCurrencyByCurrencycodedestination().getCurrencycode()).equals(name.getCurrencyByCurrencycodedestination().getCurrencycode()))
				 System.out.println(ser.findById(10).getCurrencyByCurrencycodesource().getCurrencycode());
	//			 System.err.println("Nice");
		 }
		 System.out.println("Done");
		
//		WarningMessageService ser =
//		 (WarningMessageService)
//		 context.getBean("warningmessageService");
//		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
//		String dateInString = "23-11-2016 23:20:56";
//		Date date = null;
//		try {
//			date = sdf.parse(dateInString);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.err.println(ser.getPICompletionDate());
//		System.err.println(ser.compareDateWithPICD(date));
//		
//		context.close();

		// String a= "a.sdf.xls";
		// System.err.println(a.lastIndexOf("."));
		// System.err.println(a.substring(a.lastIndexOf(".")));
		// System.err.println(a.substring(0,a.lastIndexOf(".")));
		// System.err.println(DateTime.now().toString());
	}
}
