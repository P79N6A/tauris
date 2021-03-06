package com.aliyun.tauris.config.parser;

import com.aliyun.tauris.TPlugin;
import com.aliyun.tauris.TPluginResolver;
import com.aliyun.tauris.utils.TProperty;

import java.lang.reflect.Array;
import java.util.List;

/**
 * servlets => {
 *     metrics {
 *         path => '/metrics';
 *         acl  => '*';
 *     }
 *  }
 * Created by ZhangLei on 16/12/13.
 */
class PluginsValue extends Value {

    private final List<Plugin> plugins;

    public PluginsValue(List<Plugin> plugins) {
        this.plugins = plugins;
    }

    @Override
    void _assignTo(TProperty property) throws Exception {
        //此属性是一个TPlugin数组
        Helper.m.expand("{").next();
        Object array = Array.newInstance(property.getType(), plugins.size());
        int    i     = 0;
        for (Plugin e : plugins) {
            TPlugin plugin = TPluginResolver.defaultResolver.resolvePlugin((Class<? extends TPlugin>)property.getType(), e.getName());
            plugin = e.build(plugin);
            Array.set(array, i, plugin);
            i++;
        }
        property.set(array);
        Helper.m.collapse("}").next();
    }

    @Override
    public String toString() {
        return "";
    }
}
