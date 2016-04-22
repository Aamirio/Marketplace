package com.tech.mai.place.market.service;

import com.tech.mai.place.market.type.Bid;

import java.math.BigDecimal;
import java.util.List;

public interface BidService {

  /**
   * Adds a new bid to the existing collection of bids
   * If there is a match with an existing offer,
   * an order is raised and the bid and offer's states are updated.
   * @param bid A Bid
   * @return The id of the newly added bid
   */
  public long addBid(Bid bid);

  /**
   * Returns all bids for a given buyer
   * @param buyerUserId The user id of the buyer
   * @return A list of bids belonging to the buyer
   */
  public List<Bid> getBids(long buyerUserId);

  /**
   * Returns the highest price of all bids for the given item
   * @param itemId The id of the item
   * @return The highest price of all bids for the given item
   */
  public BigDecimal getHighestBidPrice(long itemId);
}
