package com.invoice.Invoicedata.InvoiceController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.Invoicedata.InvoiceEntity.Invoice;
import com.invoice.Invoicedata.InvoiceService.InvoiceService;
@CrossOrigin(origins={"http://localhost:3000/","http://localhost:8082/"})
@RestController
public class InvoiceController {

	@Autowired
	InvoiceService invoiceservice;
	@CrossOrigin(origins="http://localhost:3000/")
	@GetMapping("/")

	public String helloword()
	{
		return "Check Table";
	}
	//add data from frontend
	@PostMapping("/invoice")
	public Invoice saveDepartments(@RequestBody Invoice invoice)
	{
		return invoiceservice.saveInvoice(invoice);
		
		
	}
 
	@PostMapping("/")
	public String saveCsvInvoices() throws IOException, ParseException
	{
		BufferedReader csvfile=new BufferedReader(new FileReader("C:\\Users\\KIIT\\Desktop\\Documents\\HighRadius\\1806077.csv"));
		SimpleDateFormat Format1 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat Format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat Format3 = new SimpleDateFormat("yyyy-MM-dd");
		List<Invoice> Invoicelist=new ArrayList<>();
		CSVParser csvParser= new CSVParser(csvfile,CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
		Iterable<CSVRecord> csvRecords=csvParser.getRecords();
		for(CSVRecord csvRecord:csvRecords)
		{
			Invoice invoice=new Invoice();
			
			invoice.setBusiness_code(csvRecord.get("business_code"));
			invoice.setCust_number(csvRecord.get("cust_number"));
			if(csvRecord.get("name_customer").length()==0)
				invoice.setName_customer(null);
			else
				invoice.setName_customer(csvRecord.get("name_customer"));
			if(csvRecord.get("clear_date").length()==0)
				invoice.setClear_date(null);
			else
				invoice.setClear_date(new java.sql.Date(Format2.parse(csvRecord.get("clear_date")).getTime()));


			invoice.setBusiness_year(Integer.parseInt(csvRecord.get("buisness_year").split("\\.")[0]));
			invoice.setDoc_id(Long.parseLong(csvRecord.get("doc_id").split("\\.")[0]));
			invoice.setPosting_date(new java.sql.Date(Format3.parse(csvRecord.get("posting_date")).getTime()));
			invoice.setDocument_create_date(new java.sql.Date(Format1.parse(csvRecord.get("document_create_date")).getTime()));
			invoice.setDue_in_date(new java.sql.Date(Format1.parse(csvRecord.get("due_in_date")).getTime()));
			invoice.setInvoice_currency(csvRecord.get("invoice_currency"));
			invoice.setDocument_type(csvRecord.get("document type"));	
			invoice.setPosting_id(Integer.parseInt(csvRecord.get("posting_id").split("\\.")[0]));
			invoice.setArea_business(csvRecord.get("area_business"));
			invoice.setTotal_open_amount(Double.parseDouble(csvRecord.get("total_open_amount")));
			invoice.setBaseline_create_date(new java.sql.Date(Format1.parse(csvRecord.get("baseline_create_date")).getTime()));
			invoice.setCust_payment_terms(csvRecord.get("cust_payment_terms"));
			if(csvRecord.get("invoice_id").length()==0)
				invoice.setInvoice_id(0);
			else
				invoice.setInvoice_id(Long.parseLong(csvRecord.get("invoice_id").split("\\.")[0]));
			invoice.setIsOpen(Integer.parseInt(csvRecord.get("isOpen")));
			


			Invoicelist.add(invoice);
		}
		 invoiceservice.saveCsvInvoices(Invoicelist);
		 return "Uploaded";
		//return null;
		
		
	}
	
	@GetMapping("/invoice/limit")
	@ResponseBody
	public List<Invoice> fetchInvoicebyLimit( @RequestParam("start")int start,@RequestParam("end")int end)
	{
		return  invoiceservice.fetchinvoicebyLimit(start,end);
	}
	@CrossOrigin(origins="http://localhost:3000/")
	@GetMapping("/invoice")
	@ResponseBody
	public List<Invoice> fetchAllInvoice()
	{
		return  invoiceservice.fetchAllinvoice();
	}
	
	//Delete invoice by id
	
	@DeleteMapping("/invoice/{id}")
	public String deleteDepartmenbyId(@PathVariable("id") String cust_number)
	{
		invoiceservice.deleteInvoicebyId(cust_number);
		return "Record Deleted";
	}

	//edit by cust_name
	@PutMapping("/invoice")
	public Invoice updateDepartmenbyId(@RequestParam("cust_number") String cust_number ,@RequestParam("amt") double amt )
	{
		return invoiceservice.updateInvoicebyCust_Number(cust_number,amt);
	}

}
