package com.project.revolvingcabinet.controller;

import com.github.pagehelper.PageInfo;
import com.project.revolvingcabinet.Vo.ArchiveBoxVo;
import com.project.revolvingcabinet.common.CommonResult;
import com.project.revolvingcabinet.common.Messages;
import com.project.revolvingcabinet.dao.ArchiveBoxMapper;
import com.project.revolvingcabinet.service.ArchiveBoxService;
import com.project.revolvingcabinet.utils.PageUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Resource
    private ArchiveBoxService archiveBoxService;

    /**
     * 搜索档案盒
     * @param keyword
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping(path = "/archiveBoxSearch", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult searchArchiveBox(@RequestParam("keyword") String keyword,
            @RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize) {
        // 参数验证
        if (StringUtils.isBlank(keyword) || pageIndex == null || pageSize == null) {
            logger.error(Messages.getErrorMsg(Messages.MSG_E_LOG_001));
            return CommonResult.validateFailed(Messages.getErrorMsg(Messages.MSG_E_LOG_001));
        }

        // 获取档案盒
        List<ArchiveBoxVo> archiveBoxVoList = archiveBoxService.getArchiveBoxByKeyWord(keyword);

        // 分页
        PageInfo<ArchiveBoxVo> pageInfo = PageUtil.getPageInfo(archiveBoxVoList, pageIndex, pageSize);
        return CommonResult.success(pageInfo);

    }

}
