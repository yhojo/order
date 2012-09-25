package order;

import static org.junit.Assert.*;

import org.junit.Test;

public class OrderTest {

    @Test
    public void testSimpleScenario() {
        Order order = new Order();
        assertFalse("注文の中身が無いので受注できない", order.isCanAcceptOrder());
        order.addItem("sample01", 100, 10);
        assertTrue("商品が決まったので、受注可能", order.isCanAcceptOrder());
        assertFalse(order.isCanCardAuthorize());
        order.doAcceptOrder();
        assertFalse("受注済みなので、もう受注できない", order.isCanAcceptOrder());
        assertFalse("まだ仕入れ用に発注できない", order.isCanOrderItem());
        assertTrue("受注したのでカードオーソリ可能", order.isCanCardAuthorize());
        order.doCardAuthorize();
        assertTrue(order.isSuccessCardAuthorize());
        assertFalse(order.isCanShip());
        order.doOrderItem();
        assertTrue(order.isCanShip());
        order.doShip();
        assertFalse(order.isCanShip());
        assertTrue(order.isFinished());
    }

}
