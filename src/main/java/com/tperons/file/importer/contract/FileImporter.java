package com.tperons.file.importer.contract;

import java.io.InputStream;
import java.util.List;

import com.tperons.data.dto.PersonDTO;

public interface FileImporter {

    List<PersonDTO> importFile(InputStream inputStream) throws Exception;

}
