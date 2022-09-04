package com.itheima.dao;

import com.itheima.domain.Book;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

// 没有像Springboot一样在此处添加@Mapper注解，但是仍然work

public interface BookDao {
  // @Insert("insert into tbl_book values(null,#{type},#{name},#{description})")
  // 返回受影响的行数，可以确定操作时候成功.
  @Insert(
    "insert into tbl_book (type,name,description) values(#{type},#{name},#{description})"
  )
  public int save(Book book);

  @Update(
    "update tbl_book set type = #{type}, name = #{name}, description = #{description} where id = #{id}"
  )
  public int update(Book book);

  @Delete("delete from tbl_book where id = #{id}")
  public int delete(Integer id);

  @Select("select * from tbl_book where id = #{id}")
  public Book getById(Integer id);

  @Select("select * from tbl_book")
  public List<Book> getAll();
}
