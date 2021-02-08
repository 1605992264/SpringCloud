服务中心 nacos
服务调用 restTemplate openFeign

1.nacos中不在同一分组的服务不能相互调用。
nacosConfig的DataId必须加后缀 如client-dev.properties

restTemplate 
    传对象时默认会使用json格式。可以自己设置请求头.
    传文件时,必须把文件转换成org.springframework.core.io.Resource传入。
openFeign
    Feign接口方法最多只能出现一个不带有注解的参数。
    无注解的参数为json格式。
    带@RequestParam注解的参数为表单参数。
Hystrix
    服务熔断:当某服务出现不可用或响应超时的情况时,暂时停止对该服务的调用