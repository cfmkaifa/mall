/*
 *
 *
 *
 *
 */
package net.mall.controller.shop;

import javax.inject.Inject;

import net.mall.Page;
import net.mall.entity.Product;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonView;

import net.mall.Pageable;
import net.mall.Results;
import net.mall.entity.BaseEntity;
import net.mall.entity.Store;
import net.mall.exception.ResourceNotFoundException;
import net.mall.service.StoreService;

/**
 * Controller - 店铺
 *
 * @author huanghy
 * @version 6.1
 */
@Controller("shopStoreController")
@RequestMapping("/store")
public class StoreController extends BaseController {

    @Inject
    private StoreService storeService;

    /**
     * 每页记录数
     */
    private static final int PAGE_SIZE = 20;

    /**
     * 首页
     */
    @GetMapping("/{storeId}")
    public String index(@PathVariable Long storeId, ModelMap model) {
        Store store = storeService.find(storeId);
        if (store == null) {
            throw new ResourceNotFoundException();
        }
        model.addAttribute("store", store);
        return "shop/store/index";
    }

    /**
     * 搜索
     */
    @GetMapping("/search")
    public String search(String keyword, Integer pageNumber, Integer pageSize, ModelMap model) {
        if (StringUtils.isEmpty(keyword)) {
            return UNPROCESSABLE_ENTITY_VIEW;
        }

        Pageable pageable = new Pageable(pageNumber, pageSize);
        model.addAttribute("storeKeyword", keyword);
        model.addAttribute("searchType", "STORE");
        Page<Store> page= storeService.search(keyword, pageable);
        model.addAttribute("page",page);
        model.addAttribute("key_word",keyword);
        model.addAttribute("result_number",page.getContent().size());
        return "shop/store/search";
    }

    /**
     * 搜索
     */
    @GetMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(BaseEntity.BaseView.class)
    public ResponseEntity<?> search(String keyword, Integer pageNumber) {
        if (StringUtils.isEmpty(keyword)) {
            return Results.NOT_FOUND;
        }

        Pageable pageable = new Pageable(pageNumber, PAGE_SIZE);
        return ResponseEntity.ok(storeService.search(keyword, pageable).getContent());
    }


   /* *//***
     * 首页工厂资源轮播
     *//*
    @PostMapping("/factory")
        public String factory(ModelMap model){
        Pageable pageable = new Pageable();
        pageable.setPageSize(12);
        Page<Store> stores = storeService.findPage(pageable);
        model.addAttribute(stores);
        model.addAttribute("number",pageable.getPageNumber());
        return "/shop/index";
    }*/
}