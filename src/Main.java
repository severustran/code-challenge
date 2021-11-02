import javafx.util.Pair;
import model.Cart;
import model.ItemInCart;
import model.Product;
import model.Promotion;
import service.CartService;
import service.PaymentService;
import service.impl.CartServiceImpl;
import service.impl.PaymentServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        CartService cartService = new CartServiceImpl();
        PaymentService paymentService = new PaymentServiceImpl(cartService);

        //Products
        Product A = new Product("A", 10.00, Promotion.promoProductA);
        Product B = new Product("B", 9.00, Promotion.halfPricePromoBaseOnItemsProductA);
        Product X = new Product("X", 10.00, Promotion.promoSet3OfProducts);
        Product Y = new Product("Y", 5.00, Promotion.promoSet3OfProducts);
        Product Z = new Product("Z", 4.00, Promotion.promoSet3OfProducts);
        Product P = new Product("P", 3.00, Promotion.promoSet3OfProducts);
        Product Q = new Product("Q", 8.00, Promotion.promoSet3OfProducts);
        Product R = new Product("R", 2.00, Promotion.normal);

        //Promotions
        List<Pair<Product, Product>> pairHalfPricePromoList = new ArrayList<>();
        pairHalfPricePromoList.add(new Pair<>(A, B));

        Cart cart = new Cart();

        List<ItemInCart> itemList = new ArrayList<>();
        cart.addToCart(new ItemInCart(A, 1));
        cart.addToCart(new ItemInCart(B, 1));
        cart.addToCart(new ItemInCart(B, 1));
        cart.addToCart(new ItemInCart(X, 1));
        cart.addToCart(new ItemInCart(Y, 1));
        cart.addToCart(new ItemInCart(Z, 1));
        cart.addToCart(new ItemInCart(R, 1));
        System.out.println(paymentService.calcAllProducts(cart, pairHalfPricePromoList));
    }
}
