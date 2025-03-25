package org.baopen753.cards.service.impl;

import lombok.RequiredArgsConstructor;
import org.baopen753.cards.constant.CardConstant;
import org.baopen753.cards.dto.CardDto;
import org.baopen753.cards.entity.Card;
import org.baopen753.cards.exception.CardIsAlreadyExistException;
import org.baopen753.cards.exception.ObjectNotFoundException;
import org.baopen753.cards.mapper.CardMapper;
import org.baopen753.cards.repository.CardRepository;
import org.baopen753.cards.service.ICardService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CardService implements ICardService {

    private final CardRepository cardRepository;

    /**
     * @param mobileNumber - Mobile number of the customer
     */
    @Override
    public CardDto createCard(String mobileNumber) {
        Optional<Card> cardInDb = cardRepository.findByMobileNumber(mobileNumber);

        if (cardInDb.isPresent())
            throw new CardIsAlreadyExistException("Card already existed with mobileNumber: " + mobileNumber);

        Card newCard = createNewCard(mobileNumber);
        cardRepository.save(newCard);
        return CardMapper.mapToCardDto(newCard, new CardDto());
    }

    private Card createNewCard(String mobileNumber) {
        Card card = new Card();

        Long randomCardNumber = 1_000_000_000L + new Random().nextInt(900000000);

        card.setMobileNumber(mobileNumber);
        card.setCardNumber(String.valueOf(randomCardNumber));
        card.setCardType(CardConstant.CREDIT_CARD);
        card.setTotalLimit(CardConstant.NEW_CARD_LIMIT);
        card.setAmountUsed(0);
        card.setAvailableAmount(CardConstant.NEW_CARD_LIMIT);

        return card;
    }


    /**
     * @param mobileNumber - Mobile number of the customer
     */
    @Override
    public CardDto fetchCard(String mobileNumber) {
        Card cardInDb = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ObjectNotFoundException("Card", "mobile_number", mobileNumber));
        return CardMapper.mapToCardDto(cardInDb, new CardDto());
    }

    /**
     * @param cardDto - CardDto object
     * @return boolean - indicating if the update of Card is successful or not
     */
    @Override
    public boolean updatedCard(CardDto cardDto) {

        Card cardInDb = cardRepository.findByCardNumber(cardDto.getCardNumber()).orElseThrow(() -> new ObjectNotFoundException("Card", "card_number", cardDto.getCardNumber()));

        CardMapper.mapToCard(cardDto, cardInDb);
        cardRepository.save(cardInDb);

        return true;
    }

    /**
     * @param mobileNumber - Mobile number of the customer
     * @return boolean - indicating if the delete of Card is successful or not
     */
    @Override
    public boolean deleteCard(String mobileNumber) {
        Card cardInDb = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ObjectNotFoundException("Card", "mobile_number", mobileNumber));
        cardRepository.delete(cardInDb);
        return true;
    }
}
