package fdfs.clients.jfdfs;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;

public class JfdfsFactory extends BasePooledObjectFactory<Jfdfs> {
	private GenericObjectPool<Jfdfs> dataSource = null;

	@Override
	public Jfdfs create() throws Exception {
		// TODO Auto-generated method stub
		return new Jfdfs((JfdfsPool) this.dataSource);
	}

	@Override
	public PooledObject<Jfdfs> wrap(Jfdfs arg0) {
		// TODO Auto-generated method stub
		return new DefaultPooledObject<Jfdfs>(arg0);
	}

	public GenericObjectPool<Jfdfs> getDataSource() {
		return dataSource;
	}

	public void setDataSource(GenericObjectPool<Jfdfs> dataSource) {
		this.dataSource = dataSource;
	}



}
