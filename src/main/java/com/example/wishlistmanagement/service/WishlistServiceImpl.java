package com.example.wishlistmanagement.service;

import com.example.wishlistmanagement.model.User;
import com.example.wishlistmanagement.model.Wishlist;
import com.example.wishlistmanagement.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    WishlistRepository wishlistRepository;

    @Override
    public Wishlist addInWishlist(String name) {
        Long id = getUserId();
        Wishlist wishlist= new Wishlist(name,id);
        return wishlistRepository.save(wishlist);
    }

    @Override
    public List<Wishlist> getWishlist() {
        Long id = getUserId();
        List<Wishlist> wishlist= wishlistRepository.findByUserId(id);
        return wishlist;
    }

    private Long getUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Long id= user.getId();
        return id;
    }

    @Override
    public Wishlist removeFromWishlist(Long id) {
        if(wishlistRepository.existsById(id)) {
            wishlistRepository.deleteById(id);
        }
        return null;
    }
}
