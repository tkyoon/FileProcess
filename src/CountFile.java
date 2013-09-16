
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
		String targetPath = "D:\\�ݿ�";
		int totalCount = 0;
		try{ 
			File fileDir = new File(path);
		    File fileList[] = fileDir.listFiles();
		    int fileCount = 0;
		    File tempFile = null;
		    
		    for(int i=0; i<fileList.length; i++){
		    	tempFile = fileList[i];

		    	if(!tempFile.isDirectory()){
		    		System.out.println("A ���ϰ��-"+tempFile.getAbsolutePath());
		    		fileCount++;
		    		totalCount++;
		    		
		    	}else{
					int tempCnt = this.caseFolder(fileList[i]);
					totalCount = tempCnt + totalCount;
		    	}
		    }
		    System.out.println("�� ���ϰ���-"+ totalCount);
			   
		}catch(Exception e){
			e.printStackTrace();
			
		}//catch ��
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
			    		//������ ���
			    		tempCnt = this.caseFolder(fileList[i]);
			    		fileCount = tempCnt + fileCount;
			    		
			    	}else{
			    		//������ ���
			    		if("Thumbs.db".equals(tempFile.getName() )){
			    			System.out.println("## ���ϻ��� - "+ tempFile.getName());
			    			System.out.println("## ���ϻ��� - "+ tempFile.getAbsolutePath());
			    			tempFile.delete();
			    		}else{
				    		System.out.println("B ���ϰ��-"+tempFile.getAbsolutePath());
				    		fileCount++;
			    		}
			    	}
			    	
			    } // End for
			    
			    if(fileList.length >= 1){
				    System.out.println("@@@@ ["+tempFile.getParent()+"] ���ϰ���-"+ fileCount);
				    System.out.println("");
			    }else{
			    	System.out.println("@@@@ ["+pFile.getAbsolutePath()+"] ���ϰ���-"+ 0);
				    System.out.println("");
			    }
			    
			    
		}catch(Exception e){
			e.printStackTrace();
			
		}//catch ��
		
		return fileCount;
	}
}
