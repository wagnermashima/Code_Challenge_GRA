package com.example.grp.codechallenge.importer.domain;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller
public class FileReader {

	@Value("${movie.file.name}")
	private String fileName;
	
	public List<String> readFile() {
		try {
			URL url = getClass().getResource(fileName);
			List<String> lines = Files.lines(Paths.get(url.toURI())).collect(Collectors.toList());
			return lines.subList(1, lines.size());
		} catch (Exception e) {
			throw new RuntimeException(e.toString());
		}
	}
	
}
