#WDA
最新加入了zip和rar文件的预览功能
### 注意
- 在web-inf/lib下是项目依赖的jar，有些jar文件需要手动导入
- 需要在服务器上安装openoffice服务，测试环境使用的是openoffice4，启动wda服务器注意先在config.properties中配置openoffice4的启动命令
### 配置文件docTypeConf.xml
![输入图片说明](http://git.oschina.net/uploads/images/2015/1129/184440_e50ee859_24089.png "在这里输入图片标题")

- conf/files:被转换为的文件类型 exname为支持的文件类型名称 filename为生成文件名filename的path参数为文件相对路径
- conf/types:文件转换关系对照
- conf/types/name:源文件后缀名
- conf/types/target:目标文件类型，可以生成多种,需要与conf/files/file/exname一致/
							
### 配置文件config.properties
![输入图片说明](http://git.oschina.net/uploads/images/2015/1129/184507_16248aba_24089.png "在这里输入图片标题")

- config.file.dir.path:文件存储地址，需要配置到webroot下
- config.server.openoffice.cmd:openoffice的soffice服务启动命令
- config.rmi.port:rmi绑定端口
							
### RMI调用
```
WdaAppInter personService = (WdaAppInter) Naming.lookup("rmi://127.0.0.1:8888/wda");
personService.generateDoc("1234", new File("D:\\doc\\1.docx"));
```
### com.farm.wda.inter.WdaAppInter
```
/**
     * 开始生产WEB文档
     *
     * @param key
     *            文档关键字，后续通过它调用相关资源
     * @param file
     *            原文件
     * @param htmlinfo
     *            被显示的html信息（如文件名称等）
     * @throws ErrorTypeException
     * @throws RemoteException
     */
    public void generateDoc(String key, File file, String htmlinfo) throws ErrorTypeException, RemoteException;

    /**
     * 开始生产WEB文档
     *
     * @param key
     *            文档关键字，后续通过它调用相关资源
     * @param file
     *            原文件
     * @param fileTypeName
     *            扩展名
     * @param htmlinfo
     *            被显示的html信息（如文件名称等）
     * @throws ErrorTypeException
     * @throws RemoteException
     */
    public void generateDoc(String key, File file, String fileTypeName, String htmlinfo)
            throws ErrorTypeException, RemoteException;

    /**
     * 获得可以被转换的文件类型
     *
     * @return
     */
    public Set<String> getSupportTypes() throws RemoteException;

    /**
     * 文档是否已经生成完毕
     *
     * @param key
     * @return
     * @throws ErrorTypeException
     */
    public boolean isGenerated(String key, String doctype) throws ErrorTypeException, RemoteException;

    /**
     * 文档是否有日志记录
     *
     * @param key
     * @return
     * @throws ErrorTypeException
     */
    public boolean isLoged(String key) throws RemoteException;

    /**
     * 删除日志（通过日志判断是否生成过文档时，可以通过此方法重新生成文档）
     *
     * @param key
     * @return
     * @throws ErrorTypeException
     */
    public void delLog(String key) throws RemoteException;

    /**
     * 获得日志地址
     *
     * @param key
     * @return
     */
    public String getlogURL(String key) throws RemoteException;

    /**
     * 获得文档文本字符串
     *
     * @param key
     * @return
     * @throws ErrorTypeException
     */
    public String getText(String key) throws ErrorTypeException, RemoteException;

    /**
     * 获得文档信息字符串
     *
     * @param key
     * @return
     * @throws ErrorTypeException
     */
    public String getInfo(String key) throws ErrorTypeException, RemoteException;

    /**
     * 获得在线文档浏览的URL
     *
     * @param key
     * @param exname
     * @return
     * @throws ErrorTypeException
     */
    public String getUrl(String key, String docType) throws ErrorTypeException, RemoteException;
```
### 演示
演示地址：[http://www.wcpdoc.com/webdoc/view/Pub8a2831b35138cc1f015139118bc30002.html](http://www.wcpdoc.com/webdoc/view/Pub8a2831b35138cc1f015139118bc30002.html)

在wcp中集成了wda的功能，通过wcp的连接展示wda系统

![输入图片说明](http://git.oschina.net/uploads/images/2015/1126/113716_0338142e_24089.png "在这里输入图片标题")

如图，点击预览后进入wda系统

![输入图片说明](http://git.oschina.net/uploads/images/2015/1126/113548_d2957986_24089.png "在这里输入图片标题")

