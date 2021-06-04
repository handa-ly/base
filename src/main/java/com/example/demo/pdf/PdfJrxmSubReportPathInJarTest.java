package com.example.demo.pdf;

import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

/**
 * @Author: hanDa
 * @Date: 2021/5/12 11:46
 * @Version:1.0
 * @Description:
 */
public class PdfJrxmSubReportPathInJarTest {
    /**
     * 使用JavaBean填充打印PDF到Html中
     * @param request
     * @param response
     * @param jasperPath 例如：classpath:jasper/report2.jasper
     * @param list JavaBean数据
     * @param parameters
     * @throws Exception
     */
    public static void printPdf2HtmlByJavaBean(HttpServletRequest request, HttpServletResponse response, String jasperPath, Map<String, Object> parameters, List<?> list) throws Exception{
        ServletOutputStream outputStream = response.getOutputStream();


        //获取报表的编译文件，后面要将对其进行填充数据
        InputStream inputStream = null;
        try {// 如果不在jar包内，就能找到文件
            String path = URLDecoder.decode(ResourceUtils.getURL(jasperPath).getPath(),"UTF-8");
            File file = new File(path);
            inputStream = new FileInputStream(file);
            parameters.put("SUBREPORT_DIR", file.getPath().replace(file.getName(), ""));
        }catch(Exception e) {// 否则需要去jar包外找
            String dirPath = getCurrentDirPath()+ File.separator;
            String path = dirPath +jasperPath.replace("classpath:", "");
            try {
                File file = new File(path);
                inputStream = new FileInputStream(file);
                parameters.put("SUBREPORT_DIR", file.getPath().replace(file.getName(), ""));
            }catch(Exception e1) {
                throw new RuntimeException(path+"不存在", e1);
            }
        }


        //由于没有数据，故这里使用空参数和空数据源，该方法需要抛出JRException异常
        JasperRunManager.runReportToPdfStream(inputStream, outputStream, parameters , new JRBeanCollectionDataSource(list));

        // 输出PDF
        response.setContentType("application/pdf");

        // 关闭资源
        outputStream.flush();
        outputStream.close();
        inputStream.close();

    }
    /**
     * 获取项目所在文件夹的绝对路径
     * @return
     */
    public static String getCurrentDirPath() {
        URL url = FileCopyUtils.class.getProtectionDomain().getCodeSource().getLocation();
        String path = url.getPath();
        if(path.startsWith("file:")) {
            path = path.replace("file:", "");
        }
        if(path.contains(".jar!/")) {
            path = path.substring(0, path.indexOf(".jar!/")+4);
        }

        File file = new File(path);
        path = file.getParentFile().getAbsolutePath();
        return path;
    }
}