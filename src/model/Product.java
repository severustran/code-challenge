package model;

public class Product {
    private String productName;
    private Double productPrice;
    private String promotion;

    public Product(final String productName, final Double productPrice, final String promotion) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.promotion = promotion;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(final String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(final Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(final String promotion) {
        this.promotion = promotion;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Product)) return false;
        Product product = (Product) obj;
        return (productName.equals(product.productName) && productPrice.equals(product.productPrice) && promotion.equals(product.promotion));
    }
}
