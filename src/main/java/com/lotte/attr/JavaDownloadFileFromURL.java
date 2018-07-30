
package com.lotte.attr;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("unused")
public class JavaDownloadFileFromURL {
	// URL
	public static final String CATALOG_URL = "http://ep.enuri.com/elLotte/Catalog_20180213.json";
	public static final String CATEGORY_URL = "http://ep.enuri.com/elLotte/Category_20180213.json";
	public static final String CATALOG_LOTTE_GOODS_URL = "http://ep.enuri.com/elLotte/Matching_20180213.json";
	public static final String ARTICLE_URL = "http://ep.enuri.com/elLotte/Attribute_20180213.json";
	
	public static final String ATTRIBUTE_URL = "http://ep.enuri.com/elLotte/Attribute_Element_20180213.json";
	public static final String CATALOG_ATTRIBUTE_URL = "http://ep.enuri.com/elLotte/Catalog_Attribute_20180213.json";
	public static final String CATEGORY_ATTRIBUTE_URL = "http://ep.enuri.com/elLotte/Category_Attribute_20180213.json";
	public static final String CATEGORY_POPULAR_CATALOG_URL = "http://ep.enuri.com/elLotte/Top100_20180213.json";
	
	// PATH
	public static final String PATH = "/Temp/test/enuri";
	// 카탈로그
	public static final String CATALOG_FILE_NAME = PATH + "/catalog/2018/05/17/00/catalog_20180517_00.json";
	public static final String CATALOG_FILE_NAME_01 = PATH + "/catalog/2018/05/17/01/catalog_20180517_01.json";
	public static final String CATALOG_FILE_NAME_02 = PATH + "/catalog/2018/05/17/02/catalog_20180517_02.json";
	// 카테고리
	public static final String CATEGORY_FILE_NAME = PATH + "/category/2018/05/17/00/category_20180517_00.json";
	public static final String CATEGORY_FILE_NAME_01 = PATH + "/category/2018/05/17/01/category_20180517_01.json";
	public static final String CATEGORY_FILE_NAME_02 = PATH + "/category/2018/05/17/02/category_20180517_02.json";
	// 에누리 롯데 상품
	public static final String CATALOG_LOTTE_GOODS_FILE_NAME = PATH + "/catalog_lotte_goods/2018/05/17/00/catalog_lotte_goods_20180517_00.json";
	public static final String CATALOG_LOTTE_GOODS_FILE_NAME_01 = PATH + "/catalog_lotte_goods/2018/05/17/01/catalog_lotte_goods_20180517_01.json";
	public static final String CATALOG_LOTTE_GOODS_FILE_NAME_02 = PATH + "/catalog_lotte_goods/2018/05/17/02/catalog_lotte_goods_20180517_02.json";
	// 속성항목
	public static final String ARTICLE_FILE_NAME = PATH + "/article/2018/05/17/00/article_20180517_00.json";
	public static final String ARTICLE_FILE_NAME_01 = PATH + "/article/2018/05/17/01/article_20180517_01.json";
	public static final String ARTICLE_FILE_NAME_02 = PATH + "/article/2018/05/17/02/article_20180517_02.json";
	// 속성값
	public static final String ATTRIBUTE_FILE_NAME = PATH + "/attribute/2018/05/17/00/attribute_20180517_00.json";
	public static final String ATTRIBUTE_FILE_NAME_01 = PATH + "/attribute/2018/05/17/01/attribute_20180517_01.json";
	public static final String ATTRIBUTE_FILE_NAME_02 = PATH + "/attribute/2018/05/17/02/attribute_20180517_02.json";
	// 상품속성
	public static final String CATALOG_ATTRIBUTE_FILE_NAME = PATH + "/catalog_attribute/2018/05/17/00/catalog_attribute_20180517_00.json";
	public static final String CATALOG_ATTRIBUTE_FILE_NAME_01 = PATH + "/catalog_attribute/2018/05/17/01/catalog_attribute_20180517_01.json";
	public static final String CATALOG_ATTRIBUTE_FILE_NAME_02 = PATH + "/catalog_attribute/2018/05/17/02/catalog_attribute_20180517_02.json";
	// 카테고리 속성항목
	public static final String CATEGORY_ATTRIBUTE_FILE_NAME = PATH + "/category_attribute/2018/05/17/00/category_attribute_20180517_00.json";
	public static final String CATEGORY_ATTRIBUTE_FILE_NAME_01 = PATH + "/category_attribute/2018/05/17/01/category_attribute_20180517_01.json";
	public static final String CATEGORY_ATTRIBUTE_FILE_NAME_02 = PATH + "/category_attribute/2018/05/17/02/category_attribute_20180517_02.json";
	// 카테고리 인기상품
	public static final String CATEGORY_POPULAR_CATALOG_FILE_NAME = PATH + "/category_populate_catalog/2018/05/17/00/category_populate_catalog_20180517_00.json";
	
