package com.wingflare.tool.generator.mbp.mysql;

import com.baomidou.mybatisplus.extension.ddl.SimpleDdl;
import com.wingflare.lib.core.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数据库维护脚本
 */
@Component
public class StructureMaintenance extends SimpleDdl {

    @Value("${migrations_dir:}")
    private String migrationsDir;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static String removeExtension(String filename) {
        int lastDotIndex = filename.lastIndexOf('.');
        if (lastDotIndex == -1 || lastDotIndex == 0 || lastDotIndex == filename.length() - 1) {
            return filename;
        }
        return filename.substring(0, lastDotIndex);
    }

    @Override
    public List<String> getSqlFiles() {
        List<String> sqlFiles = new ArrayList<>();

        if (StringUtil.isNotEmpty(migrationsDir)) {
            File directory = new File(migrationsDir);

            if (directory.isDirectory()) {
                logger.debug("开始扫描数据库迁移文件", migrationsDir);

                File[] filesList = directory.listFiles();
                if (filesList != null) {
                    for (File file : filesList) {
                        if (!file.isDirectory() && file.getName().endsWith(".sql")) {

                            logger.debug("扫描到sql文件", file.getAbsolutePath());

                            if (removeExtension(file.getName()).endsWith(".sp")) {
                                sqlFiles.add(file.getAbsolutePath() + "#$$");
                            } else {
                                sqlFiles.add(file.getAbsolutePath());
                            }
                        }
                    }
                }
            } else {
                logger.debug("数据库迁移目录不存在");
            }
        } else {
            logger.debug("数据库迁移文件目录未设置");
        }

        return sqlFiles;
    }

}
