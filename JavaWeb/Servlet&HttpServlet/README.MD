# Servlet

## Request

- 参数解析(url,uri,方法等)

- 请求转发(添加请求头数据)

- 解决乱码


## Response

- 重定向

- 设置编码和渲染格式

- 解决乱码

- 返回图片

```java
FileInputStream fis = new FileInputStream("d://a.jpg");
ServletOutputStream os = response.getOutputStream();

// 3. 完成流的copy
/*
* byte[] buff = new byte[1024];
* int len = 0;
* while ((len = fis.read(buff))!= -1){
* os.write(buff,0,len);
* }
*/

IOUtils.copy(fis, os);
fis.close();
```