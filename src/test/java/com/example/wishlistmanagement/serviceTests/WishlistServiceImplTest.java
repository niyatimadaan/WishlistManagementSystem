package com.example.wishlistmanagement.serviceTests;

import com.example.wishlistmanagement.model.User;
import com.example.wishlistmanagement.model.Wishlist;
import com.example.wishlistmanagement.repository.WishlistRepository;
import com.example.wishlistmanagement.service.WishlistServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WishlistServiceImplTest {

    @Mock
    private WishlistRepository wishlistRepository;

    @InjectMocks
    private WishlistServiceImpl wishlistService;

    @BeforeEach
    public void setUp() {
        // Mock the SecurityContextHolder to return a mock Authentication
        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);
        User user = new User();
        user.setId(1L);
        lenient().when(authentication.getPrincipal()).thenReturn(user);
        lenient().when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void testAddInWishlist() {
        Wishlist wishlist = new Wishlist("Test Wishlist",  1L);
        when(wishlistRepository.save(any(Wishlist.class))).thenReturn(wishlist);

        Wishlist result = wishlistService.addInWishlist("Test Wishlist");

        assertEquals("Test Wishlist", result.getName());
        assertEquals(1L, result.getUserId());
        verify(wishlistRepository, times(1)).save(any(Wishlist.class));
    }

    @Test
    public void testGetWishlist() {
        Wishlist wishlist1 = new Wishlist("Wishlist  1",  1L);
        Wishlist wishlist2 = new Wishlist("Wishlist  2",  1L);
        List<Wishlist> wishlists = Arrays.asList(wishlist1, wishlist2);

        when(wishlistRepository.findByUserId(1L)).thenReturn(wishlists);

        List<Wishlist> result = wishlistService.getWishlist();

        assertEquals(2, result.size());
        assertEquals("Wishlist  1", result.get(0).getName());
        assertEquals("Wishlist  2", result.get(1).getName());
        verify(wishlistRepository, times(1)).findByUserId(1L);
    }

    @Test
    public void testRemoveFromWishlist() {
        Long id =  1L;
        when(wishlistRepository.existsById(id)).thenReturn(true);

        Wishlist result = wishlistService.removeFromWishlist(id);

        assertNull(result);
        verify(wishlistRepository, times(1)).existsById(id);
        verify(wishlistRepository, times(1)).deleteById(id);
    }
}

