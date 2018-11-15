package cn.bonuli.mappers;

import cn.bonuli.values.SortingHistory;
import org.apache.ibatis.annotations.*;

import java.util.Date;

/**
 * SortingHistoryMapper
 *
 * @author D.jin
 * @date 2018/6/26
 */
@Mapper
public interface SortingHistoryMapper {
    @Insert("insert into t_sorting_history(sorting_port,carton_number,sorting_at) " //
            + "values (#{sortingPort},#{cartonNumber},#{sortingAt})")
    public int insert(SortingHistory sortingHistory);

    @ConstructorArgs({ //
            @Arg(column = "sorting_port", javaType = int.class), //
            @Arg(column = "carton_number", javaType = String.class), //
            @Arg(column = "sorting_at", javaType = Date.class) //
    })
    @Select("select * from t_sorting_history where carton_number = #{0}")
    public SortingHistory selectByCartonNumber(String CartonNumber);
}
