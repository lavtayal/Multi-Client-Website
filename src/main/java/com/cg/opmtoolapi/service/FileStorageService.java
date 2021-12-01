package com.cg.opmtoolapi.service;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

import com.cg.opmtoolapi.domain.FileDB;

public interface FileStorageService {

	public FileDB store(String userName,MultipartFile file) throws IOException;

	public FileDB getFile(String id);

	public Stream<FileDB> getAllFiles();
}
