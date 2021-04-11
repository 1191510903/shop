package com.shop.cn.common.controller;

import com.github.pagehelper.PageHelper;
import com.shop.cn.common.pojo.Address;
import com.shop.cn.common.pojo.Cart;
import com.shop.cn.common.pojo.Order;
import com.shop.cn.common.pojo.Product;
import com.shop.cn.common.service.CartService;
import com.shop.cn.common.service.CheckoutService;
import com.shop.cn.common.service.ProductService;
import com.shop.cn.common.vo.Page;
import com.shop.cn.common.vo.PageInfo;
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
import java.util.*;

@Controller
@Api(value = "订单模块", tags = {"对订单进行操作"})
public class CheckoutController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;

    @Autowired
    private CheckoutService checkoutService;

    @GetMapping("/checkout")
    @ApiOperation(value = "提交订单页面")
    public String cart() {
        return "checkout";
    }

    @GetMapping("/order")
    @ApiOperation(value = "订单页面")
    public String order() {
        return "order";
    }


    @ApiOperation(value = "根据用户id查询订单的商品")
    @PostMapping("/findAllOrderAndProduct")
    @ResponseBody
    public Map<String, List<Object>> findAllOrderAndProduct(@RequestBody Page page, HttpSession session) {
        LinkedHashMap<String, List<Object>> datas = new LinkedHashMap<>();
        try {
            Long id = (Long) session.getAttribute("userId");
            if (id == 0 || id == null) {
                Subject subject = SecurityUtils.getSubject();
                subject.logout();
            }
            //从订单表中查询所有的该用户的订单

            //===============================================可以改为分页
            System.out.println("page.getPageSize():"+page.getPageSize());
            PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
            List<Order> orders1 = checkoutService.findAllFromOrder(id);
            PageInfo<Order> info = new PageInfo<>(orders1);
            System.out.println("===============================info"+info);
            //===============================================可以改为分页
            List<Order> orders = checkoutService.findAllFromOrder(id);

            for (Order order : orders) {
                List<Object> lists = new ArrayList<>();
                String orderUUID = order.getOrderUUID();
                //根据订单号查询所有的订单中的商品
                List<Order> products = checkoutService.findAllFromOrderProductWithUUID(orderUUID);
                for (Order o : products) {
                    Long productId = o.getOrderProductId();
                    Product key = productService.findProductDetailWithKey(productId);
                    o.setProduct(key);
                }
                Date uuidCreate = order.getUuidCreate();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = sdf.format(uuidCreate);
                order.setUuidCreateStr(format);
                lists.add(order);
                lists.add(products);
                datas.put(order.getOrderUUID(), lists);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return datas;
    }


    @ApiOperation(value = "根据订单号查询订单中的商品(模糊查询)")
    @GetMapping("/findCheckoutWithUUIDForLike/{id}")
    @ApiImplicitParam(name = "id", value = "一级菜单id", required = true)
    @ResponseBody
    public Map<String, List<Object>> findCheckoutWithUUIDForLike(@PathVariable("id") String uuid, HttpSession session) {
        LinkedHashMap<String, List<Object>> datas = new LinkedHashMap<>();
        try {
            Long id = (Long) session.getAttribute("userId");
            if (id == 0 || id == null) {
                Subject subject = SecurityUtils.getSubject();
                subject.logout();
            }
            //根据订单号从订单表中查询所有订单(模糊查询)
            List<Order> orders = checkoutService.findCheckoutWithUUIDForLike(uuid);
            for (Order order : orders) {
                List<Object> lists = new ArrayList<>();
                String orderUUID = order.getOrderUUID();
                //根据订单号查询所有的订单中的商品
                List<Order> products = checkoutService.findAllFromOrderProductWithUUID(orderUUID);
                for (Order o : products) {
                    Long productId = o.getOrderProductId();
                    Product key = productService.findProductDetailWithKey(productId);
                    o.setProduct(key);
                }
                Date uuidCreate = order.getUuidCreate();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = sdf.format(uuidCreate);
                order.setUuidCreateStr(format);
                lists.add(order);
                lists.add(products);
                datas.put(order.getOrderUUID(), lists);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datas;
    }

    @ApiOperation(value = "根据订单号查询订单中的商品")
    @GetMapping("/findCheckoutWithUUID/{id}")
    @ApiImplicitParam(name = "id", value = "一级菜单id", required = true)
    @ResponseBody
    public Map<String, List<Object>> findCheckoutWithUUID(@PathVariable("id") String uuid, HttpSession session) {
        LinkedHashMap<String, List<Object>> datas = new LinkedHashMap<>();
        try {
            Long id = (Long) session.getAttribute("userId");
            if (id == 0 || id == null) {
                Subject subject = SecurityUtils.getSubject();
                subject.logout();
            }
            //根据订单号从订单表中查询所有订单
            Order order = checkoutService.findCheckoutWithUUID(uuid);
            String orderUUID = order.getOrderUUID();
            //根据订单号查询所有的订单中的商品
            List<Object> lists = new ArrayList<>();
            List<Order> products = checkoutService.findAllFromOrderProductWithUUID(orderUUID);
            for (Order o : products) {
                Long productId = o.getOrderProductId();
                Product key = productService.findProductDetailWithKey(productId);
                o.setProduct(key);
            }
            Date uuidCreate = order.getUuidCreate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = sdf.format(uuidCreate);
            order.setUuidCreateStr(format);
            lists.add(order);
            lists.add(products);
            datas.put(uuid,lists);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datas;
    }


    @ApiOperation(value = "根据订单号删除订单中的商品")
    @GetMapping("/deleteWithWithUUID/{id}")
    @ApiImplicitParam(name = "id", value = "一级菜单id", required = true)
    @ResponseBody
    public String deleteWithWithUUID(@PathVariable("id") String uuid, HttpSession session) {
        LinkedHashMap<String, List<Object>> datas = new LinkedHashMap<>();
        try {
            Long id = (Long) session.getAttribute("userId");
            if (id == 0 || id == null) {
                Subject subject = SecurityUtils.getSubject();
                subject.logout();
            }
            //根据订单号
            checkoutService.logicDeleteCheckoutWithUUID(uuid);
            checkoutService.logicDeleteFromOrderProductWithUUID(uuid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "删除成功！";
    }



    @ApiOperation(value = "进行结算(跳转到结算界面)")
    @GetMapping("checkOuting")
    public String checkOuting(@RequestParam(value = "addressId", required = false) String addressId
            , @RequestParam("expressFee") String expressFee
            , @RequestParam("expressWay") String expressWay
            , HttpSession session, Model model) {
        try {
            Long id = (Long) session.getAttribute("userId");
            if (id == 0 || id == null) {
                Subject subject = SecurityUtils.getSubject();
                subject.logout();
            }
            //找到所有购物车的信息进行数学计算
            List<Cart> list = cartService.findProductFromCart(id);
            List<Cart> carts = new ArrayList<>();
            double countAll = 0;
            for (Cart cart : list) {
                //商品主键
                Long productId = cart.getProductId();
                Product product = productService.findProductDetailWithKey(productId);
                Double salePrice = product.getSalePrice();
                String quantity = cart.getQuantity();
                //购物车该商品的总价
                cart.setPrice(salePrice * Double.valueOf(quantity));
                countAll += (salePrice * Double.valueOf(quantity));
                cart.setProduct(product);
                Date gmtCreate = cart.getCreateDate();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = sdf.format(gmtCreate);
                cart.setCreateDateStr(format);
                carts.add(cart);
            }
            //获取默认收货地址（用户在数据库中第一条数据）
            if (addressId == null) {
                List<Address> allAddress = cartService.findAllAddress(id);
                if (!allAddress.isEmpty()) {
                    Address address = allAddress.get(0);
                    addressId = address.getAddressId().toString();
                }
            }
            //判断查询用户默认的收货地址为空
            if (addressId != null) {
                Address address = cartService.findAddressWithId(Long.valueOf(addressId));
                model.addAttribute("address", address);
            }
            String express = expressWay + ":$" + expressFee;
            model.addAttribute("carts", carts);
            //商品总价
            model.addAttribute("countAll", countAll);
            model.addAttribute("expressWay", expressWay);
            model.addAttribute("expressFee", expressFee);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "checkout";
    }


    @ApiOperation(value = "提交订单（确定商品结算，商品进入订单表）")
    @PostMapping("/addCheckout")
    @ApiImplicitParam(name = "id", value = "商品id", required = true)
    @ResponseBody
    public String addCheckout(@RequestBody Order order, HttpSession session) {
        try {
            Long id = (Long) session.getAttribute("userId");
            String userName = (String) session.getAttribute("userName");
            if (id == 0 || id == null) {
                Subject subject = SecurityUtils.getSubject();
                subject.logout();
            }
            System.out.println("=======================order1:" + order);
            order.setUserId(id);
            /**
             * 将商品添加到订单表
             */

            //生成唯一uuid订单号
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            System.out.println("uuid:" + uuid);
            order.setOrderUUID(uuid);
            order.setUserId(id);
            order.setUserName(userName);
            System.out.println("=======================order2:" + order);

            //订单所有商品的总价（包含运费）
            double countAll = 0;
            //查询所有购物车中的商品
            List<Cart> list = cartService.findProductFromCart(id);
            System.out.println("=========================Carts:" + list);
            if (list != null && !list.isEmpty()) {
                for (Cart cart : list) {
                    //商品主键
                    Long productId = cart.getProductId();
                    Product product = productService.findProductDetailWithKey(productId);
                    Double salePrice = product.getSalePrice();
                    String quantity = cart.getQuantity();
                    countAll += (salePrice * Double.valueOf(quantity));
                    //订单所属商品的总数
                    order.setProductCount(quantity);
                    order.setOrderProductId(cart.getProductId());
                    order.setOrderProductName(cart.getProductName());
                    //订单所属（单个）商品的总价
                    order.setProductSubtotal(salePrice * Double.valueOf(quantity));
                    System.out.println("=======================order3:" + order);
                    checkoutService.addCheckout(order);
                }
                //订单所有商品的总价（包含运费）
                order.setProductTotal(countAll);
                checkoutService.addCheckoutUUID(order);
                /**
                 * 将商品从购物车表中删除
                 */
                cartService.deleteProductFromCart(id);
                return "结算成功！";
            }
            return "购物车为空！";

        } catch (Exception e) {
            e.printStackTrace();
            return "系统错误！";
        }
    }


}
