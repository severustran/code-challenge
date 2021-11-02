package model;

import java.util.Comparator;

public class ItemInCart{
    private Product product;
    private Integer quantity;

    public ItemInCart(final Product product, final Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public ItemInCart() {
        this.product = null;
        this.quantity = 0;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(final Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }
}
