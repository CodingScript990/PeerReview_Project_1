package com.likelion.market.service;

import com.likelion.market.repository.ItemRepository;
import com.likelion.market.repository.NegotiationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NegotiationService {
    // Call Repository
    private final ItemRepository itemRepository;
    private final NegotiationRepository negotiationRepository;

    // POST /items/{itemId}/proposal


    // GET /items/{itemId}/proposals?writer=jeeho.edu&password=qwerty1234&page=1


    // PUT /items/{itemId}/proposals/{proposalId}


    // DELETE /items/{itemId}/proposals/{proposalId}


    // PUT /items/{itemId}/proposals/{proposalId}


    // PUT /items/{itemId}/proposals/{proposalId}
}
