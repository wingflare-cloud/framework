package com.wingflare.tool.generator.controller;

import com.wingflare.tool.generator.common.Result;
import com.wingflare.tool.generator.common.ResultGenerator;
import com.wingflare.tool.generator.dto.MpgGenCodeDto;
import com.wingflare.tool.generator.mbp.MbpGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mbp-generator")
public class MbpGeneratorController {

    @Autowired
    private MbpGenerator mbpGenerator;

    @PostMapping("/gen-code")
    public Result genCode(@RequestBody MpgGenCodeDto dto) {
        mbpGenerator.genCodeBatch(dto.getGenSetting(), dto.getTables());
        return ResultGenerator.genSuccessResult();
    }

}
