package com.example.wishlistmanagement.controller;

import com.example.wishlistmanagement.model.Wishlist;
import com.example.wishlistmanagement.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")

public class WishlistController {

    @Autowired
    WishlistService wishlistService;

    @PostMapping("/wishlists")
    public Wishlist addInWishlist(@RequestBody String name)  {
        return wishlistService.addInWishlist(name);
    }

    @GetMapping("/wishlists")
    public List<Wishlist> getWishlist()  {
        return wishlistService.getWishlist();
    }

    @DeleteMapping("/wishlists")
    public Wishlist removeFromWishlist(@RequestParam Long id)  {
        return wishlistService.removeFromWishlist(id);
    }
}
