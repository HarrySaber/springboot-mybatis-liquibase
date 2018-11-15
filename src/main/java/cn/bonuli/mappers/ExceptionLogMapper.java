package cn.bonuli.mappers;

import cn.bonuli.values.ExceptionLog;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * ExceptionLogMapper
 *
 * @author D.jin
 * @date 2018/6/29
 */
@Mapper
public interface ExceptionLogMapper {

    @Insert("insert into t_exception_log(carton_number,exception,sorting_at) " //
            + "values (#{cartonNumber},#{exception},#{sortingAt})")
    public int insert(ExceptionLog exceptionLog);


    @ConstructorArgs({ //
            @Arg(column = "carton_number", javaType = String.class), //
            @Arg(column = "exception", javaType = String.class), //
            @Arg(column = "sorting_at", javaType = Date.class) //
    })
    @Select("select * from t_exception_log where carton_number = #{cartonNumber}")
    public ExceptionLog selectBycartonNumber(@Param("cartonNumber") String cartonNumber);


    @ConstructorArgs({ //
            @Arg(column = "carton_number", javaType = String.class), //
            @Arg(column = "exception", javaType = String.class), //
            @Arg(column = "sorting_at", javaType = Date.class) //
    })
    @Select("<script> select * from t_exception_log where 1=1 " +
            "<if test=\"cartonNumber!=null and cartonNumber!=''\"> and carton_number = #{cartonNumber} </if>" +
            "order by sorting_at desc LIMIT #{pageSize} OFFSET #{currentPage} </script>")
    public List<ExceptionLog> query(@Param("cartonNumber") String cartonNumber, @Param("pageSize") int pageSize, @Param("currentPage") int currentPage);

    @Select("<script> select count(-1) from t_exception_log where 1=1 " +
            "<if test=\"cartonNumber!=null and cartonNumber!=''\" > AND carton_number = #{cartonNumber} </if>" +
            " order by sorting_at desc "+
            " </script> ")

    public int count(@Param("cartonNumber") String cartonNumber);
}
