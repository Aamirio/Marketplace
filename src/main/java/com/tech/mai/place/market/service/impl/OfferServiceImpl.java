package com.tech.mai.place.market.service.impl;

import com.tech.mai.place.market.dao.BidDao;
import com.tech.mai.place.market.dao.OfferDao;
import com.tech.mai.place.market.dao.OrderDao;
import com.tech.mai.place.market.service.OfferService;
import com.tech.mai.place.market.type.Bid;
import com.tech.mai.place.market.type.Offer;
import com.tech.mai.place.market.type.Order;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Named
public class OfferServiceImpl implements OfferService {

  @Inject
  OfferDao offerDao;
  @Inject
  BidDao bidDao;
  @Inject
  OrderDao orderDao;

  /**
   * Adds a new offer to the existing collection of all offers
   *
   * @param offer An Offer
   * @return The id of the newly added offer
   */
  @Override
  public long addOffer(Offer offer) {
    long offerId = offerDao.addOffer(offer);
    Bid bid = getMatchingBidIfExists(offer);

    if (bid != null) {
      Order order = new Order(bid.getUserId(), offer.getUserId(),
              bid.getItemId(), bid.getQuantity(), offer.getPricePerUnit());
      offer.setQuantity(offer.getQuantity() - bid.getQuantity());
      if (offer.getQuantity() == 0) { offer.expire(); }
      bid.expire();

      orderDao.addOrder(order);
      bidDao.updateBid(bid);
      offerDao.updateOffer(offer);
    }
    return offerId;
  }

  /**
   * Returns all offers for a given seller
   *
   * @param sellerUserId The user id of the seller
   * @return A list of all offers belonging to the seller
   */
  @Override
  public List<Offer> getOffersBySellerId(long sellerUserId) {
    return offerDao.getOffersBySellerId(sellerUserId);
  }

  /**
   * Returns the lowest price of all offers for the given item
   *
   * @param itemId The id of the item
   * @return the lowest price of all offers for the given item
   */
  @Override
  public BigDecimal getLowestOfferPrice(long itemId) {
    return offerDao.getLowestOfferPrice(itemId);
  }

  /*
   * Returns null if no match found
   */
  private Bid getMatchingBidIfExists(Offer offer) {
    List<Bid> bids = bidDao.getBidsByItemId(offer.getItemId());
    Collections.sort(bids);

    for (Bid bid : bids) {
      boolean offerPriceQualifies = offer.getPricePerUnit().compareTo(bid.getPricePerUnit()) < 1;
      boolean offerQuantityQualifies = offer.getQuantity() >= bid.getQuantity();

      if (offerPriceQualifies && offerQuantityQualifies) { return bid; }
    }
    return null;
  }

}