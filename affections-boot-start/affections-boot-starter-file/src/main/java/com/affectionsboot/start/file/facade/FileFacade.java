package com.affectionsboot.start.file.facade;

import com.aliyun.oss.ClientException;

public interface FileFacade {
    String upload(byte[] content, String originFilename) throws ClientException;
}
