package cn.armylife.market.Service;

import cn.armylife.common.Domain.*;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public interface OrderService {


    int insert(ShopOrder record, List<Product> productList,String orderExpress);

    int VIPinsert(ShopOrder shopOrder);

    /**
     * 查询用户的订单(可查询用户,商铺)
     * @param shopOrder
     * @return
     */
    Page<ShopOrder> selectAllForMember(ShopOrder shopOrder);

    /**
     * 可查询跑腿订单
     * @param deliveryOrder
     * @return
     */
    Page<DeliveryOrder> selectAlldeliveryForMember(DeliveryOrder deliveryOrder);


    /**
     * 更新订单状态
     * @param shopOrder
     * @return
     */
    @RequestMapping("updateOrderStatus")
    int endOrders(ShopOrder shopOrder);

    /**
     * 通过orderid查找订单
     * @param orderId
     * @return
     */
    ShopOrder selectOrder(Long orderId);

    /**
     * 在订单结算后,增加物品,新建子订单
     * @param afterOrder
     * @return
     */
    int AddAfterOrder(AfterOrder afterOrder);

    /**
     * 查询子订单
     * @param ordersId
     * @return
     */
    AfterOrder SelectAfterOrder(Long ordersId);

    /**
     * 可查询跑腿订单
     * @return
     */
    Page<DeliveryOrder> selectAlldelivery();

    /**
     * 得到当前商铺排队人数
     * @return
     */
    int getShopPeople(String shopId);
    //增加店铺排队人数
    void upShopPeople(String shopId);
    //减少店铺排队人数
    void downShopPeople(String shopId);

    /**
     * 月销
     * @param shopId
     * @return
     */
    String rankMonthPayForShop(Long shopId);

    /**
     * 日销
     * @param shopId
     * @return
     */
    String rankDayPayForShop(Long shopId);

    /**
     * 确认接单
     * @param orderId
     * @return
     */
    int confirmOrder(Long orderId);

    /**
     * 拒绝退款
     * @param orderId
     * @return
     */
    int rejectRefund(Long orderId);

    /**
     * 确认退款
     * @param orderId
     * @return
     */
    int confirmRefund(Long orderId);

    /**
     * 餐后结算
     * @param orderId
     * @return
     */
    int afterOrderRefund(Long orderId, BigDecimal total);

    /**
     *订单信息添加
     * @param record
     * @return
     */
    int orderdatailinsert(OrderDetail record);

    /**
     * 查询已结束订单
     * @param stuId
     * @return
     */
    Page<ShopOrder> selectEndOrder(Long stuId);

    /**
     * 外卖结束订单
     * @param orderID
     * @return
     */
    int orderFulfillment(Long orderID);

}
