# WebViewDemo
Android学习笔记：WebView<br>
##### 1、WebViewClient
WebViewClient是WebView的一个辅助类，我们经常使用WebViewClient这个类来**监控webview加载网页的状态**。

下面的是我们在开发中经常会使用的方法。

- onLoadResource

  网页加载网页各种资源的回调，比如你的网页使用了各种图片和css文件与js文件

- onPageStarted

  网页开始加载的回调

- onPageFinished

   网页加载完成的回调

- onReceivedError(WebView view, int errorCode, String description, String failingUrl)

   网页加载失败的回调，如果是运行在6.0的手机,需要onReceivedError(WebView, WebResourceRequest, WebResourceError)

- shouldOverrideUrlLoading

  处理网页内部的跳转

##### 2、WebChromeClient
  我们经常使用WebChromeClient这个类来**处理js交互与网页内容的类**

- onJsAlert

  接收网页的alert事件

- onJsPrompt

  接收网页的prompt输入框事件

- onJsConfirm

  接收网页的confirm确认事件

- onProgressChanged

  页面加载的百分比

- onReceivedIcon

  接收网页的图标

- onReceivedTitle

  接收网页的标题
##### 3、WebSettings
  WebSettings是WebView的设置类，能够**设置webview的各种详细参数**。

- setAllowFileAccess(boolean allow)

  设置是否使用file协议访问网页，常用于访问assets与raw文件夹下面的网页

- setBuiltInZoomControls(boolean enabled)

  设置是否使用webview自带的缩放功能

- setDatabaseEnabled(boolean flag)
- setDomStorageEnabled(boolean flag);

  设置是够支持html5的数据库功能

- setDefaultFontSize(int size)

  设置页面的文字大小

- setJavaScriptEnabled(boolean flag)

   设置是否执行页面的js方法

- setCacheMode

   设置返回键的处理，是否使用缓存加载页面，LOAD_DEFAULT（先从cache加载，没有再去网络加载）, LOAD_CACHE_ELSE_NETWORK（先从cache加载，没有再去网络加载）, LOAD_NO_CACHE（不使用cache） ， LOAD_CACHE_ONLY（只使用cache）
##### 4、WebView
 android提供的控件，能够加载网页和html数据。

- addJavascriptInterface(Object object, String name)

  增加一个对象使webview能够让java代码与js交互

- loadUrl(String url)

  加载一个网页，能够是网络的网页，也能加载本地的网页，本地的网页使用（file:///assets/）来加载。也可以调用一个javaScrpit方法。

- loadData(String data, String mimeType, String encoding)
- loadDataWithBaseURL(String baseUrl, String data, String mimeType, String encoding, String historyUrl)

  加载一段html代码

- reload()

  刷新当前页面

- getTitle()

   获取当前页面的标题

- canGoBack()
- goBack()

   判断webview是否能够回到上一步
##### 5、使用WebView需要注意的问题
1、在android 4.2版本以下的手机有一个问题就是，webview会被JavaScript注入，造成手机出现信息安全的问题，比如下载病毒，发送短信等等。问题就出在addJavascriptInterface方法。JavaScript通过调用这个接口可以直接操作本地的JAVA接口。

2、google在4.2的版本上面解决了这个问题，解决的方法是在被js调用的方法上面加上一个声明， @JavascriptInterface ，但是4.2版本以下的手机就没有提出官方的解决方法。目前来说在**4.2版本以下的手机能够采用以下几个方法来解决安全问题**：
- 1 通过自定义url来进行通信，我们可以重写 shouldOverrideUrlLoading 方法来接收参数
- 2 通过js通过prompt方法传递参数到 onJsPrompt

3、**HTML5与Java交互的五种方式**：
- 1 onJsAlert->JavaScript->alter
- 2 onJsPrompt ->JavaScript->prompt->java返回一个参数给js
- 3 onJsConfirm ->JavaScript->Confirm->java返回一个参数给js
- 4 addJavascriptInterface->4.2以下的手机->@JavascriptInterface
- 5 url跳转->shouldOverrideUrlLoading->根据url进行处理
