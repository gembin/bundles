package uk.co.arum.osgi.amf3.flex.remoting.bundle;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import uk.co.arum.osgi.amf3.flex.remoting.OSGiAMFConstants;

public class AMFServicesTracker extends ServiceTracker {

	private final OSGiAMFConfig config;

	public AMFServicesTracker(BundleContext context, OSGiAMFConfig config)
			throws InvalidSyntaxException {
		super(context, context.createFilter("("
				+ OSGiAMFConstants.AMF_SERVICE_NAME + "=*)"), null);
		this.config = config;
	}

	@Override
	public Object addingService(ServiceReference reference) {
		config.addOSGiService(reference);
		return super.addingService(reference);
	}

	@Override
	public void removedService(ServiceReference reference, Object service) {
		config.removeOSGiService(reference);
		super.removedService(reference, service);
	}

}