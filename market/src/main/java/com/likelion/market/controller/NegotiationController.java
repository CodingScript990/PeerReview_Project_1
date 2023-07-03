package com.likelion.market.controller;

import com.likelion.market.repository.NegotiationRepository;
import com.likelion.market.service.NegotiationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/items/{itemId}/proposals")
public class NegotiationController {
    // Service => Negotiation
    private final NegotiationService negotiationService;

    // POST /items/{itemId}/proposal


    // GET /items/{itemId}/proposals?writer=jeeho.edu&password=qwerty1234&page=1


    // PUT /items/{itemId}/proposals/{proposalId}


    // DELETE /items/{itemId}/proposals/{proposalId}


    // PUT /items/{itemId}/proposals/{proposalId}


    // PUT /items/{itemId}/proposals/{proposalId}
}
