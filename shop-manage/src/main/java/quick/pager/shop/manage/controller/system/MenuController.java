package quick.pager.shop.manage.controller.system;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quick.pager.shop.constants.Constants;
import quick.pager.shop.constants.ResponseStatus;
import quick.pager.shop.manage.param.system.MenuOtherParam;
import quick.pager.shop.manage.param.system.MenuSaveParam;
import quick.pager.shop.manage.service.system.MenuService;
import quick.pager.shop.response.Response;

/**
 * 菜单管理
 *
 * @author siguiyang
 * @version 3.0
 */
@RestController
@RequestMapping(Constants.Module.MANAGE)
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 系统菜单列表
     */
    @PreAuthorize("hasAuthority('PAGER_SYSTEM_MENU')")
    @PostMapping("/menu/list")
    public Response list(@RequestBody MenuOtherParam param) {
        return menuService.queryList(param);
    }

    /**
     * 新增
     */
    @PreAuthorize("hasAuthority('PAGER_SYSTEM_MENU_CREATE')")
    @PostMapping("/menu/create")
    public Response create(@RequestBody MenuSaveParam param) {
        return menuService.create(param);
    }

    /**
     * 修改
     */
    @PreAuthorize("hasAuthority('PAGER_SYSTEM_MENU_MODIFY')")
    @PutMapping("/menu/modify")
    public Response<Long> modify(@RequestBody MenuSaveParam param) {
        if (Objects.isNull(param.getId())) {
            return new Response<>(ResponseStatus.Code.FAIL_CODE, ResponseStatus.PARAMS_EXCEPTION);
        }
        return menuService.modify(param);
    }

    /**
     * 删除
     */
    @PreAuthorize("hasAuthority('PAGER_SYSTEM_MENU_DELETE')")
    @DeleteMapping("/menu/{id}")
    public Response delete(@PathVariable("id") Long id) {
        return menuService.delete(id);
    }

}
