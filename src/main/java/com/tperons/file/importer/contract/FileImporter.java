package com.tperons.file.importer.contract;

import java.io.InputStream;
import java.util.List;

import com.tperons.dto.PersonDTO;

public interface FileImporter {

    List<PersonDTO> importFile(InputStream inputStream) throws Exception;

}
