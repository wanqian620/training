package com.whstone.user.common.initialzer;

import com.whstone.user.service.RoleService;
import com.whstone.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ApplicationArguments;

/**
 * @Author: Mr.Gx
 *  项目启动 初始化操作
 */
@Component
public class StartupInitialzer  implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(StartupInitialzer.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("taskExecutor")
    private TaskExecutor executor;


    @Override
    public void run(ApplicationArguments args){
        //异步执行
        executor.execute(() -> {
            logger.info("--------- 项目启动，异步初始化执行 ---------");
            //用户信息存入缓存
            //userService.addUserALLToRedis();
            //把角色相关信息存入缓存
            //roleService.addRoleALLResourceToRedis();
        });
    }
}
