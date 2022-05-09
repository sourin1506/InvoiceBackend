package com.invoice.Invoicedata.InvoiceRepository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.invoice.Invoicedata.InvoiceEntity.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

	@Query(value="select * from invoice limit ?1,?2",nativeQuery=true)
	List<Invoice> findbylimit(int start,int end);

	@Modifying
	@Transactional
	@Query(value="DELETE FROM invoice WHERE cust_number=:cust_number",nativeQuery=true)
	void deletebynumber(@Param("cust_number")String cust_number);
	
	@Query(value="Select * from invoice where cust_number=:cust_number",nativeQuery=true)
	public Invoice findbycust_number(String cust_number);

}
