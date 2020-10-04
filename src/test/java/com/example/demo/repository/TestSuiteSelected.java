package com.example.demo.repository;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({ TestRepos.class, TestReposWithInitData.class, TestReposWithInitData2.class, TestRepos2.class })
public class TestSuiteSelected {

}
