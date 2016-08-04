package commands

import com.seveniu.pojo.Template
import com.seveniu.template.PagesTemplate
import com.seveniu.common.json.Json
import groovy.sql.Sql
import org.codehaus.groovy.runtime.StackTraceUtils

/**
 * User: seveniu
 * Date: 5/26/15
 * Time: 5:32 PM
 * Project: spring-boot-sample-actuator-ui
 *
 */
import org.crsh.cli.Usage
import org.crsh.cli.Command
import org.crsh.command.InvocationContext


class convertFromOld {

    def path = "/data/temp/"

    @Usage("Say Hello")
    @Command
    def main(InvocationContext context) {
        def startTime = System.currentTimeMillis()
        def oldDb = [url : "jdbc:mysql://10.211.55.3/dhlz?useUnicode=true&characterEncoding=UTF-8",
                  user: "xxx", password: "password", driver: 'com.mysql.jdbc.Driver']
        def newDb = [url : "jdbc:mysql://10.211.55.3/dhlz_template?useUnicode=true&characterEncoding=UTF-8",
                     user: "xxx", password: "password", driver: 'com.mysql.jdbc.Driver']
//        def db = [url : "jdbc:mysql://localhost:3306/dhlz-tag?useUnicode=true&characterEncoding=UTF-8",
//                  user: "dhlz", password: "dhlz", driver: 'com.mysql.jdbc.Driver']
        def oldSql = Sql.newInstance(oldDb.url, oldDb.user, oldDb.password, oldDb.driver);
        def newSql = Sql.newInstance(newDb.url, newDb.user, newDb.password, newDb.driver);
        println "DB connection ready"

        oldSql.eachRow("select * from template",{
            row->
                Template template = new Template()
                template.id = row.id
                template.name = row.name
                template.websiteId = row.website_id
                if (row.pages == null) {
                    println("null : " + row.id)
                } else {
                    try {
                        PagesTemplate pagesTemplate = PagesTemplate.fromOldJson(row.id,row.pages)

                        if (pagesTemplate != null) {

                            pagesTemplate.templates.each {
                                it ->
                                    it.fields.each({
                                        field ->
                                            if(field.id == 1 || field.id == 2 || field.id == 8) {
                                                field.type = com.seveniu.def.FieldType.PURE_TEXT.value
                                            }
                                    })
                            }
                            template.pages = Json.toJson(pagesTemplate.templates)
                            template.type = 1;
                            template.status = 1;

                            newSql.executeInsert("INSERT INTO template (id,name,type,status,website_id,pages) VALUES (?,?,?,?,?,?)",[template.id,template.name,template.type,template.status,template.websiteId,template.pages])
                        }

                    } catch (Exception e) {
                        StackTraceUtils.printSanitizedStackTrace(e)
                    }
                }
        })
//        findNullAndInsert(sql)
//        findDiffId(sql)
//        temp(oldSql)

        def endTime = System.currentTimeMillis()
        return (endTime - startTime) / 1000
    }

    def findNullAndInsert(Sql sql) {
        println "find null and insert"
        def rows = sql.rows("select * from n_tag")

        def nullList = []
        rows.forEach({
            row ->
                def same = sql.firstRow("select * FROM temp where name = ${row.name}")
                if (same == null) {
                    nullList.add(row)
                    def sqlStr = "insert into temp (name, parent_id,children_count,status,content_count,type,employee_id,weight,descr,synonym)" +
                            " values (?,?,?,?,?,?,?,?,?,?)"
                    sql.executeInsert(sqlStr, row.name, row.parent_id, row.children_count, row.status, row.content_count, row.type, row.employee_id, row.weight, row.descr, row.synonym)
                }
        })
        writeToFile(path, "nullList", "txt", nullList)
    }
    def findDiffId(Sql sql) {
        println "find diff"
        def rows = sql.rows("select * from n_tag")
        def changeList = []
        rows.forEach({
            row ->
                def same = sql.firstRow("select * FROM tag where name = ${row.name}")
                if (same != null) {
                    if (same.id != row.id) {
                        row.put("changeId", same.id)
                        changeList.add(row)
                    }
                }

        })
//        writeToFile(path, "changeList", "txt", changeList)
        updateWorkMapTagWriteToFile(path,"updateWorkMapTag","sql",changeList)

    }
    public void writeToFile(def directory, def fileName, def extension, def infoList) {
        new File("$directory/$fileName.$extension").withWriter { out ->
            infoList.each {
                out.println it
            }
        }
    }

    public void updateWorkMapTagWriteToFile(def directory, def fileName, def extension, def infoList) {
//        new File("$directory/$fileName.$extension").withWriter { out ->
            infoList.each {
                if(it.changeId > 73206) {
                    sql.rows("SELECT count(*) as count where ").forEach({
                        row ->
                            if(row.c > 0) {
                                println(row.c)
                            }
                    })
                }
//                out.println "update work_map_tag set tag_id = ${it.id} where tag_id = ${it.changeId};";
            }
//        }
    }

}
