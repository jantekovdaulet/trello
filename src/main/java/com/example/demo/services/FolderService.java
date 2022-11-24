package com.example.demo.services;

import com.example.demo.entities.Folders;

import java.util.List;

public interface FolderService {

    Folders getFolder(Long id);
    List<Folders> getAllFolders();
    Folders addFolder(Folders folder);
    Folders saveFolder(Folders folder);
    void deleteFolder(Long id);

}
