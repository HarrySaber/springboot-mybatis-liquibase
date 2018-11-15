package cn.bonuli.mappers;

import cn.bonuli.databaseValues.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * UserMapper
 *
 * @author D.jin
 * @date 2018/10/24
 */

@Mapper
public interface UserMapper {

    @Insert("insert into t_user (id,role_id,user_name,password,company_id) " +
            " values (#{id},#{roleId},#{userName},#{password},#{companyId})")
    public int insert(User value);

    @Select("select * from t_user where user_name = #{userName} and password = #{password}")
    public User queryByUserName(@Param("userName") String userName, @Param("password") String password);

}
