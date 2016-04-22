package com.tech.mai.place.market.dao;

import com.tech.mai.place.market.type.Offer;

import java.math.BigDecimal;
import java.util.List;

public interface OfferDao {

  /**
   * Adds a new offer to the existing collection of offers
   * @param offer An Offer
   * @return The id of the newly added offer
   */
  public long addOffer(Offer offer);

  /**
   * Updates an offer from the existing collection of all offers
   *
   * @param offer An offer
   * @return True if offer updated successfully. False if offer does not exist.
   */
  public boolean updateOffer(Offer offer);

  /**
   * Returns all offers for a given seller
   * @param sellerId The id of the seller
   * @return A list of offers belonging to the seller
   */
  public List<Offer> getOffersBySellerId(long sellerId);

  /**
   * Returns the lowest price of all offers for the given item
   * @param itemId The id of the item
   * @return the lowest price of all offers for the given item
   */
  public BigDecimal getLowestOfferPrice(long itemId);

  /**
   * Returns all offers by item id
   * @param itemId The id of the item
   * @return all offers for the given item which have not expired
   */
  public List<Offer> getOffersByItemId(long itemId);

  /**
   * Removes all existing offers
   */
  public void clearData();
}
