package fdfs.clients.test;

import java.io.InputStream;

import fdfs.clients.jfdfs.Jfdfs;
import fdfs.clients.jfdfs.JfdfsFactory;
import fdfs.clients.jfdfs.JfdfsPool;
import fdfs.clients.jfdfs.JfdfsPoolConfig;

public class Jfdfs_Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String file = "E:/WORK/Java/workspace2/jfdfs/src/fdfs/clients/test/Jfdfs_Test.java";
		String CONF_FILE = "/client.conf";
		//控制一个pool最多有多少个状态为idle(空闲的)的jfdfs实例，默认值也是8。
		final int MAX_IDLE = 200;		 
		//等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
		final int MAX_WAIT = 10000;		 	 
		//在borrow一个jfdfs实例时，是否提前进行validate操作；如果为true，则得到的jfdfs实例均是可用的；
		final boolean TEST_ON_BORROW = true;
		 
		//返回读取指定资源的输入流  
		InputStream is=Jfdfs_Test.class.getResourceAsStream(CONF_FILE); 
				
		JfdfsFactory factory = new JfdfsFactory();
		JfdfsPoolConfig config = new JfdfsPoolConfig();
		config.setConfigIs(is);
		config.setMaxIdle(MAX_IDLE);
        config.setMaxWaitMillis(MAX_WAIT);
        config.setTestOnBorrow(TEST_ON_BORROW);
		JfdfsPool pool = new JfdfsPool(factory, config);
		Jfdfs jfdfs = pool.borrowObject();
		String[] res = jfdfs.upload_file(file);
		System.out.println(res[0]);
		System.out.println(res[1]);
		
		pool.close();
	}

}
