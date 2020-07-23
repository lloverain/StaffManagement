package modular.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 所有类的异常处理
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    public ModelAndView error(Exception exception) {
        ModelAndView mv=new ModelAndView();
        mv.addObject("error", exception.getMessage());
        mv.setViewName("error");
        return mv;
    }
}