
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class TemplateRun {
	private static String url = "jdbc:mysql://175.24.45.45:3306/quartz?user=root&password=handa2695170923&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";

	private static Map<String, List<String>> map;

	static {
		map = new HashMap<>();

		List<String> refList;
//		refList = Arrays.asList("match_wrk_detail_info_o", "match_wrk_main_info_o","match_wrk_main_history_o");
//		map.put("match", refList);

//		refList = Arrays.asList("cwr_artist","cwr_publisher","cwr_publisher_territory","cwr_work",
//				"cwr_work_title","cwr_writer","cwr_writer_publisher","cwr_writer_territory");
//		map.put("cwr", refList);

		refList = Arrays.asList("test");
		map.put("test", refList);

	}

	public static void main(String[] args) {
		map.forEach((k, v) -> v.forEach(table -> {
			try {
				setModel(k, table);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}));

	}

	private static void setModel(String packagePath, String tableName) throws Exception {
		// 写入模板数据
		Map<String, Object> map = new HashMap<>();
		map.put("columnList", getColumnList(tableName));
		String className = StringUtils.capitalize(replaceUnderLineAndUpperCase(tableName));
		// 类名
		map.put("className", className);
		// 变量名字首字母小写
		map.put("objectName", replaceUnderLineAndUpperCase(tableName));
		map.put("tableName", tableName);
		map.put("packagePath", packagePath);

		Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);

		String modelPath = new File("").getCanonicalPath();
//		String path = new File(modelPath).getParent() + File.separator;
		String path = modelPath + File.separator;
		configuration.setDirectoryForTemplateLoading(new File(path + "/src/test/java/ftl"));

		{
			Template template = configuration.getTemplate("entity.ftl");
			String fileName = className + ".java";
      new File(path.replace("\\", "/") + "src/main/java/com/example/demo/pojo/" + packagePath)
          .mkdirs();
			Writer writer = new FileWriter(new File(path.replace("\\", "/") + "src/main/java/com/example/demo/pojo/" + packagePath + "/" + fileName));
			template.process(map, writer);
			writer.flush();
			writer.close();
		}
		{
			Template template = configuration.getTemplate("mapper.ftl");
			String fileName = className + "Mapper.java";
			new File(path + "src/main/java/com/example/demo/mapper/" + packagePath).mkdirs();
			Writer writer = new FileWriter(
					path + "src/main/java/com/example/demo/mapper/" + packagePath + "/" + fileName);
			template.process(map, writer);
			writer.flush();
			writer.close();
		}
		{
			Template template = configuration.getTemplate("service.ftl");
			String fileName = className + "Service.java";
			new File(path + "src/main/java/com/example/demo/service/" + packagePath).mkdirs();
			Writer writer = new FileWriter(
					path + "src/main/java/com/example/demo/service/" + packagePath + "/" + fileName);
			template.process(map, writer);
			writer.flush();
			writer.close();
		}
		{
			Template template = configuration.getTemplate("serviceImpl.ftl");
			String fileName = className + "ServiceImpl.java";
			new File(path + "src/main/java/com/example/demo/service/" + packagePath + "/impl").mkdirs();
			Writer writer = new FileWriter(path + "/src/main/java/com/example/demo/service/" + packagePath
					+ "/impl/" + fileName);
			template.process(map, writer);
			writer.flush();
			writer.close();
		}
		/*
		 * { Template template = configuration.getTemplate("controller.ftl"); String
		 * fileName = className + "Controller.java"; Writer writer = new
		 * FileWriter("src/test/java/file/" + fileName); template.process(map, writer);
		 * writer.flush(); writer.close(); }
		 */
		{
			Template template = configuration.getTemplate("xml.ftl");
			String fileName = className + "Mapper.xml";
			new File(path + "src/main/resources/mapper/" + packagePath).mkdirs();
			Writer writer = new FileWriter(
					path + "src/main/resources/mapper/" + packagePath + "/" + fileName);
			template.process(map, writer);
			writer.flush();
			writer.close();
		}
	}

	private static List<Map<String, String>> getColumnList(String tableName) {
		List<Map<String, String>> columnList = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url);
			String sql = "SELECT column_name, data_type, column_comment, CONCAT(CONCAT('\\#{',column_name), '\\}') as column_value FROM Information_schema.columns WHERE table_schema = 'quartz' and table_Name =? ";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, tableName);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String columnName = rs.getString(1).toLowerCase();
				String dataType = rs.getString(2);
				String columnComment = rs.getString(3);
				String columnValue = rs.getString(4);
				Map<String, String> map = new HashMap<>();
				map.put("columnName", columnName);
				map.put("changeColumnName", replaceUnderLineAndUpperCase(columnName));
				map.put("CapitalColumnName", StringUtils.capitalize(replaceUnderLineAndUpperCase(columnName)));
				map.put("dataType", dataType);

				String type = "String";
				if ("bigint".equals(dataType)) {
					type = "Long";
				} else if ("int".equals(dataType)) {
					type = "Integer";
				} else if ("tinyint".equals(dataType)) {
					type = "Integer";
				} else if (dataType.contains("date")) {
					type = "Date";
				} else if (dataType.equals("decimal") || dataType.equals("float") || dataType.equals("double")) {
					type = "BigDecimal";
				}

				map.put("type", type);
				map.put("columnComment", columnComment);
				map.put("columnValue", columnValue);
				columnList.add(map);
			}
			preparedStatement.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return columnList;
	}

	private static String replaceUnderLineAndUpperCase(String str) {
		StringBuilder sb = new StringBuilder(str.toLowerCase());
		if (sb.toString().startsWith("is_")) {
			sb.replace(0, 2, "");
		}
		int count = sb.indexOf("_");
		while (count != 0) {
			int num = sb.indexOf("_", count);
			count = num + 1;
			if (num != -1) {
				char ss = sb.charAt(count);
				if (ss >= 'a' && ss <= 'z') {
					char ia = (char) (ss - 32);
					sb.replace(count, count + 1, ia + "");
				}
			}
		}
		return sb.toString().replaceAll("_", "");
	}
}
