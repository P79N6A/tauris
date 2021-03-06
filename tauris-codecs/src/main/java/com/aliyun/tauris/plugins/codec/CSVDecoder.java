package com.aliyun.tauris.plugins.codec;

import au.com.bytecode.opencsv.CSVParser;
import com.aliyun.tauris.DecodeException;
import com.aliyun.tauris.TEvent;
import com.aliyun.tauris.annotations.Name;
import com.aliyun.tauris.annotations.Required;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Created by ZhangLei on 16/12/7.
 */
@Name("csv")
public class CSVDecoder extends AbstractDecoder {

    @Required
    String[] fields;

    @Required
    char separator;

    char quotechar = '\0';

    char escape = '\\';

    boolean strict = false;

    private CSVParser parser;

    public void init() {
        parser = new CSVParser(separator, quotechar, escape);
    }

    @Override
    public void decode(String source, TEvent event, @Nullable String target) throws DecodeException {
        Map<String, Object> data = new HashMap<>();
        decode(source, (k, v) -> {
            if (target == null) {
                event.setField(k, v);
            } else {
                data.put(k, v);
            }
        });
        if (target != null) {
            event.set(target, data);
        }
    }

    @Override
    public TEvent decode(String source) throws DecodeException {
        TEvent event = new TEvent(source);
        decode(source, event::set);
        return event;
    }

    private void decode(String source, BiConsumer<String, String> put) throws DecodeException {
        try {
            String[] cols = parser.parseLine(source);
            if (strict && cols.length != fields.length) {
                throw new DecodeException("column's length not match");
            }
            int len = Math.min(fields.length, cols.length);
            for (int i = 0; i < len; i++) {
                put.accept(fields[i], cols[i]);
            }
        } catch (IOException e) {
            throw new DecodeException(e.getMessage());
        }
    }
}
