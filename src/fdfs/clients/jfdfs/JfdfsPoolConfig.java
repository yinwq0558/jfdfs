package fdfs.clients.jfdfs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;

public class JfdfsPoolConfig extends GenericObjectPoolConfig {
	private String configFile;
	private InputStream configIs;
	public String getConfigFile() {
		return configFile;
	}
	public void setConfigFile(String configFile) throws FileNotFoundException, IOException, MyException {
		this.configFile = configFile;
		ClientGlobal.init(this.configFile);
	}
	public InputStream getConfigIs() {
		return configIs;
	}
	public void setConfigIs(InputStream configIs) throws IOException, MyException {
		this.configIs = configIs;
		ClientGlobal.init(this.configIs);
	}
	
}
