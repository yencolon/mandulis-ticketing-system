package org.mandulis.mts.controller;

import org.mandulis.mts.entity.Ticket;
import org.mandulis.mts.service.TicketService;
import org.mandulis.mts.dto.TicketRequest;
import org.mandulis.mts.dto.TicketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<TicketResponse> findAll() {
        return ticketService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<TicketResponse> findById(@PathVariable Long id) {
        return ticketService.findById(id);
    }

    @PostMapping
    public TicketResponse save(@RequestBody TicketRequest ticketRequest) {
        return ticketService.save(ticketRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        ticketService.deleteById(id);
    }

    @PutMapping("/{id}")
    public TicketResponse update(@RequestBody TicketRequest ticketRequest, @PathVariable Long id) {
        return ticketService.update(ticketRequest, id);
    }

    @GetMapping("/filter")
    public List<TicketResponse> filterTickets(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) Ticket.Priority priority,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) LocalDateTime createdAfter,
            @RequestParam(required = false) LocalDateTime createdBefore,
            @RequestParam(required = false) LocalDateTime updatedAfter,
            @RequestParam(required = false) LocalDateTime updatedBefore) {
        return ticketService.filterTickets(
                title, categoryName, priority, userName, createdAfter, createdBefore, updatedAfter, updatedBefore
        );
    }
}

