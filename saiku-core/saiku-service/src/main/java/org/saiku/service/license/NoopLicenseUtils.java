package org.saiku.service.license;

import bi.meteorite.license.LicenseException;
import bi.meteorite.license.SaikuLicense;
import bi.meteorite.license.SaikuLicense2;
import org.saiku.service.datasource.IDatasourceManager;

import javax.jcr.RepositoryException;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.net.InetAddress;

/**
 * No-op license handler so we don't have to fuss with real ones
 */
public class NoopLicenseUtils implements ILicenseUtils {
    private SaikuLicense2 license;
    private IDatasourceManager repositoryDatasourceManager;
    private String adminuser;

    public NoopLicenseUtils() {

    }

    public void init() {

    }

    public IDatasourceManager getRepositoryDatasourceManager() {
        return repositoryDatasourceManager;
    }

    public void setRepositoryDatasourceManager(IDatasourceManager repositoryDatasourceManager) {
        this.repositoryDatasourceManager = repositoryDatasourceManager;
    }

    public void setLicense(SaikuLicense lic) throws IOException {
        // do nothing
    }

    public void setLicense(String lic) {
        // do nothing
    }

    public Object getLicense() throws IOException, ClassNotFoundException, RepositoryException {
        if (license == null) {
            MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
            long max = memoryBean.getHeapMemoryUsage().getMax();
            long conv = max / 1073741824L + 1;

            // create dummy community edition license for current host
            license = new SaikuLicense2();
            license.setHostname(InetAddress.getLocalHost().getHostName());
            license.setMemoryLimit((int) conv);
            license.setUserLimit(0);
            license.setEmail("devops@claritum.com");
            license.setExpiration(null); // not checked for community edition
            license.setLicenseNumber("0");
            license.setLicenseType("community_edition");
            license.setName("License");
            license.setVersion("3");
        }
        return license;
    }

    public SaikuLicense getLicenseNo64() throws IOException, ClassNotFoundException, RepositoryException {
        return (SaikuLicense) getLicense();
    }

    public void validateLicense() throws LicenseException, RepositoryException, IOException, ClassNotFoundException {
        // do nothing
    }

    public void setAdminuser(String adminuser) {
        this.adminuser = adminuser;
    }

    public String getAdminuser() {
        return adminuser;
    }
}
