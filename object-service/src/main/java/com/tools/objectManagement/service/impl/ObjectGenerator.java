package com.tools.objectManagement.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class ObjectGenerator {

	public ObjectGenerator() {
		
		
	}
	public boolean generateObject(String objectId) throws Exception {
		boolean returnValue = false;
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;

		File configFile = new File(this.getClass().getResource("/generatorConfig.xml").getPath());
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config;

		config = cp.parseConfiguration(configFile);
		List<TableConfiguration> tables = config.getContext("mysqlTables").getTableConfigurations();
		tables.get(0).setTableName(objectId);
		tables.get(0).setDomainObjectName(objectId);
		
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator;

		myBatisGenerator = new MyBatisGenerator(config, callback, warnings);

		myBatisGenerator.generate(null);
		returnValue = true;
		
		return returnValue;
	}
	public static void main(String[] args) throws Exception {
		try {
			ObjectGenerator generator = new ObjectGenerator();
			generator.generateObject("Test");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
