import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


public class FileCopy {
	
	//원본 경로
	String path 	= "c:\\test";
			
	//복사할 위치
	String targetPath = "d:\\test1";
	int compareDate = 20120914;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileCopy test = new FileCopy();
		System.out.println("################ STRAT ##################");
		System.out.println("################ STRAT ##################");
		System.out.println("################ STRAT ##################");
		System.out.println("################ STRAT ##################");
		
		//String path1 	= "D:\\Project\\hb2efront-3.0\\hb2efront_new\\WEB-INF\\classes\\com\\hmall";
		test.subMain();
		//test.subMain(path1);
		
		//반영하지 않을 소스는 삭제
		List pathes = new ArrayList();
		//pathes.add("\\hb2efront_new\\jsp\\mall\\css");
		//pathes.add("\\hb2efront_new\\jsp\\mall\\js");
		pathes.add("\\data\\1");
		test.deleteFile(pathes);

		System.out.println("################ END ##################");
		System.out.println("################ END ##################");
		System.out.println("################ END ##################");
		System.out.println("################ END ##################");
	}
	
	/**
	 * @param path
	 */
	public void subMain(){
		File targetfolder = new File(targetPath);
		if(!targetfolder.exists()){
			targetfolder.mkdir();
		}
		
		try{ 
			File fileDir = new File(path);
		    File fileList[] = fileDir.listFiles();
		    File tempFile = null;
		    
		    for(int i=0; i<fileList.length; i++){
		    	tempFile = fileList[i];

		    	if(!tempFile.isDirectory()){
		    		//파일일경우 
		    		copyFile(tempFile);
		    	}else{
		    		//폴더일경우
		    		//System.out.println("@@ 폴더명 : " + tempFile.getName());
		    		if(tempFile.getName().indexOf(".")<0){
		    			this.caseFolder(fileList[i]);
		    		}
		    	}
		    }
		    //System.out.println("총 파일갯수-"+ totalCount);
			   
		}catch(Exception e){
			e.printStackTrace();
			
		}//catch 끝
	}
	
	/**
	 * 폴더일 경우
	 * @param pFile
	 * @return
	 */
	public void caseFolder(File pFile){
		try{ 
		    File fileList[] = pFile.listFiles();
		    File tempFile = null;
		    for(int i=0; i<fileList.length; i++){
		    	tempFile = fileList[i];
		    	
		    	if(tempFile.isDirectory()){
		    		//폴더일 경우
		    		this.caseFolder(fileList[i]);
		    		
		    	}else{
		    		//파일일 경우
		    		copyFile(tempFile);
		    	}
		    } // End for
		}catch(Exception e){
			e.printStackTrace();
		}//catch 끝
	}
	
	/**
	 * 파일 복사
	 * @param tempFile
	 */
	private void copyFile(File tempFile){
        Calendar cc = new GregorianCalendar();
        cc.setTimeInMillis(tempFile.lastModified());
        
        String yyyy = String.valueOf(cc.get(Calendar.YEAR));
        String mm = String.valueOf(cc.get(Calendar.MONTH)+1);
        if(mm.length()==1) mm = "0" + mm;
        String dd = String.valueOf(cc.get(Calendar.DAY_OF_MONTH));
        if(dd.length()==1) dd = "0" + dd;
        
        int yyyymmdd = Integer.parseInt(yyyy+mm+dd);
        
        //System.out.println("@@ 날짜 비교 = " + yyyymmdd + ":" + compareDate);
        if(yyyymmdd >= compareDate){
			String fileName = tempFile.getName();
			String absFolderPath = tempFile.getAbsolutePath();
			
			if(absFolderPath.indexOf(".svn")<0){
				System.out.println("@@ 파일 수정날짜 = " + yyyy+"/"+mm+"/"+dd);
				System.out.println("@@ 파일 경로(from) = " + tempFile.getAbsolutePath());
				
				FileInputStream inputStream;
				FileOutputStream outputStream;
				FileChannel fcin;
				FileChannel fcout;
				
				try {
					
					inputStream = new FileInputStream(tempFile);
					absFolderPath = absFolderPath.substring(path.length(), absFolderPath.lastIndexOf("\\"));
					String newPath = targetPath + "\\" + absFolderPath;
	
					File targetfolder = new File(newPath);
					if(!targetfolder.exists()){
						targetfolder.mkdirs();
					}
					
					outputStream = new FileOutputStream(newPath + "\\" +fileName);
				
					fcin = inputStream.getChannel();
		    		fcout = outputStream.getChannel();
		    		
		    		long size = fcin.size();
		    		
		    		fcin.transferTo(0, size, fcout);
		    		
		    		fcout.close();
		    		fcin.close();
		    		outputStream.close();
		    		inputStream.close();
		    		
		    		System.out.println("@@ 복사 완료 (to) = " + newPath + "\\" +fileName + "\n");
		    		
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				//System.out.println(".svn 폴더는 생성하지 않습니다.\n");
				
			}
        }
	}
	
	/**
	 * 파일 삭제 메인
	 * @param pathes
	 */
	private void deleteFile(List pathes){
		for(int i=0; i<pathes.size(); i++){
			System.out.println("@@ 삭제 경로 = " + targetPath + pathes.get(i));
			File file = new File(targetPath + pathes.get(i));
			deleteFolder(file);
			System.out.println();
		}
	}
	
	/**
	 * 파일 삭제
	 * @param file
	 */
	public void deleteFolder(File file) {
		if (!file.exists()) return; 
		File[] files = file.listFiles();
		
		for (int i = 0; i < files.length; i++) {
			if(files[i].isDirectory()) { 
				deleteFolder(files[i]);
				
			}else{ 
			   System.out.println(files[i].getAbsolutePath());
			   files[i].delete(); 
			} 
		}
		
		file.delete(); 
	}
	
}
