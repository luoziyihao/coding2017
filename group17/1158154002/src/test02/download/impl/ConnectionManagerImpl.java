package test02.download.impl;

import test02.download.api.Connection;
import test02.download.api.ConnectionException;
import test02.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		
		return new ConnectionImpl(url);
	}

}
