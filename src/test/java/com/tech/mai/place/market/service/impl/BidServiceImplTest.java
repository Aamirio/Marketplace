package com.tech.mai.place.market.service.impl;

import com.tech.mai.place.market.dao.BidDao;
import com.tech.mai.place.market.dao.OfferDao;
import com.tech.mai.place.market.service.impl.BidServiceImpl;
import com.tech.mai.place.market.type.Bid;
import com.tech.mai.place.market.type.Offer;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BidServiceImplTest extends TestCase {

  @Mock private BidDao bidDao;
  @Mock private OfferDao offerDao;

  @InjectMocks
  private BidServiceImpl bidService;

  @Test
  public void testAddBid(){
    long itemId = 123;
    long bidAId = 11;
    Bid bidA = new Bid(itemId, 234, 3, new BigDecimal("12.50"));

    when(bidDao.addBid(any(Bid.class))).thenReturn(new Long(bidAId));
    when(offerDao.getOffersByItemId(any(Long.class))).thenReturn(new ArrayList<Offer>());

    assertEquals(bidAId, bidService.addBid(bidA));
  }

  @Test
  public void testGetBidsByBuyer(){
    long itemId = 999;
    Bid bidA = new Bid(123, itemId, 3, new BigDecimal("12.50"));
    Bid bidB = new Bid(124, itemId, 5, new BigDecimal("10.00"));
    Bid bidC = new Bid(125, itemId, 2, new BigDecimal("15.25"));

    List<Bid> expectedBids = new ArrayList<Bid>();
    expectedBids.add(bidA);
    expectedBids.add(bidB);
    expectedBids.add(bidC);

    when(bidDao.getBids(any(Long.class))).thenReturn(expectedBids);
    List<Bid> buyersBids = bidService.getBids(itemId);

    assertEquals(3, buyersBids.size());
    for (Bid bid : buyersBids) { assertEquals(999, bid.getUserId()); }
  }

  @Test
  public void testGetHighestBidPrice(){
    BigDecimal expHighBidPrice = new BigDecimal("100.12");

    when(bidDao.getHighestBidPrice(any(Long.class))).thenReturn(expHighBidPrice);
    BigDecimal highestBidPrice = bidService.getHighestBidPrice(391);

    assertEquals(0, expHighBidPrice.compareTo(highestBidPrice));
  }

}
