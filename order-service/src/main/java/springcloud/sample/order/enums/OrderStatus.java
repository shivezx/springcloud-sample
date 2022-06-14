package springcloud.sample.order.enums;

/**
 * 订单状态
 *
 * @author shive
 * @createTime 2022-06-11
 */
public enum OrderStatus {
    PENDING_PAYMENT(100, "Pending for payment"),
    PAYED(200, "Payed");

    /** 状态编码 */
    final int code;

    /** 砖头盖说明 */
    final String desc;

    OrderStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
