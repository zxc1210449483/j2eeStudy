**8.1 什么是Facelets**
Facelets是一个强大的轻量级页面声明语言，用于使用HTML样式模版构建JSF视图，构建组件树。特性：
	使用XHTML创建Web页面。
	支持Facelets标记库和JSF及JSTL标签库。
	支持EL。
	提供组件和页面模版。
优点：包含一个功能强大的模版系统，可重用和易于开发。
Facelets支持的标记库：
	JSF Facelets标记库：URI是http://xmlns.jcp.org/jsf/facelets，前缀是ui: 模版标记
	JSF HTML标记库：URI是http://xmlns.jcp.org/jsf/html，前缀是h: 所以UIComponent对象的JSF标记
	JSF 核心标记库：URI是http://xmlns.jcp.org/jsf/core，前缀是f: 独立于特定呈现包的JSF定制动作的标记
	直通元素标记库：URI是http://xmlns.jcp.org/jsf，前缀是jsf: 支持HTML5友好标记
	直通属性标记库：URI是http://xmlns.jcp.org/jsf/passthrough，前缀是p: 支持HTML5友好标记
	复合组件标记库：URI是http://xmlns.jcp.org/jsf/composite，前缀是cc: 支持复合组件的标记
	JSTL 核心标记库：URI是http://xmlns.jcp.org/jsp/jstl/core，前缀是c: JSTL 1.2核心标记
	JSTL 函数标记库：URI是http://xmlns.jcp.org/jsp/jstl/functions，前缀是fn: JSTL 1.2函数标记
**8.2 Facelets应用的生命周期**
Facelets应用的处理过程：
	1.客户端（如浏览器）对使用Facelets创建的页面发出新的请求时创建新的组件树或javax.faces.component.UIViewRoot于FacesContext中。
	2.UIViewRoot应用到Facelets，在视图中填充组件来完成呈现。
	3.新建立的视图作为响应回送给客户端。
	4.呈现视图时保存状态。
	5.客户端可以与视图交互，请求另一视图或完成修改。此时将由所存储的状态恢复之前保存的视图。
	6.所恢复的视图再一次经过JSF生命周期，生成新视图或无任何验证、事件重新呈现当前视图。
	7.如果请求同一视图，呈现之前保存的视图。
	8.如果请求新视图，继续第2步。
**8.3开发一个简单的Facelets应用**
见greeting.xhtml
遇到的问题：
	1.所有的Scoped注解应使用javax.enterprise.context.*下的Scoped注解，而不是javax.faces.bean.*下的Scoped注解。
	2.遇到bug先检查是否代码拼写有误。
**8.4 使用Facelets模版**
模板允许你创建一个页面作为应用中其他页面的基础或模板。
实现模板和相应功能的Facelets标记：
    ui:component定义一个组件，创建这个组件并增加到组件树。
    ui:composition定义一个页面组合（可以使用模板），这个标记以外的内容将被忽略。
    ui:debug定义一个调试组件，将创建这个组件并增加到组件树。
    ui:decorate与复合组件标记（ui:composition）类似，不过不会忽略标记以外的内容。
    ui:fragment与复合组件标记（ui:component）类似，不过不会忽略标记以外的内容。
    ui:include为多个页面封装和重用内容。
    ui:insert在模板中插入内容。
    ui:param用于向所包含的文件传递参数。
    ui:repeat作为c:foreach或h:dataTable等循环标记的替代标记。
    ui:remove从页面删除内容。
模板页面中ui:insert定义的地方可以被使用了模板的页面用ui:define替代内容。
The Facelets template must include the <html>, <head>, or <h:head>, and <body> or <h:body>, elements because they are 
what define the overall layout for each view that uses it.
It is important to note that a template client, or composition, contains an opening and closing <ui:composition> tag.
Everything outside of those tags is actually ignored at rendering time because the template body is used instead.
A view can specify any number of ui:define tags, and if they do not correspond to
any of the ui:insert tags within the template, then they are ignored. Likewise, a template can specify any number of
ui:insert tags, and if they do not correspond to a ui:define tag within the template client view, then the content that
is defined within the template in that location will be displayed.
**8.5 复合组件**
复合组件是一个特殊类型的模版，相当于一个组件。
常用复合组件标记及其功能：
	composite:interface声明一个复合组件的使用契约，这个复合组件可以作为单个组件，其特性集是使用契约中声明的所有特性的并集。
	composite:implementation声明复合组件的实现，如果出现一个composite:interface元素，就必须有一个相应的composite:implementation元素
	composite:attribute声明一个属性，可以指定为声明了这个标记的复合组件的一实例。
	composite:insertChildren使用页面中复合组件标记的子组件或模版文件将以这个组件为父组件，并插入到指定位置，这个位置由这个标记在composite:implementation中的位置指定。
	composite:valueHolder声明嵌入这个元素的复合组件（其契约由composite:interface声明）提供一个适当的ValueHolder实现，可以作为使用页面中关联对象的目标。
	composite:editableValueHolder声明嵌入这个元素的复合组件（其契约由composite:interface声明）提供一个适当的EditableValueHolder实现，可以作为使用页面中关联对象的目标。
	composite:actionSource声明嵌入这个元素的复合组件（其契约由composite:interface声明）提供一个适当的ActionSource2实现，可以作为使用页面中关联对象的目标。
