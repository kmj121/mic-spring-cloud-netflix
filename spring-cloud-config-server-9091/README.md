# todo
目前修改配置中心文件，无法自动化更新各服务提供者的配置内容，需要配置git上的webhook，
然后通过spring cloud bus，通过kafka做消息队列，各服务提供者去订阅消息，最后通过
@RefreshScope注解去完成自动更新配置文件的缓存，无需重启服务。