package cn.itcast.account.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cn.itcast.account.entity.Account;

/**
 * @author 虎哥
 */
public interface AccountMapper extends BaseMapper<Account> {

    @Update("update account_tbl set money = money - ${money} where user_id = #{userId}")
    int deduct(@Param("userId") String userId, @Param("money") int money);

    @Update("update account_tbl set money = money + ${money} where user_id = #{userId}")
    int refund(@Param("userId") String userId, @Param("money") int money);
}
