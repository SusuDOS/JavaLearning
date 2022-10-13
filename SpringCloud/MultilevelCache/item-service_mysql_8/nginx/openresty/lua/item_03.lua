-- 导入函数库
local common = require('common')
local cjson = require('cjson')
local read_http = common.read_http

-- 获取路径参数
local id = ngx.var[1]

-- 查询商品和库存信息
local itemJson = read_http("/item/"..id,nil)
local stockJson = read_http("/item/stock"..id,nil)

-- json格式反序列化为lua的table格式
local item = cjson.decode(itemJson)
local stock = cjson.decode(stockJson)

-- 拼接为一个table
item.stock = stock.stock
item.sold = stock.sold

-- 序列化为json后返回结果
ngx.say(cjson.encode(item))
