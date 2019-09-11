### Sample-project
使用ParcelX已封装好的sdk调用电商/服务商相关的JsonRpc接口
- onliestore中已经详细说明和调用电商相关的api，包含传递的参数等
- serviceprovider中已经详细说明和调用服务商相关的api，包含传递的参数等
- Constants类，电商或服务商中都存在该类，分别存放了调用必备的JsonRpc请求的地址、ParcelX平台申请的key、ParcelX平台申请的Secret

### 引入maven依赖，即可进行调用

> 最新版OPEN-API-SDK未推送到Maven公共库，所以请先使用lib目录中的jar包

```
<dependency>
  <groupId>io.github.parcelx</groupId>
  <artifactId>parcelx-openapi-sdk</artifactId>
  <version>1.0.1</version>
</dependency>
```