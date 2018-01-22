databaseChangeLog = {

	changeSet(author: "KonstantinKlimov", id: "2018-01-22-create-article-table") {
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

	changeSet(author: "KonstantinKlimov", id: "2018-01-22-create-user-table") {
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
		createIndex(indexName: "UK_user_username", tableName: "user", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "KonstantinKlimov", id: "2018-01-22-create-role-table") {
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
		createIndex(indexName: "UK_role_authority", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "KonstantinKlimov", id: "2018-01-22-create-tag-table") {
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
		createIndex(indexName: "UK_tag_name", tableName: "tag", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "KonstantinKlimov", id: "2018-01-22-create-article_tag-table") {
		createTable(tableName: "article_tag") {
			column(name: "article_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "tag_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
		addPrimaryKey(columnNames: "article_id, tag_id", tableName: "article_tag")
	}

	changeSet(author: "KonstantinKlimov", id: "2018-01-22-create-user_article-table") {
		createTable(tableName: "user_article") {
			column(name: "user_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "article_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
		addPrimaryKey(columnNames: "user_id, article_id", tableName: "user_article")
	}

	changeSet(author: "KonstantinKlimov", id: "2018-01-22-create-user_role-table") {
		createTable(tableName: "user_role") {
			column(name: "role_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
		addPrimaryKey(columnNames: "role_id, user_id", tableName: "user_role")
	}

	changeSet(author: "KonstantinKlimov", id: "2018-01-22-add-fk-article_tag-article_id") {
		addForeignKeyConstraint(baseColumnNames: "article_id", baseTableName: "article_tag", constraintName: "FK_article_tag-article_id", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "article", referencesUniqueColumn: "false")
	}

	changeSet(author: "KonstantinKlimov", id: "2018-01-22-add-fk-article_tag-tag_id") {
		addForeignKeyConstraint(baseColumnNames: "tag_id", baseTableName: "article_tag", constraintName: "FK_article_tag-tag_id", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "tag", referencesUniqueColumn: "false")
	}

	changeSet(author: "KonstantinKlimov", id: "2018-01-22-add-fk-user_article-article_id") {
		addForeignKeyConstraint(baseColumnNames: "article_id", baseTableName: "user_article", constraintName: "FK_user_article-article_id", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "article", referencesUniqueColumn: "false")
	}

	changeSet(author: "KonstantinKlimov", id: "2018-01-22-add-fk-user_article-user_id") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_article", constraintName: "FK_user_article-user_id", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "KonstantinKlimov", id: "2018-01-22-add-fk-user_role-role_id") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK_user_role-role_id", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
	}

	changeSet(author: "KonstantinKlimov", id: "2018-01-22-add-fk-user_role-user_id") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK_user_role-user_id", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}
}
