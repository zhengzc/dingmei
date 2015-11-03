package com.dingmei.common;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateHashModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

/**
 * 统一加载Freemarker静态方法
 */
public class FreemarkerStaticModels extends HashMap<Object, Object> {

    private static Logger logger = LoggerFactory.getLogger(FreemarkerStaticModels.class);

    private static final long serialVersionUID = 23423423423423423L;
    private static FreemarkerStaticModels FREEMARKER_STATIC_MODELS;
    private Properties staticModels;

    private FreemarkerStaticModels() {

    }

    public static FreemarkerStaticModels getInstance() {
        if (FREEMARKER_STATIC_MODELS == null) {
            FREEMARKER_STATIC_MODELS = new FreemarkerStaticModels();
        }
        return FREEMARKER_STATIC_MODELS;
    }

    public Properties getStaticModels() {
        return staticModels;
    }

    public void setStaticModels(Properties staticModels) {
        if (this.staticModels == null && staticModels != null) {
            this.staticModels = staticModels;
            Set<String> keys = this.staticModels.stringPropertyNames();
            for (String key : keys) {
                FREEMARKER_STATIC_MODELS.put(key, useStaticPackage(this.staticModels.getProperty(key)));
            }
        }
    }

    public static TemplateHashModel useStaticPackage(String packageName) {
        try {
            BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
            TemplateHashModel staticModels = wrapper.getStaticModels();
            TemplateHashModel fileStatics = (TemplateHashModel) staticModels.get(packageName);
            return fileStatics;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

}