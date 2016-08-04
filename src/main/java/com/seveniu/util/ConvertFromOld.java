package com.seveniu.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.fasterxml.jackson.core.type.TypeReference;
import com.seveniu.common.json.Json;
import com.seveniu.def.FieldType;
import com.seveniu.pojo.Template;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by seveniu on 6/4/16.
 * ConvertFromOld
 */
public class ConvertFromOld {
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    public ConvertFromOld(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<Template> convertFromMysql() {
        int limit = 1000;
        List<Template> results = new ArrayList<>();
        int count = 0;
        int lastId = 0;
        String sql = "SELECT id,name,pages,website_id,type FROM template WHERE id > ? limit ?";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, lastId, limit);
        while (list.size() > 0) {
            for (Map<String, Object> map : list) {
                Template template = new Template();
                template.setId((Integer) map.get("id"));
                template.setName((String) map.get("name"));
                template.setWebsiteId((Integer) map.get("website_id"));
                template.setType((Integer) map.get("type"));
                if (template.getType() != 1) {
                    continue;
                }
                String oldPages = (String) map.get("pages");
                if (oldPages != null) {
                    String pages;
                    try {
                        pages = convert(oldPages);
                    } catch (NullPointerException e) {
                        System.out.println("error template : " + template.getId());
                        continue;
                    }
                    template.setPages(pages);
//                    System.out.println(template.getId());
                } else {
                    template.setPages("");
                    continue;
                }
                results.add(template);
            }
            count += list.size();
            lastId = (int) list.get(list.size() - 1).get("id");
            list = jdbcTemplate.queryForList(sql, lastId, limit);
        }
        return results;
    }

    public void insertAllFromOld(DataSource toDataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(toDataSource);
        List<Template> oldList = convertFromMysql();
        for (Template template : oldList) {
            jdbcTemplate.update("INSERT INTO template (id,name,website_id,pages,type) VALUES (?,?,?,?,?)",
                    template.getId(), template.getName(), template.getWebsiteId(), template.getPages(), template.getType());
        }
    }

    public static String convert(String json) throws NullPointerException {
        List<Map<String, Object>> list = Json.toObject(json, new TypeReference<List<Map<String, Object>>>() {
        });
        List<Map<String, Object>> templates = new ArrayList<>(list.size());
        for (Map<String, Object> map : list) {
            Map<String, Object> template = new HashMap<>();
            template.put("name", map.get("name"));
            template.put("url", map.get("url"));
            List<Map<String, Object>> labels = (List<Map<String, Object>>) map.get("labels");

            List<Map<String, Object>> fields = new ArrayList<>(labels.size());
            for (Map<String, Object> label : labels) {
                Map<String, Object> field = new HashMap<>();
                field.put("name", label.get("text"));
                field.put("defaultValue", label.get("value"));
                field.put("must", label.get("must"));
                field.put("xpath", label.get("xpath"));

                // 对于 标题 时间 作者 这三种类型的字段, 对应的 type 设为 纯文本
                int contentType = (int) label.get("id");
                field.put("contentType", contentType);
                int type;
                if (contentType == 1 || contentType == 2 || contentType == 8) {
                    type = FieldType.PURE_TEXT.getValue();
                } else {
                    type = (int) label.get("type");
                }

                field.put("type", type);
                field.put("regex", label.get("regExp"));
                fields.add(field);
            }
            template.put("fields", fields);
            templates.add(template);
        }
        return Json.toJson(templates);
    }

}
