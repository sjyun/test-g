package kr.paxnet.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Item implements Serializable, Comparable<Item> {
    private static final long serialVersionUID = 1L;

    private String description;
    private String sellerEmail;
    private String item;
    private long id;
    private double threshold;
    private double basePrice;
    private LinkedHashSet<Bid> bids = new LinkedHashSet<Bid>();
    private Date sold;
    private String imageUrl;
    
    public Bid getHighestBid(){
    	if(this.bids.size() == 0){
    		return null;
    	}
    
    
    Bid selectedBid = null;
    double amount = 0;
    
    for (Bid b : this.bids) {
        if (b.getAmount() > amount) {
            selectedBid = b;
            amount = b.getAmount();
        }
    }

    return selectedBid;
}

    public synchronized void addBid(Bid b) {
        if (bids.contains(b)) {
            return;
        }

        final double price = b.getAmount();

        Bid bidWithHigherPrice = (Bid) CollectionUtils.find(bids,
                new Predicate() {
                    @Override
                    public boolean evaluate(Object object) {
                        Bid bid = (Bid) object;

                        return bid.getAmount() > price;
                    }
                });

        if (bidWithHigherPrice == null) {
            this.bids.add(b);
        }
    }
    
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSellerEmail() {
		return sellerEmail;
	}

	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getThreshold() {
		return threshold;
	}

	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public LinkedHashSet<Bid> getBids() {
		return bids;
	}

	public void setBids(LinkedHashSet<Bid> bids) {
		this.bids = bids;
	}

	public Date getSold() {
		return sold;
	}

	public void setSold(Date sold) {
		this.sold = sold;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	  @Override
	    public boolean equals(Object obj) {
	        return EqualsBuilder.reflectionEquals(this, obj, new String[] { "bids" });
	    }

	    @Override
	    public int hashCode() {
	        return HashCodeBuilder.reflectionHashCode(this, new String[] { "bids" });
	    }
    
	    @Override
	    public int compareTo(Item o) {
	        return Long.valueOf(o.getId()).compareTo(Long.valueOf(getId()));
	    }
}
