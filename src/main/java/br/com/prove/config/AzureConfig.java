package br.com.prove.config;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
@Configuration
public class AzureConfig {
	  @Autowired
	    private Environment environment;

	    @Bean
	    public CloudBlobClient cloudBlobClient() throws URISyntaxException, StorageException, InvalidKeyException {
	        CloudStorageAccount storageAccount = CloudStorageAccount.parse(environment.getProperty("azure.storage.ConnectionString"));
	        return storageAccount.createCloudBlobClient();
	    }

	    @Bean
	    public CloudBlobContainer testBlobContainer() throws URISyntaxException, StorageException, InvalidKeyException {
	        return cloudBlobClient().getContainerReference(environment.getProperty("azure.storage.container.name"));
	    }
}
