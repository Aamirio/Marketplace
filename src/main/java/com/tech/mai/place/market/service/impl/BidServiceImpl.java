package com.tech.mai.place.market.service.impl;

import com.tech.mai.place.market.dao.BidDao;
import com.tech.mai.place.market.dao.OfferDao;
import com.tech.mai.place.market.dao.OrderDao;
import com.tech.mai.place.market.service.BidService;
import com.tech.mai.place.market.type.Bid;
import com.tech.mai.place.market.type.Offer;
import com.tech.mai.place.market.type.Order;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Named
public class BidServiceImpl implements BidService {

  @Inject private BidDao bidDao;
  @Inject private OfferDao offerDao;
  @Inject private OrderDao orderDao;

  /**
   * Adds a new bid to the existing collection of all bids
   * If there is a match with an existing offer,
   * an order is raised and the bid and offer's states are updated.
   *
   * @param bid A Bid
   * @return The id of the newly added bid
   */
  @Override
  public long addBid(Bid bid) {
    long bidId = bidDao.addBid(bid);
    Offer offer = getMatchingOfferIfExists(bid);

    if (offer != null) {
      Order order = new Order(bid.getUserId(), offer.getUserId(),
                              bid.getItemId(), bid.getQuantity(), offer.getPricePerUnit());
      offer.setQuantity(offer.getQuantity() - bid.getQuantity());
      if (offer.getQuantity() == 0) { offer.expire(); }
      bid.expire();

      orderDao.addOrder(order);
      bidDao.updateBid(bid);
      offerDao.updateOffer(offer);
    }

    return bidId;
  }

  /**
   * Returns all bids for a given buyer
   * @param buyerUserId The user id of the buyer
   * @return A list of bids belonging to the buyer
   */
  @Override
  public List<Bid> getBids(long buyerUserId) {
    return bidDao.getBids(buyerUserId);
  }

  /**
   * Returns the highest price of all bids for the given item
   *
   * @param itemId The id of the item
   * @return The highest price of all bids for the given item which have not expired
   */
  @Override
  public BigDecimal getHighestBidPrice(long itemId) {
    return bidDao.getHighestBidPrice(itemId);
  }

  /*
   * Returns null if no match found
   */
  private Offer getMatchingOfferIfExists(Bid bid) {
    List<Offer> offers = offerDao.getOffersByItemId(bid.getItemId());
    Collections.sort(offers);

    for (Offer offer : offers) {
      boolean bidPriceQualifies = bid.getPricePerUnit().compareTo(offer.getPricePerUnit()) > -1;
      boolean bidQuantityQualifies = bid.getQuantity() <= offer.getQuantity();

      if (bidPriceQualifies && bidQuantityQualifies) { return offer; }
    }
    return null;
  }


}
