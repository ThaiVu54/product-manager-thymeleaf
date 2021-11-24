package com.thaivu.controller;

import com.thaivu.model.Product;
import com.thaivu.service.IProductService;
import com.thaivu.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final IProductService productService = new ProductService();

    @GetMapping("")
    public String view(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("products", new Product());
        return "/create";
    }

    @PostMapping("/save")
    public String save(Product product) {
        product.setId((int) (Math.random() * 111));
        productService.save(product);
        return "redirect:/product";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("products", productService.findById(id));
        return "/edit";
    }

    @PostMapping("/update")
    public String update(Product product) {
        productService.update(product.getId(), product);
        return "redirect:/product";
    }

    @GetMapping("{id}/delete")
    public String delete(@PathVariable int id, Model model){
        model.addAttribute("products",productService.findById(id));
        return "/delete";
//        Tham số @PathVariable int id lấy id của customer từ đường dẫn rồi gán vào biến id.
//        hàm customerService.findById(id) sẽ lấy customer theo id rồi truyền sang view edit.html
    }
    @PostMapping("/delete")
    public String delete(Product product, RedirectAttributes attributes){
        productService.remote(product.getId());
        attributes.addFlashAttribute("success","Removed customer successfully!");
        return "redirect:/product";
        //Flash message về trang danh sách khách hàng để thông báo lưu thành công, bằng cách sử dụng redirect.addFlashAttribute(messageName, messageContent).
    }

    @GetMapping("/{id}/view")
    public String view (@PathVariable int id, Model model){
        model.addAttribute("products", productService.findById(id));
        return "/view";
    }
}
