package org.baopen753.cards.service;

import org.baopen753.cards.dto.CardDto;

public interface ICardService {

    /**
     * @param mobileNumber - Mobile number of the customer
     * */
    CardDto createCard(String mobileNumber);

    /**
     * @param mobileNumber - Mobile number of the customer
     * */
    CardDto fetchCard(String mobileNumber);

    /**
     * @param cardDto - CardDto object
     * @return boolean - indicating if the update of Card is successful or not
     * */
    boolean updatedCard(CardDto cardDto);

    /**
     * @param mobileNumber - Mobile number of the customer
     * @return boolean - indicating if the delete of Card is successful or not
     * */
    boolean deleteCard(String mobileNumber);

}
