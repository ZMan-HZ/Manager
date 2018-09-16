package com.ssc.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * 
 * @author e633229
 * Funchtion:
 * This class is aim to practice the Path/Paths and Files Class's Usage.
 *
 *
 */
public class TestFile {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		String srcPathData = "C:/cc_view/125.dat";
		String destPathData = "C:/cc_view/122.dat";
//		String fileTree = "C:/e633229_NACC1/Git_NACC1";
		fileCopy(srcPathData, destPathData);
	
  /*	this is a list file test
      * Path fileP = Paths.get(fileTree);
		try(Stream<Path> list = Files.list(fileP);){
			list.forEach(lis->{
				if(!lis.toFile().isDirectory())
					System.out.print("");
				else
					System.out.println(lis);
			});
		}catch(Exception e){
			e.printStackTrace();
		}
 */
     /*  this is file write test and file directory walk test
		Path fileP = Paths.get(fileTree);
		Path path = Paths.get(fileTree+"/123.txt");
		try(Stream<Path> list = Files.walk(fileP);
			BufferedWriter bufferedWriter = Files.newBufferedWriter(path);)
		{
			list.forEach(lis->{
				if(!lis.toFile().isDirectory())
					System.out.print("");
				else
					System.out.println(lis);
				    try {
						bufferedWriter.write(lis.toString()+"\n");
						bufferedWriter.flush();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
     */
		
		
//		Files.newOutputStream(Paths.get(destPathData));
		
		
	}
	/**
	 * @param srcPathData
	 * @param destPathData
	 * @throws IOException
	 */
	public static void fileCopy(String srcPathData, String destPathData)throws IOException {
		File fileSettings= null;
		List<Path> eachSourceFilePath = getEachFilePath(srcPathData);
		List<Path> eachDescFilePath = getEachFilePath(destPathData);
		for(int i = 0 ; i< eachSourceFilePath.size();i++){
			fileSettings = new File(eachDescFilePath.get(i).toString());
			if(fileSettings.exists()){
				fileSettings.setWritable(true);
				fileSettings.delete();
			}
			Files.copy(eachSourceFilePath.get(i),eachDescFilePath.get(i));
		}
	}
	/**
	 * @param srcPathData
	 * @return
	 * @throws IOException
	 */
	private static List<Path> getEachFilePath(String srcPathData) throws IOException {
		Path path = Paths.get(srcPathData);
//		Path path2 = Paths.get(URI.create(fromPath));
//		Path path2 = FileSystems.getDefault().getPath(fromPath);
//		Path path2 = new File(fromPath).toPath();
//		BufferedReader br = Files.newBufferedReader(path,StandardCharsets.UTF_8);
		List<String> ls = Files.readAllLines(path);
		List<Path> eachFilePath = new ArrayList<Path>();
		/*this is read each file's content, for example: will read and print out dod_conf.xml
		 * ls.forEach(s->
		{   s.toString();
		    Path pathFromList = Paths.get(s);
		    List<String> sourceFilePath = null;
			try {
				 sourceFilePath = Files.readAllLines(pathFromList);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    sourceFilePath.forEach(eachFilePath -> 
		    System.out.println(eachFilePath));});
		    */
//		ls.forEach(s->System.out.println(s));
		ls.forEach(s-> eachFilePath.add(Paths.get(s)));
		return eachFilePath;
	}

	
	
	
	
	
	
	
	
	
	
	

}
