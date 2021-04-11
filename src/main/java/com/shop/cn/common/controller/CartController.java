package com.shop.cn.common.controller;

import com.shop.cn.common.pojo.Address;
import com.shop.cn.common.pojo.Cart;
import com.shop.cn.common.pojo.Product;
import com.shop.cn.common.service.CartService;
import com.shop.cn.common.service.ProductService;
import com.shop.cn.common.vo.GenericResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@Api(value = "购物车模块", tags = {"对购物车进行操作"})
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @GetMapping("/cart")
    @ApiOperation(value = "购物车页面")
    public String cart(){
        return "cart";
    }

    @ApiOperation(value = "跳转增加收货地址")
    @GetMapping("addAddress")
    public String addAddress(){
        return "addressAdd";
    }

    @ApiOperation(value = "跳转到地址页")
    @GetMapping("address")
    public String address(){
        try {
            return "address";
        } catch (Exception e) {
            e.printStackTrace();
            return "系统错误";
        }
    }

    @ApiOperation(value = "将商品加入到购物车")
    @PostMapping("/addProductToCart")
    @ApiImplicitParam(name="id",value = "商品id",required = true)
    @ResponseBody
    public GenericResult<String> addProductToCart(@RequestBody Cart cart, HttpSession session) {
        GenericResult<String> result = new GenericResult<>();
        try {
            System.out.println("cart:"+cart);
            Long id = (Long) session.getAttribute("userId");
            cart.setUserId(id);
            if(id==0 || id==null){
                result.setSuccess(false);
                result.setMessage("用户没用登录！！");
                Subject subject = SecurityUtils.getSubject();
                subject.logout();
            }
            System.out.println("cartsProductName:"+cart.getProductName());
            cartService.addProductToCart(cart);
            result.setMessage("加入购物车成功！！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  result;
    }


    @ApiOperation(value = "查询购物车中所有的商品并跳转页面(包括接收默认地址信息)")
    @GetMapping("/findProductFromCart")
    public String findProductFromCart(HttpSession session,Model model,@RequestParam(value = "addressId", required = false) String addressId) {
        try {
            String userName = (String) session.getAttribute("userName");
            Long id = (Long) session.getAttribute("userId");
            if(id==0 || id==null){
                Subject subject = SecurityUtils.getSubject();
                subject.logout();
            }
            List<Cart> list =  cartService.findProductFromCart(id);
            List<Cart> carts = new ArrayList<>();
            double countAll=0;
            for (Cart cart: list) {
                //商品主键
                Long productId = cart.getProductId();
                Product product = productService.findProductDetailWithKey(productId);
                Double salePrice = product.getSalePrice();
                String quantity = cart.getQuantity();
                countAll +=(salePrice * Double.valueOf(quantity));
                cart.setProduct(product);
                Date gmtCreate = cart.getCreateDate();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = sdf.format(gmtCreate);
                System.out.println("format:"+format);
                cart.setCreateDateStr(format);
                carts.add(cart);
            }
            if(addressId !=null){
                Long aLong = Long.valueOf(addressId);
                Address address = cartService.findAddressWithId(aLong);
                model.addAttribute("address",address);
            }
            System.out.println("countAll:"+countAll);
            System.out.println("carts:"+carts);
            model.addAttribute("carts",carts);
            model.addAttribute("countAll",countAll);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "cart";
    }



    @ApiOperation(value = "查询购物车中所有的商品数量")
    @PostMapping("/findProductFromCartCount")
    @ResponseBody
    public GenericResult<Integer> findProductFromCartCount(HttpSession session,Model model) {
        GenericResult<Integer> result = new GenericResult<>();
        try {
            String userName = (String) session.getAttribute("userName");
            System.out.println(userName + "=================");
            Long id = (Long) session.getAttribute("userId");
            if(id==0 || id==null){
                Subject subject = SecurityUtils.getSubject();
                subject.logout();
            }
            int count =  cartService.findProductFromCartCount(id);
            result.setData(count);
            System.out.println("count:"+count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @ApiOperation(value = "清空购物车中所有的商品数量")
    @PostMapping("/deleteProductFromCart")
    @ResponseBody
    public String deleteProductFromCart(HttpSession session,Model model) {
        try {
            String userName = (String) session.getAttribute("userName");
            Long id = (Long) session.getAttribute("userId");
            if(id==0 || id==null){
                Subject subject = SecurityUtils.getSubject();
                subject.logout();
            }
           cartService.deleteProductFromCart(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "系统错误！";
        }
        return "清空成功";
    }

    @ApiOperation(value = "清空购物车中选定的的商品")
    @PostMapping("/deleteProductFromCartWithProductId")
    @ResponseBody
    public String deleteProductFromCartWithProductId(@RequestBody Cart cart, HttpSession session, Model model) {
        try {
            Long id = (Long) session.getAttribute("userId");
            if(id==0 || id==null){
                Subject subject = SecurityUtils.getSubject();
                subject.logout();
            }
            cart.setUserId(id);
            String createDateStr = cart.getCreateDateStr();
            System.out.println("createDateStr:"+createDateStr);
            cartService.deleteProductFromCartWithProductId(cart);
        } catch (Exception e) {
            e.printStackTrace();
            return "系统错误！";
        }
        return "删除成功！";
    }


    @ApiOperation(value = "查询所有收货地址")
    @GetMapping("/findAllAddress")
    @ResponseBody
    public GenericResult<List<Address>> findAllAddress(HttpSession session){
        GenericResult<List<Address>> result = new GenericResult<>();
        try {
            Long id = (Long) session.getAttribute("userId");
            List<Address> lists = cartService.findAllAddress(id);
            result.setData(lists);
            result.setMessage("查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("系统错误");
        }
        return result;
    }

    @ApiOperation(value = "增加收货地址")
    @PostMapping("/addAddressToTable")
    @ResponseBody
    public String addAddressToTable(@RequestBody Address address,HttpSession session){
        try {
            Long id = (Long) session.getAttribute("userId");
            address.setUserId(id);
            cartService.addAddressToTable(address);
            return "添加成功！";
        } catch (Exception e) {
            e.printStackTrace();
            return "系统错误";
        }
    }

    @ApiOperation(value = "删除所选收货地址信息")
    @GetMapping("/deleteAddressWithId/{id}")
    @ResponseBody
    public String deleteAddressWithId(@PathVariable("id") Long addressId,HttpSession session){
        try {
            Long userId = (Long) session.getAttribute("userId");
            cartService.deleteAddressWithId(userId,addressId);
            return "删除成功！";
        } catch (Exception e) {
            e.printStackTrace();
            return "系统错误！";
        }
    }

    @ApiOperation(value = "删除所选收货地址信息")
    @PostMapping("/deleteAllAddress")
    @ResponseBody
    public String deleteAllAddress(HttpSession session){
        try {
            Long userId = (Long) session.getAttribute("userId");
            cartService.deleteAllAddress(userId);
            return "删除成功！";
        } catch (Exception e) {
            e.printStackTrace();
            return "系统错误！";
        }
    }





}
