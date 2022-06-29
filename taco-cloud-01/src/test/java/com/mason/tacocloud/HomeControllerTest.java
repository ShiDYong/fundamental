//package com.mason.tacocloud;
//import com.mason.tacocloud.controller.HomeController;
////import org.junit.jupiter.api.Test;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import static org.hamcrest.core.StringContains.containsString;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(HomeController.class)
//
//
//public class HomeControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    @Test
//    public void testHomePage() throws Exception {
//        mockMvc.perform(get("/")) //发起对“/”的GET
//                 .andExpect(status().isOk()) //⇽--- 期望得到HTTP 200
//            .andExpect(view().name("home")) //⇽--- 期望得到home视图
//        .andExpect(content().string(containsString("Welcome to..."))); //⇽--- 期望包含“Welcome to... ”
//    } }
//
