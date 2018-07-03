**7.1 什么是JSF应用**    
典型的JSF应用包括：
    一组将放置组件的web页面。
    一组用来向web页面增加组件的标记。
    一组托管bean，这些bean是轻量级的容器托管对象。
    一个web部署描述文件（Web.xml）。
    一个或多个应用资源配置文件（如faces-config.xml）。
    一组定制对象（如定制组件、验证器、转换器、监听器）。
    一组表示定制对象的定制标记。
**7.2 JSF技术优势**
好处：
    可以通过模板和复合组件特性重用和拓展组件代码。
    可以使用注解将托管bean自动注册为JSF应用可用的资源。隐式导航规则减少应用配置。
    提供丰富架构，用以管理组件状态、处理组件数据、验证用户输入和处理事件。
**7.3 一个简单的JSF应用**
简单的JSF应用通常需要完成以下任务：
    使用组件标记创建web页面
    开发托管bean
    映射FacesServlet实例
**7.4 用户界面组件模型**
JSF组件架构：
    一组javax.faces.component.UIComponent类
    一个呈现模型
    一个转换模型
    一个事件和监听模型
    一个验证模型
**7.4.1 用户组件类**
所有组件的抽象基类是javax.faces.component.UIComponent，JSF UI组件都要扩展UIComponentBase类（UIComponent的一个子类）
组件类：
    UIColumn：表示UIData组件的一列数据。
    UICommand：表示一个激活时触发动作的控件。
    UIData：表示与javax.faces.model.DataModel实例表示的数据集合的一个数据绑定。
    UIForm：表示提供给用户的一个输入表单。
    UIGraphic：显示一个图像。
    UIInput：从用户获取数据输入，UIOutput的一个子类。
    UIMessage：显示一个本地化错误消息。
    UIMessages：显示一组本地化错误消息。
    UIOutcomeTarget：以链接或者按钮形式显示一个链接。
    UIOutput：在页面上显示数据输出。
    UIPanel：管理子组件的布局。
    UIParameter：表示替代参数。
    UISelectBoolean：允许用户通过选择或取消选择来设置控件的boolean值，UIInput的一个子类。
    UISelectItem：表示一组项中的一项。
    UISelectItems：表示整组项。
    UISelectMany：允许用户从一组项中选择多项，UIInput的一个子类。
    UISelectOne：允许用户从一组项中选择一项，UIInput的一个子类。
    UIViewParameter：表示请求中的查询参数，UIInput的一个子类。
    UIViewRoot：表示组件树的根。
除了扩展UIComponentBase类，组件类还会实现一个或多个行为接口，这些接口一般都在javax.faces.component包中定义：
    ActionSource：指示组件可以触发一个动作事件。
    ActionSource2：扩展了ActionSource，允许组件在引用处理动作事件的方法时使用EL。
    EditableValueHolder：扩展了ValueHolder，增加了完成验证以及产生值改变事件的特性。
    NamingContainer：强制要求以这个组件为根的组件有唯一的ID。
    StateHolder：指示组件必须在请求之间保存状态。
    ValueHolder：指示组件维护一个本地值，也可以访问模型层中的数据。
    javax.faces.event.SystemEvenListenerHolder:为这个类定义的各个javax.faces.event.SystemEven类型维护一个javax.faces.event.SystemEvenListener实例列表
    javax.faces.component.behavior.ClientBehaviorHolder：增加关联javax.faces.component.behavior.ClientBehavior实例的功能，如可重用的脚本。
**7.4.2 组件呈现模型**
JSF组件架构的设计目标：组件的功能由组件类定义，而组件的呈现由一个单独的呈现器类定义。
呈现包（render kit）定义了组件类如何映射到适合特点客户端的组件标记。
如：h:commandButton和h:commandLink标记有同一个指定功能的UICommand类，而其中Button和Link对应各自单独的Renderer类
**7.4.3 转换模型**
JSF应用可以将组件与服务器端对象数据关联，组件绑定到一个对象时，应用会有该组件数据的两个视图：
    模型视图：在这个视图中，数据表示为数据类型，如int或long。
    表现视图：在这个视图中，数据采用用户可以读取或修改的方式来表示。如java.util.Date可以表示为mm/dd/yy格式文本串。
    如果在一个组件上注册javax.faces.convert.Converter实现，这个Converter实现会在两个视图之间对组件进行转换。
**7.4.4 事件和监听器模型**
JSF规范定义了3类事件：应用事件、系统事件和数据模型事件。
应用事件与特定组件绑定，由一个UIComponent生成，即JSF标准事件。
JSF支持两类应用事件：动作事件（javax.faces.event.ActionEvent）和值改变事件(javax.faces.event.ValueChangeEvent)。
让应用对标准组件产生的动作事件或值改变事件作出反应的两种方法：
    实现事件监听器类来处理事件，并在组件上通过嵌套标记注册监听器。
    实现组件bean的一个方法来处理事件，并用一个EL从组件的适当属性应用这个方法。
系统事件由Object而不是UIComponent生成。这种事件会在应用执行期间的预定时间生成，面向整个应用。
**7.4.5 验证模型**
JSF技术支持一种验证机制，可以验证可编辑组件的本地数据。这个验证发生在更新模型数据之前。
验证模型提供了两种方法来实现定制验证：
    实现一个javax.faces.validator.Validator接口完成验证。必须做到：
        为应用注册Validator实现。
        创建一个定制标记或者使用一个f:validator标记在组件上注册验证器。
    实现一个bean方法完成验证。
