
import java.io.File;


public class CountFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CountFile test = new CountFile();
		System.out.println("################ STRAT ##################");
		test.subMain();
		
		
	}
	
	public void subMain(){
		String path = "c:\\transdata";
		String targetPath = "D:\\반영";
		int totalCount = 0;
		try{ 
			File fileDir = new File(path);
		    File fileList[] = fileDir.listFiles();
		    int fileCount = 0;
		    File tempFile = null;
		    
		    for(int i=0; i<fileList.length; i++){
		    	tempFile = fileList[i];

		    	if(!tempFile.isDirectory()){
		    		System.out.println("A 파일경로-"+tempFile.getAbsolutePath());
		    		fileCount++;
		    		totalCount++;
		    		
		    	}else{
					int tempCnt = this.caseFolder(fileList[i]);
					totalCount = tempCnt + totalCount;
		    	}
		    }
		    System.out.println("총 파일갯수-"+ totalCount);
			   
		}catch(Exception e){
			e.printStackTrace();
			
		}//catch 끝
	}
	
	public int caseFolder(File pFile){
		int fileCount = 0;
		try{ 
			
			    File fileList[] = pFile.listFiles();
			    File tempFile = null;
			    for(int i=0; i<fileList.length; i++){
			    	int tempCnt = 0;
			    	tempFile = fileList[i];
			    	
			    	if(tempFile.isDirectory()){
			    		//폴더일 경우
			    		tempCnt = this.caseFolder(fileList[i]);
			    		fileCount = tempCnt + fileCount;
			    		
			    	}else{
			    		//파일일 경우
			    		if("Thumbs.db".equals(tempFile.getName() )){
			    			System.out.println("## 파일삭제 - "+ tempFile.getName());
			    			System.out.println("## 파일삭제 - "+ tempFile.getAbsolutePath());
			    			tempFile.delete();
			    		}else{
				    		System.out.println("B 파일경로-"+tempFile.getAbsolutePath());
				    		fileCount++;
			    		}
			    	}
			    	
			    } // End for
			    
			    if(fileList.length >= 1){
				    System.out.println("@@@@ ["+tempFile.getParent()+"] 파일갯수-"+ fileCount);
				    System.out.println("");
			    }else{
			    	System.out.println("@@@@ ["+pFile.getAbsolutePath()+"] 파일갯수-"+ 0);
				    System.out.println("");
			    }
			    
			    
		}catch(Exception e){
			e.printStackTrace();
			
		}//catch 끝
		
		return fileCount;
	}
}
