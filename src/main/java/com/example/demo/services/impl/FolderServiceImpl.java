package com.example.demo.services.impl;

import com.example.demo.entities.Folders;
import com.example.demo.repositories.FolderRepository;
import com.example.demo.services.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderServiceImpl implements FolderService {

    @Autowired
    private FolderRepository folderRepository;

    @Override
    public Folders getFolder(Long id) {
        return folderRepository.getOne(id);
    }

    @Override
    public List<Folders> getAllFolders() {
        return folderRepository.findAll();
    }

    @Override
    public Folders addFolder(Folders folder) {
        return folderRepository.save(folder);
    }

    @Override
    public Folders saveFolder(Folders folder) {
        return folderRepository.save(folder);
    }

    @Override
    public void deleteFolder(Long id) {
        folderRepository.deleteById(id);
    }
}