**7.5导航模型**
JSF技术中，导航是一组规则，用于选择一个应用动作之后要显示的下一个动作或视图。
导航的分类：
    隐式导航：如果某组件Action属性中的值为字符串response，则 默认导航处理器会尝试在应用中查找一个名为response.xhmtl的页面，并导航到这个页面。
    用户自定义的导航：利用应用资源配置文件声明自定义的导航规则，如faces-config.xml
      <navigation-rule>
        <description></description><!--描述-->
        <from-view-id></from-view-id><!--导航组件所在页面的视图ID，在JSF中的View ID是从Web应用程序的环境相对路径开始计算，设定时都是以/作为开头-->
        <navigation-case>
        <from-action></from-action><!--规定产生导航字符串的方法-->
          <from-outcome></from-outcome><!--用于导航的字符串-->
          <if></if><!---->
          <to-view-id>/result.xhtml</to-view-id><!--需要导航到的页面的视图ID-->
        </navigation-case>
      </navigation-rule>
**7.6 JSF应用的生命周期**
应用的生命周期指应用从启动到结束的不同处理阶段。
**7.6.1 JSF生命周期概述**
客户端向页面发出一个HTTP请求时，JSF应用的生命周期开始；服务器做出响应并返回页面（转化为HTML）时，生命周期结束。
生命周期可以划分为两个主要阶段：执行（execute）和呈现（render）。
JSF请求-响应生命周期处理两类请求：
	初始请求：用户首次对页面做出的请求。
	回送请求：用户提交页面上包含的表单的请求。
JSF应用生命周期执行（execute）阶段包含一下子阶段：
	恢复视图阶段
	应用请求值阶段
	处理验证阶段
	更新模型值阶段
	调用应用阶段
	呈现响应阶段
**7.6.2 恢复视图阶段**
恢复并创建一个服务器端组件树表示来自客户端的UI信息。
在这个阶段中，JSF实现会建立页面的视图，将事件处理器和验证器与视图中的组件绑定，并把视图绑定在FacesContext实例中，其中包含处理一个请求所需的全部信息。
	如果页面请求是一个初始请求：JSF实现会在这个阶段创建一个空视图，生命周期进入呈现响应阶段。
	如果页面请求是一个回送请求：FacesContext实例中已经有这个页面的一个视图，JSF实现会使用客户端或服务器上保存的状态信息恢复这个视图。
**7.6.3 应用（接受）请求值阶段**
对于回送请求，恢复组件树之后，树中的各个组件将使用decode（UIComponent.processDecodes）方法从请求参数中抽取新值，然后在各个组件本地存储这个值。
	如果某个decode方法或者事件监听器调用了当前FacesContext实例的renderResponse方法，JSF实现就会跳转至呈现响应阶段。
	如果页面上某些组件的immediate属性为true，与这个组件关联的验证、转换和事件就会在这个阶段立即处理。
	如果此时应用不再生成包含JSF组件的响应，可以调用FacesContext实例的responseComplete方法。
	如果当前请求标识为一个部分请求，会从FacesContext获取部分上下文，并应用部分处理方法。
	如果有事件排队：JSF实现广播之。
	最后，组件设置新值，消息事件入队。
**7.6.4 处理验证阶段**
到此为止，UIComponent已经有了设置的值。
在这个阶段，JSF实现使用validate(UIComponent.processValidators)方法处理所有验证器，完成验证规则与存储的本地值的比较。对于未设置immediate为true的输入组件，转换器转换之。如果：
	本地值非法或转换失败：FacesContext实例增加错误消息，生命周期直接进入呈现响应阶段，显示错误消息。
	某个validate方法或者事件监听器调用了当前FacesContext实例的renderResponse方法：JSF实现就会跳转至呈现响应阶段。
	此时应用不再生成包含JSF组件的响应：可以调用FacesContext实例的responseComplete方法。
	有事件排队：JSF实现广播之。
	当前请求标识为一个部分请求：从FacesContext获取部分上下文，并应用部分处理方法。
**7.6.5 更新模型值阶段**
UIComponent的值会与模型对象同步。
JSF实现更新服务器端对象的属性为合法本地值，如果：
	某个updateModels(UIComponent.processUpdates)方法或者事件监听器调用了当前FacesContext实例的renderResponse方法：JSF实现就会跳转至呈现响应阶段。
	此时应用不再生成包含JSF组件的响应：可以调用FacesContext实例的responseComplete方法。
	有事件排队：JSF实现广播之。
	当前请求标识为一个部分请求：从FacesContext获取部分上下文，并应用部分处理方法。
**7.6.5 调用应用阶段**
调用应用的逻辑并执行导航处理。所以已注册的UIComponent的监听器被调用。
此阶段，处理应用级事件（如提交表单或链接另一页面），如果：
	此时应用不再生成包含JSF组件的响应：可以调用FacesContext实例的responseComplete方法。
	处理的视图为重构所得，且有事件触发，广播之。
最后，呈现响应阶段。
**7.6.7 呈现响应阶段**
此阶段，JSF建立视图并委托到适当资源，完成页面呈现，如果：
	初始请求：页面上组件将增加到组件树。
	回送请求：在请求值、处理验证、更新模型值阶段出错，此阶段呈现原来页面，若有h:message或h:messages，显示错误信息。
呈现视图后，保存响应状态（UIViewRoot.saveState），以便后续请求访问，用于恢复视图阶段。
**7.7 部分请求和部分呈现**
JSF Ajax框架可以生成“部分请求”，由javax.faces.context.partialViewContext处理。