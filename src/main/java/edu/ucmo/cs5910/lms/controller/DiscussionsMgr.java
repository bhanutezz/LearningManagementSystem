package edu.ucmo.cs5910.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ucmo.cs5910.lms.boundary.dao.DiscussionsDAO;

/**
 * 
 */
@Service
public class DiscussionsMgr {
    @Autowired
    private DiscussionsDAO discussionsDao;

}
