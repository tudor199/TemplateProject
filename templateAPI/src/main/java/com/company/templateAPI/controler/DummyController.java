package com.company.templateAPI.controler;

import com.company.templateAPI.entity.Dummy;
import com.company.templateAPI.service.DummyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/dummy")
public class DummyController {
    @Autowired
    DummyService dummyService;

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public @ResponseBody List<Dummy> getDummies() {
        return dummyService.getAll();
    }

    @RequestMapping(path = "/page", method = RequestMethod.GET)
    public @ResponseBody List<Dummy> getDummiesPage(Pageable pageable) {
        return dummyService.getAll(pageable);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Optional<Dummy> getDummyById(@RequestParam("id") Integer id) {
        return dummyService.getDummy(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void insertDummy(@NotNull @RequestBody Dummy dummy) {
        dummyService.insert(dummy);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateDummy(@NotNull @RequestBody Dummy dummy) {
        dummyService.update(dummy);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteDummyById(@RequestParam("id") Integer id) {
        dummyService.delete(id);
    }
}
