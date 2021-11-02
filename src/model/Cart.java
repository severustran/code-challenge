package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<ItemInCart> itemInCartList = new ArrayList<>();
    private Double total;

    public Cart(final List<ItemInCart> itemInCartList) {
        this.itemInCartList = itemInCartList;
    }

    public Cart() {
    }

    public void addToCart(ItemInCart item) {
        boolean isInCart = true;
        for (ItemInCart itemInCart : itemInCartList) {
            if (itemInCart.getProduct() == item.getProduct()) {
                itemInCart.setQuantity(itemInCart.getQuantity() + item.getQuantity());
                isInCart = false;
                break;
            }
        }
        if (isInCart) {
            this.itemInCartList.add(item);
        }
    }

    public List<ItemInCart> getItemInCartList() {
        return itemInCartList;
    }

    public void setItemInCartList(final List<ItemInCart> itemInCartList) {
        this.itemInCartList = itemInCartList;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(final Double total) {
        this.total = total;
    }

}
