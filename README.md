# Spring MVC
 Front-Controller, Model, View 

*Interceptors*

Interceptors provide Http requests pre-prosessing and post-processing.  

![image](https://user-images.githubusercontent.com/17804600/89495725-294c2880-d7b9-11ea-925f-b9170e25165a.png)


An interceptor offers three customers entry points

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

An interceptor can be applied on specific uri patterns

```
registry.addInterceptor(loginInterceptor()).addPathPatterns();
```

*Save and get model via session*

A request operation may need to switch from one controller to another, meanwhile it need to carry states to the next.

`@SessionAttributes("xxx")` is applied on the class level(Controller), informing Spring to save the model in the Http Servlet session.  

xxx: a model attribute with the name xxx 

````
@Controller
@SessionAttributes("login")//in class level saving the login data model into the session
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    //@ModelAndAttribute Login login bean has been declared in the ControllerAdvice class, DefaultModelAttributeController.

    @PostMapping("/login")
    public String login(@ModelAttribute("login") Login login, Model model, HttpSession session) {

        User user = userRepository.findByUserName(login.getUserName());

        if (user == null) throw new ApplicationException("User is not found.");

        session.setMaxInactiveInterval(600);

        return "forward:/userProfile";
    }

````
`@SessionAttribute("xxx")` is applied in another controller's method argument, so as to access the model stored in the session.

````
@Controller
public class UserProfileController {

    @PostMapping("/userProfile")
    public String getUserProfile(@SessionAttribute("login")Login login, Model model){
        System.out.println("in user profile controller.");
        System.out.println("userName from login session object: "+ login.getUserName() );

        model.addAttribute("userName",login.getUserName());

        return "profile";
    }

````

The model is remained in the session until another controller method uses a `@SessionStatus` method argument to clear the storage. 

````
    @PostMapping("/pets/{id}")
    public String handle(Pet pet, BindingResult errors, SessionStatus status) {
        if (errors.hasErrors) {
            // ...
        }
            status.setComplete(); 
            // ...
        }
    }
}
````

*logout*

`session.invalidate()`

*forwarding* 

Switching from one resource to another in the same context. The same context means the same client http request.  

`return "forward:/userProfile";`

Forwarding brings the same request from from one request handler to another request handler. A request will be handled by two method handlers seqentially. 

From method handler(post method) to method handler(post method) is ok; if you do post to get then Spriing throw Request method not support exception. 

I think it should be like forwarding from a post to post; and from a get to a get. 

*redirecting*

Switching outside of the current application, and link to another website.

`return "redirect:http://www.xyz.com";`

Redirecting is different from forwarding; the original resquest doesn't return the response to the client, but its response will be redirected to another resource
and get its reposnse. A new request is created, and redirect is normally used within Post/Redirect/Get 

*themes*

Static resources like css and images; used to present the view in many kind of looking and feel. 

* define separate static resources for different themes
* define respective theme property files, pointing to the static resources
* Use one of ThemeResolver to decide which theme to use: 
  * CookieThemeResolver
  * SessionThemeResolver
  * FixedThemeResolver
  
  Declaring a bean of theme resolver, and add ThemeChangeInterceptor, which monitoring the theme changes. 
  
  ````
      @Bean
    protected ThemeResolver themeResolver(){
        CookieThemeResolver cookieThemeResolver = new CookieThemeResolver();
        cookieThemeResolver.setCookieName("theme");
        cookieThemeResolver.setDefaultThemeName("client-theme1");
        return cookieThemeResolver;
    }
 
  ````
 
 * cofigure ThemeChangeInterceptor to tap a change in theme value for every request
 * User <spring:theme/> to refer themed keys from .properties file on the jsp. 
 
 On the index page
 
 `<link rel="stylesheet" href="<spring:theme code='styleSheet'/>" type="text/css"/>`
 
 The code refer to key value defined in the theme property file. 
 
 
 
 *@SessionAttributes*
 
 When a request swiths from one to another, it may need to bring a Model to the next one for a futher processing.  `@SessionAttributes` is applied on the `@Controller`class level, to inform Spring to store a `@ModelAttribute` in the Session.
 
 *@SessionAttribute*
 
 It is used at the another request handler's agrument, in order to access the Model Attribute stored the session. 
 
 *@SessionStatus*
 
 `@SessionStatus` is applied in a request handler method's argument. A ModelAttribute is stored until `@SessionStatus` is set to complete, 
 

