import java.io.BufferedReader;
import java.io.FileReader;
public class ReadFile {
	private String fileName;
	public ReadFile(){
		
	}
	public ReadFile(String file){
		setFileName(file);
	}
	public void setFileName(String name){
		fileName = name;
	}
	public String getFile(){
		return fileName;
	}
	public String getContents(){
		String foo = "";
		String line = null;
		try{
			FileReader fileReader =
					new FileReader(fileName);
			BufferedReader bufferedReader = 
					new BufferedReader(fileReader);
			
			while((line = bufferedReader.readLine()) != null) {
                foo += line;
            }
			return foo;
		}
		catch(Exception e){
			return "File error";
		}
	}
}
