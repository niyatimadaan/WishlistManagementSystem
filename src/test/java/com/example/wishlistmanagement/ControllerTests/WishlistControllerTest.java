package com.example.wishlistmanagement.ControllerTests;

import com.example.wishlistmanagement.controller.WishlistController;
import com.example.wishlistmanagement.model.Wishlist;
import com.example.wishlistmanagement.service.WishlistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class WishlistControllerTest {

    @Mock
    private WishlistService wishlistService;

    @InjectMocks
    private WishlistController wishlistController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(wishlistController).build();
    }

    @Test
    public void testAddInWishlist() throws Exception {
        Wishlist wishlist = new Wishlist();
        wishlist.setName("Test Wishlist");

        when(wishlistService.addInWishlist(anyString())).thenReturn(wishlist);

        mockMvc.perform(post("/api/wishlists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("Test Wishlist"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Wishlist"));

        verify(wishlistService, times(1)).addInWishlist("Test Wishlist");
    }

    @Test
    public void testGetWishlist() throws Exception {
        Wishlist wishlist1 = new Wishlist();
        wishlist1.setName("Wishlist  1");
        Wishlist wishlist2 = new Wishlist();
        wishlist2.setName("Wishlist  2");

        List<Wishlist> wishlists = Arrays.asList(wishlist1, wishlist2);

        when(wishlistService.getWishlist()).thenReturn(wishlists);

        mockMvc.perform(get("/api/wishlists"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Wishlist  1"))
                .andExpect(jsonPath("$[1].name").value("Wishlist  2"));

        verify(wishlistService, times(1)).getWishlist();
    }
    @Test
    public void testRemoveFromWishlist() throws Exception {
        Wishlist wishlist = new Wishlist();
        wishlist.setName("Wishlist  1");

        when(wishlistService.removeFromWishlist(anyLong())).thenReturn(wishlist);

        mockMvc.perform(delete("/api/wishlists")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Wishlist  1"));

        verify(wishlistService, times(1)).removeFromWishlist(1L);
    }
}

