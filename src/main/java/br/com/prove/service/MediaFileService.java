package br.com.prove.service;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.prove.api.model.MediaFile;
import br.com.prove.repository.MediaFileRepository;
@Service
public class MediaFileService {
	
	@Autowired
	private AzureStorageService storage;
	
	@Autowired
	private MediaFileRepository mediaFileRepository;
	
	public MediaFile create(MultipartFile file) {
		MediaFile currentFile = new MediaFile();
		String fileName = String.format("%s_%s", UUID.randomUUID().toString(), file.getOriginalFilename());
		URI url = storage.uploadFile(file, fileName);
		currentFile.setName(fileName);
		currentFile.setSize(file.getSize());
		currentFile.setUrl(url);
		return mediaFileRepository.save(currentFile);
	}
	
	public void delete(String fileName) {
		storage.deleteFile(fileName);
	}
	
	public MediaFile update(MultipartFile file, Long fileId) {
		Optional<MediaFile> currentFile = mediaFileRepository.findById(fileId);
		delete(currentFile.get().getName());
		String fileName = String.format("%s_%s", UUID.randomUUID().toString(), file.getOriginalFilename());
		URI url = storage.uploadFile(file, fileName);
		currentFile.get().setName(fileName);
		currentFile.get().setSize(file.getSize());
		currentFile.get().setUrl(url);
		
		return mediaFileRepository.save(currentFile.get());
		
	}

}
