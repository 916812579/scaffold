package org.example.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.example.controller.BaseController;
import org.example.model.Test;
import org.example.service.TestService;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author AutoGenerator
 * @since 2020-03-29
 */
@RestController
@RequestMapping("/test")
public class TestController extends BaseController<Test, TestService> {

}

