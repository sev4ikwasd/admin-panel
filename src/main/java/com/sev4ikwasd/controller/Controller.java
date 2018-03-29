package com.sev4ikwasd.controller;

import com.sev4ikwasd.dao.UserDAO;
import com.sev4ikwasd.model.User;
import com.sev4ikwasd.utils.Ajax;
import com.sev4ikwasd.utils.RestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class Controller {

    @Autowired
    UserDAO userDAO;

    @GetMapping(value="/getall")
    @ResponseBody
    public Map<String, Object> getAll() throws RestException {
        try {
            List result = userDAO.getAll();
            if(result == null) {
                return Ajax.emptyResponse();
            }
            else {
                return Ajax.successResponse(result);
            }
        }
        catch (Exception e){
            throw new RestException(e);
        }
    }

    @GetMapping(value="/get")
    @ResponseBody
    public Map<String, Object> get(@RequestParam("id") Long id) throws RestException {
        try {
            User result = userDAO.get(id);
            if(result == null) {
                return Ajax.emptyResponse();
            }
            else {
                return Ajax.successResponse(result);
            }
        }
        catch (Exception e){
            throw new RestException(e);
        }
    }

    @PostMapping(value="/add")
    @ResponseBody
    public Map<String, Object> add(@RequestBody User user) throws RestException {
        try {
            userDAO.add(user);
            return Ajax.emptyResponse();
        }
        catch (Exception e){
            throw new RestException(e);
        }
    }

    @PostMapping(value="/edit")
    @ResponseBody
    public Map<String, Object> edit(@RequestBody User user) throws RestException {
        try {
            User result = userDAO.edit(user);
            if(result == null) {
                return Ajax.emptyResponse();
            }
            else {
                return Ajax.successResponse(result);
            }
        }
        catch (Exception e){
            throw new RestException(e);
        }
    }

    @GetMapping(value="/delete")
    @ResponseBody
    public Map<String, Object> delete(@RequestParam("id") Long id) throws RestException {
        try {
            userDAO.delete(id);
            return Ajax.emptyResponse();
        }
        catch (Exception e){
            throw new RestException(e);
        }
    }

    @ExceptionHandler
    @ResponseBody
    public Map<String, Object> exception(RestException exception){
        return Ajax.errorResponse(exception.getMessage());
    }
}
