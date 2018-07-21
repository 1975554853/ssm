package com.beiyou.view;

import com.alibaba.fastjson.JSONObject;
import com.beiyou.business.MYSQL.StuBus;
import com.beiyou.page.Page;
import com.beiyou.pojo.Student;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/info")
public class info extends HttpServlet {


    private StuBus stuBus;

    @Override
    public void init() throws ServletException {

        ServletContext context = this.getServletContext();
        WebApplicationContext webApplicationContext =
                WebApplicationContextUtils.getWebApplicationContext(context);
        stuBus = webApplicationContext.getBean(StuBus.class);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("XDomainRequestAllowed", "1");
        //回应的编码格式
        response.setContentType("application/json;charset=utf8");
        //设置跨域问题
//		response.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();
        //设置请求的编码格式
        request.setCharacterEncoding("utf-8");

        Integer type = Integer.valueOf(request.getParameter("type"));

        System.out.println(type);
        switch (type) {
            case 1:
                Page page = stuBus.selectStudent();
                System.out.println("fdsg asdg s");
                out.print(JSONObject.toJSONString(page));
                break;
            case 2:
                Student student = stuBus.sselectsingle(5);
                out.print(student);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}


