package com.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.server.enums.TicketStatus;
import com.server.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
	@Query("SELECT t FROM Ticket t WHERE t.user.userId = :userId")
    List<Ticket> findTicketsByUserId(@Param("userId") Long userId);
	
	List<Ticket> findByStatus(TicketStatus status);
	
	int countByStatus(TicketStatus status);
	
	@Query("SELECT COUNT(t) FROM Ticket t WHERE t.createdDate < CURRENT_TIMESTAMP AND (t.status <> 'OPEN' OR t.priority <> 'LOW')")
	int countUpdatedTickets();
	
//	@Query("SELECT t.ticketId,t.title,t.priority,t.status FROM Ticket t WHERE t.ticketId NOT IN (SELECT a.ticket.ticketId FROM Approval a WHERE a.approvalStatus = com.server.enums.ApprovalStatus.APPROVED OR a.approvalStatus=com.server.enums.ApprovalStatus.REJECTED)")
//    List<Ticket> findPendingTickets();
	
	@Query("SELECT t FROM Ticket t WHERE t.ticketId NOT IN " +
	           "(SELECT a.ticket.ticketId FROM Approval a WHERE a.approvalStatus = com.server.enums.ApprovalStatus.APPROVED OR a.approvalStatus=com.server.enums.ApprovalStatus.REJECTED)")
	    List<Ticket> findPendingTickets();
	
	//@Query("SELECT t FROM Ticket t JOIN Approval a ON t.ticketId=a.ticketId"+"WHERE t.userId=:userId AND a.approvalStatus ='APPROVED'")
	@Query(value = "SELECT t.* FROM tickets t " +
            "JOIN approvals a ON t.ticket_id = a.ticket_id " +
            "WHERE t.user_id = :userId AND a.approval_status = 'APPROVED'", 
    nativeQuery = true)
	List<Ticket> findApprovedTicketByUserId(@Param("userId") Long userId);
	
	@Query(value = "SELECT t.* FROM tickets t " +
            "JOIN approvals a ON t.ticket_id = a.ticket_id " +
            "WHERE t.user_id = :userId AND a.approval_status = 'REJECTED'", 
    nativeQuery = true)
	List<Ticket> findRejectedTicketByUserId(@Param("userId") Long userId);

}
