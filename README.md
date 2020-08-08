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


*redirecting*

Switching outside of the current application, and link to another website.

`return "redirect:http://www.xyz.com";`

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
 

