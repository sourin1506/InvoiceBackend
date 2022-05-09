package com.invoice.Invoicedata.InvoiceService;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoice.Invoicedata.InvoiceEntity.Invoice;
import com.invoice.Invoicedata.InvoiceRepository.InvoiceRepository;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	InvoiceRepository invoicerepo;
	@Override
	public List<Invoice> saveCsvInvoices(List<Invoice> invoicelist) {
		// TODO Auto-generated method stub
		return invoicerepo.saveAll(invoicelist);
	}
	@Override
	public List<Invoice> fetchinvoicebyLimit(int start,int end) {
		// TODO Auto-generated method stub
		return invoicerepo.findbylimit(start,end);
	}
	@Override
	public List<Invoice> fetchAllinvoice() {
		// TODO Auto-generated method stub
		return invoicerepo.findAll();
	}
	@Override
	public void deleteInvoicebyId(String cust_number) {
		// TODO Auto-generated method stub
		invoicerepo.deletebynumber(cust_number);
		
	}
	@Override
	public Invoice updateInvoicebyCust_Number(String cust_number, double amt) {
		// TODO Auto-generated method stub
		Invoice invoiceDb=invoicerepo.findbycust_number(cust_number);
		invoiceDb.setTotal_open_amount(amt);
		return invoicerepo.save(invoiceDb);
	}
	@Override
	public Invoice saveInvoice(Invoice invoice) {
		// TODO Auto-generated method stub
		return invoicerepo.save(invoice);
	}

}
