# Spring MVC
 Front-Controller, Model, View 

*Interceptors*

Interceptors provide Http requests pre-prosessing and post-processing.  

![image](https://user-images.githubusercontent.com/17804600/89495725-294c2880-d7b9-11ea-925f-b9170e25165a.png)


A interceptor offers three customer entry points

* preHandle(): perfoming operations before the request arriving at the controller. 
* postHanle(): performing operations before sending the reponse to the servlet dispatcher. 
* afterCompletion(): performing operations after completing the request and response. 

A customer interceptor implements `HandlerInterceptorAdapter`

```
public class LoggerInterceptor extends HandlerInterceptorAdapter {

}
```

Informing Spring the coustomer interceptor

```
@Configuration
public class ApplicationConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor());
    }

```

A interceptor can be applied on specific uri patterns

```
registry.addInterceptor(loginInterceptor()).addPathPatterns();
```

