package com.shibro.travel.controller;

import com.shibro.travel.data.vo.BaseResponseVo;
import com.shibro.travel.service.FileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class FileController {



    @Resource
    private FileService fileService;

    @RequestMapping(value = "/file/upload",method = RequestMethod.POST)
    public BaseResponseVo uploadFile(@RequestBody  MultipartFile file, HttpServletRequest request){
        BaseResponseVo responseVo = fileService.uploadFile(file,request);
        return responseVo;
    }

    @RequestMapping(value = "/files/upload",method = RequestMethod.POST)
    public BaseResponseVo uploadFiles(@RequestBody List<MultipartFile> files,HttpServletRequest request){
        BaseResponseVo responseVo = fileService.uploadFile(files,request);
        return responseVo;
    }
}
