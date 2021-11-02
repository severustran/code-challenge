package service.impl;

import javafx.util.Pair;
import model.Cart;
import model.ItemInCart;
import model.Product;
import service.CartService;
import service.PaymentService;

import java.util.*;

public class PaymentServiceImpl implements PaymentService {
    private final CartService cartService;

    public PaymentServiceImpl(final CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public Double calcHalfPrice(final Cart cartWithHalfPricePromoProducts, final List<Pair<Product, Product>> pairPromoProductsList) {
        double total = 0.0;
        if (!Objects.isNull(cartWithHalfPricePromoProducts)) {

            List<Pair<ItemInCart, ItemInCart>> pairCartWithHalfPricePromoProducts = new ArrayList<>();
            List<ItemInCart> itemInCartList = cartWithHalfPricePromoProducts.getItemInCartList();

            for (Pair<Product, Product> pairPromoProduct: pairPromoProductsList) {
                boolean hasInCart = false;
                Pair<ItemInCart, ItemInCart> cartWithPairProduct;
                ItemInCart key = new ItemInCart(pairPromoProduct.getKey(), 0);
                ItemInCart value = new ItemInCart(pairPromoProduct.getValue(), 0);
                for (ItemInCart item : itemInCartList) {
                    if (pairPromoProduct.getKey().equals(item.getProduct())){
                        cartWithPairProduct = new Pair<>(item, value);
                        hasInCart = true;
                        pairCartWithHalfPricePromoProducts.add(cartWithPairProduct);
                        break;
                    }
                }
                if (!hasInCart) {
                    cartWithPairProduct = new Pair<>(key, value);
                    pairCartWithHalfPricePromoProducts.add(cartWithPairProduct);
                }

            }

            for (Pair<ItemInCart, ItemInCart> pairItem: pairCartWithHalfPricePromoProducts) {
                for (ItemInCart item : itemInCartList) {
                    if (pairItem.getValue().getProduct().equals(item.getProduct())) {
                        pairItem.getValue().setQuantity(item.getQuantity());
                        pairItem.getValue().setProduct(item.getProduct());
                        break;
                    }
                }
            }

            //Calculate
            for (Pair<ItemInCart, ItemInCart> pairItem: pairCartWithHalfPricePromoProducts) {
                if (pairItem.getKey().getQuantity() >= pairItem.getValue().getQuantity()) {
                    total = (pairItem.getKey().getProduct().getProductPrice() * pairItem.getKey().getQuantity()) +
                            (pairItem.getValue().getQuantity()*0.5*pairItem.getValue().getProduct().getProductPrice());
                } else {
                    total = (pairItem.getKey().getProduct().getProductPrice() * pairItem.getKey().getQuantity()) +
                            (pairItem.getKey().getQuantity()*0.5*pairItem.getValue().getProduct().getProductPrice())
                            + ((pairItem.getValue().getQuantity()-pairItem.getKey().getQuantity())*pairItem.getValue().getProduct().getProductPrice());
                }
            }

        }
        return total;
    }

    @Override
    public Double calcSet3OfProducts(final Cart cartWithSetPromoProducts) {
        double total = 0.0;
       if(cartWithSetPromoProducts != null) {
           int quantity = 0;
           int setOfProduct = 0;
           List<ItemInCart> itemInCartList = cartWithSetPromoProducts.getItemInCartList();

           if (itemInCartList.size() > 2) {
               Comparator<ItemInCart> sortByPriceDesc = new Comparator<ItemInCart>() {
                   @Override
                   public int compare(final ItemInCart product1, final ItemInCart product2) {
                       return product1.getProduct().getProductPrice().compareTo(product2.getProduct().getProductPrice());
                   }
               };

               itemInCartList.sort(sortByPriceDesc);
           }

           for (ItemInCart itemInCart : itemInCartList) {
//               System.out.println(itemInCart.getProduct().getProductPrice() + " " + itemInCart.getQuantity());
               quantity += itemInCart.getQuantity();
           }

           setOfProduct = quantity/3;

           for (ItemInCart itemInCart : itemInCartList) {
               if(setOfProduct >= itemInCart.getQuantity()) {
                   setOfProduct -= itemInCart.getQuantity();
               } else {
                   total += itemInCart.getProduct().getProductPrice() * Math.abs(itemInCart.getQuantity() - setOfProduct);
                   setOfProduct = 0;
               }
           }
       }
        return total;
    }

    @Override
    public Double calcNormalProducts(final Cart cartWithNoPromoProducts) {
        double total = 0.0;
        if (cartWithNoPromoProducts != null) {
            List<ItemInCart> itemInCartList = cartWithNoPromoProducts.getItemInCartList();
            for (ItemInCart itemInCart : itemInCartList) {
                total += (itemInCart.getProduct().getProductPrice() * itemInCart.getQuantity());
            }
        }
        return total;
    }

    @Override
    public Double calcAllProducts(final Cart cart, List<Pair<Product, Product>> pairPromoProductsList) {
        double total = 0.0;
        double totalHalfPromoCart = 0.0;
        double totalSetPromoCart = 0.0;
        double totalNormalCart = 0.0;


        List<ItemInCart> items = cart.getItemInCartList();

        Cart halfPromoCart = cartService.getHalfPriceProducts(items);
        Cart setPromoCart = cartService.getSetOfPromoProducts(items);
        Cart normalCart = cartService.getNormalProduct(items);

        if (halfPromoCart.getItemInCartList() != null) {
            totalHalfPromoCart = calcHalfPrice(halfPromoCart, pairPromoProductsList);
        }
        if (setPromoCart.getItemInCartList() != null) {
            totalSetPromoCart = calcSet3OfProducts(setPromoCart);
        }
        if (normalCart.getItemInCartList() != null) {
            totalNormalCart = calcNormalProducts(normalCart);
        }

        total = totalHalfPromoCart + totalNormalCart + totalSetPromoCart;

        return total;
    }
}
