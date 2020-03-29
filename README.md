本项目是使用spring boot mybatis-plus开发简单web项目的脚手架项目

- `MybatisPlusCodeGenerator`为mybatis-plus代码生成类，主要修改一下配置，即可生成代码

```java
  /**
     * 要生成代码的表名称
     */
    private static final String[] tableName = new String[]{

            "t_test"

    };


    /**
     * 表前缀
     */
    private static final String tablePrefix = "t_";

    /**
     * 数据路主机名或者ip
     */
    private static final String host = "localhost";

    /**
     * 数据库端口号
     */
    private static final int port = 3306;

    /**
     * 数据库用户名
     */
    private static final String userName = "root";

    /**
     * 数据库密码
     */
    private static final String password = "root";

    /**
     * 数据库名
     */
    private static final String dbName = "test";
```



- `GlobalExceptionHandler`该类为统一异常处理类，异常处理结果使用`Result`进行封装

- `WrapRequestResponseBodyMethodProcessor`该类主要把rest接口返回值封装为`Result`类型，controller接口直接返回业务接口处理后的数据，不用关心数据类型的统一封装。比如接口如下，不用返回值为`Result`类型。

 ```java
  @GetMapping("/data")
  @ResponseBody
  public List<M> getData(Query<M> query) {
      return service.getData(query);
  }
 ```

- `CustomJacksonAnnotationIntrospector`增加了对枚举类型的序列化，针对枚举类型，我们只需要在上面打上`JsonEnum`注解，并指定字段代表的枚举类型，序列化时会自动把枚举类型代表的文字含义返回。如下：

```java
@JsonEnum(value = TestEnum.class)
private Integer code = 1;
```

