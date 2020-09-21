package com.imooc.controller;

import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.pojo.vo.CommentLevelCountsVO;
import com.imooc.pojo.vo.ItemInfoVO;
import com.imooc.service.ItemService;
import com.imooc.utils.JSONResult;
import com.imooc.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "商品接口", tags = {"商品信息展示的相关接口"})
@RestController
@RequestMapping("/items")
public class ItemController extends BaseController {

    @Autowired
    private ItemService itemService;

    @ApiOperation(value = "查询商品详情", notes = "查询商品详情", httpMethod = "GET")
    @GetMapping("/info/{itemId}")
    public JSONResult info(
            @ApiParam(name = "itemId", value = "商品id", required = true)
            @PathVariable String itemId
    ) {

        if (StringUtils.isBlank(itemId)) {
            return JSONResult.errorMsg(null);
        }

        Items item = itemService.queryItemById(itemId);
        List<ItemsImg> itemImgList = itemService.queryItemImgList(itemId);
        List<ItemsSpec> itemSpecList = itemService.queryItemSpecList(itemId);
        ItemsParam itemParam = itemService.queryItemParam(itemId);

        ItemInfoVO itemInfoVO = new ItemInfoVO();

        itemInfoVO.setItem(item);
        itemInfoVO.setItemImgList(itemImgList);
        itemInfoVO.setItemSpecList(itemSpecList);
        itemInfoVO.setItemParams(itemParam);

        return JSONResult.ok(itemInfoVO);
    }

    @ApiOperation(value = "查询商品评价等级", notes="查询商品评价等级", httpMethod = "GET")
    @GetMapping("/commentLevel")
    public JSONResult commentLevel(
            @ApiParam(name = "itemId", value="商品id", required = true)
            @RequestParam String itemId
    ) {

        if (StringUtils.isBlank(itemId)) {
            return JSONResult.errorMsg(null);
        }

        CommentLevelCountsVO countsVO = itemService.queryCommentCounts(itemId);
        return JSONResult.ok(countsVO); 
    }

    @ApiOperation(value = "查询商品评论", notes="查询商品评论", httpMethod = "GET")
    @GetMapping("/comments")
    public JSONResult comment(
            @ApiParam(name = "itemId", value="商品id", required = true)
            @RequestParam String itemId,
            @ApiParam(name = "level", value="评价等级", required = false)
            @RequestParam Integer level,
            @ApiParam(name = "page", value="页码", required = false)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize", value="显示条数", required = false)
            @RequestParam Integer pageSize
    ) {

        if (StringUtils.isBlank(itemId)) {
            return JSONResult.errorMsg(null);
        }

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = COMMENT_PAGE_SIZE;
        }

        PagedGridResult grid = itemService.queryPagedComments(itemId, level, page, pageSize);
        return JSONResult.ok(grid);
    }


    @ApiOperation(value = "搜索商品列表", notes="搜索商品列表", httpMethod = "GET")
    @GetMapping("/search")
    public JSONResult search(
            @ApiParam(name = "keywords", value="关键字", required = true)
            @RequestParam String keywords,
            @ApiParam(name = "sort", value="排序类型", required = false)
            @RequestParam String sort,
            @ApiParam(name = "page", value="页码", required = false)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize", value="显示条数", required = false)
            @RequestParam Integer pageSize
    ) {

        if (StringUtils.isBlank(keywords)) {
            return JSONResult.errorMsg(null);
        }

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }

        PagedGridResult grid = itemService.searchItems(keywords, sort, page, pageSize);
        return JSONResult.ok(grid);
    }

    @ApiOperation(value = "通过分类id搜索商品列表", notes="通过分类id搜索商品列表", httpMethod = "GET")
    @GetMapping("/catItems")
    public JSONResult catItems(
            @ApiParam(name = "catId", value="三级分类id", required = true)
            @RequestParam Integer catId,
            @ApiParam(name = "sort", value="排序类型", required = false)
            @RequestParam String sort,
            @ApiParam(name = "page", value="页码", required = false)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize", value="显示条数", required = false)
            @RequestParam Integer pageSize
    ) {

        if (catId == null) {
            return JSONResult.errorMsg(null);
        }

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }

        PagedGridResult grid = itemService.searchItems(catId, sort, page, pageSize);
        return JSONResult.ok(grid);
    }

}
