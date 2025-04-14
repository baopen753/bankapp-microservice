package org.baopen753.cards.controller;

import jakarta.validation.Valid;
import org.baopen753.cards.constant.CardConstant;
import org.baopen753.cards.dto.CardDetailsDto;
import org.baopen753.cards.dto.CardDto;
import org.baopen753.cards.dto.ResponseDto;
import org.baopen753.cards.service.ICardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class CardController {

    private final ICardService cardService;
    private final CardDetailsDto cardDetailsDto;

   public CardController(ICardService cardService, CardDetailsDto cardDetailsDto) {
       this.cardService = cardService;
       this.cardDetailsDto = cardDetailsDto;
   }

    @PostMapping("/cards")
    public ResponseEntity<CardDto> createCard(@RequestParam String mobileNumber) {

        CardDto createdCardDto = cardService.createCard(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdCardDto);
    }


    @GetMapping("/cards")
    public ResponseEntity<CardDto> getCardInfo(@RequestParam String mobileNumber) {
        CardDto cardDtoInDb = cardService.fetchCard(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardDtoInDb);
    }


    @PutMapping("/cards")
    public ResponseEntity<ResponseDto> updateCard(@Valid @RequestBody CardDto cardDto) {
        boolean isUpdated = cardService.updatedCard(cardDto);

        if (isUpdated)
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardConstant.STATUS_200, CardConstant.MESSAGE_200));
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(CardConstant.STATUS_417, CardConstant.MESSAGE_417_UPDATE));
    }


    @DeleteMapping("/cards")
    public ResponseEntity<ResponseDto> deleteCard(@RequestParam String mobileNumber) {
        boolean isDeleted = cardService.deleteCard(mobileNumber);

        if (isDeleted)
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardConstant.STATUS_200, CardConstant.MESSAGE_200));
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(CardConstant.STATUS_417, CardConstant.MESSAGE_417_DELETE));
    }

    @GetMapping("/card-info")
    public ResponseEntity<CardDetailsDto> getCardInfo() {
        return ResponseEntity.ok(cardDetailsDto);
    }

}
