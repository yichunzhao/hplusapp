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

*Save and get model via session

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

