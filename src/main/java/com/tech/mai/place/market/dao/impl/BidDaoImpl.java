package com.tech.mai.place.market.dao.impl;

import com.tech.mai.place.market.dao.BidDao;
import com.tech.mai.place.market.type.Bid;
import com.tech.mai.place.market.type.Status;

import javax.inject.Named;
import java.math.BigDecimal;
import java.util.*;

@Named
public class BidDaoImpl implements BidDao {

  private static Map<Long, Bid> allBids = new HashMap<Long, Bid>();

  /**
   * Adds a new bid to the existing collection of all bids
   *
   * @param bid A Bid
   * @return The id of the newly added bid
   */
  @Override
  public long addBid(Bid bid) {
    bid.setBidId(allBids.size() + 1);
    allBids.put(bid.getBidId(), bid);
    return bid.getBidId();
  }

  /**
   * Updates a bid from the existing collection of all bids
   *
   * @param bid A Bid
   * @return True if bid updated successfully. False if bid does not exist.
   */
  @Override
  public boolean updateBid(Bid bid) {
    if (allBids.containsKey(bid.getBidId())) {
      allBids.put(bid.getBidId(), bid);
      return true;
    }
    return false;
  }

  /**
   * Returns all bids for a given buyer
   * @param buyerUserId The user id of the buyer
   * @return A list of bids belonging to the buyer
   */
  @Override
  public List<Bid> getBids(long buyerUserId) {
    List<Bid> buyersBids = new ArrayList<Bid>();

    for (Bid bid : allBids.values()) {
      if (bid.getUserId() == buyerUserId && bid.getBidStatus().equals(Status.VALID)) {
        buyersBids.add(bid);
      }
    }
    return buyersBids;
  }

  /**
   * Returns the highest price of all bids for the given item which have not expired
   *
   * @param itemId The id of the item
   * @return The highest price of all bids for the given item which have not expired
   * If item does not exist, will return BigDecimal.ZERO
   */
  @Override
  public BigDecimal getHighestBidPrice(long itemId) {

    List<Bid> allBidsList = new ArrayList<Bid>();

    for (Bid bid : allBids.values()) {
      if (bid.getItemId() == itemId) { allBidsList.add(bid); }
    }

    Collections.sort(allBidsList, (o1, o2) -> o1.getPricePerUnit().compareTo(o2.getPricePerUnit()));
    Bid highestBid = allBidsList.get(allBidsList.size()-1);
    return highestBid != null ? highestBid.getPricePerUnit() : BigDecimal.ZERO;
  }

  /**
   * Returns all bids by item id
   * @param itemId The id of the item
   * @return all bids for the given item which have not expired
   */
  @Override
  public List<Bid> getBidsByItemId(long itemId) {
    List<Bid> bids = new ArrayList<>();
    for (Bid bid : allBids.values()) {
      if (bid.getItemId() == itemId && Status.VALID.equals(bid.getBidStatus())) { bids.add(bid); }
    }
    return bids;
  }

  /**
   * Removes all existing bids
   */
  @Override
  public void clearData() {
    allBids.clear();
  }
}
