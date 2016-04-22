package com.tech.mai.place.market.dao;

import com.tech.mai.place.market.type.Bid;

import java.math.BigDecimal;
import java.util.List;

public interface BidDao {

  /**
   * Adds a new bid to the existing collection of bids
   * @param bid A Bid
   * @return The id of the newly added bid
   */
  public long addBid(Bid bid);

  /**
   * Updates a bid from the existing collection of all bids
   *
   * @param bid A Bid
   * @return True if bid updated successfully. False if bid does not exist.
   */
  public boolean updateBid(Bid bid);

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

  /**
   * Returns all bids which have not expired by item id
   * @param itemId The id of the item
   * @return all bids for the given item which have not expired
   */
  public List<Bid> getBidsByItemId(long itemId);

  /**
   * Removes all existing bids
   */
  public void clearData();

}
