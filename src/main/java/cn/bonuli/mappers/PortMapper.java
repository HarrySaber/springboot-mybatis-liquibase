package cn.bonuli.mappers;

import cn.bonuli.values.PortValue;
import org.apache.ibatis.annotations.*;

/**
 * PortMapper
 *
 * @author D.jin
 * @date 2018/6/26
 */
@Mapper
public interface PortMapper {
    @Insert("insert into t_port(ip,sorting_port) " //
            + "values (#{ip},#{port})")
    public int insert(PortValue value);

    @ConstructorArgs({ //
            @Arg(column = "ip", javaType = String.class), //
            @Arg(column = "sorting_port", javaType = int.class) //
    })
    @Select("select * from t_port where sorting_port = #{0}")
    public PortValue selectBySortingPort(int port);
}
