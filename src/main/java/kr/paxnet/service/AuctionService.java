package kr.paxnet.service;

import java.util.Set;

import kr.paxnet.model.Bid;
import kr.paxnet.model.Item;

public interface AuctionService {

	//Item postItem(String sellerEmail, String item, String description, double minPrice, String imageUrl);

 //   Bid bid(Item item, double price);

    //void acceptBid(Item item, Bid bid);

    Set<Item> getItemsForAuction();
}
