package kr.paxnet.service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import kr.paxnet.model.Item;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.stereotype.Service;


@Service("auctionService")
public class AuctionServiceImpl implements AuctionService {
    static private Logger logger = Logger.getLogger(AuctionServiceImpl.class.getName());
    private ConcurrentSkipListSet<Item> items = new ConcurrentSkipListSet<Item>();
    private AtomicInteger uuidForBids = new AtomicInteger(0);
    private AtomicInteger uuidForItems = new AtomicInteger(0);
    private String[] images = "boat,car,carpet,motorbike".split(",");
   

    @PostConstruct
    public void setupFakeItems() {
       // Assert.isTrue(jmsTemplate != null);

        String[] items = "boat,car,carpet,motorbike".split(",");
        String[] sellerEmails = "gary@gary.com,daniel@daniel.com,josh@josh.com,geoerge@george.com,srinvas@srinvas.com,manuel@manuel.com".split(",");

        for (String item : items) {
            String sellerEmail = sellerEmails[(int) Math.floor(Math.random() * sellerEmails.length)];
            String description = String.format("A lovely %s", item);
            double basePrice = Math.random() * 100;
            String imageUrl = String.format("/images/%s.jpg", item);
          //  postItem(sellerEmail, item, description, basePrice, imageUrl);
        }

        logger.info(String.format("setupFakeItems(): there are %s items", "" + this.items.size()));
    }

  
   
    

    private String randomImage() {
        int indexOfImage = (int) (Math.random() * (this.images.length - 1));

        return this.images[indexOfImage];
    }

  

    @Override
    public Set<Item> getItemsForAuction() {
        Set<Item> uniqueItemsForAuction = new HashSet<Item>();
        uniqueItemsForAuction.addAll(this.items);
        CollectionUtils.filter(uniqueItemsForAuction,
            new Predicate() {
                @Override
                public boolean evaluate(Object object) {
                    Item itm = (Item) object;

                    return itm.getSold() == null;
                }
            });

        return uniqueItemsForAuction;
    }
}
