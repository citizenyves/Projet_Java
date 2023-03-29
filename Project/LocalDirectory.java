// Local Directory Object

package Project;
import java.util.*;

public class LocalDirectory {
	
	private String DirectoryName ;
	private List<File> FileList = new ArrayList<File>() ;
	
	public LocalDirectory() {
		this.DirectoryName = "Local" ;
		this.FileList = FileList ;
	}	
	
	public String getDirectoryName() {
		return DirectoryName;
	}
	public void setDirectoryName(String directoryName) {
		DirectoryName = directoryName;
	}
	
	public List<File> getFileList() {
		return FileList;
	}

	public void setFileList(List<File> fileList) {
		FileList = fileList;
	}

	public String toString() {
		return "[" + this.DirectoryName + "]: " + this.FileList;
	}
}