**14 复合组件高级主题与示例**
**14.1 复合组件的属性**
当页面中有命名空间：xmlns:em="http://xmlns.jcp.org/jsf/composite/emcomp"，<em:LoginPanel>表示引用emcomp目录下的LoginPanel.xhtml复合组件。
{cc.attrs.[attributeName]} ：cc is an implicit object that indicates the current component
当前复合组件对象调用getAttributes()方法，返回一个map，map中查找一个key为attributeName的元素
遇到的问题：
    1.示例点击登录按钮出现刷新页面的bug，why？
    2.@ManagedProperty(value = "#{authorController}")
      private AuthorController authorController;
Note:Writing a component is an easy task so developers should be encouraged to write their own business components
so they can reuse them between pages. But some components are so common (a calendar, a color picker, a scroll panel,
a breadcrumb . . .) that you don’t have to develop them anymore; you just need to pick them up in third-party component
libraries. Today there are three major open source projects that gather hundreds of already made graphical components:
_PrimeFaces, RichFaces, and IceFaces_. Check their component libraries and you might get what you are looking for without
developing anything, just using their libraries.
**8.6 Web资源**
Web资源是适当呈现Web应用所需的所有软件工件（artifact），包括图像、脚本文件和用户创建的所有组件库。
	打包到Web应用根的资源必须放在Web应用根resources目录下的一个子目录中：resources/resource-identifier
	打包到Web应用类路径的资源必须放在Web应用META-INF/resources目录下的一个子目录中：META-INF/resources/resource-identifier
资源标识符：[local-prefix/][library-name/][library-version/]resource-name[/resource-version]
指定样式表、图片或脚本最常见的两种方法：
	<h:graphicImage value="#{resource['images:wave.med.gif']}"/> 资源位置：Web/resource/images/wave.med.gif
	<h:outputStylesheet library="css" name="default.css"/> 资源位置：Web/resource/css/default.css
**8.7 可重定位资源**
可以把一个资源标记放在页面中某一部分，而指定它在这个页面的另一部分呈现。指定target属性即可，可指定的值为：
	"head"在head元素中呈现资源
	"body"在body元素中呈现资源
	"form"在form元素中呈现资源
	例：<h:outputStylesheet library="css" name="default.css" target="head"/>
多用于复合组件
**8.8 资源库契约**
资料不足，看不懂
**8.9 HTML5友好的标记**
JSF允许直接使用HTML5标记，允许HTML5元素中使用JSF属性。两类支持特性：
	直通元素：允许使用HTML5标记和属性等价于UIComponent实例关联的JSF组件。
	直通属性：允许向浏览器传递非JSF属性而无须解释。
**8.9.1 使用直通元素**
转非JSF元素为直通元素：
	增加命名空间 xmlns:jsf="http://xmlns.jcp.org/jsf"
	添加一个JSF属性jsf:id <input type="email" jsf:id="emailAgain"/>
HTML5元素与Facelets标记的对应，见JavaEE7权威指南卷1 P100 表8-4。
**8.9.3 使用直通属性**
指定直通属性：
	方法1.将JSF组件中的属性加直通属性前缀 xmlns:p="http://xmlns.jcp.org/jsf/passthrough" 例：<h:inputText id="price" p:type="number"></h:inputText>
	方法2.JSF组件中嵌套f:passThroughAttribute 
	例： <h:inputText value="#{user.email}">
			<f:passThroughAttribute name="type" value="email">
		</h:inputText>
		等同于：<input type="email" value="useremail@qq.com" />
	方法3.JSF组件中嵌套f:passThroughAttributes属性，传递一组属性
	例： <h:inputText value="#{bean.nights}">
				<f:passThroughAttributes value="bean.nameValuePairs">
		</h:inputText>
		bean.nameValuePairs()方法返回一个装有属性的map