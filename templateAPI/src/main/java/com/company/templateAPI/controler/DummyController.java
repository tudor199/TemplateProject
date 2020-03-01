package com.company.templateAPI.controler;

import com.company.templateAPI.entity.Dummy;
import com.company.templateAPI.service.DummyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DummyController {
    @Autowired
    DummyService dummyService;

    @RequestMapping(path = "/dummies", method = RequestMethod.GET)
    public @ResponseBody List<Dummy> getDummies() {
        return dummyService.getAll();
    }

    @RequestMapping(path = "/dummy", method = RequestMethod.POST)
    public String insert(@RequestBody Dummy dummy) {
        dummyService.insert(dummy);
        return dummy.toString();
    }
}
