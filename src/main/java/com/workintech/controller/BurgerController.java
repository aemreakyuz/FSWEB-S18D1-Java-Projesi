package com.workintech.controller;

import com.workintech.dao.BurgerDao;
import com.workintech.entity.BreadType;
import com.workintech.entity.Burger;
import com.workintech.util.BurgerValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// to solve cors errors.
@CrossOrigin("*")
@RestController
@RequestMapping("/burger")
public class BurgerController {
    private final BurgerDao burgerDao;

    @Autowired
    public BurgerController(BurgerDao burgerDao) {
        this.burgerDao = burgerDao;
    }

    @PostMapping
    public Burger save(@RequestBody Burger burger) {
        BurgerValidation.checkName(burger.getName());
        return burgerDao.save(burger);
    }

    @GetMapping
    public List<Burger> findAll() {
        return burgerDao.findAll();
    }

    @GetMapping("/{id}")
    public Burger getById(@PathVariable long id) {
        return burgerDao.findById(id);
    }

    @PutMapping
    public Burger update(@RequestBody Burger burger) {
        return burgerDao.update(burger);
    }

    @DeleteMapping("/{id}")
    public Burger remove(@PathVariable long id) {
        return burgerDao.remove(id);
    }

    @GetMapping("/breadType/{breadType}")
    public List<Burger> findByBreadType(@PathVariable String breadType) {
        BreadType bt = BreadType.valueOf(breadType);
        return burgerDao.findByBreadType(bt);
    }

    @GetMapping("/price/{price}")
    public List<Burger> findByPrice(@PathVariable Integer price) {
        return burgerDao.findByPrice(price);
    }

    @GetMapping("/content/{content}")
    public List<Burger> findByPrice(@PathVariable String content) {
        return burgerDao.findByContent(content);
    }

}