	private static void downloadUsingStream(String urlStr, String file) throws Exception {
		URL url = getUrl(urlStr);
		
		try ( BufferedInputStream bis = new BufferedInputStream(url.openStream());
				FileOutputStream fis = new FileOutputStream(file);
				) {
			byte[] buffer = new byte[1024];
			int count = 0;
			int countSum = 0;
			while ((count = bis.read(buffer, 0, 1024)) != -1) {
				fis.write(buffer, 0, count);
				countSum += count;
				System.out.println(countSum);
			}
		}
	}

	private static URL getUrl(String urlStr) throws MalformedURLException {
		URL url;
		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			
			throw e;
		}
		return url;
	}

	private static void downloadUsingNIO(String urlStr, String file) throws IOException {
		URL url = getUrl(urlStr);
		
		try ( ReadableByteChannel rbc = Channels.newChannel(url.openStream());
				FileOutputStream fos = new FileOutputStream(file);
				) {
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		}
	}

	private static void downloadEnuri(String url, String fileName) {
		System.out.println("#####################################");
		System.out.println("Downloading " + url + " and writing to " + fileName);
		try {
			long start = System.currentTimeMillis();
			downloadUsingNIO(url, fileName);
//			downloadUsingStream(url, fileName);
			long end = System.currentTimeMillis();
			
			System.out.println((end - start) / 1000);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Downloading End");
		}
	}
	
	private static boolean isJSONValid(String jsonString) {
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			String minprice1 = jsonObject.getString("minprice1");
			
			if(minprice1.equals("")) {
				System.out.println(jsonString);
			}
		} catch (JSONException ex) {
			// edited, to include @Arthur's comment
			// e.g. in case JSONArray is valid as well...
			try {
				System.out.println("jsonString:" + jsonString);
				new JSONArray(jsonString);
			} catch (JSONException ex1) {
				return false;
			}
		}
		return true;
	}
	
	private static void validateJsonFile(String fileName) {
		System.out.println("STSTST==========================");
		System.out.println("Validating.... " + fileName);
//		BufferedReader reader = null;
		int errorCount = 0;
		int lineNumber = 0;
		
		File f = new File(fileName);
		
		if(!f.exists() || f.isDirectory()) { 
			System.out.println("File Not exist!!!!\n\n");
			System.out.println("EDEDED==========================");
			return;
		}
		
		try (
			BufferedReader reader = new BufferedReader(new FileReader(f))
		)
		{
			String line = "";

			while ((line = reader.readLine()) != null) {
				lineNumber++;
//				line = line.replaceAll("\"", "\\\\\"");
				if (!isJSONValid(line)) {
					errorCount++;
					System.out.println("lineNumber :" + lineNumber);
					System.out.println("line :" + line);
					System.out.println(line);
				}
			}

			reader.close();
			
			System.out.println("TotalCount:" + lineNumber);
			System.out.println("SuccessCount:" + (lineNumber - errorCount));
			if (errorCount == 0) {
				System.out.println("ErrorCount:" + errorCount);
			} else {
				System.out.println("!!!!!! ErrorCount:" + errorCount);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			System.out.println("=========================");
		}
	}
	
	public static void downloadEnuriFiles() {
		downloadEnuri(CATALOG_URL, CATALOG_FILE_NAME);
		downloadEnuri(CATEGORY_URL, CATEGORY_FILE_NAME);
		downloadEnuri(CATALOG_LOTTE_GOODS_URL, CATALOG_LOTTE_GOODS_FILE_NAME);
		downloadEnuri(ARTICLE_URL, ARTICLE_FILE_NAME);
		downloadEnuri(ATTRIBUTE_URL, ATTRIBUTE_FILE_NAME);
		downloadEnuri(CATALOG_ATTRIBUTE_URL, CATALOG_ATTRIBUTE_FILE_NAME);
		downloadEnuri(CATEGORY_ATTRIBUTE_URL, CATEGORY_ATTRIBUTE_FILE_NAME);
		downloadEnuri(CATEGORY_POPULAR_CATALOG_URL, CATEGORY_POPULAR_CATALOG_FILE_NAME);
	}
	
	public static void validateEnuriJsonFiles() {
//		validateCatalogsFiles();
//		validateCategoryFiles();
//		validateCatalogLotteGoodsFiles();
//		validateArticleFiles();
//		validateAttributeFiles();
//		validateCatalogAttributeFiles();
//		validateCategoryAttributeFiles();
		validateCategoryPopularCatalog();
	}
	
	public static void validateCatalogsFiles() {
		validateJsonFile(CATALOG_FILE_NAME);
		validateJsonFile(CATALOG_FILE_NAME_01);
		validateJsonFile(CATALOG_FILE_NAME_02);
	}

	public static void validateCategoryFiles() {
		validateJsonFile(CATEGORY_FILE_NAME);
		validateJsonFile(CATEGORY_FILE_NAME_01);
		validateJsonFile(CATEGORY_FILE_NAME_02);
	}
	
	private static void validateCatalogLotteGoodsFiles() {
		validateJsonFile(CATALOG_LOTTE_GOODS_FILE_NAME);
		validateJsonFile(CATALOG_LOTTE_GOODS_FILE_NAME_01);
		validateJsonFile(CATALOG_LOTTE_GOODS_FILE_NAME_02);
	}
	
	private static void validateArticleFiles() {
		validateJsonFile(ARTICLE_FILE_NAME);
		validateJsonFile(ARTICLE_FILE_NAME_01);
		validateJsonFile(ARTICLE_FILE_NAME_02);
	}

	private static void validateAttributeFiles() {
		validateJsonFile(ATTRIBUTE_FILE_NAME);
		validateJsonFile(ATTRIBUTE_FILE_NAME_01);
		validateJsonFile(ATTRIBUTE_FILE_NAME_02);
	}

	private static void validateCatalogAttributeFiles() {
		validateJsonFile(CATALOG_ATTRIBUTE_FILE_NAME);
		validateJsonFile(CATALOG_ATTRIBUTE_FILE_NAME_01);
		validateJsonFile(CATALOG_ATTRIBUTE_FILE_NAME_02);
	}

	private static void validateCategoryAttributeFiles() {
		validateJsonFile(CATEGORY_ATTRIBUTE_FILE_NAME);
		validateJsonFile(CATEGORY_ATTRIBUTE_FILE_NAME_01);
		validateJsonFile(CATEGORY_ATTRIBUTE_FILE_NAME_02);
	}

	private static void validateCategoryPopularCatalog() {
		validateJsonFile(CATEGORY_POPULAR_CATALOG_FILE_NAME);
	}
	
	public static void myJsonTest () {
		String line = "{\"ca_code\" : \"02151306\",\"ca_name\" : \"영상,디카@블루레이,DVD,DivX@DivX@HDD 2.5\"\",\"change_type\" : \"N\"}";
		
		String a = "\"asdf\"";
		String b =a.replaceAll("\"", "\\\\\"");
		System.out.println(a);
		System.out.println(b);
		System.out.println((!isJSONValid(line)));
	}
	
	public static void main(String[] args) throws Exception {
//		downloadEnuriFiles ();
		
//		myJsonTest();
		
		validateEnuriJsonFiles ();
		
//		readByteFromCategoryAttributeFiles ();
	}

	private static void readByteFromCategoryAttributeFiles() {
		readByteFromFiles(CATEGORY_ATTRIBUTE_FILE_NAME_01);
	}
	
	private static void readByteFromFiles(String fileName) {
		Path path = Paths.get(fileName);
		try {
			byte[] data = Files.readAllBytes(path);
			System.out.println(byteArrayToHex(data));
			System.out.println(bytesToHex(data));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String byteArrayToHex(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toHexString(0x0100 + (bytes[i] & 0x00FF)).substring(1));
        }
        
        return sb.toString();
	}
	
	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
}
