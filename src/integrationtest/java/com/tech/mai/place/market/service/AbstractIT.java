package com.tech.mai.place.market.service;

import com.tech.mai.place.market.dao.BidDao;
import com.tech.mai.place.market.dao.OfferDao;
import com.tech.mai.place.market.dao.OrderDao;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
@Ignore
public class AbstractIT extends TestCase {

    @Configuration
    @ComponentScan(basePackages = "com.tech.mai.place.market")
    static class ContextConfiguration {}

    @Inject protected BidService bidService;
    @Inject protected OfferService offerService;
    @Inject protected OrderService orderService;
    @Inject protected BidDao bidDao;
    @Inject protected OfferDao offerDao;
    @Inject protected OrderDao orderDao;

    @Override
    @Before
    public void setUp() throws Exception {
        bidDao.clearData();
        offerDao.clearData();
        orderDao.clearData();
    }
}
