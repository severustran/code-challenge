package service;

import javafx.util.Pair;
import model.Cart;
import model.Product;

import java.util.List;

public interface PaymentService {
    //Use for calc total price of half price promotion products
    Double calcHalfPrice(Cart cartWithHalfPricePromoProducts, List<Pair<Product, Product>> pairPromoProductsList);
    //Use for calc total price of set of promotion products
    Double calcSet3OfProducts(Cart cartWithSetPromoProducts);
    //Use for calc total price of products with no promotion
    Double calcNormalProducts(Cart cartWithNoPromoProducts);
    //Use for calc all
    Double calcAllProducts(Cart cart, List<Pair<Product, Product>> pairPromoProductsList);
}
