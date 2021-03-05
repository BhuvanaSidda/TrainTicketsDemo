package com.proj.ticket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proj.ticket.model.TicketDetails;

@Repository
public interface TicketDetailsRepository extends JpaRepository<TicketDetails, Long> {

	List<TicketDetails> findByUserName(String userName);

}
