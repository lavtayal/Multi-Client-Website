package com.cg.opmtoolapi.serviceimpl;

import java.io.IOException;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.cg.opmtoolapi.domain.FileDB;
import com.cg.opmtoolapi.domain.UserRegistration;
import com.cg.opmtoolapi.exception.UserNotFoundException;
import com.cg.opmtoolapi.repository.FileDBRepository;
import com.cg.opmtoolapi.service.FileStorageService;
import com.cg.opmtoolapi.service.UserRegistrationService;
@Transactional
@Service
public class FileStorageServiceImpl implements FileStorageService {

	@Autowired
	private FileDBRepository fileDBRepository;
	
	@Autowired
	private UserRegistrationService userRegistrationService;

	@Override
	public FileDB store(String userName,MultipartFile file) throws IOException {
		UserRegistration userRegistration = userRegistrationService.getUserByUserName(userName);
		if(userRegistration==null)
			throw new UserNotFoundException("No User Found For this Name");
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
		userRegistration.setFileDB(FileDB);
		userRegistrationService.update(userName,userRegistration);
		return fileDBRepository.save(FileDB);
	}

	@Override
	public FileDB getFile(String id) {
		return fileDBRepository.findById(id).get();
	}

	@Override
	public Stream<FileDB> getAllFiles() {
		return fileDBRepository.findAll().stream();
	}

}
