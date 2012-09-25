
package order;
public class Order {
    private String itemName;
    private int itemPrice;
    private int itemQty;
    private int totalPrice;
    private String cardAuthorityResult;
    private int shippableItemCount;
    private boolean shipped = false;

    public void addItem(String itemName, int itemPrice, int itemQty) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQty = itemQty;
    }

    public boolean isCanAcceptOrder() {
        if (itemName == null || itemName.equals("")) {
            return false;
        }
        if (itemPrice <= 0) {
            return false;
        }
        if (itemQty <= 0) {
            return false;
        }
        if (totalPrice != 0) {
            return false;
        }

        return true;
    }

    public void doAcceptOrder() {
        if (!isCanAcceptOrder()) {
            throw new RuntimeException("Invalid Status");
        }
        totalPrice = itemPrice * itemQty;
    }

    public boolean isCanCardAuthorize() {
        if (totalPrice == 0) {
            return false;
        }
        return true;
    }

    public void doCardAuthorize() {
        if (!isCanCardAuthorize()) {
            throw new RuntimeException("Invalid Status");
        }
        cardAuthorityResult = "OK";
    }
    public boolean isSuccessCardAuthorize() {
        return "OK".equals(cardAuthorityResult);
    }

    public boolean isCanOrderItem() {
        if (!isSuccessCardAuthorize()) {
            return false;
        }
        if (itemQty <= shippableItemCount) {
            return false;
        }
        return true;
    }

    public void doOrderItem() {
        // 注文したら即納という前提で(w
        shippableItemCount = itemQty;
    }

    public boolean isCanShip() {
        if (shippableItemCount != itemQty) {
            return false;
        }
        return true;
    }

    public void doShip() {
        if (!isCanShip()) {
            throw new RuntimeException("Can't ship!");
        }
        shipped = true;
    }

    public boolean isFinished() {
        return shipped;
    }
}
