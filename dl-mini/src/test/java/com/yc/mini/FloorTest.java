package com.yc.mini;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yc.core.mini.entity.MallFloor;
import com.yc.mini.mall.service.MallFloorService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述:
 *
 * @Author: xieyc && 紫色年华
 * @Date: 2020-07-02
 * @Version: 1.0.0
 */
@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
public class FloorTest {

    @Autowired
    private MallFloorService mallFloorService;

    @Test
    public void init(){
        String floorData = "[{\"floor_title\":{\"name\":\"时尚女装\",\"image_src\":\"https://api-hmugo-web.itheima.net/pyg/pic_floor01_title.png\"},\"product_list\":[{\"name\":\"优质服饰\",\"image_src\":\"https://api-hmugo-web.itheima.net/pyg/pic_floor01_1@2x.png\",\"image_width\":\"232\",\"open_type\":\"navigate\",\"navigator_url\":\"/pages/goods_list/index?query=服饰\"},{\"name\":\"春季热门\",\"image_src\":\"https://api-hmugo-web.itheima.net/pyg/pic_floor01_2@2x.png\",\"image_width\":\"233\",\"open_type\":\"navigate\",\"navigator_url\":\"/pages/goods_list/index?query=热\"},{\"name\":\"爆款清仓\",\"image_src\":\"https://api-hmugo-web.itheima.net/pyg/pic_floor01_3@2x.png\",\"image_width\":\"233\",\"open_type\":\"navigate\",\"navigator_url\":\"/pages/goods_list/index?query=爆款\"},{\"name\":\"倒春寒\",\"image_src\":\"https://api-hmugo-web.itheima.net/pyg/pic_floor01_4@2x.png\",\"image_width\":\"233\",\"open_type\":\"navigate\",\"navigator_url\":\"/pages/goods_list/index?query=春季\"},{\"name\":\"怦然心动\",\"image_src\":\"https://api-hmugo-web.itheima.net/pyg/pic_floor01_5@2x.png\",\"image_width\":\"233\",\"open_type\":\"navigate\",\"navigator_url\":\"/pages/goods_list/index?query=心动\"}]},{\"floor_title\":{\"name\":\"户外活动\",\"image_src\":\"https://api-hmugo-web.itheima.net/pyg/pic_floor02_title.png\"},\"product_list\":[{\"name\":\"勇往直前\",\"image_src\":\"https://api-hmugo-web.itheima.net/pyg/pic_floor02_1@2x.png\",\"image_width\":\"232\",\"open_type\":\"navigate\",\"navigator_url\":\"/pages/goods_list/index?query=户外\"},{\"name\":\"户外登山包\",\"image_src\":\"https://api-hmugo-web.itheima.net/pyg/pic_floor02_2@2x.png\",\"image_width\":\"273\",\"open_type\":\"navigate\",\"navigator_url\":\"/pages/goods_list/index?query=登山包\"},{\"name\":\"超强手套\",\"image_src\":\"https://api-hmugo-web.itheima.net/pyg/pic_floor02_3@2x.png\",\"image_width\":\"193\",\"open_type\":\"navigate\",\"navigator_url\":\"/pages/goods_list/index?query=手套\"},{\"name\":\"户外运动鞋\",\"image_src\":\"https://api-hmugo-web.itheima.net/pyg/pic_floor02_4@2x.png\",\"image_width\":\"193\",\"open_type\":\"navigate\",\"navigator_url\":\"/pages/goods_list/index?query=运动鞋\"},{\"name\":\"冲锋衣系列\",\"image_src\":\"https://api-hmugo-web.itheima.net/pyg/pic_floor02_5@2x.png\",\"image_width\":\"273\",\"open_type\":\"navigate\",\"navigator_url\":\"/pages/goods_list/index?query=冲锋衣\"}]},{\"floor_title\":{\"name\":\"箱包配饰\",\"image_src\":\"https://api-hmugo-web.itheima.net/pyg/pic_floor03_title.png\"},\"product_list\":[{\"name\":\"清新气质\",\"image_src\":\"https://api-hmugo-web.itheima.net/pyg/pic_floor03_1@2x.png\",\"image_width\":\"232\",\"open_type\":\"navigate\",\"navigator_url\":\"/pages/goods_list?query=饰品\"},{\"name\":\"复古胸针\",\"image_src\":\"https://api-hmugo-web.itheima.net/pyg/pic_floor03_2@2x.png\",\"image_width\":\"263\",\"open_type\":\"navigate\",\"navigator_url\":\"/pages/goods_list?query=胸针\"},{\"name\":\"韩版手链\",\"image_src\":\"https://api-hmugo-web.itheima.net/pyg/pic_floor03_3@2x.png\",\"image_width\":\"203\",\"open_type\":\"navigate\",\"navigator_url\":\"/pages/goods_list?query=手链\"},{\"name\":\"水晶项链\",\"image_src\":\"https://api-hmugo-web.itheima.net/pyg/pic_floor03_4@2x.png\",\"image_width\":\"193\",\"open_type\":\"navigate\",\"navigator_url\":\"/pages/goods_list?query=水晶项链\"},{\"name\":\"情侣表\",\"image_src\":\"https://api-hmugo-web.itheima.net/pyg/pic_floor03_5@2x.png\",\"image_width\":\"273\",\"open_type\":\"navigate\",\"navigator_url\":\"/pages/goods_list?query=情侣表\"}]}]";
        JSONArray array = JSONArray.parseArray(floorData);
        List<MallFloor> list = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            // 一级
            MallFloor mallFloor = new MallFloor();
            JSONObject jsonObject = array.getJSONObject(i).getJSONObject("floor_title");
            mallFloor.setName(jsonObject.getString("name"));
            mallFloor.setMallFloorPid("0");
            mallFloor.setLevel(1);
            mallFloor.setImage(jsonObject.getString("image_src"));
            mallFloorService.save(mallFloor);
            String pid = mallFloor.getMallFloorId();
            // 二级
            JSONArray jsonArray = array.getJSONObject(i).getJSONArray("product_list");
            for (int x = 0; x < jsonArray.size(); x++) {
                JSONObject itemData = jsonArray.getJSONObject(x);
                mallFloor = new MallFloor();
                mallFloor.setMallFloorPid(pid);
                mallFloor.setName(itemData.getString("name"));
                mallFloor.setLevel(2);
                mallFloor.setImage(itemData.getString("image_src"));
                mallFloor.setImageWidth(Double.valueOf(itemData.getString("image_width")));
                mallFloor.setOpenType(itemData.getString("open_type"));
                mallFloor.setNavigatorUrl(itemData.getString("navigator_url"));
                list.add(mallFloor);
            };
        }
        mallFloorService.saveBatch(list);
    }
}
