package zn.zyh.back_code.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zn.zyh.back_code.utils.countutil.CountUtil;

@RestController
public class CountController {

    @RequestMapping("/count")
    public void Count(@RequestBody JSONObject param){
        CountUtil.add();
    }
}
