package fdfs.clients.jfdfs;

import org.apache.commons.pool2.impl.GenericObjectPool;

public class JfdfsPool extends GenericObjectPool<Jfdfs> {

	public JfdfsPool(JfdfsFactory factory, JfdfsPoolConfig config) {
		super(factory, config);
		// TODO Auto-generated constructor stub
		factory.setDataSource(this);
	}
	

}
