package com.ssc.test;

import org.junit.Test;
import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class File4Path {

private static String separator = File.separator;

/**
* 构建Path
*/
@Test
public void constructon(){
//1.Paths
Path path = Paths.get("/Users/kingboy/Desktop/");
Path path1 = Paths.get(URI.create("/Users/kingboy/Desktop/"));
//2.FileSystems
Path path2 = FileSystems.getDefault().getPath("/Users/kingboy/Desktop/");
//3.File
Path path3 = new File("/Users/kingboy/Desktop/").toPath();
}

/**
* 创建一个空文件/文件夹
* @throws IOException
*/
@Test
public void create() throws IOException {
//文件夹
Path path = Paths.get("/Users/kingboy/Desktop/hello");
if(!Files.exists(path)){
Files.createDirectory(path);
//创建多个目录
//Files.createDirectories(path);
}

//文件
Path path1 = Paths.get("/Users/kingboy/Desktop/helloFile");
if(Files.exists(path1)){
Files.createFile(path1);
}

}


/**
* 文件属性
*/
@Test
public void getFileProperties() throws IOException {
Path path = Paths.get("/Users/kingboy/Desktop/text.txt");
System.out.println(Files.getLastModifiedTime(path));//最后修改时间
System.out.println(Files.getOwner(path));//拥有者
System.out.println(Files.getPosixFilePermissions(path));//权限
System.out.println(Files.size(path));//文件大小
}

/**
* 读取一个文本文件
*/
@Test
public void readText() throws IOException {
Path path = Paths.get("/Users/kingboy/Desktop/text.txt");
//通过bufferedReader读取
BufferedReader bufferedReader = Files.newBufferedReader(path, StandardCharsets.UTF_8);//文件编码
StringBuilder sb = new StringBuilder();
String tempString = null;
while ((tempString = bufferedReader.readLine())!=null){
sb = sb.append(tempString);
}
System.out.println(sb);
//通过Files方法readAllLines
List<String> strings = Files.readAllLines(path);
strings.forEach(s -> System.out.print(s));

//输出结果
//adsfasdfasdfadsfasdfgsdfsdffsdfsdf
//adsfasdfasdfadsfasdfgsdfsdffsdfsdf
}

/**
* 拿到文件输入流
* @throws IOException
*/
@Test
public void getInputStream() throws IOException {
Path path = Paths.get("/Users/kingboy/Desktop/text.txt");
InputStream inputStream = Files.newInputStream(path);

}

/**
* 文件写操作
*/
@Test
public void writeFile() throws IOException {
Path path = Paths.get("/Users/kingboy/Desktop/writeFile");
BufferedWriter bufferedWriter = Files.newBufferedWriter(path);
String str = "write file test";
bufferedWriter.write(str);
bufferedWriter.flush();
bufferedWriter.close();
}

/**
* 遍历一个文件夹
*/
@Test
public void traverseDirectory() throws IOException {
Path path = Paths.get("/Users/kingboy/Desktop/");
Stream<Path> list = Files.list(path);
list.forEach(p -> {
System.out.println(p.getFileName());
});
}

/**
* 遍历文件树
*/
@Test
public void traverseTree() throws IOException {
Path path = Paths.get("/Users/kingboy/Desktop/");
Stream<Path> walk = Files.walk(path);
walk.forEach(path1 -> {
// System.out.println(path1.getRoot());//根目录
System.out.println(path1.getFileName());//文件名
// System.out.println(path1.getParent());//上级目录
// System.out.println(path1.getFileSystem());//文件系统

});

//还有种方式Files.walkFileTree()
}

/**
* 文件复制
*/

@Test
public void copyFile() throws IOException {
Path path = Paths.get("/Users/kingboy/Desktop/text.txt");
Path path2 = Paths.get("/Users/kingboy/Desktop/hello.txt");
Files.copy(path,path2);
}

/**
* 读取权限见上面示例，设置权限
*/
@Test
public void writePermission() throws IOException {
Path path = Paths.get("/Users/kingboy/Desktop/text.txt");
Set<PosixFilePermission> permissionSet = new HashSet<>();
permissionSet.add(PosixFilePermission.GROUP_WRITE);
permissionSet.add(PosixFilePermission.OWNER_EXECUTE);
Files.setPosixFilePermissions(path,permissionSet);
}


//还有很多其他操作Api,自己查看方法名，很容易就能分辨出功能。


}
