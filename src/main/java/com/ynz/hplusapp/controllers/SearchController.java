package com.ynz.hplusapp.controllers;

import com.ynz.hplusapp.beans.Product;
import com.ynz.hplusapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AsyncTaskExecutor asyncTaskExecutor;

//    @GetMapping("/search")
//    public Callable<String> getSearch(@RequestParam("search") String search, Model model, HttpServletRequest request) {
//        System.out.println("in search controller");
//        System.out.println("search criteria : " + search);
//        System.out.println("async supported? " + request.isAsyncSupported());
//        System.out.println("Thread from the servlet container: " + Thread.currentThread().getName());
//
//        return () -> {
//            //simulating a delay due to a blocking task.
//            Thread.sleep(3000);
//            System.out.println("Thread from Spring mvc task executor: " + Thread.currentThread().getName());
//            List<Product> products = productRepository.findByName(search);
//            model.addAttribute("products", products);
//            return "search";
//        };

    @GetMapping("/search")
    public DeferredResult<String> getSearch(@RequestParam("search") String search, Model model, HttpServletRequest request) {
        DeferredResult<String> deferredResult = new DeferredResult<>();

        System.out.println("in search controller");
        System.out.println("search criteria : " + search);
        System.out.println("async supported? " + request.isAsyncSupported());
        System.out.println("Thread from the servlet container: " + Thread.currentThread().getName());

//        return () -> {
//            //simulating a delay due to a blocking task.
//            Thread.sleep(3000);
//            System.out.println("Thread from Spring mvc task executor: " + Thread.currentThread().getName());
//            List<Product> products = productRepository.findByName(search);
//            model.addAttribute("products", products);
//            return "search";
//        };

        asyncTaskExecutor.execute(() -> {

            //simulating a delay due to a blocking task.
            try {
                Thread.sleep(9000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread from Spring mvc task executor: " + Thread.currentThread().getName());
            List<Product> products = productRepository.findByName(search);
            model.addAttribute("products", products);

            deferredResult.setResult("search");
        });

        return deferredResult;
    }

}
