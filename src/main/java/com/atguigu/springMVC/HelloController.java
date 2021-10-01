package com.atguigu.springMVC;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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
                          4.
     */

    @RequestMapping(value = "/servlet")
    public String getParam(String username,String password){
        System.out.println(username+"::"+password);
        return "success";
    }
}
