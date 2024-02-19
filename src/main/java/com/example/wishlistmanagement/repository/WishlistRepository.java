package com.example.wishlistmanagement.repository;

import com.example.wishlistmanagement.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    List<Wishlist> findByUserId(Long Userid);
}
