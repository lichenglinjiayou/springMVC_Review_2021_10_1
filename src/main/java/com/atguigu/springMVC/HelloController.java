package com.atguigu.springMVC;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author shkstart
 * @create 2021-10-01 6:07
 */
@Controller
//@RequestMapping(value = "/lcl")
public class HelloController {

    @RequestMapping(value = "/**/hello")
    public String test1(){
        return "hello";
    }
    /*浏览器发送请求，若该请求符合dispatcherServlet前端控制器url-pattern中的设置，则将该请求交给前端控制器进行处理
        前端控制器会读取springMVC的核心配置文件，通过组件扫描找到控制器，将请求地址与控制器中@requestMapping注解的value属性
        值进行匹配，若匹配成功，则注解所标识的控制器方法则为处理该请求的方法。控制器方法会返回一个字符串类型的视图名称，
        该视图名称会被视图解析器进行解析，添加上前缀和和后缀构成视图路径，通过thymeleaf对视图进行渲染，最终转发到视图对应的页面。
    */

    /*
    @RequestMapping:将浏览器发送的请求与处理请求的控制器方法关联起来，建立映射关系。SpringMVC接受到请求后，通过找到映射关系中对应的
    控制器方法来处理这个请求。
    @RequestMapping注解的位置：1.注解在类上：设置映射请求的请求路径的初始信息；
                              2.注解在控制器方法上：设置映射请求的请求路径的具体信息；

    @RequestMapping的value属性值：1.value属性用来设置请求地址所匹配的请求映射；
                                 2.value属性的值为一个字符串类型的数组，表示请求映射可以匹配多个请求地址的请求；
                                 3.value属性值必须设置，保证至少通过请求地址匹配到请求映射；

    @RequestMapping的method属性值：1.method属性用来设置请求的请求方式匹配的请求映射；
                                  2.method属性值为一个RequestMethod类型的数组，表示请求映射可以匹配多种请求方式的请求；
                                  3.若请求的请求地址符合请求映射的value属性值，但不符合请求映射的method属性值，浏览器会报405错：请求方式不支持。
     对于处理特定请求方式的控制器方法，springMVC提供了@RequestMapping的派生注解：@GetMapping、 @PostMapping、@PutMapping、@DeleteMapping

     @RequestMapping的Params属性值：1.params属性通过请求的请求参数匹配请求映射；
                                   2.params属性的值为一个字符串类型的数组，通过四种表达式判断请求参数与映射的匹配关系。param、！param、==、！=
                                   3.若请求的请求地址符合请求映射的value属性值，请求方式符合请求映射的method属性值，但请求参数不匹配，浏览器会报400错：参数条件不满足。

     @RequestMapping的headers属性值：1.headers属性通过请求的请求头信息匹配请求映射；
                                   2.headers属性的值为一个字符串类型的数组，通过四种表达式判断请求头与映射的匹配关系。header、！header、==、！=
                                   3.若请求的请求地址符合请求映射的value属性值，请求方式符合请求映射的method属性值，但请求头信息不匹配，浏览器会报404错：资源未找到。

    SpringMVC支持ant风格的路径：？-任意单个字符,*-任意0或多个字符,**-任意一层或多层目录

    SpringMVC支持路径中的占位符：当请求路径中将某些数据通过路径的方式发送到服务器时，可以通过占位符表示传输的数据，在形参中添加@PathVariable注解，将占位符所表示的数据的值传给控制器方法的形参。
     */

    @RequestMapping(value = "/zwf/{username}/{password}")
    public String zwf(@PathVariable String username,@PathVariable String password){
        System.out.println(username+"::::"+password);
        return "success";
    }


    /*
    SpringMVC获取请求参数：1.原生ServletAPI:将HttpServletRequest作为控制器方法的形参，此时该对象就表示封装了请求的请求报文的对象。
                          2.在控制器方法中设置与请求参数同名的形参，则当浏览器发送的请求匹配到请求映射后，前端控制器会自动将请求参数的值赋值给同名的形参
                            注意：若请求参数中存在多个同名的参数，如果形参使用字符串数组的类型，则数组中包含每一个数据，如果形参使用字符串类型，则参数的值为数据中间使用逗号拼接。
                          3.@RequestParam:创建请求参数与形参之间的映射关系（用于形参与请求参数不同名时），存在三个属性：value:请求参数的别名；required:是否必须传输此请求参数，默认值为true
                            若请求中没有传输相应的请求参数，且没有设置defaultValue属性，则会报错400；若设置为false,在没有传输的情况下，则注解表示的形参值为null.
                            defaultValue:当指定的请求参数没有传输或传输的值为""时，会使用默认值为形参赋值。
                          4.@RequestHeader:建立请求头信息与控制器方法形参之间的映射，有三个参数value、required、defaultValue，用法与@RequestParam相同。
                          5.@CookieValue:建立cookie数据与控制器方法形参的映射关系
                          6.通过pojo获取请求参数：可以将控制器方法的形参设置为实体bean类型的形参，若请求参数的参数名与实体类中的属性名一致时，请求参数就会自动为形参赋值。
     */

    /*
    解决请求参数乱码的问题：1.GET请求：get请求的乱码问题是由tomcat服务器造成的，需要在server.xml文件中配置URIEncoding="UTF-8"/useBodyEncodingForURI="true"
                          2.POST请求：通过配置编码过滤器解决乱码问题。编码过滤器必须配置在web.xml文件的最开始，保证在此之前为获取过请求参数。(看源码)
     */

    /*
    域对象中共享数据：1.使用原生servletAPI向request域中共享数据
                    区分：钝化：服务器关闭，但浏览器未关闭，session域中的数据会经过序列化后保存到磁盘上；
                          活化：钝化后的文件重新读取到session域中
                    2.使用ModelAndView向request域中共享数据:model用来向请求域中共享数据；view用来设置视图，实现页面跳转
                    3.使用Model向request域中共享数据
                    4.使用Map向request域中共享数据
                    5.使用ModelMap向request域中共享数据
                    注意：由于BindingAwareModelMap继承了ExtendedModelMap，ExtendedModelMap继承了ModelMap，实现了Model，ModelMap继承了Map的实现类LinkedHashMap，
                    因此Model,Map,ModelMap三者本质上都是BindingAwareModelMap类型。
                    6.向session域中共享数据/application域中共享数据均使用servlet原生API。但是取出域中数据时，必须添加session、application前缀。
     */

    @RequestMapping(value = "/servlet")
    public String getParam(HttpServletRequest request){
        HttpSession session = request.getSession();
        return "success";
    }

    @RequestMapping(value = "/requestparam")
    public String getParam(@RequestParam(value = "user_name",required = true)String username, String password, @RequestHeader(value = "User-Agent") String user_agent, @CookieValue(value = "JSESSIONID")String jsession){
        System.out.println(username+password+user_agent+jsession);
        return "success";
    }

    @RequestMapping(value = "/testModelAndView")
    public ModelAndView testModelAndView(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("username","lichenglin");
        mav.setViewName("success");
        return mav;
    }

    @RequestMapping(value = "/testModel")
    public String testModel(Model model){
        model.addAttribute("username","lichenglin");
        return "success";
    }

    @RequestMapping(value = "/testMap")
    public String testModel(Map<String,Object> map){
        map.put("username","lichenglin");
        return "success";
    }

    @RequestMapping(value = "/testModelMap")
    public String testModel(ModelMap modelMap){
        modelMap.addAttribute("username","lichenglin");
        return "success";
    }
}
