package com.tech.mai.place.market.dao.impl;

import com.tech.mai.place.market.dao.BidDao;
import com.tech.mai.place.market.dao.OfferDao;
import com.tech.mai.place.market.dao.OrderDao;
import junit.framework.TestCase;
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
public class AbstractDaoImplTest extends TestCase {

    @Configuration
    @ComponentScan(basePackages = "com.tech.mai.place.market.dao")
    static class ContextConfiguration {}

    @Inject protected BidDao bidDao;
    @Inject protected OfferDao offerDao;
    @Inject protected OrderDao orderDao;
}
