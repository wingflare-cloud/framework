package com.wingflare.engine.task.server.web.controller;

import com.wingflare.engine.task.common.core.annotation.OriginalControllerReturnValue;
import com.wingflare.engine.task.server.web.annotation.LoginRequired;
import com.wingflare.engine.task.server.web.annotation.RoleEnum;
import com.wingflare.engine.task.server.web.model.base.PageResult;
import com.wingflare.engine.task.server.web.model.request.ExportNotifyRecipientVO;
import com.wingflare.engine.task.server.web.model.request.NotifyRecipientQueryVO;
import com.wingflare.engine.task.server.web.model.request.NotifyRecipientRequestVO;
import com.wingflare.engine.task.server.web.model.response.CommonLabelValueResponseVO;
import com.wingflare.engine.task.server.web.model.response.NotifyRecipientResponseVO;
import com.wingflare.engine.task.server.web.service.NotifyRecipientService;
import com.wingflare.engine.task.server.web.util.ExportUtils;
import com.wingflare.engine.task.server.web.util.ImportUtils;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 告警通知接收人 前端控制器
 * </p>
 *
 * @author xiaowoniu
 * @since 2024-04-17
 */
@RestController
@RequestMapping("/notify-recipient")
public class NotifyRecipientController {
    private final NotifyRecipientService notifyRecipientService;

    public NotifyRecipientController(NotifyRecipientService notifyRecipientService) {
        this.notifyRecipientService = notifyRecipientService;
    }

    @PostMapping
    @LoginRequired
    public Boolean saveNotifyRecipient(@RequestBody @Validated NotifyRecipientRequestVO requestVO) {
        return notifyRecipientService.saveNotifyRecipient(requestVO);
    }

    @PutMapping
    @LoginRequired
    public Boolean updateNotifyRecipient(@RequestBody @Validated NotifyRecipientRequestVO requestVO) {
        return notifyRecipientService.updateNotifyRecipient(requestVO);
    }

    @GetMapping("/page/list")
    @LoginRequired
    public PageResult<List<NotifyRecipientResponseVO>> getNotifyRecipientPageList(NotifyRecipientQueryVO queryVO) {
        return notifyRecipientService.getNotifyRecipientPageList(queryVO);
    }

    @GetMapping("/list")
    @LoginRequired
    public List<CommonLabelValueResponseVO> getNotifyRecipientList() {
        return notifyRecipientService.getNotifyRecipientList();
    }

    @DeleteMapping("/ids")
    @LoginRequired
    public Boolean batchDeleteByIds(@RequestBody @NotEmpty(message = "ids cannot be null") Set<Long> ids) {
        return notifyRecipientService.batchDeleteByIds(ids);
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @LoginRequired(role = RoleEnum.ADMIN)
    public void importScene(@RequestPart("file") MultipartFile file) throws IOException {
        notifyRecipientService.importNotifyRecipient(ImportUtils.parseList(file, NotifyRecipientRequestVO.class));
    }

    @PostMapping("/export")
    @LoginRequired
    @OriginalControllerReturnValue
    public ResponseEntity<String> exportGroup(@RequestBody ExportNotifyRecipientVO exportNotifyRecipientVO) {
        return ExportUtils.doExport(notifyRecipientService.exportNotifyRecipient(exportNotifyRecipientVO));
    }
}
