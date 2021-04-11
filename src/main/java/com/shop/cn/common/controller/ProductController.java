package com.shop.cn.common.controller;

import com.shop.cn.common.pojo.*;
import com.shop.cn.common.service.ProductService;
import com.shop.cn.common.vo.GenericResult;
import com.shop.cn.common.vo.Page;
import com.shop.cn.common.vo.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@Api(value = "商品模块", tags = {"对商品进行操作"})
public class ProductController {

    @Autowired
    ProductService productService;


    @GetMapping("contact")
    @ApiOperation(value = "联系我们页面")
    public String contact() {
        return "contact";
    }


    @GetMapping("categories")
    @ApiOperation(value = "商品分类页面")
    public String categories() {
        return "categories";
    }

    @GetMapping("product")
    @ApiOperation(value = "商品默认页面")
    public String product() {
        return "product";
    }

    @GetMapping("collection")
    @ApiOperation(value = "商品默认页面")
    public String collection() {
        return "collection";
    }


    @PostMapping("letter")
    @ResponseBody
    @ApiOperation(value = "联系我们", notes = "用户对所属商家进行联系")
    public String letter(@RequestBody Letter letter, HttpSession session, Model model) {
        String userName = (String) session.getAttribute("userName");
        System.out.println(userName + "=================");
        Long id = (Long) session.getAttribute("userId");
        System.out.println(id + "=======================");
        letter.setUserId(id);
        if (id != 0 || userName != null) {
            productService.saveToLetter(letter);
        }
        return "联系成功！";
    }

    @ApiOperation(value = "查询商品所有一级目录")
    @GetMapping("findCategory1")
    @ResponseBody
    public GenericResult<List<Category1>> findCategory1() {
        GenericResult<List<Category1>> result = new GenericResult<>();
        try {
            List<Category1> list = productService.findCategory1();
            result.setData(list);
        } catch (Exception e) {
            result.setMessage("系统错误!");
            e.printStackTrace();
        }
        return result;
    }

