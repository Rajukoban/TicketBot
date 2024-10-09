package com.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.model.TicketUpdate;

public interface TicketUpdateRepository extends JpaRepository<TicketUpdate, Long> {

}
