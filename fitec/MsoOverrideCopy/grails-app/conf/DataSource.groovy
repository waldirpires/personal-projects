dataSource {
	pooled = true
	driverClassName = "oracle.jdbc.OracleDriver"
	dialect = "org.hibernate.dialect.Oracle10gDialect"
	dbCreate = "" // one of 'create', 'create-drop', 'update', 'validate', ''
	url = ""
	username = ""
	password = ""
	properties {
		validationQuery="select 1 from dual"
		testWhileIdle=true
		timeBetweenEvictionRunsMillis=60000
	}
}
hibernate {
	cache.use_second_level_cache = true
	cache.use_query_cache = false
	cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
	//    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
}

// environment specific settings
environments {
	development {
		dataSource {
			pooled = true
			driverClassName = "oracle.jdbc.OracleDriver"
			dialect = "org.hibernate.dialect.Oracle10gDialect"
			dbCreate = "" // one of 'create', 'create-drop', 'update', 'validate', ''
			url = ""
			username = ""
			password = ""
			properties {
				validationQuery="select 1 from dual"
				testWhileIdle=true
				timeBetweenEvictionRunsMillis=60000
			}
		}
	}
	test {
		dataSource {
			pooled = true
			driverClassName = "oracle.jdbc.OracleDriver"
			dialect = "org.hibernate.dialect.Oracle10gDialect"
			dbCreate = "" // one of 'create', 'create-drop', 'update', 'validate', ''
			url = ""
			username = ""
			password = ""
			properties {
				validationQuery="select 1 from dual"
				testWhileIdle=true
				timeBetweenEvictionRunsMillis=60000
			}
		}
	}
	production {
		dataSource {
			pooled = true
			driverClassName = "oracle.jdbc.OracleDriver"
			dialect = "org.hibernate.dialect.Oracle10gDialect"
			dbCreate = "" // one of 'create', 'create-drop', 'update', 'validate', ''
			url = ""
			username = ""
			password = ""
			properties {
				validationQuery="select 1 from dual"
				testWhileIdle=true
				timeBetweenEvictionRunsMillis=60000
			}
		}
	}
}
