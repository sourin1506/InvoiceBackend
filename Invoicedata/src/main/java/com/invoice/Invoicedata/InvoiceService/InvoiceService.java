package com.invoice.Invoicedata.InvoiceService;

import java.util.List;

import com.invoice.Invoicedata.InvoiceEntity.Invoice;

public interface InvoiceService {

	public List<Invoice> saveCsvInvoices(List<Invoice> invoicelist);

	public List<Invoice> fetchinvoicebyLimit(int start,int end);

	public List<Invoice> fetchAllinvoice();

	public void deleteInvoicebyId(String cust_number);

	public Invoice updateInvoicebyCust_Number(String cust_number, double amt);

	public Invoice saveInvoice(Invoice invoice);

}
