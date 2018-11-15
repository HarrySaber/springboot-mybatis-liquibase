/**
 * STARPOST CONFIDENTIAL
 * _____________________
 * <p>
 * [2014] - [2018] StarPost Supply Chain Management Co. (Shenzhen) Ltd.
 * All Rights Reserved.
 * <p>
 * NOTICE: All information contained herein is, and remains the property of
 * StarPost Supply Chain Management Co. (Shenzhen) Ltd. and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary
 * to StarPost Supply Chain Management Co. (Shenzhen) Ltd. and its suppliers and
 * may be covered by China and Foreign Patents, patents in process, and are
 * protected by trade secret or copyright law. Dissemination of this information
 * or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from StarPost Supply Chain Management Co. (Shenzhen)
 * Ltd.
 */
package cn.bonuli.mappers;

import java.util.List;

import org.apache.ibatis.annotations.*;

import cn.bonuli.values.SortingValue;

/**
 *
 * @author lijun
 *
 */
@Mapper
public interface SortingMapper {

    @Insert("insert into t_sorting(sorting_port,order_id,received_carton,total_carton) " //
            + "values (#{port},#{orderId},#{receivedCarton},#{totalCarton})")
    public int insert(SortingValue value);

    @ConstructorArgs({ //
            @Arg(column = "sorting_port", javaType = int.class), //
            @Arg(column = "order_id", javaType = String.class), //
            @Arg(column = "received_carton", javaType = int.class), //
            @Arg(column = "totalCarton", javaType = int.class) //
    })
    @Select("select sorting_port,order_id,received_carton,total_carton from t_sorting " //
            + "where sorting_port = #{0}")
    public List<SortingValue> selectBySortingPort(int port);

    @ConstructorArgs({ //
            @Arg(column = "sorting_port", javaType = int.class), //
            @Arg(column = "order_id", javaType = String.class), //
            @Arg(column = "received_carton", javaType = int.class), //
            @Arg(column = "total_carton", javaType = int.class) //
    })
    @Select("select sorting_port,order_id,received_carton,total_carton from t_sorting " //
            + "where order_id = #{0}")
    public SortingValue selectByOrderId(String orderId);

    @Update("update t_sorting set received_carton = #{receivedCarton} where order_id = #{orderId}")
    public int updateReceivedCartonByOrderId(@Param("receivedCarton") int receivedCarton, @Param("orderId") String orderId);
}
