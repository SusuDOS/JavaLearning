# 对数据库操作方法

资料位置:JavaWeb/2021MySqL(高效版)-day05

## 基于XML配置

- 字段不匹配问题&做个映射即可.

```xml
<resultMap id="brandResultMap" type="brand">
    <!--
        id：完成主键字段的映射
            column：表的列名
            property：实体类的属性名
        result：完成一般字段的映射
            column：表的列名
            property：实体类的属性名
    -->
    <result column="brand_name" property="brandName" />
    <result column="company_name" property="companyName" />
</resultMap>


<select id="selectAll" resultMap="brandResultMap">
    select *
    from tb_brand;
</select>
```

- 动态sql

```java
<select id="selectByCondition" resultMap="brandResultMap">
    select *
    from tb_brand
    /* where 1 = 1*/
    <where>
        <if test="status != null">
            and status = #{status}
        </if>
        <if test="companyName != null and companyName != '' ">
            and company_name like #{companyName}
        </if>
        <if test="brandName != null and brandName != '' ">
            and brand_name like #{brandName}
        </if>
    </where>
</select>
```

## 基于注解

- 简单的使用注解模式，复杂的使用xml配置.

```java
@Select("select * from tb_user where id  = #{id}")
User selectById(int id);
```

