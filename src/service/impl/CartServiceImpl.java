package service.impl;

import model.Cart;
import model.ItemInCart;
import model.Promotion;
import service.CartService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CartServiceImpl implements CartService {
    @Override
    public Cart getNormalProduct(final List<ItemInCart> items) {
        if (items.size() > 0) {
            Cart cart = new Cart();
            List<ItemInCart> itemInCartList = new ArrayList<>();
            for (ItemInCart item : items) {
                if (item.getProduct().getPromotion().equals(Promotion.normal)) {
                    itemInCartList.add(item);
                }
            }
            if (itemInCartList.size() > 0) {
                cart.setItemInCartList(itemInCartList);
            }
            return cart;
        } else {
            return null;
        }
    }

    @Override
    public Cart getHalfPriceProducts(final List<ItemInCart> items) {
        if(items.size() > 0) {
            Cart cart = new Cart();
            List<ItemInCart> itemInCartList = new ArrayList<>();
            for (ItemInCart item : items) {
                if (item.getProduct().getPromotion().equals(Promotion.halfPricePromoBaseOnItemsProductA)
                        || item.getProduct().getPromotion().equals(Promotion.promoProductA)) {
                    itemInCartList.add(item);
                }
            }
            if (itemInCartList.size() > 0) {
                cart.setItemInCartList(itemInCartList);
            }
            return cart;
        } else {
            return null;
        }
    }

    @Override
    public Cart getSetOfPromoProducts(final List<ItemInCart> items) {
        if (items.size() > 0) {
            Cart cart = new Cart();
            List<ItemInCart> itemInCartList = new ArrayList<>();
            for (ItemInCart item : items) {
                if (item.getProduct().getPromotion().equals(Promotion.promoSet3OfProducts)) {
                    itemInCartList.add(item);
                }
            }
            if (itemInCartList.size() > 0) {
                cart.setItemInCartList(itemInCartList);
            }
            return cart;
        }
        return null;
    }
}
