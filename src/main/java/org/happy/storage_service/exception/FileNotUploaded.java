package org.happy.storage_service.exception;

public class FileNotUploaded extends RuntimeException {
    public FileNotUploaded(String message) {
        super(message);
    }
}
