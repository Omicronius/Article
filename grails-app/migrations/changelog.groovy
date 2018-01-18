databaseChangeLog = {

	changeSet(author: "KonstantinKlimov (generated)", id: "1516279504506-1") {
		createTable(tableName: "article") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "content", type: "LONGTEXT") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "title", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(defaultValueNumeric: "0", name: "views", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "KonstantinKlimov (generated)", id: "1516279504506-2") {
		createTable(tableName: "article_tag") {
			column(name: "article_tags_id", type: "BIGINT")

			column(name: "tag_id", type: "BIGINT")
		}
	}

	changeSet(author: "KonstantinKlimov (generated)", id: "1516279504506-3") {
		createTable(tableName: "role") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "authority", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "KonstantinKlimov (generated)", id: "1516279504506-4") {
		createTable(tableName: "tag") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "KonstantinKlimov (generated)", id: "1516279504506-5") {
		createTable(tableName: "user") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "account_expired", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "account_locked", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "email", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "enabled", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "first_name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "last_name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "password", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "password_expired", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "username", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "KonstantinKlimov (generated)", id: "1516279504506-6") {
		createTable(tableName: "user_contributions") {
			column(name: "user_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "article_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "KonstantinKlimov (generated)", id: "1516279504506-7") {
		createTable(tableName: "user_role") {
			column(name: "role_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "KonstantinKlimov (generated)", id: "1516279504506-8") {
		addPrimaryKey(columnNames: "user_id, article_id", tableName: "user_contributions")
	}

	changeSet(author: "KonstantinKlimov (generated)", id: "1516279504506-9") {
		addPrimaryKey(columnNames: "role_id, user_id", tableName: "user_role")
	}

	changeSet(author: "KonstantinKlimov (generated)", id: "1516279504506-16") {
		createIndex(indexName: "UK_irsamgnera6angm0prq1kemt2", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "KonstantinKlimov (generated)", id: "1516279504506-17") {
		createIndex(indexName: "UK_1wdpsed5kna2y38hnbgrnhi5b", tableName: "tag", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "KonstantinKlimov (generated)", id: "1516279504506-18") {
		createIndex(indexName: "UK_sb8bbouer5wak8vyiiy4pf2bx", tableName: "user", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "KonstantinKlimov (generated)", id: "1516279504506-10") {
		addForeignKeyConstraint(baseColumnNames: "article_tags_id", baseTableName: "article_tag", baseTableSchemaName: "article", constraintName: "FK_4o1x1bus87nayvsarmvs9rshh", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "article", referencedTableSchemaName: "article", referencesUniqueColumn: "false")
	}

	changeSet(author: "KonstantinKlimov (generated)", id: "1516279504506-11") {
		addForeignKeyConstraint(baseColumnNames: "tag_id", baseTableName: "article_tag", baseTableSchemaName: "article", constraintName: "FK_pkndl0ud6fkak73gdkls858a5", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "tag", referencedTableSchemaName: "article", referencesUniqueColumn: "false")
	}

	changeSet(author: "KonstantinKlimov (generated)", id: "1516279504506-12") {
		addForeignKeyConstraint(baseColumnNames: "article_id", baseTableName: "user_contributions", baseTableSchemaName: "article", constraintName: "FK_hkgjymhgyws9w8aixax5fevqj", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "article", referencedTableSchemaName: "article", referencesUniqueColumn: "false")
	}

	changeSet(author: "KonstantinKlimov (generated)", id: "1516279504506-13") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_contributions", baseTableSchemaName: "article", constraintName: "FK_kwkajcps6k8srajjn9520bu8m", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "user", referencedTableSchemaName: "article", referencesUniqueColumn: "false")
	}

	changeSet(author: "KonstantinKlimov (generated)", id: "1516279504506-14") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", baseTableSchemaName: "article", constraintName: "FK_it77eq964jhfqtu54081ebtio", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "role", referencedTableSchemaName: "article", referencesUniqueColumn: "false")
	}

	changeSet(author: "KonstantinKlimov (generated)", id: "1516279504506-15") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", baseTableSchemaName: "article", constraintName: "FK_apcc8lxk2xnug8377fatvbn04", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "user", referencedTableSchemaName: "article", referencesUniqueColumn: "false")
	}
}
