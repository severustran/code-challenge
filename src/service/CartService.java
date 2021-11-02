package service;

import model.Cart;
import model.ItemInCart;

import java.util.List;

public interface CartService {
    Cart getNormalProduct(List<ItemInCart> items);
    Cart getHalfPriceProducts(List<ItemInCart> items);
    Cart getSetOfPromoProducts(List<ItemInCart> items);
}
