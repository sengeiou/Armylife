package cn.armylife.common.Domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class AfterOrder implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column after_order.after_order_id
     *
     * @mbggenerated
     */
    private Long afterOrderId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column after_order.orders_id
     *
     * @mbggenerated
     */
    private Long ordersId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column after_order.product_id
     *
     * @mbggenerated
     */
    private String productId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column after_order.creat_time
     *
     * @mbggenerated
     */
    private String creatTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column after_order.after_order_total
     *
     * @mbggenerated
     */
    private BigDecimal afterOrderTotal;

    private List<OrderDetail> orderDetail;
    private AfterOrderDetail afterOrderDetail;

    private int isPay;

    public int getIsPay() {
        return isPay;
    }

    public void setIsPay(int isPay) {
        this.isPay = isPay;
    }

    public AfterOrderDetail getAfterOrderDetail() {
        return afterOrderDetail;
    }

    public void setAfterOrderDetail(AfterOrderDetail afterOrderDetail) {
        this.afterOrderDetail = afterOrderDetail;
    }

    public List<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table after_order
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column after_order.after_order_id
     *
     * @return the value of after_order.after_order_id
     *
     * @mbggenerated
     */
    public Long getAfterOrderId() {
        return afterOrderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column after_order.after_order_id
     *
     * @param afterOrderId the value for after_order.after_order_id
     *
     * @mbggenerated
     */
    public void setAfterOrderId(Long afterOrderId) {
        this.afterOrderId = afterOrderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column after_order.orders_id
     *
     * @return the value of after_order.orders_id
     *
     * @mbggenerated
     */
    public Long getOrdersId() {
        return ordersId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column after_order.orders_id
     *
     * @param ordersId the value for after_order.orders_id
     *
     * @mbggenerated
     */
    public void setOrdersId(Long ordersId) {
        this.ordersId = ordersId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column after_order.product_id
     *
     * @return the value of after_order.product_id
     *
     * @mbggenerated
     */
    public String getProductId() {
        return productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column after_order.product_id
     *
     * @param productId the value for after_order.product_id
     *
     * @mbggenerated
     */
    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column after_order.creat_time
     *
     * @return the value of after_order.creat_time
     *
     * @mbggenerated
     */
    public String getCreatTime() {
        return creatTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column after_order.creat_time
     *
     * @param creatTime the value for after_order.creat_time
     *
     * @mbggenerated
     */
    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime == null ? null : creatTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column after_order.after_order_total
     *
     * @return the value of after_order.after_order_total
     *
     * @mbggenerated
     */
    public BigDecimal getAfterOrderTotal() {
        return afterOrderTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column after_order.after_order_total
     *
     * @param afterOrderTotal the value for after_order.after_order_total
     *
     * @mbggenerated
     */
    public void setAfterOrderTotal(BigDecimal afterOrderTotal) {
        this.afterOrderTotal = afterOrderTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table after_order
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", afterOrderId=").append(afterOrderId);
        sb.append(", ordersId=").append(ordersId);
        sb.append(", productId=").append(productId);
        sb.append(", creatTime=").append(creatTime);
        sb.append(", afterOrderTotal=").append(afterOrderTotal);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}