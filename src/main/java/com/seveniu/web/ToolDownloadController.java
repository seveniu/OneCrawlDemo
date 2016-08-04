package com.seveniu.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping(value = "/download")
public class ToolDownloadController {
    @RequestMapping("/onePage")
    public void getFile(HttpServletResponse response) throws IOException {
        download("/FireManualDefine-20150324.zip", "FireManualDefine-0324.zip", response);
    }

    @RequestMapping("/define")
    public void getDefine(HttpServletResponse response) throws IOException {
        download("/FireDefine-20150324.zip", "FireDefine-0324.zip", response);
    }

    @RequestMapping("/jsSwitch")
    public void getJavascriptSwitch(HttpServletResponse response) throws IOException {
        download("/javascript_switch.zip", "javascript_switch.zip", response);
    }

    @RequestMapping("/xpath")
    public void getXpath(HttpServletResponse response) throws IOException {
        download("/xpath.zip", "xpath.zip", response);
    }

    @RequestMapping("/periodical")
    public void getPeriodical(HttpServletResponse response) throws IOException {
        download("/periodical-20150831.zip", "periodical-20150831.zip", response);
    }

    private static final String PERIODICAL_SINGLE = "periodical-single-20150918.zip";

    @RequestMapping("/periodical-single")
    public void getPeriodicalSingle(HttpServletResponse response) throws IOException {
        download("/" + PERIODICAL_SINGLE, PERIODICAL_SINGLE, response);
    }


    private byte[] getByte(String path) {
        // 以流的形式下载文件
        BufferedInputStream inputStream = new BufferedInputStream(
                getClass().getResourceAsStream(path));
        try {
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            return buffer;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void download(String path, String name, HttpServletResponse response) throws IOException {
        // 以流的形式下载文件
        byte[] buffer = getByte(path);
        // 清空response
        response.reset();
        // 设置response的Header
        response.addHeader("Content-Disposition",
                "attachment;filename=" + name);
//				response.addHeader("Content-Length", "" + file.length());
        OutputStream outputStream = new BufferedOutputStream(response
                .getOutputStream());
        response.setContentType("application/octet-stream");
        outputStream.write(buffer);
        outputStream.flush();
        outputStream.close();
    }

}
