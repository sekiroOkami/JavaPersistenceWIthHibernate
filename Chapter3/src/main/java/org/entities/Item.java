package org.entities;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Item {
    private Set<Bid> bids = new HashSet<>();

    public Set<Bid> getBids() {
        return Collections.unmodifiableSet(bids);
    }

    public void setBids(Set<Bid> bids) {
        this.bids = bids;
    }
    public void addBid(Bid bid) {
        Objects.requireNonNull(bid,()-> "Can't add null Bid.");
        if (bid.getItem() != null) {
            throw new IllegalStateException("Bid is already assigned to an Item.");
        }
        bids.add(bid);
        bid.setItem(this);
    }
}
