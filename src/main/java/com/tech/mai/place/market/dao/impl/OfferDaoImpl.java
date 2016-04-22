package com.tech.mai.place.market.dao.impl;

import com.tech.mai.place.market.dao.OfferDao;
import com.tech.mai.place.market.type.Offer;
import com.tech.mai.place.market.type.Status;

import javax.inject.Named;
import java.math.BigDecimal;
import java.util.*;

@Named
public class OfferDaoImpl implements OfferDao {

  private static Map<Long, Offer> allOffers = new HashMap<Long, Offer>();

  /**
   * Adds a new offer to the existing collection of all offers
   *
   * @param offer An Offer
   * @return The id of the newly added offer
   */
  @Override
  public long addOffer(Offer offer) {
    offer.setOfferId(allOffers.size() + 1);
    allOffers.put(offer.getOfferId(), offer);
    return offer.getOfferId();
  }

  /**
   * Updates an offer from the existing collection of all offers
   *
   * @param offer An Offer
   * @return True if offer updated successfully. False if offer does not exist.
   */
  @Override
  public boolean updateOffer(Offer offer) {
    if (allOffers.containsKey(offer.getOfferId())) {
      allOffers.put(offer.getOfferId(), offer);
      return true;
    }
    return false;
  }

  /**
   * Returns all offers for a given seller which have not expired
   *
   * @param sellerUserId The user id of the seller
   * @return A list of all offers belonging to the seller which have not expired
   */
  @Override
  public List<Offer> getOffersBySellerId(long sellerUserId) {
    List<Offer> sellersOffers = new ArrayList<Offer>();

    for (Offer offer : allOffers.values()) {
      if (offer.getUserId() == sellerUserId && offer.getOfferStatus().equals(Status.VALID)) {
        sellersOffers.add(offer);
      }
    }
    return sellersOffers;
  }

  /**
   * Returns the lowest price of all offers for the given item which have not expired
   *
   * @param itemId The id of the item
   * @return the lowest price of all offers for the given item which have not expired.
   * If item does not exist, will return BigDecimal.ZERO
   */
  @Override
  public BigDecimal getLowestOfferPrice(long itemId) {

    List<Offer> filteredList = new ArrayList<Offer>();

    for (Offer offer : allOffers.values()) {
      if (offer.getItemId() == itemId) { filteredList.add(offer); }
    }

    Collections.sort(filteredList, (o1, o2) -> o1.getPricePerUnit().compareTo(o2.getPricePerUnit()));

    Offer offer = filteredList.get(0);
    return offer != null ? offer.getPricePerUnit() : BigDecimal.ZERO;
  }

  /**
   * Returns all offers by item id
   * @param itemId The id of the item
   * @return all offers for the given item which have not expired
   */
  @Override
  public List<Offer> getOffersByItemId(long itemId) {
    List<Offer> offers = new ArrayList<Offer>();
    for (Offer offer : allOffers.values()) {
      if (offer.getItemId() == itemId && offer.getOfferStatus().equals(Status.VALID)) {
        offers.add(offer);
      }
    }
    return offers;
  }

  /**
   * Removes all existing offers
   */
  @Override
  public void clearData() {
    allOffers.clear();
  }
}
