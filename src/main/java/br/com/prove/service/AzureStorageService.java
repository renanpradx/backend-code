package br.com.prove.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.BlobProperties;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.microsoft.azure.storage.blob.ListBlobItem;
import com.microsoft.azure.storage.blob.SharedAccessBlobPolicy;


@Service
public class AzureStorageService {
	
	@Autowired
    private CloudBlobClient cloudBlobClient;

    @Autowired
    private CloudBlobContainer cloudBlobContainer;
    
    public URI uploadFile(MultipartFile multipartFile, String fileName){
    	
    	URI uri = null;
    	
    	try {
        	 CloudBlockBlob blob = cloudBlobContainer.getBlockBlobReference(fileName);
             BlobProperties properties = blob.getProperties();
             properties.setContentType(multipartFile.getContentType());
        	 blob.upload(multipartFile.getInputStream(), multipartFile.getSize());
             String sasKey = generateSas(blob,"DownloadPolicy");
             uri = new URI(String.format("%s?%s" ,blob.getUri().toASCIIString(), sasKey));
          
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        
    	return uri;
    }
    
     public List<URI> listFiles(String containerName){
        List<URI> uris = new ArrayList<>();
        try {
            CloudBlobContainer container = cloudBlobClient.getContainerReference(containerName);
            for (ListBlobItem blobItem : container.listBlobs()) {
                uris.add(blobItem.getUri());
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            e.printStackTrace();
        }
        return uris;
    }
    public void deleteFile(String name){
         try {
             CloudBlockBlob blobToBeDeleted = cloudBlobContainer.getBlockBlobReference(name);
             blobToBeDeleted.deleteIfExists();
         } catch (URISyntaxException e) {
             e.printStackTrace();
         } catch (StorageException e) {
             e.printStackTrace();
         }
     }
    
   
	private String generateSas(CloudBlockBlob blob, String policy) {
		SharedAccessBlobPolicy itemPolicy = new SharedAccessBlobPolicy();
		
		  LocalDateTime currentTime = LocalDateTime.now();
		  Instant result = currentTime.minusMinutes(15).atZone(ZoneOffset.UTC).toInstant();
		  Date startTime = Date.from(result);

		  currentTime = LocalDateTime.now();
		  result = currentTime.plusYears(100).atZone(ZoneOffset.UTC).toInstant();
		  Date expirationTime = Date.from(result);
		  
		  itemPolicy.setSharedAccessStartTime(startTime);
		  itemPolicy.setSharedAccessExpiryTime(expirationTime);
    	
		  String sasToken = null;
		try {
			sasToken = blob.generateSharedAccessSignature(itemPolicy, policy);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (StorageException e) {
			e.printStackTrace();
		}
    	return sasToken;
    }
}
