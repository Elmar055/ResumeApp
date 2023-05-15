package com.company.controller;

import com.company.entity.User;
import com.company.form.UserForm;
import com.company.service.DummyService;
import com.company.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserServiceInter userService;

//    @RequestMapping(method = RequestMethod.GET)
//    public String index(HttpServletRequest request){
//        String name = request.getParameter("name");
//        String surname = request.getParameter("surname");
//
//        String nationalityIdStr = request.getParameter("nid");
//        Integer nationalityId=null;
//        if(nationalityIdStr!=null && !nationalityIdStr.trim().isEmpty()) {
//            nationalityId = Integer.parseInt(nationalityIdStr);
//        }
//
//
//        List<UserDTO> list = userService.getAll(name, surname, nationalityId);
//
//        request.setAttribute("list", list);
//        return "users";
//    }Data Transfer Object
    @RequestMapping(method = RequestMethod.GET, value="/users")//users?name=Sarkhan
    public ModelAndView index(
            @RequestParam(value="name", required = false) String name,
            @RequestParam(value="surname", required = false) String surname,
            @RequestParam(value="nid", required = false) Integer nationalityId){
        List<User> list = userService.getAll(name, surname, nationalityId);
        ModelAndView mv = new ModelAndView("users");

        mv.addObject("users",list);
        return mv;
    }


//    Bu bir Spring MVC Controller sınıfındaki bir metot. Spring MVC, Model-View-Controller tasarım desenine dayalı bir web uygulama çerçevesidir.
//
//    Bu örnek metot, bir HTTP GET isteği aldığında çalışacak olan "/usersm" yolunu işler. Bu metot, ModelAndView objesi döndürür ve "users" adlı bir view sayfasına gönderir.
//
//    @Valid ve @ModelAttribute notasyonları, Spring MVC tarafından sağlanan veri bağlama mekanizmasını kullanarak, gelen HTTP isteği parametrelerini bir UserForm nesnesine bağlar. Bu notasyonlar, gelen verilerin doğruluğunu kontrol eder ve herhangi bir hata varsa BindingResult nesnesine hataları kaydeder.
//
//    Metot, userService nesnesi kullanarak tüm kullanıcıları alır ve "users" adlı view sayfasına ekler. "users" sayfası, bu kullanıcıları listeleyen bir HTML tablosu gibi bir şey olabilir.
//
//    Eğer doğrulama hatası varsa, metot doğrudan aynı sayfaya geri döner ve hata mesajları gösterilir. Aksi takdirde, "users" sayfasına yönlendirilir ve kullanıcılar listesi görüntülenir.
    @RequestMapping(method = RequestMethod.GET, value="/usersm")//users?name=Sarkhan
        public ModelAndView indexM(
                @Valid
                @ModelAttribute("user") UserForm u,
                BindingResult bindingResult){
        ModelAndView mv = new ModelAndView("users");
        if (bindingResult.hasErrors()) {
            return mv;
        }

        List<User> list = userService.getAll(u.getName(), u.getSurname(), u.getNationalityId());
        mv.addObject("users", list);
        return mv;
    }


//    @RequestMapping(method = RequestMethod.GET, value="/alma")//users?name=Sarkhan
//    public String login(){
//        return "login";
//    }

    @RequestMapping(method = {RequestMethod.GET}, value="/login")//users?name=Sarkhan
    public String loginPost(){
        return "login";
    }

    @RequestMapping(method = {RequestMethod.GET}, value="/logout")//users?name=Sarkhan
    public String logoutPage(){
        return "logout";
    }

    @Autowired
    DummyService dummyService;

    @RequestMapping(method = {RequestMethod.GET}, value="/foo")//users?name=Sarkhan
    public String foo(){
        System.out.println("foo in Controller");
        dummyService.foo();
        return "users";
    }

    @ModelAttribute("user")
    public UserForm getEmptyUserForm(){
       return new UserForm(null, null,null);
    }
}
