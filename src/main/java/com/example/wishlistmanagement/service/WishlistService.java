package com.example.wishlistmanagement.service;

import com.example.wishlistmanagement.model.Wishlist;

import java.util.List;

public interface WishlistService {
    Wishlist addInWishlist(String name);

    List<Wishlist> getWishlist();

    Wishlist removeFromWishlist(Long id);
}