    @ApiOperation(value = "根据商品一级目录查询二级目录")
    @GetMapping("/findCategory2/{id}")
    @ApiImplicitParam(name = "id", value = "一级菜单id", required = true)
    public String findCategory2(@PathVariable("id") Long id, Model model) {
        try {
            List<Category2> list = productService.findCategory2(id);
            System.out.println("list:" + list);
            model.addAttribute("list", list);
            model.addAttribute("id", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "categories";
    }


    @ApiOperation(value = "根据商品id查询单个商品详情==返回到商品页面")
    @GetMapping("/findProductDetail/{id}")
    @ApiImplicitParam(name = "id", value = "商品id", required = true)
    public String findProductDetail(@PathVariable("id") Long id, Model model) {
        try {
            Product product = productService.findProductDetail(id);
            //更新点击商品事件
            Long hits = product.getHits();
            hits+=1;
            productService.updateClickProduct(id,hits);
            Long category1Id = product.getCategory1Id();
            Long category2Id = product.getCategory2Id();
            System.out.println("product:" + product);
            model.addAttribute("data", product);
            model.addAttribute("category1Id", category1Id);
            model.addAttribute("category2Id", category2Id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "product";
    }

    @ApiOperation(value = "根据商品一级目录查询二级目录====返回值")
    @GetMapping("/findCategory2WithResult/{id}")
    @ResponseBody
    @ApiImplicitParam(name = "id", value = "一级菜单id", required = true)
    public GenericResult<List<Category2>> findCategory2WithResult(@PathVariable("id") Long id, Model model) {
        GenericResult<List<Category2>> result = new GenericResult<>();
        try {
            List<Category2> list = productService.findCategory2(id);
            System.out.println("根据商品一级目录查询二级目录:" + list);
            result.setData(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    @ApiOperation(value = "根据商品二级目录查询商品")
    @ApiImplicitParam(name = "product", value = "查询商品条件（一级菜单目录/二级菜单目录/页码）", required = true)
    @PostMapping("/findProduct")
    @ResponseBody
    public GenericResult<PageInfo<Product>> findProduct(@RequestBody Product product, Model model) {
        GenericResult<PageInfo<Product>> result = new GenericResult<>();
        try {
            Long category1Id = product.getCategory1Id();
            Long category2Id = product.getCategory2Id();
            System.out.println("category1Id:" + category1Id);
            System.out.println("category2Id:" + category2Id);
            System.out.println("pageNum:" + product.getPageNum());
            long id1 = Long.valueOf(category1Id);
            long id2 = Long.valueOf(category2Id);
            //设置当前页面，和显示的页面商品数量
            Page page = new Page();
            page.setCurrentPage(product.getPageNum());
            page.setPageSize(12);
            PageInfo<Product> data = productService.findProduct(product, page);
            model.addAttribute("list", data);
            result.setData(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return result;
    }

    @ApiOperation(value = "查询所有商品根据点击量进行排序")
    @ApiImplicitParam(name = "product", value = "查询商品条件（一级菜单目录/二级菜单目录/页码）", required = true)
    @GetMapping("/findAllProductOrderByHits")
    @ResponseBody
    public GenericResult<List<Product>> findAllProductOrderByHits() {
        GenericResult<List<Product>> result = new GenericResult<>();
        try {
            List<Product> list =productService.findAllProductOrderByHits();
            result.setData(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return result;
    }


    @ApiOperation(value = "根据商品'主键id'查询单个商品详情")
    @GetMapping("/findProductDetailWithKey/{id}")
    @ApiImplicitParam(name = "id", value = "商品id", required = true)
    public String findProductDetailWithKey(@PathVariable("id") Long id, Model model) {
        try {
            Product product = productService.findProductDetailWithKey(id);
            Long category1Id = product.getCategory1Id();
            Long category2Id = product.getCategory2Id();
            System.out.println("product:" + product);
            model.addAttribute("data", product);
            model.addAttribute("category1Id", category1Id);
            model.addAttribute("category2Id", category2Id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "product";
    }

    @ApiOperation(value = "收藏商品")
    @GetMapping("saveToCollection/{id}")
    @ApiImplicitParam(name = "id", value = "商品id", required = true)
    @ResponseBody
    public String saveToCollection(@PathVariable("id") Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        try {

            //先从数据库中找是否存在用户收藏的商品
            Collection collection = productService.findCollectionWithProductIdAndUserId(id,userId);
            System.out.println("===================collection"+collection);
            if (collection==null){
                productService.saveToCollection(id, userId);
                return "收藏成功！";
            }
            return "该商品您已经收藏！";
        } catch (Exception e) {
            e.printStackTrace();
            return "系统错误！";
        }

    }

    @ApiOperation(value = "查询所有收藏商品")
    @GetMapping("findAllFromCollection")
    @ResponseBody
    public GenericResult<List<Product>> findAllFromCollection(HttpSession session) {
        GenericResult<List<Product>> result = new GenericResult<>();
        Long userId = (Long) session.getAttribute("userId");
        try {
            List<Collection> collections = productService.findAllFromCollection(userId);
            List<Product> products = new ArrayList<>();
            for (Collection collection: collections) {
                Long productId = collection.getProductId();
                Product product = productService.findProductDetailWithKey(productId);
                products.add(product);
            }
            result.setData(products);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    @ApiOperation(value = "清空收藏夹")
    @GetMapping("deleteAllFromCollection")
    @ResponseBody
    public String deleteAllFromCollection(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        try {
            productService.deleteAllFromCollection(userId);
            return "清空成功！";
        } catch (Exception e) {
            e.printStackTrace();
            return "系统错误！";
        }
    }

    @ApiOperation(value = "删除所选的收藏夹商品")
    @GetMapping("deleteFromCollectionWithId/{id}")
    @ResponseBody
    public String deleteFromCollectionWithId(@PathVariable("id") Long productId, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        try {
            productService.deleteFromCollectionWithId(productId,userId);
            return "取消成功！";
        } catch (Exception e) {
            e.printStackTrace();
            return "系统错误！";
        }
    }

}
